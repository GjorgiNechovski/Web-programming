package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.implementation.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.implementation.TickerOrderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TickerOrderController {
    private final TickerOrderImpl tickerOrder;
    private final MovieServiceImpl movieService;

    public TickerOrderController(TickerOrderImpl tickerOrder, MovieServiceImpl movieService) {
        this.tickerOrder = tickerOrder;
        this.movieService = movieService;
    }

    @PostMapping("/ticketOrder")
    protected String orderTickets(HttpServletRequest req, Model model)  {
        String movieName = req.getParameter("movie");
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));
        String address = req.getRemoteAddr();
        String clientName = req.getParameter("clientName");


        model.addAttribute("movie", movieName);
        model.addAttribute("tickets", numberOfTickets);
        model.addAttribute("address", address);
        model.addAttribute("clientName", clientName);


        tickerOrder.placeOrder(movieName, clientName, numberOfTickets);
        long totalTickets = 0;
        try {
            totalTickets = tickerOrder.getClientsNumberOfTickets(clientName);
        }
        catch (Exception e){
            model.addAttribute("error", "The username doesn't exist!");

            List<Movie> movies = movieService.listAll();
            model.addAttribute("movies", movies);
            return "listMovies";
        }
        model.addAttribute("totalTickets", totalTickets);

        return "orderConfirmation";
    }
    @GetMapping("/allTickets")
    public String getAllTickerOrders(Model model){
        List<TicketOrder> ticketOrders = tickerOrder.getAllTickets();

        model.addAttribute("tickets", ticketOrders);

        return "ticket-orders";
    }

    @PostMapping("/ticket/delete/{id}")
    public String deleteTicket(@PathVariable long id){
        tickerOrder.deleteTicket(id);

        return "redirect:/allTickets";
    }

    @PostMapping("buyTickets")
    public String buyTicketsFromCart(@RequestParam String username, Model model){
        List<TicketOrder> tickets = tickerOrder.placeOrderFromShoppingCart(username);

        model.addAttribute("tickets", tickets);

        return "ticket-orders";
    }
}
