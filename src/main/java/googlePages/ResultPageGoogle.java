package googlePages;

import basics.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPageGoogle extends AbstractPage {

    @FindBy(className = "yuRUbf")
    private List <WebElement> links;

    public ResultPageGoogle(WebDriver driver) {
        super(driver);
    }

    // клик по ссылке
    public ResultPageGoogle clickOnGoogleLink(){
        links.get(0).click();
        return this;
    }

}
