package googlePages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageGoogle {

    public WebDriver driver;

    public MainPageGoogle (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@aria-label='Найти']")
    private WebElement searchLineInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]")
    private WebElement submitButton1;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[2]/center/input[1]")
    private WebElement submitButton2;


    public void getToGoogle() {
        String googleURL = "https://www.google.com/";
        driver.get(googleURL);
    }

    public void typeTextToSearchInGoogleSearchLine(){
        String textToSearch = "белорусская железная дорога";
        searchLineInput.sendKeys(textToSearch);
    }

    public void clickGoogleSearchButton(){
        try{
            submitButton1.click();
        }
        catch (ElementClickInterceptedException ex){
            submitButton2.click();
        }
    }

}
