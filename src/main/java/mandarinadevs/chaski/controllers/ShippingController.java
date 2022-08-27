package mandarinadevs.chaski.controllers;

import mandarinadevs.chaski.entities.Shipping;
import mandarinadevs.chaski.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shipping")
@CrossOrigin
public class ShippingController {
    @Autowired
    ShippingService shippingService;

    @GetMapping(value = "")
    public List<Shipping> getAll() {
        return shippingService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Shipping getById(@PathVariable Integer id) {
        return shippingService.getById(id);
    }

    @PostMapping(value = "")
    public Shipping save(@RequestBody Shipping shipping) {
        return shippingService.save(shipping);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        shippingService.delete(id);
    }

    @GetMapping(value = "/conversation/{sender}/{receptor}")
    public List<Shipping> getConversation(@PathVariable Integer sender, @PathVariable Integer receptor) {
        return shippingService.getConversation(sender, receptor);
    }
}
