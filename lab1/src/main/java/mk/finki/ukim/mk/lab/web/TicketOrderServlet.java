package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.implementation.TickerOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet("/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final TickerOrderImpl tickerOrder;

    @Autowired
    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine, TickerOrderImpl tickerOrder) {
        this.springTemplateEngine = springTemplateEngine;
        this.tickerOrder = tickerOrder;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));
        String address = req.getRemoteAddr();
        String clientName = req.getParameter("clientName");

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("movie", movieName);
        webContext.setVariable("tickets", numberOfTickets);
        webContext.setVariable("address", address);
        webContext.setVariable("clientName", clientName);

        tickerOrder.placeOrder(movieName, clientName, address, numberOfTickets);

        springTemplateEngine.process("orderConfirmation.html", webContext, resp.getWriter());
    }
}
