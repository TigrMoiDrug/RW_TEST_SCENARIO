package tests.RW;

import basics.Printer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rwPages.MainPageRW;
import rwPages.TrainSearchResultPage;
import tests.BasicTest;

public class ThirdTest extends BasicTest{

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {

        mainPageRW = new MainPageRW(driver);
        trainSearchResultPage = new TrainSearchResultPage(driver);

        getToURL(RW_URL,driver);

        validLocationsToFieldsFromToAndDatePlusFiveDays(mainPageRW);

        //обновленый вывод в консоль
        Printer.printTitlesAndDepartureTime(trainSearchResultPage.getTitle(),
                trainSearchResultPage.getDepartureTime());

        trainSearchResultPage.firstLinkClicker();
    }

    @Test
    public void test1nameOfTheTrainIsDisplayed () {
        Assert.assertNotNull(trainSearchResultPage.trainName());
    }

    @Test
    public void test2textIsDisplayed () {
        Assert.assertTrue(trainSearchResultPage.getTextSize() != 0);
    }

    @Test
    public void test3pageLoadedFine () {

        trainSearchResultPage.logoClick()
                .loadingStatus(driver);

        Assert.assertEquals(mainPageRW.loadingStatus(driver), "complete");
    }

}
