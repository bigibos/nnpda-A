package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Phase;
import cz.upce.fei.nnpiacv.domain.Roster;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
