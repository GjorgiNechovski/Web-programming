package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.TickerOrderRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.interfaces.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TickerOrderImpl implements TicketOrderService {
    private final TickerOrderRepository tickerOrderRepository;
    private final UserRepository userRepository;


    @Autowired
    public TickerOrderImpl(TickerOrderRepository tickerOrderRepository, UserRepository userRepository) {
        this.tickerOrderRepository = tickerOrderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, int numberOfTickets) {
        User user = userRepository.findByUsername(clientName);

        return tickerOrderRepository.save(new TicketOrder(movieTitle, user, numberOfTickets));
    }

    @Override
    public List<TicketOrder> getAllTickets() {
        return tickerOrderRepository.findAll();
    }

    @Override
    public void deleteTicket(long id) {
        tickerOrderRepository.deleteById(id);
    }

    @Override
    public List<TicketOrder> placeOrderFromShoppingCart(String username) {
        User user = userRepository.findByUsername(username);
        ShoppingCart cart = user.getCarts().get(0);

        tickerOrderRepository.saveAll(cart.getTicketOrders());

        return cart.getTicketOrders();
    }

    public long getClientsNumberOfTickets(String clientName) {
        long totalTickets = tickerOrderRepository.findAll()
                .stream()
                .filter(ticketOrder -> ticketOrder.getUser().getUsername().equals(clientName))
                .mapToInt(TicketOrder::getNumberOfTickets)
                .sum();

        if (totalTickets == 0) {
            throw new NoSuchElementException("Client with username " + clientName + " not found");
        }

        return totalTickets;
    }
}
