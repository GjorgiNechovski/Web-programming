package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "client_name")
    private String name;

    private String surname;

    @Column(name = "client_password")
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShoppingCart> carts;
}
