import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.String;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TyposTest {
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
    public void typos() {
        this.driver.get("https://the-internet.herokuapp.com/typos");
        List<WebElement> paragraphs = this.driver.findElements(By.tagName("p"));
        if (paragraphs.size() >= 2) {
            String secondParagraphText = ((WebElement)paragraphs.get(1)).getText();
            Assert.assertEquals(secondParagraphText,
                    "Sometimes you'll see a typo, other times you won't.");
        } else {
            System.out.println("There are not enough paragraphs on"
                    +" the page to check the second paragraph.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}