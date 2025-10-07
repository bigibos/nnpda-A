package cz.upce.fei.nnpda.dto;

import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.validator.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketAddDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @ValidEnum(enumClass = Ticket.Priority.class)
    private String priority;

    @NotNull
    @ValidEnum(enumClass = Ticket.Type.class)
    private String type;

    public Ticket.Priority getPriority() {
        return Ticket.Priority.valueOf(priority);
    }

    public Ticket.Type getType() {
        return Ticket.Type.valueOf(type);
    }
}
