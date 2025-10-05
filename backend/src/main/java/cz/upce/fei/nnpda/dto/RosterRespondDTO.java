package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
