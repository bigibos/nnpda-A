package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Attachment;
import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.dto.Attachment.AttachmentRequestDTO;
import cz.upce.fei.nnpda.repository.AttachmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@AllArgsConstructor
public class AttachmentService {
    private final ModelMapper modelMapper;
    private final ProjectService projectService;
    private final AttachmentRepository attachmentRepository;
    private final TicketService ticketService;


    public Attachment uploadFile(MultipartFile file) {
        try {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setType(file.getContentType());
            attachment.setData(file.getBytes());
            return attachment;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<byte[]> downloadFile(Long attachmentId) {
        // String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachment.getData());
    }


    @Transactional
    public Attachment uploadProjectAttachment(Long projectId, MultipartFile file) {

        Project project = projectService.findProject(projectId);

        Attachment attachment = uploadFile(file);
        attachment.setProject(project);
        attachment = attachmentRepository.save(attachment);

        return attachment;
    }

    @Transactional
    public Attachment uploadTicketAttachment(Long ticketId, MultipartFile file) {

        Ticket ticket = ticketService.findTicket(ticketId);

        Attachment attachment = uploadFile(file);
        attachment.setTicket(ticket);
        attachment = attachmentRepository.save(attachment);

        return attachment;
    }


    @Transactional
    public void deleteAttachment(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Attachment attachment = attachmentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
        attachmentRepository.delete(attachment);
    }

    public Attachment findAttachment(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return attachmentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Attachment findAttachments(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return attachmentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
    }

}
