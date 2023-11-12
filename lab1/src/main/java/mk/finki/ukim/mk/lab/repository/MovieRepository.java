package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    List<Movie> movieList;

    public MovieRepository(ProductionRepository productionRepository) {
        List<Production> productions = productionRepository.findAll();
        movieList = new ArrayList<>(10);

        movieList.add(new Movie(1L, "Movie 1", "Summary 1", 7.5, productions.get(0)));
        movieList.add(new Movie(2L, "Movie 2", "Summary 2", 8.0, productions.get(1)));
        movieList.add(new Movie(3L, "Movie 3", "Summary 3", 6.9, productions.get(2)));
        movieList.add(new Movie(4L, "Movie 4", "Summary 4", 7.2, productions.get(3)));
        movieList.add(new Movie(5L, "Movie 5", "Summary 5", 8.5, productions.get(4)));
        movieList.add(new Movie(6L, "Movie 6", "Summary 6", 6.7, productions.get(0)));
        movieList.add(new Movie(7L, "Movie 7", "Summary 7", 7.9, productions.get(1)));
        movieList.add(new Movie(8L, "Movie 8", "Summary 8", 8.2, productions.get(2)));
        movieList.add(new Movie(9L, "Movie 9", "Summary 9", 7.0, productions.get(3)));
        movieList.add(new Movie(10L, "Movie 10", "Summary 10", 7.3, productions.get(4)));
    }

    public List<Movie> findAll() {
        return movieList;
    }

    public List<Movie> searchMovies(String text) {
        String searchLower = text.toLowerCase();
        return movieList
                .stream()
                .filter(x -> x.getTitle().toLowerCase().contains(searchLower) || x.getSummary().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByRating(double rating) {
        return movieList
                .stream()
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByTextAndRating(String text, double rating) {
        String searchLower = text.toLowerCase();
        return movieList
                .stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchLower) || movie.getSummary().toLowerCase().contains(searchLower))
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList());
    }

    public Movie findById(long id){
        return movieList.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void set(Movie movie) {
        Movie existingMovie = movieList.stream()
                .filter(movie1 -> movie1.getId() == movie.getId())
                .findFirst().get();

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setSummary(movie.getSummary());
        existingMovie.setRating(movie.getRating());
        existingMovie.setProduction(movie.getProduction());
    }

    public void delete(long id){
        movieList.removeIf(movie -> movie.getId() == id);
    }

    public void addMovie(String title, String summary, float rating, Production production) {
        long newSize = movieList.size() + 1;

        this.movieList.add(new Movie(newSize,title,summary,rating,production));
    }
}
