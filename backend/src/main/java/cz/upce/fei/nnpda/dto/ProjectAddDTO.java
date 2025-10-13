package cz.upce.fei.nnpda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddDTO {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 120)
    private String name;

    @NotNull
    @NotBlank
    private String description;
}
