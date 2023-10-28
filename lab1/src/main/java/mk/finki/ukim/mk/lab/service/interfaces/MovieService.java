package mk.finki.ukim.mk.lab.service.interfaces;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;

public interface MovieService {
    //I would totally delete these and leave only the last function
    //but since you specified I need listAll() and searchMovies(String text); I'm leaving them in
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchMovies(String text, String rating);
}

