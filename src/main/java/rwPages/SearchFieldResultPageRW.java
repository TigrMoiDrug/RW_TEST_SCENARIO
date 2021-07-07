package rwPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class SearchFieldResultPageRW {

    public WebDriver driver;

    public SearchFieldResultPageRW (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "notetext")
    private WebElement nothingFoundText;

    @FindBy(id = "searchinpm")
    private WebElement searchLine;

    @FindBy(xpath = "//*[@id='sform']/*/input")
    private WebElement searchButton;

    @FindBy(className = "search-preview")
    private List <WebElement> links;

//получить урл после введения текста
    public String getURL() throws UnsupportedEncodingException {
         driver.getCurrentUrl();
         return URLDecoder.decode( driver.getCurrentUrl(), "UTF-8" );
    }

// проверка сообщения "по запросу ничего не найдено"
    public String nothingFoundOnRWText() {
        System.out.println(nothingFoundText.getText());
        return nothingFoundText.getText();
    }

// поиска и введение нового текста в поисковую строку
    public void clearSearchLine() {
        searchLine.clear();
        searchLine.sendKeys("Санкт-петербург");
    }

// кли по кнопке начать поиск
    public void clickOnSearchRWAfterNothingFound() {
        searchButton.click();
    }

//15 результатов на странице поиска и их текст
    public List<WebElement> fifteenLinks() {
        return links;
    }
}
