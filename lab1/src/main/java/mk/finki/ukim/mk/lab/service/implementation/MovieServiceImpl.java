package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.exceptions.MovieNotFound;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchMovies(String text, String rating) {
        if (text != null && rating !=null && !rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.searchMoviesByTextAndRating(text, parsedRating);
        }

        if (text != null) {
            return movieRepository.searchMovies(text);
        }

        if (rating !=null && !rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.searchMoviesByRating(parsedRating);
        }

        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void editMovie(Long id, String title, String summary, float rating, Long productionId) throws MovieNotFound {
        Movie movie = movieRepository.findById(id);
        Production production = productionRepository.findById(productionId);

        if (movie==null){
            throw new MovieNotFound();
        }

        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(production);

        movieRepository.set(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public void addMovie(String title, String summary, float rating, Long productionId) {
        Production production = productionRepository.findById(productionId);

        movieRepository.addMovie(title, summary, rating, production);
    }


}
