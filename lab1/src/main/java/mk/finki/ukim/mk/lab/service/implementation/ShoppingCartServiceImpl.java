package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartEmptyException;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.interfaces.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addToCart(String movieTitle, String clientName, int numberOfTickets) {
        User user = userRepository.findByUsername(clientName);

        if (user != null) {
            List<ShoppingCart> carts = user.getCarts();

            ShoppingCart shoppingCart;
            if (carts != null && !carts.isEmpty()) {
                shoppingCart = carts.get(0);
            } else {
                shoppingCart = new ShoppingCart();
                shoppingCart.setUser(user);
                shoppingCart.setDateCreated(LocalDateTime.now());

                carts = new ArrayList<>();
                carts.add(shoppingCart);
                user.setCarts(carts);
            }

            TicketOrder newTicketOrder = new TicketOrder(movieTitle, user, numberOfTickets);

            newTicketOrder.setShoppingCart(shoppingCart);
            newTicketOrder.setUser(user);

            List<TicketOrder> ticketOrders = shoppingCart.getTicketOrders();
            if (ticketOrders == null || ticketOrders.isEmpty()) {
                ticketOrders = new ArrayList<>();
            }

            ticketOrders.add(newTicketOrder);

            shoppingCart.setTicketOrders(ticketOrders);

            userRepository.save(user);
        }
    }


    @Override
    public ShoppingCart getUserShoppingCart(String username) throws ShoppingCartEmptyException {
        User user = userRepository.findByUsername(username);

        List<ShoppingCart> carts = user.getCarts();

        if (carts == null || carts.isEmpty()){
            throw new ShoppingCartEmptyException();
        }

        return carts.get(0);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCartsInDate(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return shoppingCartRepository.findByDateCreatedBetween(from, to);
        } else if (from != null) {
            return shoppingCartRepository.findByDateCreatedAfter(from);
        } else if (to != null) {
            return shoppingCartRepository.findByDateCreatedBefore(to);
        } else {
            return shoppingCartRepository.findAll();
        }
    }

}
