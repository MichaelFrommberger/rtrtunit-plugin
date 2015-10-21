package com.thalesgroup.rtrtunit;

import org.jenkinsci.lib.dtkit.descriptor.TestTypeDescriptor;
import org.jenkinsci.lib.dtkit.type.TestType;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * RTRTUnitType.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RTRTUnitType extends TestType {

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
    
    @DataBoundConstructor
    public RTRTUnitType(String pattern, boolean skipNoTestFiles, boolean failIfNotNew, boolean deleteOutputFiles, boolean stopProcessingIfError) {
        super(pattern, skipNoTestFiles, failIfNotNew, deleteOutputFiles, stopProcessingIfError);
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
    @Override
    public Object readResolve() {
    	return new RTRTUnitPluginType(this.getPattern(), this.isFaildedIfNotNew());
    }

}
