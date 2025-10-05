package cz.upce.fei.nnpiacv.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name="app_user")
public class User {
    @Id
    private long id;
    @Column(unique=true)
    private String email;
    private String password;

    public User() {}
}
