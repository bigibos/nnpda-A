package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.Year;

@Data
@AllArgsConstructor
@Entity
@Table(
        name = "time_record"
)
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Time time;


    public TimeRecord() {}
}
