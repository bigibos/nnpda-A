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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class AttachmentService {
    private final ModelMapper modelMapper;
    private final ProjectService projectService;
    private final AttachmentRepository attachmentRepository;
    private final TicketService ticketService;


    public Attachment processFile(MultipartFile file) {
        try {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setType(file.getContentType());
            attachment.setData(file.getBytes());
            return attachment;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Attachment getAttachment(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }


    @Transactional
    public Attachment addProjectAttachment(Long projectId, AttachmentRequestDTO attachmentDto) {

        Project project = projectService.findProject(projectId);
        /*
        Attachment attachment = modelMapper.map(attachmentDto, Attachment.class);
        attachment.setProject(project);
        attachment = attachmentRepository.save(attachment);
         */
        Attachment attachment = processFile(attachmentDto.getFile());
        attachment.setProject(project);
        attachment = attachmentRepository.save(attachment);
        // project.getAttachments().add(attachment);
        return attachment;
    }

    @Transactional
    public Attachment addTicketAttachment(Long ticketId, AttachmentRequestDTO attachmentDto) {

        Ticket ticket = ticketService.findTicket(ticketId);
        /*
        Attachment attachment = modelMapper.map(attachmentDto, Attachment.class);
        attachment.setTicket(ticket);
        attachment = attachmentRepository.save(attachment);
        */
        Attachment attachment = processFile(attachmentDto.getFile());
        ticket.getAttachments().add(attachment);

        return attachment;
    }



    @Transactional
    public Attachment updateAttachment(Long id, AttachmentRequestDTO attachmentDto) {
        // TODO: Wont be ablo to update - only delete and add another
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Attachment attachment = attachmentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);

        modelMapper.map(attachmentDto, attachment);
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

    public Collection<Attachment> findAttachments() {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        return attachmentRepository.findByUserUsername(username);
    }

    public Attachment findAttachment(Long id) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return attachmentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(EntityNotFoundException::new);
    }

}
