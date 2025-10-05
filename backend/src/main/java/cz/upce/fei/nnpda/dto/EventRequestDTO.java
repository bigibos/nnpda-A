package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy")
    private Year season;
}
