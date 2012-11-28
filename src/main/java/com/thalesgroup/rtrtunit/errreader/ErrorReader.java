package com.thalesgroup.rtrtunit.errreader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thalesgroup.rtrtunit.rioreader.RioStructure;
import com.thalesgroup.rtrtunit.rioreader.RioTest;

/**
 * @author Sebastien Barbier
 * @version 1.0
 */
public class ErrorReader {

    /**
     * Pointer into the err file.
     */
    private FileInputStream file;

    /**
     * Default Constructor.
     * @param fis the input stream
     */
    public ErrorReader(final FileInputStream fis) {
        file = fis;
    }

    /**
     * Read the err file.
     * @return Data on the error log file
     * @throws IOException if bad input
     */
    public final RioStructure read() throws IOException {

        RioStructure output = new RioStructure();
        RioTest error = new RioTest();

        InputStreamReader ipsrErr = null;
        BufferedReader brErr = null;

        ipsrErr = new InputStreamReader(file);
        brErr = new BufferedReader(ipsrErr);

        String message = "";
        String line;
        while ((line = readNextLine(brErr)) != null) {
            message += line + System.getProperty("line.separator");
        }

        error.setName(message);
        output.add(error);
        return output;
    }

    /**
     * Read next line into the .err ASCII files.
     * @param br the current buffer
     * @return the next line
     * @throws IOException if bad input
     */
    private static String readNextLine(final BufferedReader br)
            throws IOException {
        String sNextLine = null;

        sNextLine = br.readLine();

        return sNextLine;
    }

}
