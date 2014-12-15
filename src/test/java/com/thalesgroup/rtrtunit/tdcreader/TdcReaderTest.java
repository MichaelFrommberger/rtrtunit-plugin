package com.thalesgroup.rtrtunit.tdcreader;

import java.io.File;

import org.junit.Assert;
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

	/**
	 * Vérifie qu'aucune exception n'est levée à la lecture de ce TDC qui
	 * contient des définitions de variable sur plus de 2 lignes.
	 * 
	 * @throws Exception
	 */
    @Test
    public void testBusFailureHub2() throws Exception {
        File file = new File(this.getClass().getResource("TClass_BusFailureHub_2.tdc").toURI());
        TdcReader reader = new TdcReader(file);
        try {
            reader.generateTable();
        } catch (TdcException e) {
           Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }
}
