import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoverTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"start-maximized"});
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    }

    @Test
    public void hoversTest() {
        this.driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement iconFirst = this.driver.findElement(By.
                xpath("//*[@id=\"content\"]/div/div[1]"));

        Actions actionHoverFirst = new Actions(this.driver);
        actionHoverFirst.moveToElement(iconFirst).perform();
        WebElement headerFirst = this.driver.findElement(By.tagName("h5"));
        String headerValueFirst = headerFirst.getText();
        Assert.assertEquals(headerValueFirst,
                "name: user1", "Incorrect value in input");
        WebElement viewProfileFirstButton = this.driver.findElement(By.
                xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        Actions actionClickFirstHover = new Actions(this.driver);

        actionClickFirstHover.moveToElement(viewProfileFirstButton).click().perform();
        this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        Assert.assertTrue(this.driver.getTitle().contains("404"),
                "404 error after redirect appears");

        this.driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement iconSecond = this.driver.findElement(By.
                xpath("//*[@id=\"content\"]/div/div[2]"));
        Actions actionHoverSecond = new Actions(this.driver);

        actionHoverSecond.moveToElement(iconSecond).perform();
        WebElement headerSecond = this.driver.findElement(By.tagName("h5"));
        String headerValueSecond = headerSecond.getText();
        Assert.assertEquals(headerValueSecond, "name: user2",
                "Incorrect value in input");
        WebElement viewProfileSecondButton = this.driver.findElement(By
                .xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        Actions actionClickSecondHover = new Actions(this.driver);
        actionClickSecondHover.moveToElement(viewProfileSecondButton).click().perform();

        this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        Assert.assertTrue(this.driver.getTitle().contains("404"),
                "404 error after redirect appears");
        this.driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement iconThird = this.driver.findElement(By.
                xpath("//*[@id=\"content\"]/div/div[3]"));
        Actions actionHoverThird = new Actions(this.driver);
        actionHoverThird.moveToElement(iconThird).perform();
        WebElement headerThird = this.driver.findElement(By.tagName("h5"));
        String headerValueThird = headerThird.getText();
        Assert.assertEquals(headerValueThird, "name: user3",
                "Incorrect value in input");
        WebElement viewProfileThirdButton = this.driver.findElement(By
                .xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        Actions actionClickThirdHover = new Actions(this.driver);
        actionClickThirdHover.moveToElement(viewProfileThirdButton).click().perform();
        this.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        Assert.assertTrue(this.driver.getTitle().contains("404"),
                "404 error after redirect appears");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }
}
