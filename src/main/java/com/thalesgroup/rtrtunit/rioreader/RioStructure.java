package com.thalesgroup.rtrtunit.rioreader;

import java.util.ArrayList;

/**
 * Java class containing info of .rio files.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RioStructure {
    /**
     * Number of Errors.
     */
    private int nbErrors;
    /**
     * List of tests.
     */
    private ArrayList<RioTest> tests;

    /**
     * Default Constructor.
     */
    public RioStructure() {
        nbErrors = 0;
        tests = new ArrayList<RioTest>();
    }

    /**
     * Adding a test.
     * @param info data on the test
     */
    public final void add(final RioTest info) {
        tests.add(info);
        // Update nbErrors;
        if (info.getNbFailedVariables() > 0) {
            nbErrors++;
        }
    }

    /**
     * Getter for the tests.
     * @return list of tests
     */
    public final ArrayList<RioTest> getTests() {
        return tests;
    }

    /**
     * Number of tests.
     * @return the number of tests
     */
    public final int getNbTests() {
        return tests.size();
    }

    /**
     * Number of errors.
     * @return the number of errors
     */
    public final int getNbErrors() {
        return nbErrors;
    }

}
