package cz.upce.fei.nnpda.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name="attachment")
public class Attachment extends Attachable {
    private String name;
    private String type;

    @Lob
    private byte[] data;

    public Attachment() {}
}
