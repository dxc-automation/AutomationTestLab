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

    boolean addBtn       = WebDriverUtils.isClickable(toolbarBtn_Add);
    boolean removeBtn    = WebDriverUtils.isClickable(toolbarBtn_Remove);
    boolean cloneBtn     = WebDriverUtils.isClickable(toolbarBtn_Clone);
    boolean copySgmBtn   = WebDriverUtils.isClickable(toolbarBtn_CopySegments);
    boolean removeSgmBtn = WebDriverUtils.isClickable(toolbatBtn_RemoveSegments);
    boolean deselectBtn  = WebDriverUtils.isClickable(toolbarBtn_DeselectSegments);



    // Start report session
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                  "[TC_01] Promotions Page",
                  "<pre>"
                             + "D E S C R I P T I O N<br/>"
                             + "[1] openSumoHomePage:    Verify that login functionality and redirection are working properly.<br/>"
                             + "[2] noSelectionBtnState: Verify that only 'Add' button is enabled when there are no selected checkboxes.<br/>"
                             + "[3] selectionBtnState:   Verify that only 'Deselect Expires' button is disabled when there is a one selected checkbox.<br/>"
                             + "[4] checkTable:          Verify that the table column numbers and names are correct."
                             + "</pre>");
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
    public void noSelectionBtnState(Method testMethod) throws Exception {
        PageFactory.initElements(driver, SumoPromotionsPage.class);

        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 15);

        //***   Set the screenshot file name
        imageFile = testMethod.getName();

        //***   Take screenshot and store it into "Screenshots/Actual/method_name.png"
        takeScreenshot(driver, "promotions_no_selection");
        File img_NoSelection = screenshotFile;

        //***   Localize the element and crop it from the screenshot
        BufferedImage img = ImageIO.read(img_NoSelection);
        point = toolbarPage.getLocation();
        width = toolbarPage.getSize().getWidth();
        height = toolbarPage.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/toolbar_no_selection.png"));


        //***   Compare the actual screenshot with file from data base
        actualImage = filePath + "/" + "Screenshots/Actual/toolbar_no_selection.png";
        expectedImage = filePath + "/" + "Screenshots/Expected/toolbar_no_selection.png";

        GetImageCompare.GetCompare();


        //***   Check the state of the toolbar buttons
        if (addBtn == true && removeBtn == false && cloneBtn == false && copySgmBtn == false && removeSgmBtn == false && deselectBtn == false) {
            test.pass("Toolbar buttons has correct states when there is no selection");
        }
        else if (addBtn == true && removeBtn == true && cloneBtn == true && copySgmBtn == true && removeSgmBtn == true && deselectBtn == true) {
            test.fail("Toolbar buttons has incorrect states when there is no selection", MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
    }

    @Test
    public void selectionBtnState() throws Exception {
        tableCheckBox_Row1.click();
        takeScreenshot(driver, "selection");


        takeScreenshot(driver, "promotions_select_one");
        File img_Selection = screenshotFile;

        //***   Localize the element and crop it from the screenshot
        BufferedImage img = ImageIO.read(img_Selection);
        point = toolbarPage.getLocation();
        width = toolbarPage.getSize().getWidth();
        height = toolbarPage.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/toolbar_select_one.png"));


        //***   Compare the actual screenshot with file from data base
         actualImage = filePath + "/" + "Screenshots/Actual/toolbar_select_one.png";
         expectedImage = filePath + "/" + "Screenshots/Expected/toolbar_select_one.png";

         GetImageCompare.GetCompare();

        //***   Check the state of the toolbar buttons
        if (addBtn == true && removeBtn == true && cloneBtn == true && copySgmBtn == true && removeSgmBtn == true && deselectBtn == false) {
            test.pass("Toolbar buttons has correct states when there is selected checkbox");
        }
        else if (addBtn == false && removeBtn == false && cloneBtn == false && copySgmBtn == false && removeSgmBtn == false && deselectBtn == true) {
            test.fail("Toolbar buttons has incorrect states when there is no selection", MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
    }

    @Test
    public void checkTable() throws Exception {
        BufferedImage img = ImageIO.read(screenshotFile);
        point = header_Table.getLocation();
        width = header_Table.getSize().getWidth();
        height = header_Table.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/table_header.png"));

        //***   Compare the actual screenshot with file from data base
        actualImage = filePath + "/" + "Screenshots/Actual/table_header.png";
        expectedImage = filePath + "/" + "Screenshots/Expected/table_header.png";

        GetImageCompare.GetCompare();

    }
}