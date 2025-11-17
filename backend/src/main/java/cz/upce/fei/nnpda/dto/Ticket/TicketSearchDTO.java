package cz.upce.fei.nnpda.dto.Ticket;

import cz.upce.fei.nnpda.domain.Ticket;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "tickets")
public class TicketSearchDTO {
    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private Ticket.Type type;

    @Field(type = FieldType.Keyword)
    private Ticket.Priority priority;

    @Field(type = FieldType.Keyword)
    private Ticket.Status status;
}
