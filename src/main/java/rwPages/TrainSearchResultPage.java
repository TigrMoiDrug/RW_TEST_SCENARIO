package rwPages;

import basics.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TrainSearchResultPage extends AbstractPage {

    @FindBy(className = "train-route")
    private List<WebElement> title;

    @FindBy(className = "departure")
    private List<WebElement> departureTime;

    @FindBy(xpath = "//*[@id='sch-route']/div[3]//*/span[2]")
    private WebElement firstLink;

    @FindBy(className = "sch-title")
    private WebElement trainName;

    @FindBy(xpath = "//*[@id='workarea']//*/div/div[2]")
    private List<WebElement> textUnderTrainName;

    @FindBy(className = "logo-png")
    private WebElement logo;

    public TrainSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(title.get(0)));
        return title;
    }

    public List<WebElement> getDepartureTime() {
        wait.until(ExpectedConditions.elementToBeClickable(departureTime.get(0)));
        return departureTime;
    }

    // клик по первой ссылке
    public TrainSearchResultPage firstLinkClicker () {
        firstLink.click();
        return this;
    }

//возвращает название поезда
    public String trainName () {
        return trainName.getText();
    }

// возвращает размер текста из таблицы под название поеда
    public int getTextSize () {
        return  textUnderTrainName.size();
    }

// клик по лого для возврата на главную страницу
    public MainPageRW logoClick () {
        logo.click();
        return new MainPageRW(driver);
    }
}
