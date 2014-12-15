package com.thalesgroup.rtrtunit.riochecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;

import com.thalesgroup.rtrtunit.riochecker.SyntaxRioChecker;
import com.thalesgroup.rtrtunit.riochecker.SimpleCharStream;

/**
 * Unitary Test for RioChecker
 * @author Sebastien Barbier
 * @version 1.0
 *
 */
public class SyntaxRioCheckerTest {

    @Test
    public void testBaseMethods() throws Exception {

        File file = null;
        InputStream ips = null;
        InputStreamReader ipsr = null;
        BufferedReader br = null;

        try {
            file = new File(this.getClass().getResource("out.rio").toURI());
            ips = new FileInputStream(file);
            ipsr = new InputStreamReader(ips);
            br = new BufferedReader(ipsr);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        SyntaxRioChecker rioChecker = new SyntaxRioChecker(br);
        rioChecker.ReInit(br);

        Token token = rioChecker.getNextToken();
        Assert.assertNotNull(token);
        Assert.assertEquals("H0", token.image);

        token = rioChecker.getToken(2);
        Assert.assertNotNull(token);
        Assert.assertEquals("Feb", token.toString());

        rioChecker.enable_tracing();
        rioChecker.disable_tracing();

    }

    @Test
    public void testSimpleCharStream() throws Exception {

        File file = null;
        InputStream ips = null;
        InputStream ips2 = null;

        try {
            file = new File(this.getClass().getResource("out.rio").toURI());
            ips = new FileInputStream(file);
            ips2 = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        SyntaxRioChecker rioChecker = new SyntaxRioChecker(ips);
        SimpleCharStream scs = new SimpleCharStream(ips2);
        SyntaxRioCheckerTokenManager srctm = new SyntaxRioCheckerTokenManager(
                scs);
        SyntaxRioChecker rioCheckerSecond = new SyntaxRioChecker(srctm);

        rioChecker.ReInit(ips);
        Token token, token2, endToken;

        token = rioChecker.getNextToken();
        token2 = rioCheckerSecond.getNextToken();
        endToken = new Token(0);
        while (token.kind != endToken.kind ) {
            Assert.assertEquals(token.image, token2.image);
            token = rioChecker.getNextToken();
            token2 = rioCheckerSecond.getNextToken();
        }
        Assert.assertEquals(endToken.kind, token.kind);
    }

    private void testChecker(final String rioFileName) throws Exception {
        InputStream ips = null;
        try {
        	File file = new File(this.getClass().getResource(rioFileName).toURI());
            ips = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        SyntaxRioChecker rioChecker = new SyntaxRioChecker(ips);
        Assert.assertTrue(rioChecker.validate());
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
