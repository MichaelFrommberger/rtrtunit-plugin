package com.thalesgroup.rtrtunit.riochecker;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.thalesgroup.rtrtunit.rioreader.RioReader;

/**
 * Unitary Test for RioChecker
 * @author Sebastien Barbier
 * @version 1.0
 *
 */
public class SyntaxRioCheckerTest {

    private void testChecker(final String rioFileName) throws Exception {
    	File file;
    	file = new File(this.getClass().getResource(rioFileName).toURI());
        RioReader rioChecker = new RioReader();
        Assert.assertTrue(rioChecker.validate(file));
    }

    @Test
    public void testValidation1() throws Exception {
    	testChecker("out.rio");
    }

    @Test
    public void testValidation2() throws Exception {
    	testChecker("out2.rio");
    }

    @Test
    public void testValidation3() throws Exception {
    	testChecker("out3.rio");
    }

    @Test
    public void testValidation4() throws Exception {
    	testChecker("out4.rio");
    }

    @Test
    public void testValidation5() throws Exception {
    	testChecker("out5.rio");
    }

    @Test
    public void testValidation6() throws Exception {
    	testChecker("out6.rio");
    }

    @Test
    public void testValidation7() throws Exception {
    	testChecker("out7.rio");
    }

    @Test
    public void testValidation8() throws Exception {
    	testChecker("out8.rio");
    }

    @Test
    public void testValidation9() throws Exception {
    	testChecker("out9.rio");
    }

    @Test
    public void testValidation10() throws Exception {
    	testChecker("out10.rio");
    }

    @Test
    public void testValidation11() throws Exception {
    	testChecker("out11.rio");
    }

    @Test
    public void testValidation12() throws Exception {
    	testChecker("out12.rio");
    }

    @Test
    public void testValidation13() throws Exception {
    	testChecker("out13.rio");
    }

    @Test
    public void testValidation14() throws Exception {
    	testChecker("out14.rio");
    }

    @Test
    public void testValidation15() throws Exception {
    	testChecker("out15.rio");
    }

    @Test
    public void testValidation16() throws Exception {
    	testChecker("out16.rio");
    }

    @Test
    public void testValidation17() throws Exception {
    	testChecker("out17.rio");
    }

    /**
     * rio file from Ada test, with "E" tokens
     * @throws Exception
     */
    @Test
    public void testValidation18() throws Exception {
    	testChecker("out18.rio");
    }

    /**
     * RTRT 8 rio file
     * @throws Exception
     */
    @Test
    public void testValidation19() throws Exception {
    	testChecker("out19.rio");
    }

    @Test
    public void testValidation20() throws Exception {
        testChecker("out20.rio");
    }

    @Test
    public void testValidation21() throws Exception {
        testChecker("out21.rio");
    }

    @Test
    public void testValidation22() throws Exception {
        testChecker("out22.rio");
    }

    @Test
    public void testValidation23() throws Exception {
        testChecker("out23.rio");
    }

    @Test
    public void testValidation24() throws Exception {
        testChecker("out24.rio");
    }

    @Test
    public void testValidationA350rtrt7() throws Exception {
    	testChecker("a350_rtrt7.rio");
    }

    @Test
    public void testValidationA350rtrt7_2() throws Exception {
    	testChecker("a350_rtrt7_2.rio");
    }

    @Test
    public void testValidationA350rtrt7_3() throws Exception {
    	testChecker("a350_rtrt7_3.rio");
    }
}
