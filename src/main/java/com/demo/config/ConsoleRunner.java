package com.demo.config;

import org.testng.TestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.demo.properties.FilePaths.*;


/**
 *  This class is used as an main class and point out TestNG
 *  framework  with  XML file.  The  original  XML  file  is
 *  located in project resources folder and after start scripts
 *  execution we copy it to the target folder.
 */

public class ConsoleRunner {

    protected static File xmlFile;
    private static String testXml;



    public static void main(String[] args) throws Exception {

        // Load properties file
        InputStream inputStream = new FileInputStream(config_properties_file);
        Properties properties   = new Properties();
        properties.load(inputStream);

        // Get value of the property
        testXml = properties.getProperty("xml");
        inputStream.close();

        // Source file
        String xml = testXml + ".xml";

        // Original folder + source file
        File xmlFile = new File(xml_files_folder + xml);

        try {
            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<String>();
            suites.add(xmlFile.toString());
            testng.setTestSuites(suites);
            testng.setOutputDirectory(report_folder);
            testng.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}