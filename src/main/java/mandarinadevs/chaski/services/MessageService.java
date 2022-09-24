package mandarinadevs.chaski.services;

import com.pusher.rest.Pusher;
import io.github.jav.exposerversdk.*;
import lombok.extern.slf4j.Slf4j;
import mandarinadevs.chaski.entities.models.Message;
import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.repositories.MessageRepo;
import mandarinadevs.chaski.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {
    @Value("${spring.pusher.app-id}")
    private String appId;
    @Value("${spring.pusher.key}")
    private String key;
    @Value("${spring.pusher.secret}")
    private String secret;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private PersonRepo personRepo;

    public Flux<Message> getAll() {
        return messageRepo.findAll();
    }

    public Mono<Message> getById(String id) {
        return messageRepo.findById(id);
    }

    public Mono<Message> save(Message message) {
        return messageRepo.save(message);
    }

    public void delete(String id) {
        messageRepo.deleteById(id);
    }

    public Flux<Message> getConversation(List<String> participants, Integer from, Integer to) {
        String sender = participants.get(0);
        String receptor = participants.get(1);

        return Flux.merge(
            messageRepo.findBySenderAndReceptor(sender, receptor),
            messageRepo.findBySenderAndReceptor(receptor, sender)
        ).sort(Comparator.comparing(Message::getInsertionDate).reversed())
            .skip(from - 1).take(to)
            .log();
    }

    public Message sendMessage(Message message) throws PushClientException {
        // Save
        messageRepo.save(message).log().subscribe();

        // Notify with Pusher
        Pusher pusher = new Pusher(appId, key, secret);
        pusher.setCluster("sa1");
        pusher.setEncrypted(true);
        pusher.trigger(message.getReceptor(), "send-message", message);

        // Notify with Expo Notifications
        personRepo.findById(message.getSender())
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(sender -> {
                personRepo.findById(message.getReceptor()).doOnNext(receptor -> {
                    String title = sender.getName();
                    String body = message.getContent();
                    String recipient = receptor.getExpoToken();
                    try {
                        sendWithExpoNotifications(message, title, body, recipient);
                    } catch (PushClientException e) {
                        log.info(e.getMessage());
                    }
                }).onErrorStop().subscribe();
            }).subscribe();

        // Ok
        return message;
    }

    private void sendWithExpoNotifications(
            Message message,
            String title,
            String body,
            String recipient
    ) throws PushClientException {

        if (!PushClient.isExponentPushToken(recipient))
            throw new Error("Token:" + recipient + " is not a valid token.");

        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("id", message.getId());
        messageMap.put("sender", message.getSender());
        messageMap.put("receptor", message.getReceptor());
        messageMap.put("content", message.getContent());
        messageMap.put("insertionDate", message.getInsertionDate());

        ExpoPushMessage expoPushMessage = new ExpoPushMessage();
        expoPushMessage.getTo().add(recipient);
        expoPushMessage.setTitle(title);
        expoPushMessage.setBody(body);
        expoPushMessage.setData(Map.of("Mensajito", "Entregado"));

        List<ExpoPushMessage> expoPushMessages = new ArrayList<>();
        expoPushMessages.add(expoPushMessage);

        PushClient client = new PushClient();
        List<List<ExpoPushMessage>> chunks = client.chunkPushNotifications(expoPushMessages);

        List<CompletableFuture<List<ExpoPushTicket>>> messageRepliesFutures = new ArrayList<>();

        for (List<ExpoPushMessage> chunk : chunks) {
            messageRepliesFutures.add(client.sendPushNotificationsAsync(chunk));
        }

        // Wait for each completable future to finish
        List<ExpoPushTicket> allTickets = new ArrayList<>();
        for (CompletableFuture<List<ExpoPushTicket>> messageReplyFuture : messageRepliesFutures) {
            try {
                allTickets.addAll(messageReplyFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<ExpoPushMessageTicketPair<ExpoPushMessage>> zippedMessagesTickets = client.zipMessagesTickets(expoPushMessages, allTickets);

        List<ExpoPushMessageTicketPair<ExpoPushMessage>> okTicketMessages = client.filterAllSuccessfulMessages(zippedMessagesTickets);
        String okTicketMessagesString = okTicketMessages.stream().map(
                p -> "Title: " + p.message.getTitle() + ", Id:" + p.ticket.getId()
        ).collect(Collectors.joining(","));
        System.out.println(
                "Recieved OK ticket for " +
                        okTicketMessages.size() +
                        " messages: " + okTicketMessagesString
        );

        List<ExpoPushMessageTicketPair<ExpoPushMessage>> errorTicketMessages = client.filterAllMessagesWithError(zippedMessagesTickets);
        String errorTicketMessagesString = errorTicketMessages.stream().map(
                p -> "Title: " + p.message.getTitle() + ", Error: " + p.ticket.getDetails().getError()
        ).collect(Collectors.joining(","));
        System.out.println(
                "Recieved ERROR ticket for " +
                        errorTicketMessages.size() +
                        " messages: " +
                        errorTicketMessagesString
        );

        List<String> ticketIds = (client.getTicketIdsFromPairs(okTicketMessages));
        CompletableFuture<List<ExpoPushReceipt>> receiptFutures = client.getPushNotificationReceiptsAsync(ticketIds);

        List<ExpoPushReceipt> receipts = new ArrayList<>();
        try {
            receipts = receiptFutures.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Recieved " + receipts.size() + " receipts:");

        for (ExpoPushReceipt reciept : receipts) {
            System.out.println(
                    "Receipt for id: " +
                            reciept.getId() +
                            " had status: " +
                            reciept.getStatus());

        }
    }
}
