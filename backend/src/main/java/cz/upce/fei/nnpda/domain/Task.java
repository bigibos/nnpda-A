package cz.upce.fei.nnpda.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Task {
    @Id
    private long id;
    private String title;
    private String description;


    public Task() {
    }
}
