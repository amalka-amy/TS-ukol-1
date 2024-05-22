package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailPage {
    WebDriver driver;

    @FindBy(id = "login-email")
    WebElement emailField;
    @FindBy(id = "email-submit")
    WebElement emailButton;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email) {
        emailField.sendKeys(email);
        emailButton.click();
    }
}
