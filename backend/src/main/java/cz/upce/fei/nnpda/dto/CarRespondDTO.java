package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRespondDTO {
    private long id;
    private String brand;
    private String name;

    @JsonIgnoreProperties("car")
    private List<RacerRespondDTO> racers;
}
