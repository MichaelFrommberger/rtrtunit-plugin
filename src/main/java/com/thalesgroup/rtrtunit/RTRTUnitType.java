package com.thalesgroup.rtrtunit;

import com.thalesgroup.dtkit.metrics.hudson.api.descriptor.TestTypeDescriptor;
import com.thalesgroup.hudson.plugins.xunit.types.XUnitType;

/**
 * RTRTUnitType.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RTRTUnitType extends XUnitType {

    /**
     * Constructor.
     * @param pattern the pattern where to search the file
     * @param faildedIfNotNew configuration option
     */
    public RTRTUnitType(final String pattern, final boolean faildedIfNotNew) {
        // Fix a bug due to the generation of temporary files by Xunit
        // but not done by our code.
        // Result: always failure of the build it deleteJUnitFiles=true
        super(pattern, faildedIfNotNew, false);
    }

    /**
     * No descriptor.
     * @return null
     */
    @Override
    public final TestTypeDescriptor<?> getDescriptor() {
        return null;
    }

    /**
     * Call at Hudson startup for backward compatibility.
     * @return an new hudson object
     */
    public final Object readResolve() {
        return new RTRTUnitPluginType(this.getPattern(), this
                .isFaildedIfNotNew());
    }

}
