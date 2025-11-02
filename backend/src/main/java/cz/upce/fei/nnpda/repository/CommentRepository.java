package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Comment;
import cz.upce.fei.nnpda.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUserUsername(Long id, String username);
    List<Comment> findByUserUsername(String username);
}
