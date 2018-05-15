package selenium;

import helpers.CSVfileReader;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class RedmineParameterizedTest {

    private String username;

    private String password;

    WebDriver driver;

    public RedmineParameterizedTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\TS1labs\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        driver.get("http://demo.redmine.org/");

        WebElement loginButton = driver.findElement(By.cssSelector("a.login"));
        assertEquals("Sign in", loginButton.getText());
        assertTrue(loginButton.isDisplayed());

        loginButton.click();

        assertEquals("http://demo.redmine.org/login", driver.getCurrentUrl());
        WebElement usernameElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));
        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        WebElement submitButton = driver.findElement(By.name("login"));
        submitButton.click();

        assertTrue(driver.findElement(By.id("loggedas")).getText().contains(username));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters()
    public static Collection<String[]> data() throws IOException {
        return CSVfileReader.readCSVfileToCollection("src\\test\\resources\\usernames.csv");
    }

}
