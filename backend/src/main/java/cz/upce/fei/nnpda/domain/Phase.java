package cz.upce.fei.nnpda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@Table(
        name = "phase"
)
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name="track_id", referencedColumnName="id")
    private Track track;

    @ManyToOne
    @JoinColumn(name="event_id", referencedColumnName="id")
    private Event event;

    public Phase() {}
}
