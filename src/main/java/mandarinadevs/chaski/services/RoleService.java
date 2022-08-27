package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.Role;
import mandarinadevs.chaski.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public List<Role> getAll() {
        return (List<Role>) roleRepo.findAll();
    }
    public Role getById(Integer id) {
        Optional<Role> optional = roleRepo.findById(id);
        return optional.orElse(null);
    }
    public Role save(Role role) {
        return roleRepo.save(role);
    }
    public void delete(Integer id) {
        roleRepo.deleteById(id);
    }
}
