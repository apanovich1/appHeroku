import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContextMenuTest {
    //https://the-internet.herokuapp.com/context_menu
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
    public void contextMenu() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.id("hot-spot")))
                .perform();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Assert.assertEquals(alertMessage,"You selected a context menu",
                "Incorrect alert text");
        alert.dismiss();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}
