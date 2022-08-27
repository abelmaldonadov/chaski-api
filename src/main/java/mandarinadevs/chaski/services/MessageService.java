package mandarinadevs.chaski.services;

import mandarinadevs.chaski.entities.Message;
import mandarinadevs.chaski.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;

    public List<Message> getAll() {
        return (List<Message>) messageRepo.findAll();
    }

    public Message getById(Integer id) {
        Optional<Message> optional = messageRepo.findById(id);
        return optional.orElse(null);
    }

    public Message save(Message message) {
        return messageRepo.save(message);
    }

    public void delete(Integer id) {
        messageRepo.deleteById(id);
    }

    public List<Message> getConversation(List<Integer> messageIds) {
        return (List<Message>) messageRepo.findAllById(messageIds);
    }
}
