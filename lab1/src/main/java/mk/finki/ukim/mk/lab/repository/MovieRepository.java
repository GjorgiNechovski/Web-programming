package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findById(long id);
    List<Movie> searchMoviesByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(String title, String summary);
    List<Movie> findByRatingGreaterThanEqual(double rating);
    List<Movie> findByTitleContainingIgnoreCaseAndRatingGreaterThanEqual(String title, double rating);
    void deleteById(long id);

    Movie findByTitle(String title);
}
