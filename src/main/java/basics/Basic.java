package basics;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class Basic {

//случайные буквы
    public String randomSymbols(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWZYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчъыьэюя";
        StringBuilder generated = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            generated.append(alphabet.toCharArray()[random.nextInt(alphabet.toCharArray().length)]);
        }
        return generated.toString();
    }

// проверка загрузки страницы

    public String loadingStatus(WebDriver driver){
        String status = null;
        do{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        status = (String)js.executeScript("return document.readyState");

        }
        while ( !status.equals("complete") );
        return status;
    }

}
