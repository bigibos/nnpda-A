package cz.upce.fei.nnpiacv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(
        name = "racer",
        uniqueConstraints = @UniqueConstraint(columnNames = {"number", "team_id"})
)
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @Min(value = 1)
    @Max(value = 999)
    private Integer number;

    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName="id")
    @JsonIgnoreProperties("members")
    private Team team;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName="id")
    @JsonIgnoreProperties("racers")
    private Car car;

    @OneToMany(mappedBy = "racer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("racer")
    private List<Roster> rosters;


    public Racer() {}
}
