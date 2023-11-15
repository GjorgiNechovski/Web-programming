package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.TickerOrderRepository;
import mk.finki.ukim.mk.lab.service.interfaces.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickerOrderImpl implements TicketOrderService {
    TickerOrderRepository tickerOrderRepository;

    @Autowired
    public TickerOrderImpl(TickerOrderRepository tickerOrderRepository) {
        this.tickerOrderRepository = tickerOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        return tickerOrderRepository.order(movieTitle, clientName, address, numberOfTickets);
    }

    @Override
    public List<TicketOrder> getAllTickets() {
        return tickerOrderRepository.getAllTickets();
    }

    @Override
    public void deleteTicket(long id) {
        tickerOrderRepository.delete(id);
    }

    public long getClientsNumberOfTickets(String clientName){
         return tickerOrderRepository.getAllTickets()
                .stream()
                .filter(ticketOrder -> ticketOrder.getClientName().equals(clientName))
                .mapToInt(TicketOrder::getNumberOfTickets)
                 .sum();
    }
}
