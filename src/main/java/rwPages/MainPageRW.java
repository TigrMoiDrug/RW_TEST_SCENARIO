package rwPages;

import basics.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPageRW extends AbstractPage {

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

    @FindBy(css = "a.ui-state-default")
    private List <WebElement> datePickerTable;

    public MainPageRW(WebDriver driver) {
        super(driver);
    }

    //смена языка
    public MainPageRW switchLanguage () {
        languageIconEng.click();
        return this;
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
    public MainPageRW searchRW (String rd) {
        searchField.sendKeys(rd);
        return this;
    }

//клик по кнопке поискаБЧ
    public MainPageRW clickOnSearchRW () {
        searchFieldButton.click();
        return this;
    }

//клик по иконке дэйтпикера
    public MainPageRW clickDatePickerButton (){
        datePickerButton.click();
        return this;
    }

//список элементов дэйтпикера
    public List<WebElement> getDatePickerTable (){
        return datePickerTable;
    }

//клик по кнопке поиска
    public TrainSearchResultPage searchButtonClick(){
        searchButton.click();
        return new TrainSearchResultPage(driver);
    }
//ввод данных в поле откуда
    public MainPageRW enterTextToFieldFrom(String text){
        fromField.sendKeys(text);
        return this;
    }
//ввод данных в поле куда
    public MainPageRW enterTextToFieldTo(String text){
        toField.sendKeys(text);
        return this;
    }
//клик по дате из дэйтпикера по индексу
    public MainPageRW clickChosenDateInDatePickerByIndex(int index){
        wait.until(ExpectedConditions.elementToBeClickable(datePickerTable.get(index))).click();
        return this;
    }
}
