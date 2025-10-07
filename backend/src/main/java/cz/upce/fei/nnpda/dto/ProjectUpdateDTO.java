package cz.upce.fei.nnpda.dto;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.validator.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @ValidEnum(enumClass = Project.Status.class)
    private String status;

    public Project.Status getStatus() {
        return Project.Status.valueOf(status);
    }
}
