package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRespondDTO {

    private long id;
    private String name;
    private Year season;

    @JsonIgnoreProperties("event")
    private List<PhaseRespondDTO> phases;

    @JsonIgnoreProperties("event")
    private List<RosterRespondDTO> rosters;
}
