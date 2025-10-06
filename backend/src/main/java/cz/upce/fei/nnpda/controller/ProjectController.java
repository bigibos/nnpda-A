package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.ProjectUpdateDTO;
import cz.upce.fei.nnpda.dto.ProjectRespondDTO;
import cz.upce.fei.nnpda.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects")
    public ProjectRespondDTO addProject(@Valid @RequestBody ProjectUpdateDTO project) {
        return projectService.addProject(project);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/projects/{id}")
    public ProjectRespondDTO updateProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO project) {
        return projectService.updateProject(id, project);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/projects/{id}")
    public ProjectRespondDTO deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects")
    public Collection<ProjectRespondDTO> getProjects() {
        return projectService.findProjects();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects/{id}")
    public ProjectRespondDTO findProject(@PathVariable Long id) {
        return projectService.findProject(id);
    }
}
