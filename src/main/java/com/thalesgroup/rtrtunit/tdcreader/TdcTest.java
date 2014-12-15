package com.thalesgroup.rtrtunit.tdcreader;

import java.util.HashMap;

/**
 * Java class encoding a data structure for test encoding into .tdc file.
 *
 * @author Sebastien Barbier
 * @version 1.0
 */
public class TdcTest {

    /**
     * Name of the test.
     */
    private String name;

    /**
     * Name of the tested service.
     */
    private String serviceName;

    /**
     * Vars included into the test.
     */
    private final HashMap<String, TdcVariable> vars;

    /**
     * Default Constructor.
     */
    public TdcTest() {
        this.name = new String();
        vars = new HashMap<String, TdcVariable>();
    }

    /**
     * Setter of the name.
     *
     * @param sName
     *            the name of the test
     */
    public final void setName(final String sName) {
        this.name = sName;
    }

    /**
     * Getter of the name.
     *
     * @return name of the test
     */
    public final String getName() {
        return name;
    }

    /**
     * Get the tested service name.
     *
     * @return the tested service name
     */
    public final String getServiceName() {
        return serviceName;
    }

    /**
     * Set the tested service name.
     *
     * @param newServiceName
     *            service name
     */
    public final void setServiceName(final String newServiceName) {
        this.serviceName = newServiceName;
    }

    /**
     * Add a new variable of the test.
     *
     * @param token
     *            the token associated with the var such as V*
     * @param nameVar
     *            its name
     * @param isPointer
     *            true if pointer
     */
    public final void addVar(final String token, final String nameVar,
            final boolean isPointer) {
        vars.put(token, new TdcVariable(nameVar, isPointer));
    }

    /**
     * Obtain a variable.
     *
     * @param token
     *            the token of the variable V*
     * @return the data associated with this variable
     */
    public final TdcVariable getVar(final String token) {
        return vars.get(token);
    }

}
