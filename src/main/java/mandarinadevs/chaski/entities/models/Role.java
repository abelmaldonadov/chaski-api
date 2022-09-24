package mandarinadevs.chaski.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime insertionDate;
}
