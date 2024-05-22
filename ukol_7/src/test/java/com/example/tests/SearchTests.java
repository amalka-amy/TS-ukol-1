package com.example.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.example.pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SearchTests {
    WebDriver driver;
    HomePage homePage;
    EmailPage emailPage;
    LoginPage loginPage;
    AdvancedSearchPage advancedSearchPage;
    SearchResultsPage searchResultsPage;
    ArticlePage articlePage;

    List<String> savedArticleTitles = new ArrayList<>();
    List<String> savedArticleDOIs = new ArrayList<>();
    List<String> savedArticleDates = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Amy/Downloads/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        System.out.println("SETUP done");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin() {
        driver.get("https://link.springer.com/signup-login");
        emailPage = new EmailPage(driver);
        emailPage.login("jirotama@cvut.cz");
        loginPage = new LoginPage(driver);
        loginPage.login("klokan2skace");
    }

    @ParameterizedTest
    @MethodSource("articleSearchProvider")
    public void searchAndSaveArticles(String All, String atLeastOne, String year) {
        driver.get("https://link.springer.com/advanced-search");
        driver.findElement(By.cssSelector("button[data-cc-action='accept']")).click();
        advancedSearchPage = new AdvancedSearchPage(driver);
        advancedSearchPage.performAdvancedSearch(All, atLeastOne, year);

        searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.FilterArticle();
        searchResultsPage = new SearchResultsPage(driver);

        for (int i = 0; i < 4; i++) {
            driver.get(searchResultsPage.get(i));
            articlePage = new ArticlePage(driver);
            savedArticleTitles.add(articlePage.getTitle());
            savedArticleDOIs.add(articlePage.getDOI());
            savedArticleDates.add(articlePage.getDate());
            driver.navigate().back();
        }
        Assertions.assertEquals(4, savedArticleTitles.size());

        for (int i = 0; i < 4; i++) {
            driver.get(searchResultsPage.get(i));
            articlePage = new ArticlePage(driver);
            Assertions.assertEquals(savedArticleTitles.get(i), articlePage.getTitle());
            Assertions.assertEquals(savedArticleDOIs.get(i), articlePage.getDOI());
            Assertions.assertEquals(savedArticleDates.get(i), articlePage.getDate());
        }
    }

    static Stream<Arguments> articleSearchProvider() {
        return Stream.of(
                Arguments.of("Page Object Model", "Selenium Testing", "2024")
        );
    }
}
