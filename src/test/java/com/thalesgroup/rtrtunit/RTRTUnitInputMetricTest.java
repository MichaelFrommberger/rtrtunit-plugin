package com.thalesgroup.rtrtunit;

import junit.framework.Assert;

import org.junit.Test;

import com.thalesgroup.dtkit.metrics.model.InputType;

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
        Assert.assertEquals("1.0", inputMetric.getToolVersion());
        
    }

}
