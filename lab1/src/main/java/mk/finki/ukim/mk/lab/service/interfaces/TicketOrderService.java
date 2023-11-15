package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.util.List;

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);

    List<TicketOrder> getAllTickets();

    void deleteTicket(long id);
}
