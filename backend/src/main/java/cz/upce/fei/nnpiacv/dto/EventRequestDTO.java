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
public class EventRequestDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy")
    private Year season;
}
