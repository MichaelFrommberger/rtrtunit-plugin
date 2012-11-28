package com.thalesgroup.rtrtunit.tdcreader;

/**
 * TdcException: Exception specialized for .tdc reader.
 */
@SuppressWarnings("serial")
public class TdcException extends Exception {

    /**
     * Constructor with string message.
     * @param message error message
     */
    public TdcException(final String message) {
        super("Error during reading .tdc file: " + message);
    }

}
