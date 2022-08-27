package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.Directory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepo extends CrudRepository<Directory, Integer> {
}
