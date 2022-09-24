package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.models.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends ReactiveMongoRepository<Role, String> {
}
