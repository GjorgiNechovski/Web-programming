package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket_order")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String movieTitle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int numberOfTickets;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public TicketOrder() {

    }

    public TicketOrder(String movieTitle, User user, int numberOfTickets) {
        this.movieTitle = movieTitle;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }
}
