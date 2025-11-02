package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.dto.Comment.CommentRequestDTO;
import cz.upce.fei.nnpda.dto.Comment.CommentRespondDTO;
import cz.upce.fei.nnpda.dto.Project.ProjectAddDTO;
import cz.upce.fei.nnpda.dto.Project.ProjectUpdateDTO;
import cz.upce.fei.nnpda.dto.Project.ProjectRespondDTO;
import cz.upce.fei.nnpda.service.CommentService;
import cz.upce.fei.nnpda.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects")
    public ProjectRespondDTO addProject(@Valid @RequestBody ProjectAddDTO project) {
        return modelMapper.map(projectService.addProject(project), ProjectRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/projects/{id}")
    public ProjectRespondDTO updateProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO project) {
        return modelMapper.map(projectService.updateProject(id, project), ProjectRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/projects/{id}")
    public ProjectRespondDTO deleteProject(@PathVariable Long id) {
        return modelMapper.map(projectService.deleteProject(id), ProjectRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects")
    public Collection<ProjectRespondDTO> getProjects() {
        return projectService.findProjects().stream()
                .map(project -> modelMapper.map(project, ProjectRespondDTO.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects/{id}")
    public ProjectRespondDTO findProject(@PathVariable Long id) {
        return modelMapper.map(projectService.findProject(id), ProjectRespondDTO.class);
    }
}
