package infra.exceptions;


public class FileException extends Exception {

    public FileException(String message){
        super(message);
    }

    public FileException(String message, Throwable causa){
        super(message, causa);
    }
    
}
