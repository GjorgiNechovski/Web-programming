package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    long id;
    String movieTitle;
    String clientName;
    String clientAddress;
    int numberOfTickets;

    public TicketOrder(long id, String movieTitle, String clientName, String clientAddress, int numberOfTickets) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
