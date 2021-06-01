package business.control.validation.exceptions;


public class FileException extends CustomException {

    public FileException(String message){
        super(message);
    }

    public FileException(String message, Throwable causa){
        super(message, causa);
    }
    
}
