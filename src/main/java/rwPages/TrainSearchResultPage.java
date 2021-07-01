package rwPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrainSearchResultPage {

    public WebDriver driver;

    public TrainSearchResultPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[3]/div/div[3]/div[2]/div/form/div[2]/div[1]/span/input")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='sch-route']/div[3]/div[2]/div[1]/div[3]/div/div/div/div[1]/a/span[2]")
    private List<WebElement> title;

    @FindBy(xpath = "//*[@id='sch-route']/div[3]/div[2]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div[1]/div[1]")
    private List<WebElement> departureTime;

    @FindBy(xpath = "//*[@id='sch-route']/div[3]/div[2]/div[1]/div[3]/div/div[1]/div/div[1]/a/span[2]")
    private WebElement firstLink;

    @FindBy(xpath = "//*[@id='workarea']/div[2]/div[1]/div/div[2]")
    private WebElement trainName;

    @FindBy(xpath = "//*[@id='workarea']/div[2]/div[1]/div/div[2]")
    private List<WebElement> textUnderTrainName;

    @FindBy(xpath = "//*[@id='db']/div[1]/div[1]/div/header/div/div[3]/div[1]/a/span[1]/img")
    private WebElement logo;


// вывод данных в консоль
    public void consoleWriter () {
        System.out.println("Количество рейсов: "+title.size());
        System.out.println();
        for (int i = 0; i < title.size(); i++){
            System.out.println(title.get(i).getText() + " — " + departureTime.get(i).getText());
            System.out.println();
        }
    }

// клик по первой ссылке
    public void firstLinkClicker () {
        firstLink.click();
    }

//возвращает название поезда
    public String trainName () {
        return trainName.getText();
    }

// возвращает текст из таблицы под название поеда
    public int getText () {
        return  textUnderTrainName.size();
    }

// клик по лого для возврата на главную страницу
    public void logoClick () {
        logo.click();
    }

}
