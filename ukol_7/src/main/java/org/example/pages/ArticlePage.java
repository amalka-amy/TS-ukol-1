package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage {
    WebDriver driver;

    @FindBy(css = "h1.c-article-title")
    WebElement articleTitle;

    @FindBy(css = "span.c-bibliographic-information__value:nth-child(3)")
    WebElement articleDOI;

    @FindBy(css = "time")
    WebElement articleDate;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return articleTitle.getText();
    }

    public String getDOI() {
        return articleDOI.getText();
    }

    public String getDate() {
        return articleDate.getText();
    }
}
