package infra.exceptions;

public class InexistentUser extends Exception {
    public InexistentUser(String message){
        super(message);
    }

    public InexistentUser(String message, Throwable causa){
        super(message, causa);
    }
}
