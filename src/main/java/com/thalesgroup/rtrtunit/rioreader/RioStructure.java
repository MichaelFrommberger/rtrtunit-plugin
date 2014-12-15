package com.thalesgroup.rtrtunit.rioreader;

import java.util.ArrayList;

/**
 * Java class containing info of .rio files.
 *
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
    private final ArrayList<RioTest> tests;

    /**
     * Default Constructor.
     */
    public RioStructure() {
        nbErrors = 0;
        tests = new ArrayList<RioTest>();
    }

    /**
     * Adding a test.
     *
     * @param info
     *            data on the test
     */
    public final void add(final RioTest info) {
        // if a previously recorded test has the same name,
        // (i.e. if this test is multi-occurrent, with
        // several result files), then update it.
        for (RioTest test : tests) {
            if (test.getName().equals(info.getName())) {
                test.getFailedVariables().addAll(info.getFailedVariables());
                if (info.getNbFailedVariables() > 0) {
                    nbErrors++;
                }
                return;
            }
        }
        tests.add(info);
        // Update nbErrors;
        if (info.getNbFailedVariables() > 0) {
            nbErrors++;
        }
    }

    /**
     * Getter for the tests.
     *
     * @return list of tests
     */
    public final ArrayList<RioTest> getTests() {
        return tests;
    }

    /**
     * Number of tests.
     *
     * @return the number of tests
     */
    public final int getNbTests() {
        return tests.size();
    }

    /**
     * Number of errors.
     *
     * @return the number of errors
     */
    public final int getNbErrors() {
        return nbErrors;
    }

}
