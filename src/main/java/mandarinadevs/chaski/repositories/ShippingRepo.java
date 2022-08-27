package mandarinadevs.chaski.repositories;

import mandarinadevs.chaski.entities.Shipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepo extends CrudRepository<Shipping, Integer> {
}
