package business.control.validation.exceptions;

public class CustomException extends Exception {

	public CustomException(String msg) {
		super(msg);
	}
	
	public CustomException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
