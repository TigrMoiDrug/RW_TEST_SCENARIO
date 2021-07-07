package tests.Google;

import googlePages.MainPageGoogle;
import googlePages.ResultPageGoogle;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import rwPages.MainPageRW;
import tests.BasicTest;

public class FromGoogleTest extends BasicTest{

    public static WebDriver driver;
    public static MainPageGoogle mainPageGoogle;
    public static ResultPageGoogle resultPageGoogle;
    public static MainPageRW mainPageRW;
    public static String textToSearch = "белорусская железная дорога";
    public static String urlByEnv = urlByEnvironment();

    @BeforeClass
    @Parameters("browserName")
    public static void starter (String browserName){
        driver = setup(browserName);

        mainPageGoogle = new MainPageGoogle(driver);
        resultPageGoogle = new ResultPageGoogle(driver);
        mainPageRW = new MainPageRW(driver);
// Вот новый вариант перехода за заданный урл в зависимости от среды
        getToURL(urlByEnv, driver);
        setWait(30, driver);

        maximizeWindow(driver);

        mainPageGoogle.typeTextInGoogleSearchLine(textToSearch);

        setImplicitlyWait(driver);

        mainPageGoogle.clickGoogleSearchButton();
        resultPageGoogle.clickOnGoogleLink();

    }

    @Test
    public void mainPageLoadedFine(){
        Assert.assertEquals(mainPageRW.loadingStatus(driver), "complete");
    }

    @AfterClass
    public void stop (){
        driver.quit();
    }

}
