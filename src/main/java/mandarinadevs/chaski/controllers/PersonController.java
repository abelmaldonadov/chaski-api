package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.Person;
import mandarinadevs.chaski.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/people")
@CrossOrigin
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping(value = "")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Person getById(@PathVariable Integer id) {
        return personService.getById(id);
    }

    @PostMapping(value = "")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        personService.delete(id);
    }

    @PostMapping(value = "/register")
    public Person register(@RequestBody Person person) {
        return personService.register(person);
    }
}
