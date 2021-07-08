package business.control.validation.exceptions;

public class UnableToUndoException extends CustomException {
    public UnableToUndoException(String message){
        super(message);
    }

    public UnableToUndoException(String message, Throwable causa){
        super(message, causa);
    }
}
