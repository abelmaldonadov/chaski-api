package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public Flux<Person> getAll() {
        return personRepo.findAll();
    }

    public Mono<Person> getById(String id) {
        return personRepo.findById(id);
    }

    public Mono<Person> save(Person person) {
        return personRepo.save(person);
    }

    public void delete(String id) {
        personRepo.deleteById(id);
    }

    public Mono<Person> signIn(String username, String password) {
        return personRepo
            .findByUsernameAndPassword(username, password)
            .log();
    }

    public void saveExpoNotificationToken(Person person) {
        personRepo.save(person).subscribe();
    }
}
