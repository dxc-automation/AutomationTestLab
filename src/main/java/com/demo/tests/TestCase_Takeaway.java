package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.demo.scripts.ui.takeaway.OpenHomePage.*;
import static com.demo.scripts.ui.takeaway.SearchRestaurant.*;
import static com.demo.scripts.ui.takeaway.SearchNearestRestaurant.*;
import static com.demo.utilities.user_interface.AlertHandling.*;

public class TestCase_Takeaway extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();


    @Test(description = "WEB")
    public void loadHomePage() throws Exception {
        //  start screen recorder
        videoReord.startRecording();
        openHomePage();
        acceptAlert();
        videoReord.stopRecording();
    }


    @Test(description = "WEB")
    public void search() throws Exception {
        videoReord.startRecording();
        searchRestaurant("8888");
        videoReord.stopRecording();
    }


    @Test(description = "WEB")
    public void selectRestaurant() throws Exception {
        videoReord.startRecording();
        searchNearestRestaurant();
        videoReord.stopRecording();
    }
}