package com.thalesgroup.rtrtunit.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class XMLUtil {

    public static String readXmlAsString(File input) throws IOException {
        String xmlString = "";

        if (input == null) {
            throw new IOException("The input stream object is null.");
        }

        FileInputStream fileInputStream = new FileInputStream(input);
        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            xmlString += line + "\n";
            line = bufferedReader.readLine();
        }
        fileInputStream.close();
        fileInputStream.close();
        bufferedReader.close();

        return xmlString;
    }

}
