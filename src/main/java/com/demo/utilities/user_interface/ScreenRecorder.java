package com.demo.utilities.user_interface;

import com.demo.config.BasicConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.awt.*;

import org.monte.media.math.Rational;
import org.monte.media.Format;

import static com.demo.test_properties.FilePaths.video_files;
import static org.monte.media.VideoFormatKeys.*;


public class ScreenRecorder extends BasicConfiguration {

    private static final Logger LOG = LogManager.getLogger(ScreenRecorder.class);
    private static org.monte.screenrecorder.ScreenRecorder screenRecorder;


    public static void recordVideo() throws Exception {
        File videoFile = new File(video_files);
        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new org.monte.screenrecorder.ScreenRecorder(gconfig, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
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


    public static void startScreenRecord() throws Exception {
        screenRecorder.start();
    }

    public static void stopScreenRecord() throws Exception {
        screenRecorder.stop();
    }
}

