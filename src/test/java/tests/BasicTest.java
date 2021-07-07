package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import rwPages.TrainSearchResultPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BasicTest {

    public static WebDriver driver;

    public static MainPageRW mainPageRW;
    public static SearchFieldResultPageRW searchFieldResultPageRW;
    public static TrainSearchResultPage trainSearchResultPage;

    public final  Logger log = Logger.getLogger(String.valueOf(BasicTest.class));

    public static String RW_URL = "https://www.rw.by/";

    public static String googleURL = "https://www.google.com/";

    @BeforeClass
    @Parameters("browserName")
    public static void init (String browserName){
        driver = setup(browserName);
        setWait(driver, 30);
        maximizeWindow(driver);
    }

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

    public static void setWait(WebDriver driver, int sec){
        driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
    }

    public static void setImplicitlyWait (WebDriver driver){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    public static void setImplicitlyWait (WebDriver driver, int sec){
        driver.manage().timeouts().implicitlyWait(sec,TimeUnit.SECONDS);
    }

    public static void setExplicitWaitOnElementBeClickable (String tag){
        WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.elementToBeClickable(By.tagName(tag)));
    }

    public static void maximizeWindow (WebDriver driver){
        driver.manage().window().maximize();
    }

    public static void validLocationsToFieldsFromToAndDatePlusFiveDays(MainPageRW mainPageRW, WebDriver driver) {
        Date currentDate = new Date();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("d");
        String actualDate = currentDateFormat.format(currentDate);
        System.out.println("Today is: " + actualDate);
        mainPageRW.getFromField().sendKeys("Минск-Пассажирский");
        mainPageRW.getToField().sendKeys("Молодечно");
        mainPageRW.clickDatePickerButton();
        setImplicitlyWait(driver);

        for (int i = 0 ; i <  mainPageRW.getDatePickerTable().size(); i++){
            if(mainPageRW.getDatePickerTable().get(i).getText().equals(actualDate)){
                mainPageRW.getDatePickerTable().get(i+5).click();
            }
        }
        mainPageRW.searchButtonClick();

    }
// url в зависимости от переменной окружения ENV
    public static String urlByEnvironment (){

        String dev = "development";
        String integration = "integration";
        String prefix = "src/main/resources/";
        String postfix = ".properties";
        String propertiesName = null;

        String env = System.getenv("ENV");
        Properties prop = new Properties();

        if (env.equals(integration)){
            propertiesName = prefix + integration + postfix;

        }
        else if (env.equals(dev)) {
            propertiesName = prefix + dev + postfix;

        }

        try {
            prop.load(new FileInputStream(propertiesName));

        }

        catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop.getProperty("URL");
    }

    @AfterClass
    public void stop () {
        driver.quit();
    }

}
