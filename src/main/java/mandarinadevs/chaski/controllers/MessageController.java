package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.Message;
import mandarinadevs.chaski.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/messages")
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping(value = "")
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Message getById(@PathVariable Integer id) {
        return messageService.getById(id);
    }

    @PostMapping(value = "")
    public Message save(@RequestBody Message message) {
        return messageService.save(message);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        messageService.delete(id);
    }

    @PostMapping(value = "/conversation")
    public List<Message> getConversation(@RequestBody List<Integer> messageIds) {
        return messageService.getConversation(messageIds);
    }

    @GetMapping(value = "/also")
    public Stream<Message> also() {
        List<Message> list = messageService.getAll();
        return Stream
                .of(new Message(1,"asd", LocalDateTime.now()));
    }
}
