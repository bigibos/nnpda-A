package cz.upce.fei.nnpda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="ticket")
public class Ticket {

    public enum Status {
        OPEN,
        IN_PROGRESS,
        DONE
    }

    public enum Type {
        BUG,
        FEATURE,
        TASK
    }

    public enum  Priority {
        LOW,
        MEDIUM,
        HIGH
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Status status;
    private Type type;
    private Priority priority;

    @ManyToOne
    @JoinColumn(name="project_id", referencedColumnName="id")
    @JsonIgnoreProperties("tickets")
    private Project project;

    public Ticket() {}
}
