package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.id.RosterId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(
        name = "event_roster"
)
public class Roster
{
    @EmbeddedId
    private RosterId id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("rosters")
    private Event event;

    @ManyToOne
    @MapsId("racerId")
    @JoinColumn(name = "racer_id")
    @JsonIgnoreProperties("rosters")
    private Racer racer;

    public Roster() {}
}
