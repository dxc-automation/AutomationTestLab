package com.demo.test_scripts.ui;

import com.demo.ui_mapping.HomePage;
import com.demo.config.BasicConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.awt.*;

import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import static com.demo.test_properties.FilePaths.video_files;
import static org.monte.media.VideoFormatKeys.*;

import static com.demo.config.ReporterConfig.startTestReport;


public class RecordVideo extends BasicConfiguration {


    static final Logger LOG = LogManager.getLogger(RecordVideo.class);
    private static ScreenRecorder screenRecorder;

    private static HomePage homePage  = PageFactory.initElements(driver, HomePage.class);


    private static void report() throws Exception {
        String testName        = "Record Video";
        String testDescription = "The purpose of this test_suites is to verify that the user can log in RAM successfully.";
        String testCategory    = "UI";

        startTestReport(testName, testDescription, testCategory);
    }

    public static void recordVideo() throws Exception {
        report();

        File videoFile = new File(video_files);
        GraphicsConfiguration gconfig = GraphicsEnvironment
                                        .getLocalGraphicsEnvironment()
                                        .getDefaultScreenDevice()
                                        .getDefaultConfiguration();

        screenRecorder = new ScreenRecorder(gconfig, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                                                     new Format(MediaTypeKey,
                                                                    MediaType.VIDEO,
                                                                    EncodingKey,
                                                                    ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                                                    CompressorNameKey,
                                                                    ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                                                                    DepthKey, (int) 24,
                                                                    FrameRateKey,
                                                                    Rational.valueOf(15),
                                                                    QualityKey, 1.0f,
                                                                    KeyFrameIntervalKey, (int) (15 * 60)),
                                                    new Format(MediaTypeKey,
                                                                    MediaType.VIDEO,
                                                                    EncodingKey,
                                                                    "black",
                                                                    FrameRateKey,
                                                                    Rational.valueOf(30)),
                                                         null);
        screenRecorder.start();

        driver.get("https://www.google.com/");

        screenRecorder.stop();
    }
}

