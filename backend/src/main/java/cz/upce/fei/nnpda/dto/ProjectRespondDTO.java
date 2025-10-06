package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRespondDTO {
    private String name;
    private String description;
    private Project.Status status;

    @JsonIgnoreProperties("projects")
    private UserRespondDTO user;

    @JsonIgnoreProperties("project")
    private List<TicketRespondDTO> tickets;
}
