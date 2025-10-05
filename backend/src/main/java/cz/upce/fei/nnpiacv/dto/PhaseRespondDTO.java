package cz.upce.fei.nnpiacv.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpiacv.domain.Event;
import cz.upce.fei.nnpiacv.domain.Track;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
