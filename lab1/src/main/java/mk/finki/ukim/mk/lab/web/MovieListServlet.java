package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.implementation.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class MovieListServlet extends HttpServlet {
    private final MovieServiceImpl movieService;
    private final SpringTemplateEngine springTemplateEngine;

    @Autowired
    public MovieListServlet(MovieServiceImpl movieService, SpringTemplateEngine springTemplateEngine) {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);

        String filterText = req.getParameter("text");
        String rating = req.getParameter("rating");

        List<Movie> movies;

        movies = movieService.searchMovies(filterText, rating);

        webContext.setVariable("movies", movies);

        springTemplateEngine.process("listMovies.html", webContext, resp.getWriter());
    }
}
