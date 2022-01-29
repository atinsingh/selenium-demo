import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*
    3. What are different ways to populate data in input box ( sendKeys() )
    1. Can you deal with input tags ( Send Keys for populating data) (clear to clear data)
    2. CheckBoxes and Radio buttons
    4. Dealing with select (drop downs)
 */
public class ActionsTest {
    WebDriver driver;

    @BeforeSuite
    public void setup(){
        System.setProperty("webdriver.chrome.driver","/Users/atin/Downloads/chromedriver");
        System.setProperty("webdriver.gecko.driver","/Users/atin/Downloads/geckodriver");
        driver = new ChromeDriver();
        driver.get("https://zoom.us/"); // get opens a URL in browser
    }

    @Test(enabled = false)
    public void sendKeyTest(){
        //arguments[0].value = 'ABCD';
        WebElement company = driver.findElement(By.id("keep_me_signin"));
       // ((JavascriptExecutor) driver).executeScript("arguments[0].type = 'checkbox'", company );

        if (company.isSelected()) {
            System.out.println("not checked");
            company.click();
        }else {
            System.out.println("Already checked not clicking" );
        }

    }

    @Test(enabled = false)
    public void dealingWithSelect() throws InterruptedException {
        WebElement country = driver.findElement(By.id("country"));
        Select countrySelect = new Select(country);
        countrySelect.selectByValue("IN");
        countrySelect.selectByVisibleText("Canada");
        countrySelect.selectByIndex(5);

        driver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

        WebElement iframe = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframe);
        WebElement car = driver.findElement(By.id("cars"));

        Select carSelect = new Select(car);
        if(carSelect.isMultiple()) {
            carSelect.selectByValue("audi");
            carSelect.selectByValue("opel");
        }
        Thread.sleep(2000);
        carSelect.deselectAll();




    }

    @Test(enabled = false)
    public void closeCookieAccept(){
        WebElement cookieAccept = driver.findElement(By.cssSelector("#onetrust-close-btn-container>button"));
        cookieAccept.click();

    }

    @Test
    public void waitDemo(){
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);x
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to("http://localhost:3000/");
        ((JavascriptExecutor) driver).executeScript(" setTimeout(function(){\n" +
                "    alert('Hello from Delay')\n" +
                "  },5000)");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();


        driver.findElement(By.tagName("input")).sendKeys("Hello");
        WebElement button = driver.findElement(By.tagName("button"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none'", button);

        ((JavascriptExecutor) driver).executeScript("setTimeout(function(){document.querySelector('button').style.display='block'},5000)",button);

        WebElement webElement = wait.until(ExpectedConditions.visibilityOf(button));

        webElement.click();
//        wait.until(ExpectedConditions)
        //WebElement element = driver.findElement();
        String text = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("display-length"))).getText();
        Assert.assertTrue(text.contains("5"));
    }

    @Test(enabled = false)
    public void mouseActions(){
        WebElement solutions = driver.findElement(By.id("btnSolutions"));

        WebElement govtSolution = driver.findElement(By.xpath("//*[@id='second-col-nav']//ul/li[3]/a"));

        Actions actions = new Actions(driver);
        actions.moveToElement(solutions).pause(5000).moveToElement(govtSolution)
                .pause(3000).click().build().perform();
    }


    @Test(enabled = false)
    public void dragNDrop(){
        driver.navigate().to("https://jqueryui.com/draggable/");
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);


        WebElement draggable = driver.findElement(By.id("draggable"));
        //WebElement droppable = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggable,20, 20).build().perform();

    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(20_000);
        driver.quit();
    }
}
