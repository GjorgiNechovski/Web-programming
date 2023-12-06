package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String summary;
    private double rating;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    @ManyToOne
    private MoviePrice price;

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.price.setPrice(0);
        this.price.setDiscount(0);
    }

    public Movie(String title, String summary, double rating, Production production, MoviePrice price) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
        this.price = price;
    }

    public double getMoviePrice(){
        return this.price.getPrice() * this.price.getDiscount();
    }

    public Movie() {

    }
}
