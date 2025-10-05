package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhaseRespondDTO {
    private long id;
    private LocalDateTime startTime;

    @JsonIgnoreProperties("phases")
    private TrackRespondDTO track;

    @JsonIgnoreProperties("phases")
    private EventRespondDTO event;
}
