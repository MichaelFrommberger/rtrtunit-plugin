package com.thalesgroup.rtrtunit.tdcreader;

import java.io.File;

import org.springframework.util.Assert;

/**
 * @author Sebastien Barbier
 * @version 1.0
 *
 */
public class AbstractTdcReaderTest {

    public void testBadInput(String inputPath) throws Exception {

        File file = null;
        file = new File(this.getClass().getResource(inputPath).toURI());
        TdcReader reader = new TdcReader(file);
        
        TdcException neededException = null;
        
        try {
            reader.generateTable();
        } catch (TdcException e) {
           neededException = e;
        } finally {
            Assert.notNull(neededException);
        }
        

    }
    
    
}
