package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.models.Role;
import mandarinadevs.chaski.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "")
    public Flux<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Role> getById(@PathVariable String id) {
        return roleService.getById(id);
    }

    @PostMapping(value = "")
    public Mono<Role> save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        roleService.delete(id);
    }
}
