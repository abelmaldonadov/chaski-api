package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.Role;
import mandarinadevs.chaski.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping(value = "")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PostMapping(value = "")
    public Role save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        roleService.delete(id);
    }
}
