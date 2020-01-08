package com.demo.config;

import com.demo.test_properties.FilePaths;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


/**
 *  This class is used as an main class and point out TestNG
 *  framework  with  XML file.  The  original  XML  file  is
 *  located in project resources folder and after start test_scripts
 *  execution we copy it to the target folder.
 */

public class ConsoleRunner {

    protected static  File xmlFile;
    public static  TestNG  testNG;


    public static void main(String[] args) throws Exception {

        /**
         * Load config.properties file
         */
        InputStream inputStream = new FileInputStream(FilePaths.config_properties_file);
        Properties properties = new Properties();
        properties.load(inputStream);

        String xmlFileName = "testng.xml";
        File xmlFrom = new File(FilePaths.path + "/src/main/resources/xml_files/" + xmlFileName);

        InputStream xmlSuiteStream = ConsoleRunner.class.getResourceAsStream(String.valueOf(xmlFrom));
        List<XmlSuite> suites = (List<XmlSuite>) new Parser(xmlSuiteStream).parse();
        testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.setSourcePath(String.valueOf(FilePaths.path));
        testNG.run();
    }
}
