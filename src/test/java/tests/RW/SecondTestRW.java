package tests.RW;

import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import tests.BasicTest;
import java.io.UnsupportedEncodingException;

public class SecondTestRW extends BasicTest{

    public static String rd;
    public static String place = "Санкт-петербург";

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName) {


        mainPageRW = new MainPageRW(driver);
        searchFieldResultPageRW = new SearchFieldResultPageRW(driver);

        getToURL(RW_URL, driver);

        rd = randomSymbols();

        mainPageRW.searchRW(rd)
                .clickOnSearchRW();
    }

    @Test
    public void test1hasURLChanged () {
        String expectedURL = "https://www.rw.by/search/?s=Y&q="+rd+"";
        Assert.assertEquals(searchFieldResultPageRW.getURL(),expectedURL);

    }

    @Test
    public void test2nothingFoundIsDisplayed () {
        String expected = "К сожалению, на ваш поисковый запрос ничего не найдено.";
        Assert.assertEquals(searchFieldResultPageRW
                .getTextFromElement(searchFieldResultPageRW.getNothingFoundOnRW()), expected);
    }

    @Test
    public void test3linksAreDisplayed() {

        searchFieldResultPageRW.clearSearchLine(place)
                .clickOnSearchRWAfterNothingFound();

        int expected = 15;
        log.info("Количество ссылок: "+ searchFieldResultPageRW.fifteenLinks().size());
        log.info("Тексты ссылок: ");
        for (int i = 0; i < searchFieldResultPageRW.fifteenLinks().size(); i++) {
            log.info(searchFieldResultPageRW.fifteenLinks().get(i).getText());
        }
        Assert.assertEquals(searchFieldResultPageRW.fifteenLinks().size(), expected);
    }

}
