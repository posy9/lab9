import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FavoriteTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupBrowser(){
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
    }



    @Test
    public void testShoppingBagCalculation() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1600, 1000));
        for(int i = 0; i < 5; i++){
            driver.get("https://www2.hm.com/en_us/men/new-arrivals/clothes.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            if( i == 0){
                wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))).click();
            }
            WebElement element = driver.findElement(By.cssSelector("[data-index='"+ i + "']"))
                    .findElement(By.className("image-container"))
                    .findElement(By.tagName("a"));
            Thread.sleep(300);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            WebElement clothFavorite = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[1]/div[2]/div[1]/div[1]/div/hm-favourites-button/button"));
            clothFavorite.click();
        }
        driver.get("https://www2.hm.com/en_us/favourites");
        Assert.assertEquals(driver.findElements(By.cssSelector("[data-articlecode]")).size(),5);



    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
//        driver.quit();
//        driver = null;
    }

}
