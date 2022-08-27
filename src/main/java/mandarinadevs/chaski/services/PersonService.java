package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.Person;
import mandarinadevs.chaski.entities.RoleByPerson;
import mandarinadevs.chaski.repositories.PersonRepo;
import mandarinadevs.chaski.repositories.RoleByPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;
    @Autowired
    RoleByPersonRepo roleByPersonRepo;

    public List<Person> getAll() {
        return (List<Person>) personRepo.findAll();
    }
    public Person getById(Integer id) {
        Optional<Person> optional = personRepo.findById(id);
        return optional.orElse(null);
    }
    public Person save(Person person) {
        return personRepo.save(person);
    }
    public void delete(Integer id) {
        personRepo.deleteById(id);
    }
    public Person register(Person person) {
        Person registered = personRepo.save(person);
        RoleByPerson userRole = new RoleByPerson();
        userRole.setPerson(registered.getId());
        userRole.setRole(2);
        RoleByPerson writerRole = new RoleByPerson();
        writerRole.setPerson(registered.getId());
        writerRole.setRole(3);
        RoleByPerson readerRole = new RoleByPerson();
        readerRole.setPerson(registered.getId());
        readerRole.setRole(4);
        roleByPersonRepo.saveAll(List.of(userRole, writerRole, readerRole));
        return registered;
    }
}
