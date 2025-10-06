package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.ProjectAddDTO;
import cz.upce.fei.nnpda.dto.ProjectUpdateDTO;
import cz.upce.fei.nnpda.repository.ProjectRepository;
import cz.upce.fei.nnpda.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

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
        User user = userRepository.findByUsername(username);

        Project project = projectRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        modelMapper.map(projectDTO, project);
        project.setStatus(projectDTO.getStatus());

        return projectRepository.save(project);
    }

    public Project deleteProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project project = projectRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        projectRepository.deleteById(project.getId());

        return project;
    }

    public Collection<Project> findProjects() {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return projectRepository.findByUserUsername(username);
    }

    public Project findProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Project project = projectRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        return project;
    }
}
