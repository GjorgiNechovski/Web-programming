package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.exceptions.MovieNotFound;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

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
        return movieRepository.searchMoviesByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(text, text);
    }

    @Override
    public List<Movie> searchMovies(String text, String rating) {
        if (text != null && rating !=null && !rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.findByTitleContainingIgnoreCaseAndRatingGreaterThanEqual(text, parsedRating);
        }

        if (text != null) {
            return movieRepository.searchMoviesByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(text, text);
        }

        if (rating !=null && !rating.isEmpty()) {
            double parsedRating = Double.parseDouble(rating);
            return movieRepository.findByRatingGreaterThanEqual(parsedRating);
        }

        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void editMovie(Long id, String title, String summary, float rating, Long productionId) throws MovieNotFound {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        Production production = productionRepository.findById(productionId).get();

        if (optionalMovie.isEmpty()){
            throw new MovieNotFound();
        }



        Movie movie = optionalMovie.get();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float formattedRating = Float.parseFloat(decimalFormat.format(rating));

        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setRating(formattedRating);
        movie.setProduction(production);

        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void addMovie(String title, String summary, float rating, Long productionId) {
        Production production = productionRepository.findById(productionId).get();

        Movie movie = new Movie(title,summary,rating);

        movie.setProduction(production);

        movieRepository.save(movie);
    }


}
