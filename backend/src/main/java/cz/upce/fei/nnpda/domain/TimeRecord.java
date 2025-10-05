package cz.upce.fei.nnpda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

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
