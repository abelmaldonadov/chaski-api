package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.models.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepo extends ReactiveMongoRepository<Message, String> {
    Flux<Message> findBySenderAndReceptor(String sender, String receptor);
}
