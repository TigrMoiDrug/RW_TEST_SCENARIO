package rwPages;

import basics.Core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class MainPageRW extends Core {

    public WebDriver driver;

    public MainPageRW (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[2]/div[2]/div[3]/div[1]/div[4]/a")
    private WebElement languageIconEng;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[3]/div/div[4]/div[2]/div/div[2]/div/dl/dt")
    private List <WebElement> newsBlock;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[4]/div[1]")
    private WebElement copyright;

    @FindBy(xpath = "//*[@id='main_menu']/div/table/tbody/tr/td")
    private List<WebElement> topButtons;

    @FindBy(xpath = "//*[@id='searchinp']")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div[2]/div[2]/div[3]/div[2]/form/button")
    private WebElement searchFieldButton;

    @FindBy(xpath = "//*[@id='fTickets']/div[2]/div[1]/span/input")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='acFrom']")
    private WebElement fromField;

    @FindBy(xpath = "//*[@id='acTo']")
    private WebElement toField;

    @FindBy(xpath = "//*[@id='yDate']")
    private WebElement dateField;

    @FindBy(className = "calendar")
    private WebElement datePickerButton;

    @FindBy(className = "ui-state-default")
    private List <WebElement> datePickerTable;

//смена языка
    public void switchLanguage () {
        languageIconEng.click();
    }

// количество новостей
    public int getNewsBlockSize () {
        return newsBlock.size();
    }

// получаем текст копирайта
    public String getCopyright () {
        return copyright.getText();
    }

//верхняя меню панель
    public List<WebElement> topButtons() {
        return topButtons;
    }

//поиск на БЧ
    public void searchRW (String rd) {
        searchField.sendKeys(rd);
    }

//клик по кнопке поискаБЧ
    public void clickOnSearchRW () {
        searchFieldButton.click();
    }

//поле откуда
    public WebElement getFromField (){
        return fromField;
    }

//поле куда
    public WebElement getToField (){
        return toField;
    }

//клик по иконке дэйтпикера
    public void clickDatePickerButton (){
        datePickerButton.click();
    }

//список элементов дэйтпикера
    public List<WebElement> getDatePickerTable (){
        return datePickerTable;
    }

//клик по кнопке поиска
    public void searchButtonClick(){
        searchButton.click();
    }

}
