package business.control.validation.exceptions;

public class MissingParamException extends Exception {

	public MissingParamException(String msg) {
		super(msg);
	}

	public MissingParamException(String msg, Throwable causa) {
		super(msg,causa);
	}
	
}
