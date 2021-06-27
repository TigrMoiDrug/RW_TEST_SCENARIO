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

import java.util.concurrent.TimeUnit;

public class FromGoogleTest {

    public static WebDriver driver;
    public static MainPageGoogle mainPageGoogle;
    public static ResultPageGoogle resultPageGoogle;
    public static MainPageRW mainPageRW;

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {
        BasicTest basicTest = new BasicTest();
        driver = basicTest.starter(browserName);

        mainPageGoogle = new MainPageGoogle(driver);
        resultPageGoogle = new ResultPageGoogle(driver);
        mainPageRW = new MainPageRW(driver);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        mainPageGoogle.getToGoogle();
        mainPageGoogle.typeTextToSearchInGoogleSearchLine();
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
