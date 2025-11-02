package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Comment;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.dto.Comment.CommentRequestDTO;
import cz.upce.fei.nnpda.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {
    private final ModelMapper modelMapper;
    private final ProjectService projectService;
    private final CommentRepository commentRepository;
    private final TicketService ticketService;


    @Transactional
    public Comment addProjectComment(Long projectId, CommentRequestDTO commentDto) {

        Project project = projectService.findProject(projectId);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setProject(project);
        comment = commentRepository.save(comment);
        project.getComments().add(comment);

        return comment;
    }

    @Transactional
    public Comment addTicketComment(Long ticketId, CommentRequestDTO commentDto) {

        Ticket ticket = ticketService.findTicket(ticketId);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setTicket(ticket);
        comment = commentRepository.save(comment);
        ticket.getComments().add(comment);

        return comment;
    }



    @Transactional
    public Comment updateComment(Long id, CommentRequestDTO commentDto) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);

        modelMapper.map(commentDto, comment);
        comment = commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public void deleteComment(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Comment comment = commentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
        commentRepository.delete(comment);
    }

    public Collection<Comment> findComments() {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        return commentRepository.findByUserUsername(username);
    }

    public Comment findComment(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return commentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
    }

}
