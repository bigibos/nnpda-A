package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByIdAndUser(Long id, User user);
    List<Project> findByUser(User user);
}
