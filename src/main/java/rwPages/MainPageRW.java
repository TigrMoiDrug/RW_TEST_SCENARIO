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

    @FindBy(linkText = "ENG")
    private WebElement languageIconEng;

    @FindBy(tagName = "dt")
    private List <WebElement> newsBlock;

    @FindBy(className = "copyright")
    private WebElement copyright;

    @FindBy(xpath = "//*[@id='main_menu']//*/td")
    private List<WebElement> topButtons;

    @FindBy(id = "searchinp")
    private WebElement searchField;

    @FindBy(tagName = "button")
    private WebElement searchFieldButton;

    @FindBy(className = "std-button")
    private WebElement searchButton;

    @FindBy(id = "acFrom")
    private WebElement fromField;

    @FindBy(id = "acTo")
    private WebElement toField;

    @FindBy(id = "yDate")
    private WebElement dateField;

    @FindBy(className = "calendar")
    private WebElement datePickerButton;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']//*/a")
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
