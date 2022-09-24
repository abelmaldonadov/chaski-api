package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.models.Directory;
import mandarinadevs.chaski.entities.models.Message;
import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.repositories.DirectoryRepo;
import mandarinadevs.chaski.repositories.MessageRepo;
import mandarinadevs.chaski.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class DirectoryService<T> {
    @Autowired
    private DirectoryRepo directoryRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private PersonRepo personRepo;

    public Flux<Directory> getAll() {
        return directoryRepo.findAll();
    }

    public Mono<Directory> getById(String id) {
        return directoryRepo.findById(id);
    }

    public Mono<Directory> save(Directory directory) {
        return directoryRepo.save(directory);
    }

    public void delete(String id) {
        directoryRepo.deleteById(id);
    }

    public Flux<Person> findByOwner(String owner) {
        return directoryRepo
            .findByOwner(owner)
            .flatMap(p -> personRepo.findById(p.getContact()));
    }

    public Flux<Tuple2<Person, Message>> findContactsWithLastMessages(String owner) {
        return directoryRepo.findByOwner(owner)
            .flatMap(p -> personRepo.findById(p.getContact()))
            .flatMap(p -> messageRepo
                .findBySenderAndReceptor(p.getId(), owner)
                .mergeWith(messageRepo.findBySenderAndReceptor(owner, p.getId()))
                .sort(Comparator.comparing(Message::getInsertionDate).reversed())
                .take(1)
                .map(m -> Tuples.of(p, m))
            )
            .log();
    }
}
