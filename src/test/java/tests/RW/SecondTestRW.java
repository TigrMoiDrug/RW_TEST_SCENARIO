package tests.RW;

import basics.Core;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import tests.BasicTest;
import java.io.UnsupportedEncodingException;

public class SecondTestRW extends BasicTest{

    public static WebDriver driver;
    public static Core basic;
    public static String rd;
    public static MainPageRW mainPageRW;
    public static SearchFieldResultPageRW searchFieldResultPageRW;

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {
        driver = setup(browserName);
        basic = new Core();
        mainPageRW = new MainPageRW(driver);
        searchFieldResultPageRW = new SearchFieldResultPageRW(driver);
        setWait(30, driver);
        maximizeWindow(driver);
        getToURL(RW_URL, driver);
        rd = basic.randomSymbols();
        mainPageRW.searchRW(rd);
        mainPageRW.clickOnSearchRW();
    }

    @Test(priority = 1)
    public void hasURLChanged () throws UnsupportedEncodingException {
        String expectedURL = "https://www.rw.by/search/?s=Y&q="+rd+"";
        setImplicitlyWait(driver);
        Assert.assertEquals(searchFieldResultPageRW.getURL(),expectedURL);
    }

    @Test(priority = 2)
    public void nothingFoundIsDisplayed () {
        setImplicitlyWait(driver);
        String expected = "К сожалению, на ваш поисковый запрос ничего не найдено.";
        Assert.assertEquals(searchFieldResultPageRW.nothingFoundOnRWText(), expected);
    }

    @Test(priority = 3)
    public void linksAreDisplayed() {
        searchFieldResultPageRW.clearSearchLine();
        searchFieldResultPageRW.clickOnSearchRWAfterNothingFound();
        setImplicitlyWait(driver);
        int expected = 15;
        log.info("Количество ссылок: "+ searchFieldResultPageRW.fifteenLinks().size());
        log.info("Тексты ссылок: ");
        for (int i = 0; i < searchFieldResultPageRW.fifteenLinks().size(); i++) {
            System.out.println(searchFieldResultPageRW.fifteenLinks().get(i).getText());
        }
        Assert.assertEquals(searchFieldResultPageRW.fifteenLinks().size(), expected);
    }

    @AfterClass
    public void stop () {
        driver.quit();
    }

}
