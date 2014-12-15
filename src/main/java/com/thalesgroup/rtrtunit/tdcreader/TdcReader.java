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
 *
 * @author Sebastien Barbier
 * @version 1.0
 */
public class TdcReader {
    /**
     * the .tdc file.
     */
    private final File file;
    /**
     * Information on each unitary test belonging to the .tdc file.
     */
    private final HashMap<String, TdcTest> tests;

    /**
     * Default Constructor.
     *
     * @param fFile
     *            the .tdc file.
     */
    public TdcReader(final File fFile) {
        this.file = fFile;
        tests = new HashMap<String, TdcTest>();
    }

    /**
     * Generation of all the information about the tests. - name of the tests -
     * name of the variables into the tests
     *
     * @throws TdcException
     *             bad file input exception
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
        }

        String lineTdc;
        String tokenTest = "";
        // Dummy test for L variables !
        TdcTest currentTest = new TdcTest();
        currentTest.setName(tokenTest);
        String currentService = "";
        boolean adaLanguage = false;

        String nextLine = null;

        nextLine = readNextLine(brTdc);
        while (nextLine != null) {
            lineTdc = nextLine;
            nextLine = null;
            if (lineTdc.startsWith("E9 ")) {
                // Programming language
                // either "E9 C C" for C
                // or "E9 ADA ADA" for Ada
                String[] tokens = lineTdc.split(" ");
                if ((tokens.length > 1) && (tokens[1].equals("ADA"))) {
                    adaLanguage = true;
                }
            } else if (lineTdc.startsWith("O")) {
                // tested service name
                String[] tokens = lineTdc.split(" ", 2);
                if (tokens.length > 1) {
                    currentService = tokens[1];
                } else {
                    currentService = "";
                }
            } else if (lineTdc.startsWith("T")) {
                // Test identifier
                tests.put(tokenTest, currentTest);
                String[] sTest = new String[2];
                sTest = lineTdc.split(" ");
                currentTest = new TdcTest();
                currentTest.setServiceName(currentService);
                tokenTest = sTest[0];

                try {
                    currentTest.setName(sTest[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new TdcException(e.getMessage());
                }
            } else if (lineTdc.startsWith("M")) {
                // Information on a stub function (which call counts are tested)
                // M0
                // Mi stubName functionName
                String[] stubTokens = lineTdc.split(" ", 2);
                if (stubTokens.length > 1) {
                    currentTest.addVar(stubTokens[0], // M# identifier
                            stubTokens[1], // stub function name
                            false);
                }
            } else if (lineTdc.startsWith("V") || lineTdc.startsWith("L")) {
                // Information on variable
                // C language:
                // V## name RA="#", INIT=#, VA=#,
                // L## name RA="#", INIT=#, VA=#
                // Ada language:
                // V# # # # O_LINE # # O_NAME name O_INIT # O_EV #
                String varLine = lineTdc;
                // Warning: might be on several lines
                nextLine = readNextLine(brTdc);
                while ((nextLine != null) && nextLine.startsWith("&")) {
                    varLine += nextLine.substring(1);
                    nextLine = readNextLine(brTdc);
                }
                String[] tokens = varLine.split(" ");
                if (adaLanguage) {
                    // Ada language
                    // identifier is #0 and name is #8 element
                    final int nameTokenIndex = 8;
                    if (tokens.length > nameTokenIndex) {
                        String varId = tokens[0];
                        String varName = tokens[nameTokenIndex];
                        currentTest.addVar(varId, varName, false);
                    } else {
                        throw new TdcException(String.format(
                                "%s: malformed observable line: %s",
                                file.getAbsolutePath(), varLine));
                    }
                } else {
                    // C language
                    // identifier is #0 and name is #1 element
                    if (tokens.length > 2) {
                        String varId = tokens[0];
                        // If L variable, transform to V variable
                        varId = varId.replace("L", "V");
                        String varName = tokens[1];
                        // Special output for pointers
                        // RA=FORM=CH0 => no MI and MA in the .rio file
                        boolean varIsPointer = (tokens[2]
                                .compareTo("RA=FORM=CH0,") == 0);
                        currentTest.addVar(varId, varName, varIsPointer);
                    } else {
                        throw new TdcException(String.format(
                                "%s: malformed observable line: %s",
                                file.getAbsolutePath(), varLine));
                    }
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
     *
     * @param br
     *            the current buffer
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
     *
     * @param token
     *            Token encoding a new test such as T*
     * @return the name of the test
     * @throws TdcException
     *             if ttoken does not exist
     */
    public final String getTestName(final String token) throws TdcException {
        TdcTest test = tests.get(token);
        if (test == null) {
            String message = String.format("%s: test id %s not found",
                    file.getAbsolutePath(), token);
            throw new TdcException(message);
        }

        return test.getName();
    }

    /**
     * Return the name of the test.
     *
     * @param token
     *            Token encoding a new test such as T*
     * @return the name of the test
     * @throws TdcException
     *             if ttoken does not exist
     */
    public final String getTestedServiceName(final String token)
            throws TdcException {
        TdcTest test = tests.get(token);
        if (test == null) {
            throw new TdcException(String.format("%s: test id %s not found",
                    file.getAbsolutePath(), token));
        }
        return test.getServiceName();
    }

    /**
     * Return if the current variable identified by vtoken is a pointer.
     *
     * @param ttoken
     *            token of the current test T*
     * @param vtoken
     *            token of the current variable V*
     * @return vtoken is a pointer
     * @throws TdcException
     *             if ttoken or vtoken do not exist
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
        throw new TdcException(String.format("%s: observable id %s not found",
                file.getAbsolutePath(), vtoken));
    }

    /**
     * Return the name variable identified by vtoken.
     *
     * @param ttoken
     *            token of the current test T*
     * @param vtoken
     *            token of the current variable V*
     * @return name of the variable identified by vtoken
     * @throws TdcException
     *             if ttoken or vtoken do not exist
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
        throw new TdcException(String.format("%s: observable id %s not found",
                file.getAbsolutePath(), vtoken));
    }
}
