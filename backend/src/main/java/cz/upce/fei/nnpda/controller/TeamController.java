package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.TeamRequestDTO;
import cz.upce.fei.nnpda.dto.TeamRespondDTO;
import cz.upce.fei.nnpda.service.TeamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/teams/{id}")
    public TeamRespondDTO updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequestDTO team) {
        return teamService.updateTeam(id, team);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/teams")
    public TeamRespondDTO addTeam(@Valid @RequestBody TeamRequestDTO team) {
        return teamService.addTeam(team);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/teams/{id}")
    public TeamRespondDTO deleteTeam(@PathVariable Long id) {
        return teamService.deleteTeam(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams/{id}")
    public TeamRespondDTO findTeam(@PathVariable Long id) {
        return teamService.findTeam(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams")
    public Collection<TeamRespondDTO> getTeams() {
        return teamService.findTeams();
    }

}
