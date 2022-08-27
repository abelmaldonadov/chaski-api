package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.RoleByPerson;
import mandarinadevs.chaski.repositories.RoleByPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleByPersonService {
    @Autowired
    RoleByPersonRepo roleByPersonRepo;

    public List<RoleByPerson> getAll() {
        return (List<RoleByPerson>) roleByPersonRepo.findAll();
    }
    public RoleByPerson getById(Integer id) {
        Optional<RoleByPerson> optional = roleByPersonRepo.findById(id);
        return optional.orElse(null);
    }
    public RoleByPerson save(RoleByPerson roleByPerson) {
        return roleByPersonRepo.save(roleByPerson);
    }
    public void delete(Integer id) {
        roleByPersonRepo.deleteById(id);
    }
}
