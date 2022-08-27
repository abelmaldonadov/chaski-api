package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.Directory;
import mandarinadevs.chaski.services.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/directory")
@CrossOrigin
public class DirectoryController {
    @Autowired
    DirectoryService directoryService;

    @GetMapping(value = "")
    public List<Directory> getAll() {
        return directoryService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Directory getById(@PathVariable Integer id) {
        return directoryService.getById(id);
    }

    @PostMapping(value = "")
    public Directory save(@RequestBody Directory directory) {
        return directoryService.save(directory);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        directoryService.delete(id);
    }
}
