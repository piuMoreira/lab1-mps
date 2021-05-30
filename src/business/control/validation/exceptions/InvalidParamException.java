package business.control.validation.exceptions;

public class InvalidParamException extends Exception {

	public InvalidParamException(String msg) {
		super(msg);
	}
	
	public InvalidParamException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
