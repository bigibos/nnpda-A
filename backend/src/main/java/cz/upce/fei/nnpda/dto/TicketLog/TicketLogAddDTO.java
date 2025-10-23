package cz.upce.fei.nnpda.dto.TicketLog;

import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.validator.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketLogAddDTO {

    @NotNull
    @UpdateTimestamp
    private Timestamp time;

    @NotNull
    @ValidEnum(enumClass = Ticket.Status.class)
    private String status;

    @NotNull
    @ValidEnum(enumClass = Ticket.Priority.class)
    private String priority;

}
