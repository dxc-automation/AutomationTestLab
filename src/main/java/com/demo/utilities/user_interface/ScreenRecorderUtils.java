package com.demo.utilities.user_interface;

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
import static org.monte.screenrecorder.ScreenRecorder.State.*;

import static com.demo.config.ReporterConfig.startTestReport;


public class ScreenRecorderUtils extends BasicConfiguration {

    private static final Logger LOG = LogManager.getLogger(ScreenRecorderUtils.class);
    private static ScreenRecorder screenRecorder;


    public static void recordVideo() throws Exception {
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
    }

    //*
    *
    public static void startScreenRecord() throws Exception {
        screenRecorder.start();
    }

    public static void stopScreenRecord() throws Exception {
        screenRecorder.stop();
    }
}

