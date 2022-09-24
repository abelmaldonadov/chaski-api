package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.models.Directory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DirectoryRepo extends ReactiveMongoRepository<Directory, String> {
    Flux<Directory> findByOwner(String owner);
}
