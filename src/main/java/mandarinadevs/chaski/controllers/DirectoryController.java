package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.models.Directory;
import mandarinadevs.chaski.entities.models.Message;
import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.services.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping(value = "/api/directory", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DirectoryController {
    @Autowired
    private DirectoryService directoryService;

    @GetMapping(value = "")
    public Flux<Directory> getAll() {
        return directoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Directory> getById(@PathVariable String id) {
        return directoryService.getById(id);
    }

    @PostMapping(value = "")
    public Mono<Directory> save(@RequestBody Directory directory) {
        return directoryService.save(directory);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        directoryService.delete(id);
    }

    @GetMapping(value = "/contacts/{owner}")
    public Flux<Person> findByOwner(@PathVariable String owner) {
        return directoryService.findByOwner(owner);
    }

    @GetMapping(value = "/contacts/last-message/{owner}")
    public Flux<Tuple2<Person, Message>> findContactsWithLastMessages(@PathVariable String owner) {
        return directoryService.findContactsWithLastMessages(owner);
    }
}
