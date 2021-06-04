package business.control.validation.exceptions;

public class InexistentNewsException extends CustomException {
    public InexistentNewsException(String message){
        super(message);
    }

    public InexistentNewsException(String message, Throwable causa){
        super(message, causa);
    }
}
