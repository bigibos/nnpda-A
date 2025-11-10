package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.domain.Attachment;
import cz.upce.fei.nnpda.dto.Attachment.AttachmentRequestDTO;
import cz.upce.fei.nnpda.dto.Attachment.AttachmentRespondDTO;
import cz.upce.fei.nnpda.service.AttachmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "attachments/projects/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public AttachmentRespondDTO addProjectAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return modelMapper.map(attachmentService.uploadProjectAttachment(id, file), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "attachments/ticket/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public AttachmentRespondDTO addTicketAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return modelMapper.map(attachmentService.uploadTicketAttachment(id, file), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/attachments/{id}")
    public AttachmentRespondDTO updateAttachment(@PathVariable Long id, @Valid @RequestBody AttachmentRequestDTO attachment) {
        /*
        return modelMapper.map(attachmentService.updateAttachment(id, attachment), AttachmentRespondDTO.class);

         */
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/attachments/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);

        return ResponseEntity.noContent().build();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/attachments/{id}")
    public AttachmentRespondDTO findAttachment(@PathVariable Long id) {
        return modelMapper.map(attachmentService.findAttachment(id), AttachmentRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/attachments/{id}/download")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Long id) {
        return attachmentService.downloadFile(id);
    }
}
