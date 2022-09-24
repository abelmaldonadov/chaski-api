package mandarinadevs.chaski;

import lombok.extern.slf4j.Slf4j;
import mandarinadevs.chaski.controllers.*;
import mandarinadevs.chaski.entities.models.Directory;
import mandarinadevs.chaski.entities.models.Message;
import mandarinadevs.chaski.entities.models.Person;
import mandarinadevs.chaski.entities.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

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
    RoleController roleController;

    @Test
    void contextLoads() {
    }

    @Test
    void directoryTest() {
        Directory testDirectory = new Directory();
        testDirectory.setOwner("testOwner");
        testDirectory.setContact("testContact");
        testDirectory.setInsertionDate(LocalDateTime.now());

        StepVerifier.create(directoryController.save(testDirectory))
            .expectNext(testDirectory)
            .expectComplete()
            .verify();
    }

    @Test
    void messageTest() {
        Message testMessage = new Message();
        testMessage.setSender("testSender");
        testMessage.setContent("Test Content");
        testMessage.setReceptor("testReceptor");
        testMessage.setInsertionDate(LocalDateTime.now());

        messageController.save(testMessage).subscribe(p -> log.info(p.toString()));
    }

    @Test
    void personTest() {
        Person testPerson = new Person();
        testPerson.setUsername("usertest");
        testPerson.setPassword("12345");
        testPerson.setName("User Test");
        testPerson.setState(true);
        testPerson.setInsertionDate(LocalDateTime.now());

        personController.save(testPerson);
        personController.getById(testPerson.getId());
        personController.delete(testPerson.getId());
    }

    @Test
    void roleTest() {
        Role testRole = new Role();
        testRole.setName("Test Role");
        testRole.setDescription("Test Role Description");
        testRole.setInsertionDate(LocalDateTime.now());

        roleController.save(testRole);
        roleController.getById(testRole.getId());
        roleController.delete(testRole.getId());
    }

}
