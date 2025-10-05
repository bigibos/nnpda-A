package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Car;
import cz.upce.fei.nnpiacv.domain.Roster;
import cz.upce.fei.nnpiacv.domain.Team;
import cz.upce.fei.nnpiacv.repository.RosterRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
