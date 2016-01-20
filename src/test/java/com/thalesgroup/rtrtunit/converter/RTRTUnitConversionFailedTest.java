package com.thalesgroup.rtrtunit.converter;

import org.junit.Test;

import com.thalesgroup.rtrtunit.RTRTUnitInputMetric;

public class RTRTUnitConversionFailedTest extends AbstractRTRTUnitConversionTest {
     
    @Test
    public void rtrtUnitTestBadInput() throws Exception {
        // no T token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outT.rio");
        // Bad D token => no second argument (no execution time)
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outD1.rio");
        // No M token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outM.rio");
        // Bad V token, no second arg
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV0.rio");
        // Bad V token, bad third arg
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV1.rio");
        // Bad V token, no arg
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV2.rio");
        // Bad V token, RA=<T|F> missing
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV3.rio");
        // Bad V token, MI but not MA
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV4.rio");
        // Bad V token, bad MI token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outV5.rio");
    }
    
    @Test
    public void rtrtUnitTestBadMatches() throws Exception {
        // bad V match
        failedConversion(RTRTUnitInputMetric.class, "badmatch/out1.rio");
        // Bad T match
        failedConversion(RTRTUnitInputMetric.class, "badmatch/out2.rio");
    }
    


}
