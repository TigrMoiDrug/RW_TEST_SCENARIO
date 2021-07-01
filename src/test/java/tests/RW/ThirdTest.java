package tests.RW;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rwPages.MainPageRW;
import rwPages.TrainSearchResultPage;
import tests.BasicTest;

public class ThirdTest extends BasicTest{

    public static WebDriver driver;
    public static MainPageRW mainPageRW;
    public static TrainSearchResultPage trainSearchResultPage;
    public static String RW_URL = "https://www.rw.by/";

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) throws InterruptedException {
        driver = setup(browserName);

        mainPageRW = new MainPageRW(driver);
        trainSearchResultPage = new TrainSearchResultPage(driver);
        setWait(30, driver);
        maximizeWindow(driver);
        getToURL(RW_URL,driver);
        Thread.sleep(5000);
        validLocationsToFieldsFromToAndDatePlusFiveDays(mainPageRW);
        trainSearchResultPage.consoleWriter();
        Thread.sleep(5000);
        trainSearchResultPage.firstLinkClicker();
    }

    @Test(priority = 1)
    public void nameOfTheTrainIsDisplayed () {
        Assert.assertNotNull(trainSearchResultPage.trainName());
    }

    @Test(priority = 2)
    public void textIsDisplayed () {
        Assert.assertTrue(trainSearchResultPage.getText() != 0);
    }

    @Test(priority = 3)
    public void pageLoadedFine () {
        trainSearchResultPage.logoClick();
        mainPageRW.loadingStatus(driver);
        Assert.assertEquals(mainPageRW.loadingStatus(driver), "complete");
    }

    @AfterClass
    public void stop (){
        driver.quit();
    }

}
