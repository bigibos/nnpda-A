package cz.upce.fei.nnpda.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.dto.Project.ProjectRespondDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespondDTO {
    private String username;
    private String email;

    @JsonIgnoreProperties("user")
    private List<ProjectRespondDTO> projects;
}
