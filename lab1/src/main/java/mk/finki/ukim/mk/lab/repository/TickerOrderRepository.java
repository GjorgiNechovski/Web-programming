package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TickerOrderRepository {
    List<TicketOrder> orders;

    public TicketOrder order(String movieName, String clientName, String address, Long tickets){
        if(orders == null){
            orders = new ArrayList<>();
        }

        TicketOrder order = new TicketOrder(movieName, clientName, address, tickets);
        orders.add(order);

        return order;
    }
}
