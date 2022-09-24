package mandarinadevs.chaski.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String expoToken;
    private Boolean state;
    private LocalDateTime insertionDate;
}
