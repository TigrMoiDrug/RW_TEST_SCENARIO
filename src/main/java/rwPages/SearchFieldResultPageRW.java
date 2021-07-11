package rwPages;

import basics.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

public class SearchFieldResultPageRW extends AbstractPage {

    public final Logger log = Logger.getLogger(String.valueOf(SearchFieldResultPageRW.class));

    @FindBy(className = "notetext")
    private WebElement nothingFoundText;

    @FindBy(id = "searchinpm")
    private WebElement searchLine;

    @FindBy(xpath = "//*[@id='sform']/*/input")
    private WebElement searchButton;

    @FindBy(className = "search-preview")
    private List <WebElement> links;

    public SearchFieldResultPageRW(WebDriver driver) {
        super(driver);
    }

    //получить урл после введения текста
    public String getURL() {
         driver.getCurrentUrl();
         return URLDecoder.decode( driver.getCurrentUrl(), StandardCharsets.UTF_8);
    }

// проверка сообщения "по запросу ничего не найдено"
    public WebElement getNothingFoundOnRW() {
        return nothingFoundText;
    }

// поиска и введение нового текста в поисковую строку
    public SearchFieldResultPageRW clearSearchLine(String place) {
        searchLine.clear();
        searchLine.sendKeys(place);
        return this;
    }

// кли по кнопке начать поиск
    public SearchFieldResultPageRW clickOnSearchRWAfterNothingFound() {
        searchButton.click();
        return this;
    }

//15 результатов на странице поиска и их текст
    public List<WebElement> fifteenLinks() {
        return links;
    }
}
