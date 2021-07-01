package tests.Google;

import googlePages.MainPageGoogle;
import googlePages.ResultPageGoogle;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rwPages.MainPageRW;
import tests.BasicTest;


public class FromGoogleTest extends BasicTest{

    public static WebDriver driver;
    public static MainPageGoogle mainPageGoogle;
    public static ResultPageGoogle resultPageGoogle;
    public static MainPageRW mainPageRW;
    public static String textToSearch = "белорусская железная дорога";

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) throws InterruptedException{
        driver = setup(browserName);

        mainPageGoogle = new MainPageGoogle(driver);
        resultPageGoogle = new ResultPageGoogle(driver);
        mainPageRW = new MainPageRW(driver);

        getToURL(googleURL, driver);
        setWait(30, driver);

        maximizeWindow(driver);

        mainPageGoogle.typeTextInGoogleSearchLine(textToSearch);

        Thread.sleep(5000);

        mainPageGoogle.clickGoogleSearchButton();
        resultPageGoogle.clickOnGoogleLink();

    }

    @Test
    public void mainPageLoadedFine(){
        Assert.assertEquals(mainPageRW.loadingStatus(driver), "complete");
    }

    @AfterClass
    public void stop (){
        driver.quit();
    }

}
