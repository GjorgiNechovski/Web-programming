package mk.finki.ukim.mk.lab.model.exceptions;

public class MovieNotFound extends Exception{
    public MovieNotFound() {
        super("A movie with the given id does not exist!");
    }
}
