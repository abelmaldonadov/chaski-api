package mandarinadevs.chaski;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.chaski.controllers.*;
import mandarinadevs.chaski.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class ApplicationTests {
    @Autowired
    DirectoryController directoryController;
    @Autowired
    MessageController messageController;
    @Autowired
    PersonController personController;
    @Autowired
    RoleByPersonController roleByPersonController;
    @Autowired
    RoleController roleController;
    @Autowired
    ShippingController shippingController;

    @Test
    void contextLoads() {
    }

    @Test
    void directoryTest() {
        Directory testDirectory = new Directory();
        testDirectory.setOwner(1);
        testDirectory.setContact(2);
        testDirectory.setInsertionDate(LocalDateTime.now());

        directoryController.save(testDirectory);
        directoryController.getById(testDirectory.getId());
        directoryController.delete(testDirectory.getId());
    }

    @Test
    void messageTest() {
        Message testMessage = new Message();
        testMessage.setContent("Test Content");
        testMessage.setInsertionDate(LocalDateTime.now());

        messageController.save(testMessage);
        messageController.getById(testMessage.getId());
        messageController.delete(testMessage.getId());
    }

    @Test
    void personTest() {
        Person testPerson = new Person();
        testPerson.setUsername("usertest");
        testPerson.setPassword("12345");
        testPerson.setName("User Test");
        testPerson.setState(1);
        testPerson.setInsertionDate(LocalDateTime.now());

        personController.save(testPerson);
        personController.getById(testPerson.getId());
        personController.delete(testPerson.getId());
    }

    @Test
    void roleByPersonTest() {
        RoleByPerson testRoleByPerson = new RoleByPerson();
        testRoleByPerson.setPerson(1);
        testRoleByPerson.setRole(1);

        roleByPersonController.save(testRoleByPerson);
        roleByPersonController.getById(testRoleByPerson.getId());
        roleByPersonController.delete(testRoleByPerson.getId());
    }

    @Test
    void roleTest() {
        Role testRole = new Role();
        testRole.setName("Test Role");
        testRole.setDescription("Test Role Description");
        testRole.setState(1);
        testRole.setInsertionDate(LocalDateTime.now());

        roleController.save(testRole);
        roleController.getById(testRole.getId());
        roleController.delete(testRole.getId());
    }

    @Test
    void shippingTest() {
        Shipping testShipping = new Shipping();
        testShipping.setSender(1);
        testShipping.setMessage(1);
        testShipping.setReceptor(2);
        testShipping.setInsertionDate(LocalDateTime.now());

        shippingController.save(testShipping);
        shippingController.getById(testShipping.getId());
        shippingController.delete(testShipping.getId());
    }

}
