package cz.upce.fei.nnpda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Attachable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    @JsonIgnoreProperties("comments")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @PrePersist
    @PreUpdate
    private void validateAttachment() {
        if ((project == null && ticket == null) || (project != null && ticket != null)) {
            throw new IllegalStateException(
                    this.getClass().getSimpleName() + " can be assigned either to project or ticket (not both)"
            );
        }
    }
}
