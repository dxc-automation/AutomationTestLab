package com.demo.properties;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePaths {

    //  Get project main dir
    public static Path path = Paths.get(new File(System.getProperty("user.dir")).getParent());




    //  * * * *    F I L E   P A T H S
    public final static String report_json_folder          = path + "/report/JSON/";
    public final static String report_folder               = path + "/report/";
    public final static String report_html_file            = path + "/report/TestReport.html";
    public final static String report_config_xml_file      = path + "/src/main/resources/extent-config.xml";
    public final static String xml_files_folder            = path + "/src/main/resources/xml_files/";

    public final static String screenshots_failed_folder   = path + "/report/Screenshots/Failed/";
    public final static String screenshots_actual_folder   = path + "/report/Screenshots/Actual/";
    public final static String screenshots_buffer_folder   = path + "/report/Screenshots/Buffer/";
    public final static String screenshots_expected_folder = path + "/report/Screenshots/Expected/";
    public final static String video_files                 = path + "/report/video/";

    public final static String config_properties_file      = path + "/src/main/resources/config.properties";
    public final static String chrome_driver_file          = path + "/src/main/resources/drivers/chromedriver.exe";
    public final static String firefox_driver_file         = path + "/src/main/resources/drivers/geckodriver.exe";

    public final static String cookies_file                = path + "/report/cookies/";


}


