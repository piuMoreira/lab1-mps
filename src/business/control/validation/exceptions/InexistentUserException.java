package business.control.validation.exceptions;

public class InexistentUserException extends CustomException {
    public InexistentUserException(String message){
        super(message);
    }

    public InexistentUserException(String message, Throwable causa){
        super(message, causa);
    }
}
