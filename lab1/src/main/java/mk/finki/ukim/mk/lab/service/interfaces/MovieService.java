package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.exceptions.MovieNotFound;
import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchMovies(String text, String rating);
    Movie findMovieById(long id);
    void editMovie(Long movieId, String title, String summary, float rating, Long productionId) throws MovieNotFound;
    void deleteMovie(Long id);
    void addMovie(String title, String summary, float rating, Long productionId);
}

