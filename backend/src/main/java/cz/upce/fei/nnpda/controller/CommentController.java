package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.Comment.CommentRequestDTO;
import cz.upce.fei.nnpda.dto.Comment.CommentRespondDTO;
import cz.upce.fei.nnpda.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("comments/projects/{id}")
    public CommentRespondDTO addProjectComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO comment) {
        return modelMapper.map(commentService.addProjectComment(id, comment), CommentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("comments/ticket/{id}")
    public CommentRespondDTO addTicketComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO comment) {
        return modelMapper.map(commentService.addTicketComment(id, comment), CommentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/comments/{id}")
    public CommentRespondDTO updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO comment) {
        return modelMapper.map(commentService.updateComment(id, comment), CommentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments")
    public Collection<CommentRespondDTO> getComments() {
        return commentService.findComments().stream()
                .map(comment -> modelMapper.map(comment, CommentRespondDTO.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{id}")
    public CommentRespondDTO findComment(@PathVariable Long id) {
        return modelMapper.map(commentService.findComment(id), CommentRespondDTO.class);
    }
}