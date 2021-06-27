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

import java.util.concurrent.TimeUnit;

public class ThirdTest {

    public static WebDriver driver;
    public static MainPageRW mainPageRW;
    public static TrainSearchResultPage trainSearchResultPage;

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) throws InterruptedException {
        BasicTest basicTest = new BasicTest();
        driver = basicTest.starter(browserName);

        mainPageRW = new MainPageRW(driver);
        trainSearchResultPage = new TrainSearchResultPage(driver);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPageRW.getToRW();
        mainPageRW.validLocationsToFieldsFromToAndDate();
        trainSearchResultPage.consoleWriter();
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
