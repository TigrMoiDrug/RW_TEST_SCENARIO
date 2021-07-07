package tests.RW;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rwPages.MainPageRW;
import rwPages.TrainSearchResultPage;
import tests.BasicTest;

public class ThirdTest extends BasicTest{

    public static String RW_URL = "https://www.rw.by/";

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {
        mainPageRW = new MainPageRW(driver);
        trainSearchResultPage = new TrainSearchResultPage(driver);
        getToURL(RW_URL,driver);
        setImplicitlyWait(driver);
        validLocationsToFieldsFromToAndDatePlusFiveDays(mainPageRW, driver);
        trainSearchResultPage.consoleWriter();
        setImplicitlyWait(driver);
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

}
