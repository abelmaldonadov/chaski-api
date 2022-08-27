package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.Directory;
import mandarinadevs.chaski.repositories.DirectoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryService {
    @Autowired
    DirectoryRepo directoryRepo;

    public List<Directory> getAll() {
        return (List<Directory>) directoryRepo.findAll();
    }
    public Directory getById(Integer id) {
        Optional<Directory> optional = directoryRepo.findById(id);
        return optional.orElse(null);
    }
    public Directory save(Directory directory) {
        return directoryRepo.save(directory);
    }
    public void delete(Integer id) {
        directoryRepo.deleteById(id);
    }
}
