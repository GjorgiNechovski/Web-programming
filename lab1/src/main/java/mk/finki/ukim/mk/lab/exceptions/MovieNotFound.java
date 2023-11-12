package mk.finki.ukim.mk.lab.exceptions;

public class MovieNotFound extends Exception{
    public MovieNotFound() {
        super("A movie with the given id does not exist!");
    }
}
