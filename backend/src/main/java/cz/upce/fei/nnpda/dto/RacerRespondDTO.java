package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RacerRespondDTO {
    private long id;
    private String firstName;
    private String lastName;
    private Integer number;

    @JsonIgnoreProperties("members")
    private TeamRespondDTO team;

    @JsonIgnoreProperties("racers")
    private CarRespondDTO car;

    @JsonIgnoreProperties("racer")
    private List<RosterRespondDTO> rosters;
}
