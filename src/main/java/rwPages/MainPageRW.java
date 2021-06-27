package rwPages;

import basics.Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageRW extends Basic{

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
    private List <WebElement> dayPickerTable;

    //переход на главную страницу
    public void getToRW () {
        driver.get("https://www.rw.by/");
    }

//смена языка
    public void switchLanguage () {
        languageIconEng.click();
    }

// количество новостей
    public boolean newsBlockChecker () {
        return newsBlock.size() >= 4;
    }

// белорусская железная дорога
    public boolean copyright () {
        String expected = "© 2021 Belarusian Railway";
        return copyright.getText().contains(expected);
    }

//верхняя меню панель сравнение с заданными наименованиями кнопок
    public boolean topButtons () {
        // по условию названия и количество кнопок не совпадают, тест должен упасть
        String pressCenter = "Press Center";
        String timeTable = "Timetable";
        String passengerServices = "Passenger Services";
        String freight = "Freight";
        String corporate = "Corporate";

        String [] searchedButtons = new String[5];
        searchedButtons[0] = pressCenter;
        searchedButtons[1] = timeTable;
        searchedButtons[2] = passengerServices;
        searchedButtons[3] = freight;
        searchedButtons[4] = corporate;

        int counterOfFoundMatches = 0;

        for (int i = 0; i < topButtons.size(); i++){
            for (int j = 0; j < searchedButtons.length; j++){
                if (topButtons.get(i).getText().equalsIgnoreCase(searchedButtons[j])){
                    counterOfFoundMatches++;
                }
            }
        }
        System.out.println("Количество кнопок на странице: "+ topButtons.size());
        System.out.println("Количество совпадений с условием: "+counterOfFoundMatches);
        return counterOfFoundMatches == 5;
    }

//поиск на БЧ
    public void searchRW (String rd) {
        searchField.sendKeys(rd);
    }

//клик по кнопке поискаБЧ
    public void clickOnSearchRW () {
        searchFieldButton.click();
    }

//ввод данных в поля поиска вручную
    /*
    public void validLocationsToFieldsFromToAndDate() {
        GregorianCalendar currentDate = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        fromField.sendKeys("Минск-Пассажирский");
        toField.sendKeys("Молодечно");

        currentDate.add(Calendar.DAY_OF_MONTH, 5);
        format.setTimeZone(currentDate.getTimeZone());

        dateField.sendKeys(format.format(currentDate.getTime()));
        dateField.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchButton.click();
    }
   */

    // ввод данных в поле поиска, используя DatePicker
    public void validLocationsToFieldsFromToAndDate() throws InterruptedException {
        Date currentDate = new Date();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd");
        String actualDate = currentDateFormat.format(currentDate);
        System.out.println("Today is: " + actualDate);
        fromField.sendKeys("Минск-Пассажирский");
        toField.sendKeys("Молодечно");
        datePickerButton.click();
        for (int i = 0 ; i < dayPickerTable.size(); i++){
            if(dayPickerTable.get(i).getText().equals(actualDate)){
                dayPickerTable.get(i+5).click();
                break;
            }
        }
        Thread.sleep(5000);
        searchButton.click();
    }

}
