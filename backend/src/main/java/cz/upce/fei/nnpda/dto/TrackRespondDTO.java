package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackRespondDTO {
    private long id;
    private String name;

    @JsonIgnoreProperties("track")
    private List<PhaseRespondDTO> phases;
}
