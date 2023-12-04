package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartEmptyException;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.interfaces.MovieService;
import mk.finki.ukim.mk.lab.service.interfaces.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieService movieService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, MovieService movieService) {
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
    }

    @PostMapping("addToCart")
    public String addToCart(HttpServletRequest req, Model model){

        String movieName = req.getParameter("movie");
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));
        String address = req.getRemoteAddr();
        String clientName = req.getParameter("clientName");


        model.addAttribute("movie", movieName);
        model.addAttribute("tickets", numberOfTickets);
        model.addAttribute("address", address);
        model.addAttribute("clientName", clientName);


        shoppingCartService.addToCart(movieName, clientName, numberOfTickets);

        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        return "listMovies";
    }

    @GetMapping("showCart")
    public String getUserCart(@RequestParam String username, Model model){
        ShoppingCart cart = null;
        try {
            cart = shoppingCartService.getUserShoppingCart(username);
        } catch (ShoppingCartEmptyException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("username", username);
        model.addAttribute("cart", cart);

        return "userCart";
    }

    @GetMapping("all/carts")
    public String getAllCarts(@RequestParam (required = false) LocalDateTime from,
                              @RequestParam (required = false) LocalDateTime to,
                              Model model){

        model.addAttribute("carts", shoppingCartService.getAllShoppingCartsInDate(from, to));

        return "allShoppingCarts";
    }
}
