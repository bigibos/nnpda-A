package cz.upce.fei.nnpda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
