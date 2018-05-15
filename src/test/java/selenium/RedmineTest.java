package selenium;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class RedmineTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\TS1labs\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginProcedure() {
        a_testLogin();
        b_goToProfile();
    }

    public void a_testLogin() {
        driver.get("http://demo.redmine.org/");

        WebElement loginButton = driver.findElement(By.cssSelector("a.login"));
        assertEquals("Sign in", loginButton.getText());
        assertTrue(loginButton.isDisplayed());

        loginButton.click();

        assertEquals("http://demo.redmine.org/login", driver.getCurrentUrl());
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys("testUser222");
        password.sendKeys("heslo");

        WebElement submitButton = driver.findElement(By.name("login"));
        submitButton.click();

        assertEquals("Logged in as testUser222",
            driver.findElement(By.id("loggedas")).getText());
    }

    public void b_goToProfile() {
        WebElement userActive = driver.findElement(By.cssSelector("#loggedas > a.active"));
        userActive.click();

        assertEquals("http://demo.redmine.org/users/338636", driver.getCurrentUrl());
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
}
