package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.RoleByPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleByPersonRepo extends CrudRepository<RoleByPerson, Integer> {
}
