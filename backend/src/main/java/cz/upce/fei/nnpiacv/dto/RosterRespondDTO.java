package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Event;
import cz.upce.fei.nnpiacv.domain.Racer;
import cz.upce.fei.nnpiacv.id.RosterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RosterRespondDTO {
    @JsonIgnoreProperties("rosters")
    private EventRespondDTO event;

    @JsonIgnoreProperties("rosters")
    private RacerRespondDTO racer;
}
