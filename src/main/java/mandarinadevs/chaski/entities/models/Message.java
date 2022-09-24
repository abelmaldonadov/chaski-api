package mandarinadevs.chaski.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Message {
    @Id
    private String id;
    private String sender;
    private String content;
    private String receptor;
    private LocalDateTime insertionDate;
}
