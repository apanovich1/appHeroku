import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
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

public class SortableDataTablesTest {
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
    public void sortableDataTables() {
        this.driver.get("https://the-internet.herokuapp.com/tables");
        WebElement table = this.driver.findElement(By.xpath(
                "//*[@id=\"table1\"]/tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int rowIndex = 0;
        int colIndex = 0;
        WebElement row = (WebElement)rows.get(rowIndex);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        WebElement cell = (WebElement)cells.get(colIndex);
        String cellValue = cell.getText();
        Assert.assertEquals(cellValue, "Smith",
                "Incorrect value in cell");
        WebElement rowSecond = (WebElement)rows.get(rowIndex + 3);
        List<WebElement> cellsSecond = rowSecond.findElements(By.tagName("td"));
        WebElement cellSecond = (WebElement)cellsSecond.get(colIndex + 2);
        String cellValueSecond = cellSecond.getText();
        Assert.assertEquals(cellValueSecond,
                "tconway@earthlink.net", "Incorrect value in cell");
        WebElement rowThird = (WebElement)rows.get(rowIndex + 2);
        List<WebElement> cellsThird = rowThird.findElements(By.tagName("td"));
        WebElement cellThird = (WebElement)cellsThird.get(colIndex + 4);
        String cellValueThird = cellThird.getText();
        Assert.assertEquals(cellValueThird,
                "http://www.jdoe.com", "Incorrect value in cell");
    }

    @AfterMethod( alwaysRun = true)
    public void tearDown() {

        this.driver.quit();
    }
}