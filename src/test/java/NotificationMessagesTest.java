import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotificationMessagesTest {
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
    public void NotificationMessagesTest() {
        this.driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        WebElement notificationButton = this.driver.findElement(By.
                xpath("//*[@id=\"content\"]/div/p/a"));
        Actions actionClickNotificationButton = new Actions(this.driver);
        actionClickNotificationButton.moveToElement(notificationButton).click().perform();
        WebDriverWait wait = new WebDriverWait(this.driver, 10L);
        WebElement notification = (WebElement)wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.id("flash")));
        String notificationText = notification.getText();
        Assert.assertTrue(notificationText.contains("Action successful"),
                "Incorrect notification text");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}

