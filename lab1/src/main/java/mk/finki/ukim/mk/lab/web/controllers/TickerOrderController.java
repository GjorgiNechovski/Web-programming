package mk.finki.ukim.mk.lab.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.implementation.TickerOrderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TickerOrderController {
    private final TickerOrderImpl tickerOrder;

    public TickerOrderController(TickerOrderImpl tickerOrder) {
        this.tickerOrder = tickerOrder;
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


        tickerOrder.placeOrder(movieName, clientName, address, numberOfTickets);
        long totalTickets = tickerOrder.getClientsNumberOfTickets(clientName);
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
}
