package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "")
    public Flux<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Person> getById(@PathVariable String id) {
        return personService.getById(id);
    }

    @PostMapping(value = "")
    public Mono<Person> save(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }

    @PostMapping(value = "/sign-in")
    public Mono<Person> signIn(@RequestBody List<String> credentials) {
        return personService.signIn(credentials.get(0), credentials.get(1));
    }

    @PostMapping(value = "/save-token")
    public ResponseEntity<?> saveExpoNotificationToken(Person person) {
        try {
            personService.saveExpoNotificationToken(person);
            return ResponseEntity.ok().build();
        } catch (Exception err) {
            return ResponseEntity.badRequest().build();
        }
    }
}
