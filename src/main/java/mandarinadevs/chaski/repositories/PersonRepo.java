package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.models.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepo extends ReactiveMongoRepository<Person, String> {
    Mono<Person> findByUsernameAndPassword(String username, String password);
}
