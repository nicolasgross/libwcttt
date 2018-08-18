package de.nicolasgross.wcttt.lib.model;

public class WctttModelFatalException extends RuntimeException {

	public WctttModelFatalException() {
		super();
	}

	public WctttModelFatalException(String message) {
		super(message);
	}

	public WctttModelFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	public WctttModelFatalException(Throwable cause) {
		super(cause);
	}
}
