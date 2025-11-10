package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.Attachment.AttachmentRequestDTO;
import cz.upce.fei.nnpda.dto.Attachment.AttachmentRespondDTO;
import cz.upce.fei.nnpda.service.AttachmentService;
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
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("attachments/projects/{id}")
    public AttachmentRespondDTO addProjectAttachment(@PathVariable Long id, @Valid @RequestBody AttachmentRequestDTO attachment) {
        return modelMapper.map(attachmentService.addProjectAttachment(id, attachment), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("attachments/ticket/{id}")
    public AttachmentRespondDTO addTicketAttachment(@PathVariable Long id, @Valid @RequestBody AttachmentRequestDTO attachment) {
        return modelMapper.map(attachmentService.addTicketAttachment(id, attachment), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/attachments/{id}")
    public AttachmentRespondDTO updateAttachment(@PathVariable Long id, @Valid @RequestBody AttachmentRequestDTO attachment) {
        return modelMapper.map(attachmentService.updateAttachment(id, attachment), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/attachments/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);

        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/attachments")
    public Collection<AttachmentRespondDTO> getAttachments() {
        return attachmentService.findAttachments().stream()
                .map(attachment -> modelMapper.map(attachment, AttachmentRespondDTO.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/attachments/{id}")
    public AttachmentRespondDTO findAttachment(@PathVariable Long id) {
        return modelMapper.map(attachmentService.findAttachment(id), AttachmentRespondDTO.class);
    }
}
