package business.control.validation.exceptions;

public class InvalidParamException extends CustomException {

	public InvalidParamException(String msg) {
		super(msg);
	}
	
	public InvalidParamException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
}
