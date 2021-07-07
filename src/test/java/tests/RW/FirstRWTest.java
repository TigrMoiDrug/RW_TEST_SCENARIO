package tests.RW;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rwPages.MainPageRW;
import tests.BasicTest;
import java.util.ArrayList;
import java.util.List;

public class FirstRWTest extends BasicTest{

    public static String expected = "© 2021 Belarusian Railway";

    @BeforeClass
    @Parameters("browserName")
    public static void starter(String browserName){
        mainPageRW = new MainPageRW(driver);
        getToURL(RW_URL, driver);
        mainPageRW.switchLanguage();
    }

//проверка количества новостей в блоке
    @Test(priority = 1)
    public void notLessThanFourNewsDisplayed () {
        log.info("Количество новостей в блоке: " + mainPageRW.getNewsBlockSize());
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
        String pressCenter = "PRESS CENTER";
        String timeTable = "TIMETABLE";
        String passengerServices = "PASSENGER SERVICES";
        String freight = "FREIGHT";
        String corporate = "CORPORATE";

        List<String> buttonsList = new ArrayList<>();
        buttonsList.add(pressCenter);
        buttonsList.add(timeTable);
        buttonsList.add(passengerServices);
        buttonsList.add(freight);
        buttonsList.add(corporate);

        int counterOfFoundMatches = 0;
        for (int i = 0; i < mainPageRW.topButtons().size(); i++){
            if (buttonsList.contains(mainPageRW.topButtons().get(i).getText())){
                counterOfFoundMatches++;
            }
        }
        log.info("Количество кнопок на странице: "+ mainPageRW.topButtons().size());
        log.info("Количество совпадений с условием: "+counterOfFoundMatches);

        Assert.assertEquals(mainPageRW.topButtons().size(), 5);

    }

}
