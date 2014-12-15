package com.thalesgroup.rtrtunit;

import junit.framework.Assert;

import org.junit.Test;

import org.jenkinsci.lib.dtkit.model.InputType;

/**
 * Unitary Test for the InputMetric.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class RTRTUnitInputMetricTest {
    
    @Test
    public void test() throws Exception {
        
        RTRTUnitInputMetric inputMetric = new RTRTUnitInputMetric();
        
        Assert.assertEquals(InputType.TEST, inputMetric.getToolType());
        Assert.assertEquals("RTRTUnit", inputMetric.getToolName());
    }

}
