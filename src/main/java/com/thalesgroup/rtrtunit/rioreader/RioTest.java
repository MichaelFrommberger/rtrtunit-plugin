package com.thalesgroup.rtrtunit.rioreader;

import java.util.ArrayList;
/**
 * Java class containing info on a given test.
 * Such as time and failed variables
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RioTest {
    /**
     * Name of the test.
     */
    private String name;
    /**
     * Execution time.
     */
    private float time;
    /**
     * List of failed variables into the unitary tests.
     */
    private ArrayList<RioFailedVariable> failedVariables;

    /**
     * Default Constructor.
     */
    public RioTest() {
        name = new String();
        time = 0.f;
        failedVariables = new ArrayList<RioFailedVariable>();
    }

    /**
     * Extract the id from the token TNUM.
     * @param sName the id of the test
     * see RioReader.jj
     */
    public final void setName(final String sName) {
        this.name = sName;
    }

    /**
     * Return the id.
     * @return id of the test
     */
    public final String getName() {
        return name;
    }

    /**
     * Extract the time from the token NUM.
     * @param sTime the execution time
     * see RioReader.jj
     */
    public final void setTime(final String sTime) {
        final float fMILLISECOND = 1000.f;
        this.time = Float.parseFloat(sTime) / fMILLISECOND;
    }

    /**
     * Return the execution time.
     * @return the execution time
     */
    public final String getTime() {
        return Float.toString(time);
    }

    /**
     * Add a failed variable to the list.
     * @param vars data of the failed variable
     */
    public final void addFailedVariables(final RioFailedVariable vars) {
        failedVariables.add(vars);
    }

    /**
     * Return the list of failed variables.
     * @return the list of failed variables
     */
    public final ArrayList<RioFailedVariable> getFailedVariables() {
        return failedVariables;
    }

    /**
     * Return the number of failed variables for this test.
     * @return the number of failed variables
     */
    public final int getNbFailedVariables() {
        return failedVariables.size();
    }

}
