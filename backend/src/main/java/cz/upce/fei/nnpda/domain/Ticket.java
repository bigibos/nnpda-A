package cz.upce.fei.nnpda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    @JsonIgnoreProperties("tickets")
    private User user;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TicketLog> logs = new ArrayList<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    public Ticket() {}
}
