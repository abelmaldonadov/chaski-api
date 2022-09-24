package mandarinadevs.chaski.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "directory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Directory {
    @Id
    private String id;
    private String owner;
    private String contact;
    private LocalDateTime insertionDate;
}
