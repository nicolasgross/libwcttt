package de.nicolasgross.wcttt.lib.binder;

public class WctttBinderFatalException extends RuntimeException {

	public WctttBinderFatalException() {
		super();
	}

	public WctttBinderFatalException(String message) {
		super(message);
	}

	public WctttBinderFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	public WctttBinderFatalException(Throwable cause) {
		super(cause);
	}

	protected WctttBinderFatalException(String message, Throwable cause, boolean
			enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
