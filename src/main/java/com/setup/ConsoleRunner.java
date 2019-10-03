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

    public static String host;
    public static String ramHost;
    public static String walletHost;
    public static String scheme;
    public static File   xmlFile;
    private static String testXml;

    public static String username;
    public static String password;
    public static String sign;
    public static String devIx;


    public static void main(String[] args) throws Exception {

        File path = new File(System.getProperty("user.dir"));


        // Load properties file
        InputStream inputStream = new FileInputStream(path + "/src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        // Get value of the property
        testXml = properties.getProperty("xml");
        scheme  = properties.getProperty("scheme");
        host    = properties.getProperty("host");
        ramHost = properties.getProperty("ram_host");
        walletHost = properties.getProperty("wallet_host");

        username  = properties.getProperty("username");
        password  = properties.getProperty("password");
        sign      = properties.getProperty("signature");
        devIx     = properties.getProperty("devIx");
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
