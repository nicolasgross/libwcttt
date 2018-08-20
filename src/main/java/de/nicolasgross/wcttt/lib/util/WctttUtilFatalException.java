package de.nicolasgross.wcttt.lib.util;

public class WctttUtilFatalException extends RuntimeException {

	public WctttUtilFatalException() {
		super();
	}

	public WctttUtilFatalException(String message) {
		super(message);
	}

	public WctttUtilFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	public WctttUtilFatalException(Throwable cause) {
		super(cause);
	}
}
