import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
    public class AddRemoveElementsTest {
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
        public void AddRemoveElements() {
            this.driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
            this.driver.findElement(By.xpath("//button[text()='Add Element']")).click();
            this.driver.findElement(By.xpath("//button[text()='Add Element']")).click();
            ((WebElement) this.driver.findElements(By.xpath("//button[text()='Delete']")).get(1)).click();
            Assert.assertEquals(this.driver.findElements(By.xpath("//button[text()='Delete']")).size(), 1, "Element is not deleted");
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {
            this.driver.quit();
        }
    }