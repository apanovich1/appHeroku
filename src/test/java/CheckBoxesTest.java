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

public class CheckBoxesTest {
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
    public void Checkboxes() {
        this.driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckBox = this.driver.findElement(By.
                cssSelector("[type=checkbox]:nth-of-type(1)"));
        boolean isFirstCheckBoxSelected = firstCheckBox.isSelected();
        if (!isFirstCheckBoxSelected) {
            firstCheckBox.click();
        }

        boolean selectionOfFirstCheckBox = firstCheckBox.isEnabled();
        Assert.assertTrue(selectionOfFirstCheckBox, "Checkbox should be selected");
        WebElement secondCheckBox = this.driver.findElement(By.
                cssSelector("[type=checkbox]:nth-of-type(2)"));
        boolean isSecondCheckBoxSelected = secondCheckBox.isSelected();
        if (isSecondCheckBoxSelected) {
            secondCheckBox.click();
        }

        boolean selectionOfSecondCheckBox = secondCheckBox.isEnabled();
        Assert.assertFalse(selectionOfSecondCheckBox, "Checkbox should be unselected");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}
