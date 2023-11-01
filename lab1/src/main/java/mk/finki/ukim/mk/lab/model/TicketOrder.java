package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    String movieTitle;
    String clientName;
    String clientAddress;
    int numberOfTickets;

    public TicketOrder(String movieTitle, String clientName, String clientAddress, int numberOfTickets) {
        this.movieTitle = movieTitle;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
