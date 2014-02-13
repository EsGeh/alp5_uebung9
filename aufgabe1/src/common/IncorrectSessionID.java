package common;

public class IncorrectSessionID extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectSessionID() {
	}

	public IncorrectSessionID(String message) {
		super(message);
	}

	public IncorrectSessionID(Throwable cause) {
		super(cause);
	}

	public IncorrectSessionID(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectSessionID(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
