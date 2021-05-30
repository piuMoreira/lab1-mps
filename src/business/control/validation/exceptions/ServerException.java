package business.control.validation.exceptions;

public class ServerException extends Exception {

	public ServerException(String msg) {
		super(msg);
	}

	public ServerException(String msg, Throwable causa) {
		super(msg,causa);
	}
	
}
