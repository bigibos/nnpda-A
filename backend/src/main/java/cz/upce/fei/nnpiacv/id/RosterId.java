package cz.upce.fei.nnpiacv.id;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RosterId implements Serializable {
    private Long eventId;
    private Long racerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterId rosterId = (RosterId) o;
        return Objects.equals(eventId, rosterId.eventId) &&
                Objects.equals(racerId, rosterId.racerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, racerId);
    }
}


