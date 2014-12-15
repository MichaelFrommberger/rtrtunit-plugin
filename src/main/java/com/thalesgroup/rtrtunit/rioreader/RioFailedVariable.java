package com.thalesgroup.rtrtunit.rioreader;
/**
 * Java class encoding the value of a failed variable.
 * Comparison between the expected value and the given value by the test
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RioFailedVariable {

    /**
     * name of the variable.
     */
    private String name;
    /**
     * expected value at the end of the unitary test.
     */
    private String expectedValue;
    /**
     * obtained value at the end of the unitary test.
     */
    private String givenValue;
    /**
     * test on this variable failed.
     */
    private boolean failed;

    /**
     * Default Constructor.
     */
    public RioFailedVariable() {
        name = new String();
        expectedValue = new String();
        givenValue = new String();
        failed = false;
    }

    /**
     * Extract the id from the token VNUM.
     * @param sName the id of the var
     * see RioReader.jj
     */
    public final void setName(final String sName) {
        this.name = sName;
    }

    /**
     * Return the token VNUM#.
     * @return id of the variable
     */
    public final String getName() {
        return name;
    }

    /**
     * Extract the tag from the function RA().
     * @param sFailed into {RA=T, RA=F}
     * see RioReader.jj
     */
    public final void setFailed(final String sFailed) {
        String[] sValueRA = new String[2];
        sValueRA = sFailed.split("=");
        if (sValueRA[1].compareTo("T") != 0) {
            this.failed = true;
        }
    }

    /**
     * Return if test on the variable failed.
     * @return true if test failed
     */
    public final boolean isFailed() {
        return failed;
    }

    /**
     * Extract the givenValue from the tokens MANUM or MAVAR.
     * @param sExpectedValue the expected value
     * see RioReader.jj
     */
    public final void setExpectedValue(final String sExpectedValue) {
        String[] sValue = new String[2];
        sValue = sExpectedValue.split("=");
        this.expectedValue = sValue[1];
    }

    /**
     * Return the expected value.
     * @return the expected value
     */
    public final String getExpectedValue() {
        return expectedValue;
    }

    /**
     * Extract the expectedValue from the tokens VAR or NUM.
     * @param sGivenValue the given value
     * see RioReader.jj
     */
    public final void setGivenValue(final String sGivenValue) {
        this.givenValue = sGivenValue;
    }

    /**
     * Return the given value.
     * @return the given value
     */
    public final String getGivenValue() {
        return givenValue;
    }

}
