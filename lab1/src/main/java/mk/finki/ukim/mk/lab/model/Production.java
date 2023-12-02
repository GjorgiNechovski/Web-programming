package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "production_name")
    private String name;
    private String country;
    private String address;

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    private List<Movie> movies;

    public Production() {

    }

    public Production(Long id, String name, String country, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
    }
}
