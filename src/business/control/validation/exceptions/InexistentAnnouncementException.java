package business.control.validation.exceptions;

public class InexistentAnnouncementException extends CustomException {
    public InexistentAnnouncementException(String message){
        super(message);
    }

    public InexistentAnnouncementException(String message, Throwable causa){
        super(message, causa);
    }
}
