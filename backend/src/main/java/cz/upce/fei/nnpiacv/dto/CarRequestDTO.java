package cz.upce.fei.nnpiacv.dto;

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
public class CarRequestDTO {
    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @NotBlank
    private String name;
}
