package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.RoleByPerson;
import mandarinadevs.chaski.services.RoleByPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rolesbyperson")
@CrossOrigin
public class RoleByPersonController {
    @Autowired
    RoleByPersonService roleByPersonService;

    @GetMapping(value = "")
    public List<RoleByPerson> getAll() {
        return roleByPersonService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RoleByPerson getById(@PathVariable Integer id) {
        return roleByPersonService.getById(id);
    }

    @PostMapping(value = "")
    public RoleByPerson save(@RequestBody RoleByPerson roleByPerson) {
        return roleByPersonService.save(roleByPerson);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        roleByPersonService.delete(id);
    }
}
