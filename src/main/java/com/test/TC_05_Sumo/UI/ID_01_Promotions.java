package com.test.TC_05_Sumo.UI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.pages.SumoLoginPage;
import com.pages.SumoPromotionsPage;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.GetImageCompare;
import com.setup.WebDriverUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;

import static com.pages.SumoLoginPage.*;
import static com.pages.SumoPromotionsPage.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;

public class ID_01_Promotions extends BasicSetup {

    private static BufferedImage bufferedImage;
    private static Point point;
    private static int height;
    private static int width;
    public static String actualImage;
    public static String expectedImage;
    public static String imageFile;


    private WebElement removeBtn;
    private WebElement cloneBtn;
    private WebElement copySegmentsBtn;
    private WebElement removeSegmentsBtn;
    private WebElement deselectBtn;



    // Start report session
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[TC_01] Promotions Page", "<pre>dhjbsabhdashjda</pre>");
        test.assignAuthor("SUMO");
        test.assignCategory("Front-End");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }



    @Test
    public void openSumoHomePage() throws Exception {
        PageFactory.initElements(driver, SumoLoginPage.class);
        //***   Go to web page
        driver.get("https://sumo-qa.pokerstarsdev.com/matrix/promotions");

        //***   Resize browser window
        driver.manage().window().maximize();

        input_UserName.sendKeys("test1");
        input_Password.sendKeys("test1");
        btn_Login.click();

        Thread.sleep(1000);

        //***   Verify that the redirection is correct
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://sumo-qa.pokerstarsdev.com/matrix/promotions");

    }

    @Test
    public void noSelectionBtnStates(Method testMethod) throws Exception {
        PageFactory.initElements(driver, SumoPromotionsPage.class);

        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //***   Set the screenshot file name
        imageFile = testMethod.getName();

        //***   Take screenshot and store it into "Screenshots/Actual/method_name.png"
        takeScreenshot(driver, "promotions");

        //***   Localize the element and crop it from the screenshot
        BufferedImage img = ImageIO.read(screenshotFile);
        point = toolbarPage.getLocation();
        width = toolbarPage.getSize().getWidth();
        height = toolbarPage.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/actualTableHeader.png"));

        //***   Compare the actual screenshot with file from data base
        actualImage = filePath + "/" + "Screenshots/Actual/actualTableHeader.png";
        expectedImage = filePath + "/" + "Screenshots/Expected/expectedTableHeader.png";

        GetImageCompare.GetCompare();



        boolean removeButton    = WebDriverUtils.isClickable(toolbarBtn_Remove);
        boolean cloneButton     = WebDriverUtils.isClickable(toolbarBtn_Clone);
        boolean copySgmButton   = WebDriverUtils.isClickable(toolbarBtn_CopySegments);
        boolean removeSgmButton = WebDriverUtils.isClickable(toolbatBtn_RemoveSegments);
        boolean deselectButton  = WebDriverUtils.isClickable(toolbarBtn_DeselectSegments);

        if (removeButton == false || cloneButton == false || copySgmButton == false || removeSgmButton == false || deselectButton == false) {
            test.pass("Toolbar buttons has correct states when there is no selection");
        }
        else if (removeButton == true || cloneButton == true || copySgmButton == true || removeSgmButton == true || deselectButton == true) {
            test.fail("Toolbar buttons has incorrect states when there is no selection", MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
    }

    @Test
    public void selectionBtnStates() throws Exception {
    }
}