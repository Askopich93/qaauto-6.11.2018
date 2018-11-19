import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        System.out.println("Hello world!!!");

        WebDriver webDriver = new ChromeDriver();
        String searchTerm = "Selenium";
        webDriver.get("https://www.google.com/");

        WebElement element = webDriver.findElement(By.name("q"));
        element.sendKeys("searchTerm");
        element.sendKeys(Keys.ENTER);

        List<WebElement> resultsList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        System.out.println(resultsList.size());

        //for each WebElement in list of WebElement print Text.

        for (WebElement result : resultsList) {
            String resultText = result.getText();
            System.out.println(resultText);

            if (resultText.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("searchTerm " + searchTerm + " found in block:\n" + resultText);
            } else {
                System.out.println("searchTerm " + searchTerm + " NOT found in block:\n" + resultText);
            }
        }


    }
}
