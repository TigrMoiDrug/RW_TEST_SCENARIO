package basics;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Printer {

    public static void printTitlesAndDepartureTime( List<WebElement> title, List<WebElement> departureTime) {
        for (int i = 0; i < title.size(); i++) {
            System.out.println(title.get(i).getText() + " — " + departureTime.get(i).getText());
            System.out.println();
        }
    }
}
