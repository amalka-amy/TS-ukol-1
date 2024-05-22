package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v122.page.Page;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    WebDriver driver;
    public List<String> hrefs;
    @FindBy(css = "h2.title")
    List<WebElement> articleTitles;
    @FindBy(css = "a.app-card-open__link")
    public List<WebElement> articles;
    @FindBy(css = "label[for='content-type-article']")
    WebElement chBoxArticle;
    @FindBy(css = "button.eds-c-button--primary")
    WebElement updateResultsButton;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        getAllArticleLinks();
        System.out.println(articleTitles);
    }

    public void getAllArticleLinks() {
        this.hrefs = new ArrayList<>();
        for (WebElement article : articles) {
            hrefs.add(article.getAttribute("href"));
        }
    }

    public void FilterArticle(){
        chBoxArticle.click();
        updateResultsButton.click();
    }

    public List<WebElement> getArticleTitles() {
        return articleTitles;
    }
    public String get(int i){
        return hrefs.get(i);
    }
}
