package googlePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPageGoogle {
    public WebDriver driver;

    public ResultPageGoogle (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='rso']/div[1]/div/div/div/div/div/div[1]/a")
    private WebElement link;

// клик по ссылке
    public void clickOnGoogleLink(){
        link.click();
    }

}