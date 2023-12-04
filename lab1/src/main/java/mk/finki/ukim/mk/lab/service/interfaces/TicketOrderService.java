package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.util.List;

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, int numberOfTickets);

    List<TicketOrder> getAllTickets();

    void deleteTicket(long id);

    List<TicketOrder> placeOrderFromShoppingCart(String username);
}
