package com.thalesgroup.rtrtunit;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

import org.jenkinsci.lib.dtkit.descriptor.TestTypeDescriptor;
import org.jenkinsci.lib.dtkit.type.TestType;

/**
 * RTRTUnitPluginType.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RTRTUnitPluginType extends TestType {

    /**
     * Constructor.
     * @param pattern the pattern where to search the file
     * @param faildedIfNotNew configuration option
     */
    public RTRTUnitPluginType(final String pattern,
            final boolean faildedIfNotNew) {
        // Fix a bug due to the generation of temporary files by Xunit
        // but not done by our code.
        // Result: always failure of the build it deleteOuputFiles=true
        super(pattern, faildedIfNotNew, false);
    }
    
    @DataBoundConstructor
    public RTRTUnitPluginType(String pattern, boolean skipNoTestFiles, boolean failIfNotNew, boolean deleteOutputFiles, boolean stopProcessingIfError) {
        super(pattern, skipNoTestFiles, failIfNotNew, deleteOutputFiles, stopProcessingIfError);
    }

    /**
     * Get the implementation of the descriptor.
     * @return implementation of the descriptor
     */
    public final TestTypeDescriptor<?> getDescriptor() {
        return RTRTUnitPluginType.DESCRIPTOR;
    }

    /**
     * The Descriptor.
     */
    @Extension
    public static final TestTypeDescriptor<RTRTUnitPluginType> DESCRIPTOR = new RTRTUnitPluginType.DescriptorImpl();

    /**
     * Implementation of the descriptor.
     */
    public static class DescriptorImpl extends
            TestTypeDescriptor<RTRTUnitPluginType> {

        /**
         * Constructor.
         */
        public DescriptorImpl() {
            super(RTRTUnitPluginType.class, RTRTUnitInputMetric.class);
        }

        /**
         * Get the identity.
         * @return the id
         */
        public final String getId() {
            return RTRTUnitPluginType.class.getCanonicalName();
        }
    }
    
    @Override
    public Object readResolve() {
    	return super.readResolve();
    }

}
