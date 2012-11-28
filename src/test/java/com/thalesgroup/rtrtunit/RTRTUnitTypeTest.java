package com.thalesgroup.rtrtunit;

import static org.mockito.Matchers.anyString;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.thalesgroup.dtkit.metrics.model.InputMetric;
import com.thalesgroup.hudson.plugins.xunit.types.XUnitType;
import com.thalesgroup.rtrtunit.RTRTUnitPluginType;
import com.thalesgroup.rtrtunit.RTRTUnitType;

public class RTRTUnitTypeTest {

    private void backwardCompatibility(String pattern, boolean faildedIfNotNew,
            boolean deleteJUnitFiles) throws Exception {

        // An old instance can be created and the its getDescriptor method
        // returns null
        XUnitType rtrtUnitType = new RTRTUnitType(pattern, faildedIfNotNew);
        Assert.assertNull(rtrtUnitType.getDescriptor());

        // Test new Object type
        Method readResolveMethod = RTRTUnitType.class.getMethod("readResolve");
        Object object = readResolveMethod.invoke(rtrtUnitType);
        Assert.assertTrue(object.getClass() == RTRTUnitPluginType.class);

        RTRTUnitPluginType rtrtUnitPluginType = (RTRTUnitPluginType) object;
        Assert.assertNotNull(rtrtUnitPluginType.getDescriptor());

        Assert.assertEquals(rtrtUnitType.getPattern(), rtrtUnitPluginType
                .getPattern());
        Assert.assertEquals(rtrtUnitType.isDeleteJUnitFiles(),
                rtrtUnitPluginType.isDeleteOutputFiles());
        Assert.assertEquals(rtrtUnitType.isFaildedIfNotNew(),
                rtrtUnitPluginType.isFaildedIfNotNew());

        InputMetric inputMetric = rtrtUnitPluginType.getInputMetric();
        Assert.assertNotNull(inputMetric);
    }

    @Test
    public void test1() throws Exception {
        backwardCompatibility(anyString(), true, true);
    }

    @Test
    public void test2() throws Exception {
        backwardCompatibility(anyString(), true, false);
    }

    @Test
    public void test3() throws Exception {
        backwardCompatibility(anyString(), false, true);
    }

    @Test
    public void test4() throws Exception {
        backwardCompatibility(anyString(), false, false);
    }

}
