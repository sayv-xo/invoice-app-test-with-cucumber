package com.example.steps;

import com.example.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

public class ToggleThemeSteps {

    private WebDriver driver;
    private WebDriverWait wait;


    @Given("the app is in {word} mode")
    public void the_app_is_in_mode(String initialTheme) {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://invoice-app-6rkf.vercel.app/");


        // Ensure the app is in the initial theme mode
        if (initialTheme.equals("light") && isDarkMode()) {
            toggleTheme();
        } else if (initialTheme.equals("dark") && !isDarkMode()) {
            toggleTheme();
        }
    }

    @When("I click the toggle theme button")
    public void i_click_the_toggle_theme_button() {
        toggleTheme();
    }

    @Then("the app should switch to the {word}")
    public void the_app_should_switch_to_the(String expectedTheme) {
        boolean isDarkMode = isDarkMode();
        if (expectedTheme.equals("dark")) {
            assert isDarkMode : "Expected dark mode, but it is light mode";
        } else {
            assert !isDarkMode : "Expected light mode, but it is dark mode";
        }
        driver.quit();
    }

    @Given("the app was in dark mode")
    public void the_app_was_in_dark_mode() {
        the_app_is_in_mode("dark");
    }

    @When("I close and reopen the app")
    public void i_close_and_reopen_the_app() {
        driver.quit();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://invoice-app-6rkf.vercel.app/");
    }

    @Then("the app should still be in dark mode")
    public void the_app_should_still_be_in_dark_mode() {
        assert isDarkMode() : "Expected dark mode, but it is light mode";
        driver.quit();
    }

    private void toggleTheme() {
        driver.findElement(By.className("toggle-theme")).click();
        wait.until(ExpectedConditions.attributeToBe(By.tagName("html"), "data-theme", isDarkMode() ? "light" : "dark"));
    }

    private boolean isDarkMode() {
        String theme = driver.findElement(By.tagName("body")).getAttribute("data-theme");
        return "dark".equals(theme);
    }
}
