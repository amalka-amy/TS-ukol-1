
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class firstAutoTest {
    @Test
    public void firstAutoTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Amy/Downloads/chromedriver-mac-x64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationteststore.com/");
        driver.findElement(By.cssSelector("#customer_menu_top a")).click();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void FormFillTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/Amy/Downloads/chromedriver-mac-x64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationteststore.com/index.php?rt=account/create");
        driver.findElement(By.cssSelector("#AccountFrm_firstname")).sendKeys("Username");
        Select countrySelect = new Select(driver.findElement(By.cssSelector("#AccountFrm_country_id")));
        countrySelect.selectByVisibleText("Czech Republic");
    }
}
