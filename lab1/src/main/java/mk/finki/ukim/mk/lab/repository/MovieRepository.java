package mk.finki.ukim.mk.lab.model.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    List<Movie> movieList;

    public MovieRepository() {
        movieList = new ArrayList<>(10);

        movieList.add(new Movie("Movie 1", "Summary 1", 7.5));
        movieList.add(new Movie("Movie 2", "Summary 2", 8.0));
        movieList.add(new Movie("Movie 3", "Summary 3", 6.9));
        movieList.add(new Movie("Movie 4", "Summary 4", 7.2));
        movieList.add(new Movie("Movie 5", "Summary 5", 8.5));
        movieList.add(new Movie("Movie 6", "Summary 6", 6.7));
        movieList.add(new Movie("Movie 7", "Summary 7", 7.9));
        movieList.add(new Movie("Movie 8", "Summary 8", 8.2));
        movieList.add(new Movie("Movie 9", "Summary 9", 7.0));
        movieList.add(new Movie("Movie 10", "Summary 10", 7.3));
    }

    public List<Movie> findAll(){
        return movieList;
    }

    public List<Movie> searchMovies(String text){
        return movieList
                .stream()
                .filter(x->x.getTitle().contains(text) || x.getSummary().contains(text))
                .collect(Collectors.toList());
    }
}
