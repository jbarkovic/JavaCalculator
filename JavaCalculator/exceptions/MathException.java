package exceptions;

public class MathException extends Exception {

	private static final long serialVersionUID = 138158767351159907L;

	public MathException() {
	}

	public MathException(String message) {
		super(message);
	}

	public MathException(Throwable cause) {
		super(cause);
	}

	public MathException(String message, Throwable cause) {
		super(message, cause);
	}

	public MathException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
