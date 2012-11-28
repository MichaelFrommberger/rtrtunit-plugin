package com.thalesgroup.rtrtunit.tdcreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Java class reading this streambuffer the .tdc files.
 * @author Sebastien Barbier
 * @version 1.0
 */
public class TdcReader {
    /**
     * the .tdc file.
     */
    private File file;
    /**
     * Information on each unitary test belonging to the .tdc file.
     */
    private HashMap<String, TdcTest> tests;

    /**
     * Default Constructor.
     * @param fFile the .tdc file.
     */
    public TdcReader(final File fFile) {
        this.file = fFile;
        tests = new HashMap<String, TdcTest>();
    }

    /**
     * Generation of all the information about the tests. - name of the tests -
     * name of the variables into the tests
     * @throws TdcException bad file input exception
     */
    public final void generateTable() throws TdcException {
        InputStream ipsTdc = null;
        InputStreamReader ipsrTdc = null;
        BufferedReader brTdc = null;

        try {
            ipsTdc = new FileInputStream(file);
            ipsrTdc = new InputStreamReader(ipsTdc);
            brTdc = new BufferedReader(ipsrTdc);
        } catch (FileNotFoundException e) {
            throw new TdcException(e.getMessage());
        } catch (IOException e) {
            throw new TdcException(e.getMessage());
        }

        String lineTdc;
        String tokenTest = "";
        // Dummy test for L variables !
        TdcTest currentTest = new TdcTest();
        currentTest.setName(tokenTest);

        String nextLine = null;

        nextLine = readNextLine(brTdc);
        while (nextLine != null) {
            lineTdc = nextLine;
            nextLine = null;
            if (lineTdc.startsWith("T")) {
                tests.put(tokenTest, currentTest);

                // test
                String[] sTest = new String[2];
                sTest = lineTdc.split(" ");

                currentTest = new TdcTest();
                tokenTest = sTest[0];

                try {
                    currentTest.setName(sTest[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new TdcException(e.getMessage());
                }

            } else if (lineTdc.startsWith("V") || lineTdc.startsWith("L")) {
                // Information on variable
                // sNameVar == V## name RA="#", INIT=#, VA=#,
                // sNameVar == L## name RA="#", INIT=#, VA=#
                String varLine = lineTdc;

                // Warning might be on two lines
                lineTdc = readNextLine(brTdc);
                nextLine = lineTdc;
                if (lineTdc != null && lineTdc.startsWith("&")) {
                    varLine += lineTdc.substring(1);
                }
                final int iSizeNameVar = 5;
                String[] sNameVar = new String[iSizeNameVar];
                sNameVar = varLine.split(" ");

                if (sNameVar.length < iSizeNameVar) {
                    throw new TdcException(
                            "Bad V variable: not enough information: "
                                    + varLine);
                }

                // If L variable, transform to V variable
                sNameVar[0] = sNameVar[0].replace("L", "V");
                // Special error output for pointers
                // RA=FORM=CH0 => no MI and MA in the .rio file
                if (sNameVar[2].compareTo("RA=FORM=CH0,") == 0) {
                    currentTest.addVar(sNameVar[0], sNameVar[1], true);
                } else {
                    currentTest.addVar(sNameVar[0], sNameVar[1], false);
                }
                // EOF
                if (nextLine == null) {
                    break;
                }

            }
            if (nextLine == null) {
                nextLine = readNextLine(brTdc);
            }
        }
        // Adding the last test
        tests.put(tokenTest, currentTest);

        try {
            /* close */
            brTdc.close();
            ipsrTdc.close();
            ipsTdc.close();
        } catch (IOException e) {
            throw new TdcException(e.getMessage());
        }

    }

    /**
     * Read next line into the .tdc ASCII files.
     * @param br the current buffer
     * @return the next line
     */
    private static String readNextLine(final BufferedReader br) {
        String sNextLine = null;

        try {
            sNextLine = br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return sNextLine;
    }

    /**
     * Return the name of the test.
     * @param token Token encoding a new test such as T*
     * @return the name of the test
     * @throws TdcException if ttoken does not exist
     */
    public final String getTestName(final String token) throws TdcException {
        TdcTest test = tests.get(token);
        if (test == null) {
            throw new TdcException("Test with token: " + token
                    + " does not exist;");
        }

        return test.getName();
    }

    /**
     * Return if the current variable identified by vtoken is a pointer.
     * @param ttoken token of the current test T*
     * @param vtoken token of the current variable V*
     * @return vtoken is a pointer
     * @throws TdcException if ttoken or vtoken do not exist
     */
    public final boolean isPointerVariable(final String ttoken,
            final String vtoken) throws TdcException {

        TdcVariable tdcVar = tests.get(ttoken).getVar(vtoken);

        if (tdcVar != null) {
            return tdcVar.isPointer();
        } else {
            // Search into previous tests. Var can be defined into them.
            // More, L vars are defined in the dummy first test.
            for (TdcTest tdcTest : tests.values()) {
                if (tdcTest.getVar(vtoken) != null) {
                    return tdcTest.getVar(vtoken).isPointer();
                }
            }
        }
        throw new TdcException("Var with token: " + vtoken + " does not exist!");
    }

    /**
     * Return the name variable identified by vtoken.
     * @param ttoken token of the current test T*
     * @param vtoken token of the current variable V*
     * @return name of the variable identified by vtoken
     * @throws TdcException if ttoken or vtoken do not exist
     */
    public final String getVariableName(final String ttoken, final String vtoken)
            throws TdcException {

        TdcVariable tdcVar = tests.get(ttoken).getVar(vtoken);
        if (tdcVar != null) {
            return tdcVar.getName();
        } else {
            // Search into previous tests. Var can be defined into them.
            // More, L vars are defined in the dummy first test.
            for (TdcTest tdcTest : tests.values()) {
                if (tdcTest.getVar(vtoken) != null) {
                    return tdcTest.getVar(vtoken).getName();
                }
            }
        }
        throw new TdcException("Var with token: " + vtoken + " does not exist!");

    }
    // private static final Logger logger =
    // Logger.getLogger(TdcReader.class.getName());
}
