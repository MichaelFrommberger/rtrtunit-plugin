package com.thalesgroup.rtrtunit.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.custommonkey.xmlunit.Diff;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.thalesgroup.rtrtunit.converter.XMLUtil;

/**
 * @author Sebastien Barbier
 * @version 1.0
 *
 */
public class JUnitOutputTest {
    
    
    @Test
    public void test() {
        
       File outputXMLFile = null; 
       final String expectedResultPath = "junit-result.xml";
        
       Marshaller marshaller = null;
       ObjectFactory objFactory = null;
       
       try {
           // WARNING: 2nd argument is necessary to instaure context. Without
           // the relative path is unknown and throwed Exception.
           outputXMLFile = File.createTempFile("result", "xml");
           
           JAXBContext jaxbContext = JAXBContext.newInstance(
                   "com.thalesgroup.rtrtunit.junit", this.getClass()
                           .getClassLoader());

           marshaller = jaxbContext.createMarshaller();
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

           objFactory = new ObjectFactory();
       } catch (JAXBException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       } catch (IOException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
       
       Testsuites testsuites = (Testsuites) objFactory.createTestsuites();

       Testsuite testsuite = objFactory.createTestsuite();
       testsuite.setName("test");
       Assert.assertEquals("test", testsuite.getName());
       testsuite.setTests("2");
       Assert.assertEquals("2", testsuite.getTests());
       testsuite.setFailures("1");
       Assert.assertEquals("1", testsuite.getFailures());
       testsuite.setErrors("1");
       Assert.assertEquals("1", testsuite.getErrors());
       testsuite.setTime("10");
       Assert.assertEquals("10", testsuite.getTime());
       testsuite.setSkipped("false");
       Assert.assertEquals("false", testsuite.getSkipped());
       testsuite.setTimestamp("12:00:31");
       Assert.assertEquals("12:00:31",testsuite.getTimestamp());
       testsuite.setHostname("toto");
       Assert.assertEquals("toto", testsuite.getHostname());
       testsuite.setId("1");
       Assert.assertEquals("1", testsuite.getId());
       testsuite.setPackage("paquet");
       Assert.assertEquals("paquet", testsuite.getPackage());
       
       
       Properties properties = objFactory.createProperties();
       Property property = objFactory.createProperty();
       property.setName("property");
       Assert.assertEquals("property",property.getName());
       property.setValue("null");
       Assert.assertEquals("null", property.getValue());
       properties.getProperty().add(property);
       testsuite.setProperties(properties);
       Assert.assertNotNull(testsuite.getProperties());
       
       
       Testcase testcaseError = objFactory.createTestcase();
       testcaseError.setName("1");
       Assert.assertEquals("1", testcaseError.getName());
       testcaseError.setAssertions("true");
       Assert.assertEquals("true", testcaseError.getAssertions());
       testcaseError.setTime("9");
       Assert.assertEquals("9", testcaseError.getTime());
       testcaseError.setClassname("toto");
       Assert.assertEquals("toto", testcaseError.getClassname());
       Error error = objFactory.createError();
       error.setType("erreur");
       Assert.assertEquals("erreur", error.getType());
       error.setMessage("grosse erreur");
       Assert.assertEquals("grosse erreur", error.getMessage());
       error.setContent("Error");
       Assert.assertEquals("Error", error.getContent());
       testcaseError.getError().add(error);
       testsuite.getTestcase().add(testcaseError);
       Assert.assertNotNull(testsuite.getTestcase());
       
       
       Testcase testcaseFailure = objFactory.createTestcase();
       testcaseFailure.setName("2");
       testcaseFailure.setAssertions("true");
       testcaseFailure.setTime("1");
       testcaseFailure.setClassname("toto");
       Failure failure = objFactory.createFailure();
       failure.setType("echec");
       Assert.assertEquals("echec",failure.getType());
       failure.setMessage("petit echec");
       Assert.assertEquals("petit echec", failure.getMessage());
       failure.setContent("Failure");
       Assert.assertEquals("Failure", failure.getContent());
       testcaseFailure.getFailure().add(failure);
       testsuite.getTestcase().add(testcaseFailure);
       Assert.assertNotNull(testsuite.getTestcase());
       
       testsuite.setSystemErr("il y a une erreur");
       Assert.assertNotNull(testsuite.getSystemErr());
       testsuite.setSystemOut("il y a un echec");
       Assert.assertNotNull(testsuite.getSystemOut());
       
       try {
           // XML and JAXB
           testsuites.getTestsuite().add(testsuite);
           marshaller.marshal(testsuites, new FileOutputStream(outputXMLFile));

       } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       } catch (JAXBException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
       
       Diff myDiff = null;
    try {
        myDiff = new Diff(XMLUtil.readXmlAsString(new File(this.getClass()
                   .getResource(expectedResultPath).toURI())), XMLUtil
                   .readXmlAsString(outputXMLFile));
    } catch (SAXException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    } catch (URISyntaxException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
       Assert.assertTrue("Building did not work" + myDiff, myDiff.similar()); 
       outputXMLFile.deleteOnExit();    
    }
    
   

}
