package cz.upce.fei.nnpda.dto.Project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.dto.Ticket.TicketRespondDTO;
import cz.upce.fei.nnpda.dto.User.UserRespondDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRespondDTO {
    private Long id;
    private String name;
    private String description;
    private Project.Status status;

    @JsonIgnoreProperties("projects")
    private UserRespondDTO user;

    @JsonIgnoreProperties("project")
    private List<TicketRespondDTO> tickets;
}
