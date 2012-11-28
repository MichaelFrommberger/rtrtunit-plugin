package com.thalesgroup.rtrtunit.converter;

/**
 * Ensure the writing of the error message when an unitary test fails.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class ErrorReport {

    /**
     * current error report.
     */
    private String report;

    /**
     * Default Constructor.
     */
    public ErrorReport() {
        report = new String("");
    }

    /**
     * Add an error message for a variable.
     * @param varname the name of the variable
     * @param resultValue the value obtained by the unitary test
     * @param expectedValue the value expected
     */
    public final void addVariableError(final String varname, final String resultValue,
            final String expectedValue) {
        report += varname + " failed. The obtained value " + resultValue
                + " is different from the expected value " + expectedValue
                + "." + System.getProperty("line.separator");
    }

    /**
     * Add an error message for a pointer.
     * @param varname the name of the pointer
     */
    public final void addPointerError(final String varname) {
        report += varname + " failed. The value of the pointer is wrong."
                + System.getProperty("line.separator");
    }

    /**
     * Get the error report.
     * @return the error report
     */
    public final String getErrorReport() {
        return report;
    }

}
