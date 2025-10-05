package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Car;
import cz.upce.fei.nnpiacv.domain.Roster;
import cz.upce.fei.nnpiacv.domain.Team;
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
public class RacerRequestDTO {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @Min(value = 1)
    @Max(value = 999)
    private Integer number;
}
