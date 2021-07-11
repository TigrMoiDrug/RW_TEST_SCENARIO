package googlePages;

import basics.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageGoogle extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchLineInput;

    @FindBy(name = "btnK")
    private WebElement submitButton;

    public MainPageGoogle(WebDriver driver) {
        super(driver);
    }

    // Ввод текста в строку поиска
    public MainPageGoogle typeTextInGoogleSearchLine(String textToSearch){
        searchLineInput.sendKeys(textToSearch);
        return this;
    }

    // Клик по кнопке "Поиск в Google"
    public ResultPageGoogle clickGoogleSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return new ResultPageGoogle(driver);
    }

}
