package com.thalesgroup.rtrtunit.tdcreader;

/**
 * Java class containing info on a given variable into the .tdc file.
 * Such as its name and if it is a pointer
 * @author Sebastien Barbier
 * @version 1.0
 */
public class TdcVariable {

    /**
     * name of the variable.
     */
    private String name;
    /**
     * Is a pointer or not?
     */
    private boolean isPointer;

    /**
     * Constructor.
     * @param sName name of the variable
     * @param bIsPointer true if pointer
     */
    public TdcVariable(final String sName, final boolean bIsPointer) {
        this.name = sName;
        this.isPointer = bIsPointer;
    }

    /**
     * Setter for name.
     * @param sName the name
     */
    public final void setName(final String sName) {
        this.name = sName;
    }

    /**
     * Getter for name.
     * @return the name of the variable
     */
    public final String getName() {
        return name;
    }
    /**
     * Setter for pointer.
     * @param bIsPointer true if pointer
     */
    public final void setIsPointer(final boolean bIsPointer) {
        this.isPointer = bIsPointer;
    }

    /**
     * Getter for pointer.
     * @return true if pointer false otherwise
     */
    public final boolean isPointer() {
        return isPointer;
    }

}
