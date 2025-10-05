package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Racer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDTO {
    @NotNull
    @NotBlank
    private String name;
}
