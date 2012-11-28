package com.thalesgroup.rtrtunit.tdcreader;

import org.junit.Test;

/**
 * @author Sebastien Barbier
 * @version 1.0
 * 
 */
public class TdcReaderTest extends AbstractTdcReaderTest {

    @Test
    public void testBadInput1() throws Exception {
        // Error on the token T
        testBadInput("bad1.tdc");
    }
    
    @Test
    public void testBadInput2() throws Exception {
        // Error on the token V
        testBadInput("bad2.tdc");
    }

}
