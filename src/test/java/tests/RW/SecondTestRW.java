package tests.RW;

import basics.Basic;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import tests.BasicTest;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class SecondTestRW {

    public static WebDriver driver;
    public static Basic basic;
    public static String rd;
    public static MainPageRW mainPageRW;
    public static SearchFieldResultPageRW searchFieldResultPageRW;

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {
        BasicTest basicTest = new BasicTest();
        driver = basicTest.starter(browserName);

        basic = new Basic();
        mainPageRW = new MainPageRW(driver);
        searchFieldResultPageRW = new SearchFieldResultPageRW(driver);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPageRW.getToRW();
        rd = basic.randomSymbols();
        mainPageRW.searchRW(rd);
        mainPageRW.clickOnSearchRW();
    }

    @Test(priority = 1)
    public void hasURLChanged () throws UnsupportedEncodingException {
        String expectedURL = "https://www.rw.by/search/?s=Y&q="+rd+"";
        Assert.assertEquals(searchFieldResultPageRW.getURL(),expectedURL);
    }

    @Test(priority = 2)
    public void nothingFoundIsDisplayed () {
        String expected = "К сожалению, на ваш поисковый запрос ничего не найдено.";
        Assert.assertEquals(searchFieldResultPageRW.nothingFoundOnRWText(), expected);
    }

    @Test(priority = 3)
    public void linksAreDisplayed() {
        searchFieldResultPageRW.clearSearchLine();
        searchFieldResultPageRW.clickOnSearchRWAfterNothingFound();
        int expected = 15;
        Assert.assertEquals(searchFieldResultPageRW.findFifteenLinks(), expected);
    }

    @AfterClass
    public void stop () {
        driver.quit();
    }

}
