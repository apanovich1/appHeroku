import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputsTest {
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
    public void Inputs() {
        this.driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement input = this.driver.findElement(By.tagName("input"));
        input.sendKeys(new CharSequence[]{Keys.ARROW_UP});
        String inputValueFirst = input.getAttribute("value");
        Assert.assertEquals(inputValueFirst, "1",
                "Incorrect value in input");
        input.sendKeys(new CharSequence[]{Keys.ARROW_DOWN});
        String inputValueSecond = input.getAttribute("value");
        Assert.assertEquals(inputValueSecond, "0",
                "Incorrect value in input");
        input.sendKeys(new CharSequence[]{Keys.ARROW_DOWN});
        String inputValueThird = input.getAttribute("value");
        Assert.assertEquals(inputValueThird, "-1",
                "Incorrect value in input");
        input.sendKeys(new CharSequence[]{"acd"});
        String inputValueNonNumeric = input.getAttribute("value");
        Assert.assertEquals(inputValueNonNumeric, "-1",
                "A user is able to insert non numeric value in input");
    }

    @AfterMethod( alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}