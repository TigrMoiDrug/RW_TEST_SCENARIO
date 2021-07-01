package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import rwPages.MainPageRW;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BasicTest {

    public static String RW_URL = "https://www.rw.by/";

    public static String googleURL = "https://www.google.com/";

    public static WebDriver setup(String browserName){
        WebDriver driver = null;
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public static void getToURL(String googleURL, WebDriver driver) {
        driver.get(googleURL);
    }

    public static void setWait(int sec, WebDriver driver){
        driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
    }

    public void setImplicitlyWait (int sec, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public static void maximizeWindow (WebDriver driver){
        driver.manage().window().maximize();
    }

    public static void validLocationsToFieldsFromToAndDatePlusFiveDays(MainPageRW mainPageRW) throws InterruptedException {
        Date currentDate = new Date();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd");
        String actualDate = currentDateFormat.format(currentDate);
        System.out.println("Today is: " + actualDate);
        mainPageRW.getFromField().sendKeys("Минск-Пассажирский");
        mainPageRW.getToField().sendKeys("Молодечно");
        mainPageRW.clickDatePickerButton();

        for (int i = 0 ; i <  mainPageRW.getDatePickerTable().size(); i++){
            if( mainPageRW.getDatePickerTable().get(i).getText().equals(actualDate)){
                mainPageRW.getDatePickerTable().get(i+5).click();
                break;
            }
        }

        Thread.sleep(1000);
        mainPageRW.searchButtonClick();

    }

}
