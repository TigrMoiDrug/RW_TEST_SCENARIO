package googlePages;

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

    @FindBy(name = "q")
    private WebElement searchLineInput;

    @FindBy(name = "btnK")
    private WebElement submitButton;

// Ввод текста в строку поиска
    public void typeTextInGoogleSearchLine(String textToSearch){
        searchLineInput.sendKeys(textToSearch);
    }

// Клик по кнопке "Поиск в Google"
    public void clickGoogleSearchButton(){
        submitButton.click();
    }

}
