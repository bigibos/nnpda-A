package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.ProjectAddDTO;
import cz.upce.fei.nnpda.dto.ProjectUpdateDTO;
import cz.upce.fei.nnpda.dto.ProjectRespondDTO;
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

    public ProjectRespondDTO addProject(ProjectAddDTO project) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project newProject = modelMapper.map(project, Project.class);
        newProject.setUser(user);
        newProject.setStatus(Project.Status.ACTIVE);

        newProject = projectRepository.save(newProject);

        return modelMapper.map(newProject, ProjectRespondDTO.class);
    }

    public ProjectRespondDTO updateProject(Long id, ProjectUpdateDTO projectDTO) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project existingProject = projectRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        modelMapper.map(projectDTO, existingProject);
        existingProject.setStatus(projectDTO.getStatus());

        Project updatedProject = projectRepository.save(existingProject);

        return modelMapper.map(updatedProject, ProjectRespondDTO.class);
    }

    public ProjectRespondDTO deleteProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project existingProject = projectRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        projectRepository.deleteById(existingProject.getId());

        return modelMapper.map(existingProject, ProjectRespondDTO.class);
    }

    public Collection<ProjectRespondDTO> findProjects() {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        return projectRepository.findByUser(user).stream()
                .map(project -> modelMapper.map(project, ProjectRespondDTO.class))
                .collect(Collectors.toList());
    }

    public ProjectRespondDTO findProject(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Project existingProject = projectRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AccessDeniedException("Project is property of other user"));

        return modelMapper.map(existingProject, ProjectRespondDTO.class);
    }
}
