package de.nicolasgross.wcttt.binder;

public class WctttBinderException extends Exception {

	public WctttBinderException() {
		super();
	}

	public WctttBinderException(String message) {
		super(message);
	}

	public WctttBinderException(String message, Throwable cause) {
		super(message, cause);
	}

	public WctttBinderException(Throwable cause) {
		super(cause);
	}

	protected WctttBinderException(String message, Throwable cause,
	                               boolean enableSuppression,
	                               boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
