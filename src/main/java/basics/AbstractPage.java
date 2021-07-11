package basics;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    public WebDriver driver;
    public Wait<WebDriver> wait;

    public AbstractPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        this.driver = driver;
    }

    public String loadingStatus(WebDriver driver){
        String state;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        state = (String) js.executeScript("return document.readyState");
        wait.until(driver1 -> state.equalsIgnoreCase("complete"));
        return state;
    }

    public String getTextFromElement (WebElement element){
        return element.getText();
    }

}
