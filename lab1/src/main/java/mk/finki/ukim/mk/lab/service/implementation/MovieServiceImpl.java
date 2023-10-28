package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
        if (text != null && !rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.searchMoviesByTextAndRating(text, parsedRating);
        }

        if (text != null) {
            return movieRepository.searchMovies(text);
        }

        if (!rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.searchMoviesByRating(parsedRating);
        }

        return movieRepository.findAll();
    }


}
