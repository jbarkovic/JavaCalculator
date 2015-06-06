package exceptions;

public class BracketsException extends Exception {

	private static final long serialVersionUID = -7337202972883704237L;

	public BracketsException() {
		super();
	}

	public BracketsException(String message) {
		super(message);
	}

	public BracketsException(Throwable cause) {
		super(cause);
	}

	public BracketsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BracketsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
