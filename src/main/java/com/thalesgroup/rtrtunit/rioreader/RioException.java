package com.thalesgroup.rtrtunit.rioreader;

public class RioException extends Exception {

	private static final long serialVersionUID = 308875316140764640L;

	/**
     * Constructor with string message.
     * @param message error message
     */
    public RioException(final String message) {
        super(message);
    }
}
