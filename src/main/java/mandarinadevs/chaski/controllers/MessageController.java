package mandarinadevs.chaski.controllers;

import io.github.jav.exposerversdk.PushClientException;
import mandarinadevs.chaski.entities.models.Message;
import mandarinadevs.chaski.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping(value = "")
    public Flux<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Message> getById(@PathVariable String id) {
        return messageService.getById(id);
    }

    @PostMapping(value = "")
    public Mono<Message> save(@RequestBody Message message) {
        return messageService.save(message);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        messageService.delete(id);
    }

    @PostMapping(value = "/conversation/{from}/{to}")
    public Flux<Message> getConversation(
            @RequestBody List<String> participants,
            @PathVariable Integer from,
            @PathVariable Integer to
    ) {
        return messageService.getConversation(participants, from, to);
    }

    @PostMapping(value = "/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        try {
            return ResponseEntity.ok().body(messageService.sendMessage(message));
        } catch (PushClientException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
