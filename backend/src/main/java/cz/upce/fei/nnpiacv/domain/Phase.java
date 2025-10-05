package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;

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
