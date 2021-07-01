package tests.RW;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import tests.BasicTest;

public class FirstRWTest extends BasicTest{

    public static WebDriver driver;
    public static MainPageRW mainPageRW;
    public static String expected = "© 2021 Belarusian Railway";

    @BeforeClass
    @Parameters("browserName")
    public static void starter(String browserName){
        driver = setup(browserName);
        mainPageRW = new MainPageRW(driver);
        setWait(30, driver);
        maximizeWindow(driver);
        getToURL(RW_URL, driver);
        mainPageRW.switchLanguage();
    }

//проверка количества новостей в блоке
    @Test(priority = 1)
    public void notLessThanFourNewsDisplayed () {
        System.out.println("Количество новостей в блоке -" + mainPageRW.getNewsBlockSize());
        Assert.assertTrue(mainPageRW.getNewsBlockSize() >= 4);
    }
// Текст копирайта
    @Test(priority = 2)
    public void belorussianRailwayIsDisplayed () {
        Assert.assertTrue(mainPageRW.getCopyright().contains(expected));
    }

//В этом тесте фактическое количество кнопок не совпадает с ожидаемым
    @Test(priority = 3)
    public void theNumberOfButtonsIsCorrect () {
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
        for (int i = 0; i < mainPageRW.topButtons().size(); i++){
            for (int j = 0; j < searchedButtons.length; j++){
                if (mainPageRW.topButtons().get(i).getText().equalsIgnoreCase(searchedButtons[j])){
                    counterOfFoundMatches++;
                }
            }
        }
        System.out.println("Количество кнопок на странице: "+ mainPageRW.topButtons().size());
        System.out.println("Количество совпадений с условием: "+counterOfFoundMatches);


        Assert.assertEquals(mainPageRW.topButtons().size(), 5);

    }

    @AfterClass
    public void stop (){
        driver.quit();
    }

}
