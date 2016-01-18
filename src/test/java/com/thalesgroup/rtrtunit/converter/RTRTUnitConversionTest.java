package com.thalesgroup.rtrtunit.converter;

import org.junit.Test;

import com.thalesgroup.rtrtunit.RTRTUnitInputMetric;

public class RTRTUnitConversionTest extends AbstractRTRTUnitConversionTest {

    @Test
    public void rtrtUnitTestNoError() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "noerror/out.rio",
                "noerror/junit-report.xml");
    }

    @Test
    public void rtrtUnitTestNoError2() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "noerror/out2.rio",
                "noerror/junit-report2.xml");
    }

    @Test
    public void rtrt7UnitTestNoError() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "noerror/a350_rtrt7.rio",
                "noerror/junit-report_a350.xml");
    }
    @Test
    public void rtrt7UnitTestNoError2() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "noerror/a350_rtrt7_2.rio",
                "noerror/junit-report_a350_2.xml");
    }
    
    @Test
    public void rtrt7UnitTestNoError3() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "noerror/a350_rtrt7_3.rio",
                "noerror/junit-report_a350_3.xml");
    }

    @Test
    public void rtrtUnitTestOneError() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "oneerror/out.rio",
                "oneerror/junit-report.xml");
    }

    @Test
    public void rtrtUnitTestAllError() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "allerror/out.rio",
                "allerror/junit-report.xml");
    }
    
    @Test
    public void rtrtUnitTestErrorFiles() throws Exception {
        conversionAndValidation(RTRTUnitInputMetric.class, "errorfile/out.rio",
        "errorfile/junit-report.xml");
    }
    
    @Test
    public void rtrtUnitTestConversionVarBefore() throws Exception {
        conversion(RTRTUnitInputMetric.class, "varbefore/out.rio");
    }
    
    @Test
    public void rtrtUnitTestConversionWithLVar() throws Exception {
        conversion(RTRTUnitInputMetric.class, "withlvar/out.rio");
    }

    @Test
    public void testWithFailedStubCallNumber() throws Exception {
    	conversionAndValidation(
    				RTRTUnitInputMetric.class,
    				"failedstubcount/out.rio",
    				"failedstubcount/junit-report.xml");
    }

    @Test
    public void testRtrt8() throws Exception {
    	conversionAndValidation(
    				RTRTUnitInputMetric.class,
    				"rtrt8/out.rio",
    				"rtrt8/junit-report.xml");
    }


    @Test
    public void testComplexRio() throws Exception {
    	conversionAndValidation(
    				RTRTUnitInputMetric.class,
    				"noerror/complex.rio",
    				"noerror/junit-report-complex.xml");
    }

    @Test
    public void testAda() throws Exception {
    	conversionAndValidation(
    				RTRTUnitInputMetric.class,
    				"ada/out.rio",
    				"ada/junit-report.xml");
    }

    @Test
    public void testFuseau() throws Exception {
    	conversionAndValidation(
    				RTRTUnitInputMetric.class,
    				"fuseau/out.rio",
    				"fuseau/junit-report.xml");
    }
}
