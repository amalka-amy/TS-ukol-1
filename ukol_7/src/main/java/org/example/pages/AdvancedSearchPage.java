package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage {
    WebDriver driver;

    @FindBy(id = "all-words")
    WebElement allWordsField;

    @FindBy(id = "least-words")
    WebElement leastWordsField;

    @FindBy(id = "submit-advanced-search")
    WebElement searchButton;

    @FindBy(id = "date-facet-mode")
    WebElement yearSearch_w;

    @FindBy(id = "facet-start-year")
    WebElement dateIn;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void performAdvancedSearch(String allWords, String leastWords, String Year) {
        allWordsField.sendKeys(allWords);
        leastWordsField.sendKeys(leastWords);
        Select yearSearch = new Select(yearSearch_w);
        yearSearch.selectByValue("in");
        dateIn.sendKeys(Year);
        searchButton.click();

    }
}
