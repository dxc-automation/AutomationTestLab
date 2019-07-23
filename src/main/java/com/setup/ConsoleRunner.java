package com.setup;

import org.apache.commons.io.FileUtils;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static com.setup.BasicSetup.filePath;

/**
 *  This class is used as an main class and point out TestNG
 *  framework  with  XML file.  The  original  XML  file  is
 *  located in project resources folder and after start test
 *  execution we copy it to the target folder.
 */

public class ConsoleRunner {

    public static File xmlFile;
    public static String scheme;
    public static String host;


    public static void main(String[] args) throws Exception {

        // Load properties file
        InputStream inputStream = new FileInputStream(filePath  + "/src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        // Get value of the property
        String testXml = properties.getProperty("xml");
        scheme = properties.getProperty("scheme");
        host = properties.getProperty("host");
        inputStream.close();

        // Source file
        String xml = testXml + ".xml";

        // Original folder + source file
        File from = new File(filePath + "/src/main/resources/xml_files/" + xml);

        // Destination folder
        File to = new File(filePath + "/target/");

        // Destination folder + source file
        File xmlCopied = new File(filePath + "/target/" + xml);

        // Destination folder + renamed file
        xmlFile = new File(filePath + "/" + "target/testng.xml");

        FileUtils.copyFileToDirectory(from, to);
        boolean isRenamed = xmlCopied.renameTo(xmlFile);

        try {
            InputStream xmlSuiteStream = ConsoleRunner.class.getResourceAsStream(from.toString());
            List<XmlSuite> suite = (List<XmlSuite>) new Parser(xmlSuiteStream).parse();

            TestNG testng = new TestNG();
            testng.setXmlSuites(suite);
            testng.setOutputDirectory(filePath + "/" + "report/");
            testng.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
