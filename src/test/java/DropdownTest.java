import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTest {
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
    public void dropdownTest() {
        this.driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = this.driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        Assert.assertEquals(((WebElement)options.get(0)).
                getText(), "Please select an option",
                "Incorrect option text");
        Assert.assertEquals(((WebElement)options.get(1)).
                getText(), "Option 1",
                "Incorrect option text");
        Assert.assertEquals(((WebElement)options.get(2)).
                getText(), "Option 2",
                "Incorrect option text");
        WebElement selectedOption = select.getFirstSelectedOption();
        Boolean selection = selectedOption.isSelected();
        Assert.assertTrue(selection, "The selected option should be selected");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}
