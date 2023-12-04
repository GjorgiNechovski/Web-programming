package mk.finki.ukim.mk.lab.model.exceptions;

public class ShoppingCartEmptyException extends Exception{
    public ShoppingCartEmptyException() {
        super("The shopping cart of this user is empty!");
    }
}
