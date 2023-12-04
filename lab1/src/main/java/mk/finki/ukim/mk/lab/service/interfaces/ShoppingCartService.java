package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartEmptyException;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {
    void addToCart(String movieTitle, String clientName, int numberOfTickets);

    ShoppingCart getUserShoppingCart(String username) throws ShoppingCartEmptyException;

    List<ShoppingCart> getAllShoppingCartsInDate(LocalDateTime from, LocalDateTime to);
}
