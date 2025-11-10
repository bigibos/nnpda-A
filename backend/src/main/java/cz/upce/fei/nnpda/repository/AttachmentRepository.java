package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Attachment;
import cz.upce.fei.nnpda.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findByIdAndUserUsername(Long id, String username);
    List<Attachment> findByUserUsername(String username);
}
