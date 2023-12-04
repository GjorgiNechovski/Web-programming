package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.exceptions.MovieNotFound;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.implementation.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.implementation.ProductionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {
    private final MovieServiceImpl movieService;
    private final ProductionServiceImpl productionService;

    public MovieController(MovieServiceImpl movieService, ProductionServiceImpl productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping("/movies")
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        List<Movie> movies = movieService.listAll();

        model.addAttribute("movies", movies);
        if (error!=null && !error.isEmpty()){
            model.addAttribute("error", error);
        }
        return "listMovies";
    }

    @GetMapping("/movie/edit/{movieId}")
    public String getEditPage(@PathVariable long movieId, Model model){
        Movie movie = movieService.findMovieById(movieId);
        List<Production> productions = productionService.findAll();

        if(movie==null){
            return "redirect:/movies?error=A movie with the given id does not exist!";
        }

        model.addAttribute("movie", movie);
        model.addAttribute("productions", productions);

        return "editMovie";
    }

    @PostMapping("/movie/edit/{movieId}")
    public String editMovie(@PathVariable long movieId,
                            @RequestParam String title,
                            @RequestParam String summary,
                            @RequestParam float rating,
                            @RequestParam(name = "production") Long productionId) {
        try {
            movieService.editMovie(movieId, title, summary, rating, productionId);
        }
        catch (MovieNotFound e){
            return "redirect:/movies?error="+ e.getMessage();
        }
        return "redirect:/movies";
    }

    @PostMapping("/movie/delete/{movieId}")
    public String deleteMovie(@PathVariable long movieId){
        movieService.deleteMovie(movieId);

        return "redirect:/movies";
    }

    @GetMapping("/movie/delete/{movieId}")
    public String deleteMovieGet(@PathVariable long movieId){
        movieService.deleteMovie(movieId);

        return "redirect:/movies";
    }

    @GetMapping("movie/add")
    public String getAddMoviePage(Model model){
        List<Production> productions = productionService.findAll();

        model.addAttribute("productions", productions);

        return "add-movie";
    }

    @PostMapping("movie/add")
    public String addMovie(@RequestParam String title,
                           @RequestParam String summary,
                           @RequestParam float rating,
                           @RequestParam(name = "production") Long productionId){

        this.movieService.addMovie(title,summary,rating,productionId);
        return "redirect:/movies";

    }
}
