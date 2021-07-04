package googlePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultPageGoogle {
    public WebDriver driver;

    public ResultPageGoogle (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "yuRUbf")
    private List <WebElement> links;

// клик по ссылке
    public void clickOnGoogleLink(){
        links.get(0).click();
    }

}
