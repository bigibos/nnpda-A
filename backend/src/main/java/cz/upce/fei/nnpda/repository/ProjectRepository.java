package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByIdAndUserUsername(Long id, String username);
    List<Project> findByUserUsername(String username);
}
