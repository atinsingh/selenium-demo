import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class HomeTest {

    WebDriver driver;

    @BeforeSuite
    public void setup(){
        System.setProperty("webdriver.chrome.driver","/Users/atin/Downloads/chromedriver");
        System.setProperty("webdriver.gecko.driver","/Users/atin/Downloads/geckodriver");
        driver = new ChromeDriver();
        driver.get("https://zoom.us/"); // get opens a URL in browser
    }

    @Test(enabled = false)
    public void dummyTc() throws InterruptedException {
        WebElement success_stories = driver.findElement(By.xpath("//div[@id='navbar']//ul//li[.='Contact Sales']"));
        // Alerts
        ((JavascriptExecutor) driver).executeScript("alert('Hello from Selenium')");
        Thread.sleep(2000);
        //success_stories.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();


    }

    @Test(enabled = false)
    public void testFrame() throws InterruptedException {
        try {
            driver.navigate().to(new URL("https://www.w3schools.com/html/html_iframe.asp"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().fullscreen();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='W3Schools HTML Tutorial']"));
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",iframe);
        driver.switchTo().frame(iframe);

        Thread.sleep(5000);
        WebElement htmlTuts = driver.findElement(By.cssSelector(" div[class~=intro]>a[href='html_intro.asp']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",htmlTuts);

        htmlTuts.click();
        Thread.sleep(5000);
        driver.switchTo().parentFrame();
        WebElement spaces = driver.findElement(By.cssSelector("#loginactioncontainer+div>a[title~='Spaces']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",spaces);
        spaces.click();
    }

    @Test
    public void testWebElement() throws InterruptedException {
        WebElement heading = driver.findElement(By.xpath("//*[@id='screen2_container']//div[@class='top-title']"));
        heading.click(); // Clicking on text has no impact

        //((JavascriptExecutor) driver).executeScript("document.querySelector(\"#signupfree>a\").style.display='none'");

        WebElement signUpLink = driver.findElement(By.xpath("//*[@id='signupfree']/a"));

        System.out.println(signUpLink.getLocation().x);
        System.out.println(signUpLink.getLocation().y);

        System.out.println(signUpLink.getSize().width);
        System.out.println(signUpLink.getSize().height);
        System.out.println(signUpLink.getCssValue("background-color"));
        System.out.println(signUpLink.getCssValue("padding"));

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(screenshot.toPath(), Paths.get("fullPage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // System.out.println(driver.findElement(By.xpath("//li[@class='signin']")).getText());

        System.out.println(signUpLink.isDisplayed());

        //driver.findElement(By.xpath("//ul//li//a[contains(@class,'top-contactsales')]")).click();

        //form[@class='FormStandard']

        //WebElement pragraForm = driver.findElement(By.xpath(" //form[@class='FormStandard ']"));
        //Thread.sleep(5000);
        //WebElement email = driver.findElement(By.id("email"));
        //email.sendKeys("info@zoom.us");
        //System.out.println(email.getTagName());
        //Thread.sleep(2000);
        ///email.clear();

        //pragraForm.submit();



    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10_000);
        driver.quit();
    }
}
