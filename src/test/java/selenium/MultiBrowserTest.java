package selenium;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class MultiBrowserTest {

    static WebDriver chromeDriver;
    static WebDriver firefoxDriver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\TS1labs\\src\\test\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\unittests\\src\\test\\resources\\geckodriver.exe");
        chromeDriver = new ChromeDriver();
        firefoxDriver = new FirefoxDriver();
    }

    @Test
    public void simpleTest() {
        chromeDriver.get("https://www.google.com/");
        firefoxDriver.get("https://www.bing.com/");
    }
}
