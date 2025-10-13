package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.ProjectAddDTO;
import cz.upce.fei.nnpda.dto.ProjectUpdateDTO;
import cz.upce.fei.nnpda.repository.ProjectRepository;
import cz.upce.fei.nnpda.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project addProject(ProjectAddDTO projectDto) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project project = modelMapper.map(projectDto, Project.class);
        project.setUser(user);
        project.setStatus(Project.Status.ACTIVE);

        project = projectRepository.save(project);

        return project;
    }

    public Project updateProject(Long id, ProjectUpdateDTO projectDTO) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Project project = projectRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!project.getUser().getUsername().equals(username))
            throw new AccessDeniedException("You do not have permission to update this project");

        modelMapper.map(projectDTO, project);
        project.setStatus(projectDTO.getStatus());

        return projectRepository.save(project);
    }

    public Project deleteProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Project project = projectRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!project.getUser().getUsername().equals(username))
            throw new AccessDeniedException("You do not have permission to delete this project");

        projectRepository.deleteById(project.getId());

        return project;
    }

    public Collection<Project> findProjects() {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return projectRepository.findByUserUsername(username);
    }

    public Project findProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Project project = projectRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!project.getUser().getUsername().equals(username))
            throw new AccessDeniedException("You do not have permission to view this project");

        return project;
    }
}
