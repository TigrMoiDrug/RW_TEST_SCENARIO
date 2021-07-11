package tests;

import basics.Printer;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import rwPages.MainPageRW;
import rwPages.SearchFieldResultPageRW;
import rwPages.TrainSearchResultPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class BasicTest{

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

    public static void maximizeWindow (WebDriver driver){
        driver.manage().window().maximize();
    }

    public static void validLocationsToFieldsFromToAndDatePlusFiveDays(MainPageRW mainPageRW) {
        String from = "Минск-Пассажирский";
        String to = "Молодечно";
        int index = 5;
        mainPageRW.enterTextToFieldFrom(from)
                .enterTextToFieldTo(to)
                .clickDatePickerButton()
                .clickChosenDateInDatePickerByIndex(index)
                .searchButtonClick();
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

    //случайные буквы
    public static String randomSymbols(){
        int length = 20;
        return RandomStringUtils.random(length, true, false);
    }

    @AfterClass
    public void stop () {
        driver.quit();
    }

}
