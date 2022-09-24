package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.models.Role;
import mandarinadevs.chaski.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public Flux<Role> getAll() {
        return roleRepo.findAll();
    }

    public Mono<Role> getById(String id) {
        return roleRepo.findById(id);
    }
    public Mono<Role> save(Role role) {
        return roleRepo.save(role);
    }

    public void delete(String id) {
        roleRepo.deleteById(id);
    }
}
