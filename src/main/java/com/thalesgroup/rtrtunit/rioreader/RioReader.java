package com.thalesgroup.rtrtunit.rioreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jenkinsci.lib.dtkit.util.validator.ValidationException;

/**
 * .RIO file parser
 */
public class RioReader {

	/**
	 * Validate input RIO file.
	 * 
	 * @param inputRioFile
	 *            input .RIO file
	 * @return true if RIO file is valid
	 */
	public boolean validate(File inputRioFile) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputRioFile));
		} catch (IOException e) {
			String message = " Error opening file " + inputRioFile.getName()
					+ ": " + e.getMessage();
			System.out.println(message);
			throw new ValidationException(message, e);
		}
		RioStructure rioStructure = new RioStructure();
		AtomicInteger lineNb = new AtomicInteger(1);
		try {
			parseLines(reader, rioStructure, lineNb);
		} catch (Exception e) {
			try {
				reader.close();
			} catch (IOException e2) {
			}
			String message = " Error reading file " + inputRioFile.getName()
					+ ", line " + lineNb.get() + ": " + e.getMessage();
			throw new ValidationException(message, e);
		}
		try {
			reader.close();
		} catch (IOException e) {
			String message = " Error closing file " + inputRioFile.getName()
					+ ": " + e.getMessage();
			System.out.println(message);
			throw new ValidationException(message, e);
		}
		return true;
	}

	/**
	 * Read input .RIO file, and return its content: a list of tests, with
	 * eventual failed variables.
	 * 
	 * @param inputRioFile
	 *            input .RIO file
	 * @return test result data contained in .RIO file
	 */
	public RioStructure read(File inputRioFile) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputRioFile));
		} catch (IOException e) {
			System.out.println(" Error opening file " + inputRioFile.getName()
					+ ": " + e.getMessage());
			return null;
		}
		RioStructure rioStructure = new RioStructure();
		AtomicInteger lineNb = new AtomicInteger(1);
		try {
			parseLines(reader, rioStructure, lineNb);
		} catch (Exception e) {
			System.out.println(" Error reading file " + inputRioFile.getName()
					+ ", line " + lineNb.get() + ": " + e.getMessage());
		}
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(" Error closing file " + inputRioFile.getName()
					+ ": " + e.getMessage());
		}
		return rioStructure;
	}

	private void parseLines(final BufferedReader reader,
			final RioStructure rioStructure, final AtomicInteger lineNb)
			throws IOException, RioException {
		String currentLineString = reader.readLine();
		RioTest currentTest = null;
		String stubId = null;
		float timeValue = 0.f;

		while (currentLineString != null) {
			if (currentLineString.startsWith("T")) {
				// Txxx = test bloc beginning
				// Collect previous test then start a new one
				if (currentTest != null) {
					rioStructure.add(currentTest);
				}
				currentTest = new RioTest();
				String[] tokens = currentLineString.split(" ");
				currentTest.setName(tokens[0]);
				timeValue = 0.f;
				stubId = null;
			} else if ((currentTest != null)
					&& currentLineString.startsWith("D")) {
				// Dxx xx = test duration
				String[] tokens = currentLineString.split(" ");
				if (tokens.length < 2) {
					throw new RioException("Expecting one argument on 'D' line");
				}
				timeValue += Float.parseFloat(tokens[1]);
				currentTest.setTime(Float.toString(timeValue));
			} else if (currentLineString.startsWith("M")) {
				// Mxxx xx = stub id
				String[] tokens = currentLineString.split(" ");
				stubId = tokens[0];
			} else if (currentLineString.startsWith("V")) {
				// Vxxx obtainedValue RA=(T|F) [MI=xx MA=xx]
				RioFailedVariable variable = new RioFailedVariable();
				String[] tokens = tokenizeVariableLine(currentLineString);
				// check arguments
				if (tokens.length < 3) {
					throw new RioException("Expecting at least two arguments on 'V' line");
				} else if (!tokens[2].startsWith("RA")) {
					throw new RioException("Expecting RA=[TF] as second argument on 'V' line");
				} else {
					String[] raTokens = tokens[2].split("=");
					if ((raTokens.length != 2)
							|| !raTokens[0].equals("RA")
							|| !(raTokens[1].equals("T") || raTokens[1].equals("F"))) {
						throw new RioException("Expecting RA=[TF] as second argument on 'V' line");
					}
					if (tokens.length == 4) {
						throw new RioException("Missing MA=xx as fifth argument on 'V' line");
					}
				}
				// parse variable result
				variable.setName(tokens[0]);
				variable.setGivenValue(tokens[1]);
				variable.setFailed(tokens[2]);
				if (tokens.length > 4) {
					variable.setExpectedValue(tokens[4]);
				}
				if (variable.isFailed()) {
					if (variable.getName().equals("V0")) {
						// special case:
						// M1 0
						// V0 ...
						// represents stub function call counts
						// => use the stub function id as variable id.
						if (stubId == null) {
							throw new RioException("Missing 'M' line (stub function id)");
						}
						variable.setName(stubId);
					}
					currentTest.addFailedVariables(variable);
				}
			}
			currentLineString = reader.readLine();
			lineNb.incrementAndGet();
		}
		// collect last test
		if (currentTest != null) {
			rioStructure.add(currentTest);
		}
		// verification on the parsed RIO structure
		if (rioStructure.getNbTests() < 1) {
			throw new RioException("File has no test result");
		}
	}
	
	private String[] tokenizeVariableLine(final String line) {
		String trimedLine = line.trim();
		List<String> tokens = new ArrayList<String>();
		String currentToken = null;
		boolean insideDoubleQuotes = false;
		boolean insideQuotes = false;
		for (int i = 0 ; i < trimedLine.length() ; i++) {
			char c = trimedLine.charAt(i);
			if ((currentToken == null) && (c == ' ')) {
				// just a token separator space
			} else {
				// inside a token
				if (currentToken == null) {
					// first token character
					currentToken = new String();
				}
				// check quotes
				if (!insideQuotes && !insideDoubleQuotes && (c == '\'')) {
					// beginning of a quoted string
					insideQuotes = true;
					currentToken += c;
				} else if (!insideQuotes && !insideDoubleQuotes && (c == '"')) {
					// beginning of a double-quoted string
					insideDoubleQuotes = true;
					currentToken += c;
				} else if (insideDoubleQuotes || insideQuotes) {
					// inside a (doubled)quoted token
					currentToken += c;
					if (insideDoubleQuotes && (c == '"')
							|| insideQuotes && (c == '\'')) {
						// final (double)quote
						insideDoubleQuotes = false;
						insideQuotes = false;
					}
				} else {
					// not in a quoted string
					if (c == ' ') {
						// end of token
						tokens.add(currentToken);
						currentToken = null;
					} else {
						// collect character
						currentToken += c;
					}
				}
			}
		}
		// collect final token
		if (currentToken != null) {
			tokens.add(currentToken);
		}
		String[] result = new String[tokens.size()];
		return tokens.toArray(result);
	}
}
