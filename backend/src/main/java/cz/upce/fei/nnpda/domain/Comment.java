package cz.upce.fei.nnpda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

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
    private void validate() {
        if ((project == null && ticket == null) || (project != null && ticket != null)) {
            throw new IllegalStateException("Komentář musí být přiřazen buď projektu, nebo ticketu, ale ne obojímu.");
        }
    }

    public Comment() {}
}
