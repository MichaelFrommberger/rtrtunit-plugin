package com.thalesgroup.rtrtunit.converter;

import org.junit.Test;

import com.thalesgroup.rtrtunit.RTRTUnitInputMetric;

public class RTRTUnitConversionFailedTest extends AbstractRTRTUnitConversionTest {
     
    @Test
    public void rtrtUnitTestBadInput() throws Exception {
        // No H token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH.rio");
        // Bad H token, no day
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH1.rio");
        // Bad H token, no month
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH2.rio");
        // Bad H token, no day number
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH3.rio");
        // Bad H token, no hour
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH4.rio");
        // Bad H token, no year
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outH5.rio");
        // No O token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outO.rio");
        // no T token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outT.rio");
        // no D token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outD.rio");
        // Bad D token => no second argument (no execution time)
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outD1.rio");
        // No M token
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outM.rio");
        // Bad M token => no second argument (no execution time)
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outM1.rio");
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
        // bad N token, missing second arg
        failedInputValidation(RTRTUnitInputMetric.class, "badinput/outN.rio");
    }
    
    @Test
    public void rtrtUnitTestBadMatches() throws Exception {
        // bad V match
        failedConversion(RTRTUnitInputMetric.class, "badmatch/out1.rio");
        // Bad T match
        failedConversion(RTRTUnitInputMetric.class, "badmatch/out2.rio");
    }
    


}
