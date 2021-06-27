package tests.RW;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import tests.BasicTest;

import java.util.concurrent.TimeUnit;

public class FirstRWTest {

    public static WebDriver driver;
    public static MainPageRW mainPageRW;

    @BeforeClass
    @Parameters("browserName")
    public static void setup(String browserName){
        BasicTest basicTest = new BasicTest();
        driver = basicTest.starter(browserName);

        mainPageRW = new MainPageRW(driver);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPageRW.getToRW();
        mainPageRW.switchLanguage();
    }

    @Test(priority = 1)
    public void notLessThanFourNewsDisplayed () {
        System.out.println(mainPageRW.newsBlockChecker());
        Assert.assertTrue(mainPageRW.newsBlockChecker());
    }

    @Test(priority = 2)
    public void belorussianRailwayIsDisplayed () {
        Assert.assertTrue(mainPageRW.copyright());
    }

    @Test(priority = 3)
    public void theNumberOfButtonsIsCorrect () {
        Assert.assertTrue(mainPageRW.topButtons());
    }

    @AfterClass
    public void stop (){
        driver.quit();
    }

}
