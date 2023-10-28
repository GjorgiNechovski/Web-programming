package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.TickerOrderRepository;
import mk.finki.ukim.mk.lab.service.interfaces.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TickerOrderImpl implements TicketOrderService {
    TickerOrderRepository tickerOrderRepository;

    @Autowired
    public TickerOrderImpl(TickerOrderRepository tickerOrderRepository) {
        this.tickerOrderRepository = tickerOrderRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets) {
        return tickerOrderRepository.order(movieTitle, clientName, address, (long) numberOfTickets);
    }
}
