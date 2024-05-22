package org.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Main {
    private final static String JMENO = "Jirotková Amálie 108C"; //Jmeno a paralelka
    private final static String USERNAME = "jirotama";
    private final static String PASSWORD = "BudejovickyMajales2024";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/Amy/Downloads/chromedriver-mac-x64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://moodle.fel.cvut.cz/mod/quiz/view.php?id=308513");
        driver.findElement(By.cssSelector("a.btn")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> driver.findElement(By.id("username")).isDisplayed());
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(".btn")).click();
        wait.until(d -> driver.findElement(By.cssSelector("button[type=submit]")).isDisplayed());
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        driver.findElement(By.cssSelector("#id_submitbutton")).click();
        driver.findElement(By.cssSelector("textarea")).sendKeys(JMENO);
        driver.findElement(By.cssSelector("input[type=text]")).sendKeys("86400");
        new Select(driver.findElement(By.cssSelector("select"))).selectByVisibleText("Oberon");
        new Select(driver.findElements(By.cssSelector("select")).get(1)).selectByVisibleText("Rumunsko");
        driver.findElement(By.id("mod_quiz-next-nav")).click();
    }
}

