package tests;

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FirstSeleniumTest {
    public WebDriver driver;
    public WebDriverWait wait;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }
    
    private WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }    

    @Test
    public void testSelenium() {
        this.driver.get("http://selenium.thinkcode.se");
       
        Assert.assertTrue(this.waitAndReturnElement(By.id("requestPassword")).getText().contains("Request password - fill out and submit a form"));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        this.waitAndReturnElement(By.id("requestPassword")).click();
        
        this.waitAndReturnElement(By.id("account")).sendKeys("MahdiBentaleb");
        this.waitAndReturnElement(By.name("submit")).click();

        WebElement bodyElemnet = this.waitAndReturnElement(By.tagName("body"));
        System.out.println("-------------------------------------");
        System.out.println(bodyElemnet.getText());
        Assert.assertTrue(bodyElemnet.getText().contains("MahdiBentaleb"));
    }
    

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
