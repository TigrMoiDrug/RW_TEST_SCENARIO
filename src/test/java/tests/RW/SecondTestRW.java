package tests.RW;

import basics.Core;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import tests.BasicTest;
import java.io.UnsupportedEncodingException;

public class SecondTestRW extends BasicTest{

    public static Core basic;
    public static String rd;
    public static String place = "Санкт-петербург";

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {
        basic = new Core();
        mainPageRW = new MainPageRW(driver);
        searchFieldResultPageRW = new SearchFieldResultPageRW(driver);
        getToURL(RW_URL, driver);
        rd = basic.randomSymbols();
        mainPageRW.searchRW(rd);
        // Выключение неявного ожидания
        setImplicitlyWait(driver, 0);
        // Включение явного ожидания
        setExplicitWaitOnElementBeClickable("button");
        mainPageRW.clickOnSearchRW();
        // Включение неявного ожидания
        setImplicitlyWait(driver);
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
        searchFieldResultPageRW.clearSearchLine(place);
        searchFieldResultPageRW.clickOnSearchRWAfterNothingFound();
        int expected = 15;
        log.info("Количество ссылок: "+ searchFieldResultPageRW.fifteenLinks().size());
        log.info("Тексты ссылок: ");
        for (int i = 0; i < searchFieldResultPageRW.fifteenLinks().size(); i++) {
            log.info(searchFieldResultPageRW.fifteenLinks().get(i).getText());
        }
        Assert.assertEquals(searchFieldResultPageRW.fifteenLinks().size(), expected);
    }

}
