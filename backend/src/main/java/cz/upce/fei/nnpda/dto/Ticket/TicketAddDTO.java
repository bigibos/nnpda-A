package cz.upce.fei.nnpda.dto.Ticket;

import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.validator.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketAddDTO {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 160)
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
