package amzn.domain.proxies;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.ArrayList;

public class RotateProxy {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        getFreeProxies(driver);
        driver.quit();
    }

    public static void getFreeProxies(WebDriver driver) {
        driver.get("https://sslproxies.org");

        WebElement table = driver.findElement(By.tagName("table"));
        WebElement thead = table.findElement(By.tagName("thead"));
        WebElement tbody = table.findElement(By.tagName("tbody"));

        String[] headers = thead.findElements(By.tagName("th")).stream()
                .map(WebElement::getText)
                .map(String::strip)
                .toArray(String[]::new);

        for (WebElement tr : tbody.findElements(By.tagName("tr"))) {
            String[] proxyData = tr.findElements(By.tagName("td")).stream()
                    .map(WebElement::getText)
                    .map(String::strip)
                    .toArray(String[]::new);

            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i] + ": " + proxyData[i]);
            }
            System.out.println();
        }
    }
}
