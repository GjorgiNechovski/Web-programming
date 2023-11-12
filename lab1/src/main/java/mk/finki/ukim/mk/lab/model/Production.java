package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Production {
    private Long id;
    private String name;
    private String country;
    private String address;
}
