package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.Shipping;
import mandarinadevs.chaski.repositories.ShippingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShippingService {
    @Autowired
    ShippingRepo shippingRepo;

    public List<Shipping> getAll() {
        return (List<Shipping>) shippingRepo.findAll();
    }
    public Shipping getById(Integer id) {
        Optional<Shipping> optional = shippingRepo.findById(id);
        return optional.orElse(null);
    }
    public Shipping save(Shipping shipping) {
        return shippingRepo.save(shipping);
    }
    public void delete(Integer id) {
        shippingRepo.deleteById(id);
    }
    public List<Shipping> getConversation(Integer sender, Integer receptor) {
        List<Shipping> list = (List<Shipping>) shippingRepo.findAll();
        Stream<Shipping> sent = list.stream()
            .filter(p -> p.getSender().equals(sender))
            .filter(p -> p.getReceptor().equals(receptor));
        Stream<Shipping> received = list.stream()
                .filter(p -> p.getSender().equals(receptor))
                .filter(p -> p.getReceptor().equals(sender));
        return Stream.concat(sent, received)
                .sorted(Comparator.comparing(Shipping::getInsertionDate).reversed())
                .collect(Collectors.toList());
    }
}
