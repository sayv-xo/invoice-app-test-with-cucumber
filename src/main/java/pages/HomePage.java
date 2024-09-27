package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://invoice-app-6rkf.vercel.app/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get(BASE_URL);
    }

    public void toggleTheme() {
        driver.findElement(By.className("toggle-theme")).click();
        wait.until(ExpectedConditions.attributeToBe(By.tagName("html"), "data-theme", isDarkMode() ? "light" : "dark"));
    }

    public boolean isDarkMode() {
        String theme = driver.findElement(By.tagName("body")).getAttribute("data-theme");
        return "dark".equals(theme);
    }
}