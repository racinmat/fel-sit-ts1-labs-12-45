package selenium;

import helpers.LoginPageObject;
import helpers.TopMenuPublic;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class RedmineTest {

    static WebDriver driver;

    private LoginPageObject loginPage;
    private TopMenuPublic topMenuPublic;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\TS1labs\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() throws Exception {
        loginPage = LoginPageObject.create(driver);
        topMenuPublic = TopMenuPublic.create(driver);

    }

    @Test
    public void testLoginProcedure() {
        a_testLogin();
        b_goToProfile();
    }

    public void a_testLogin() {
        driver.get("http://demo.redmine.org/");

        assertEquals("Sign in", topMenuPublic.getLoginButton().getText());
        assertTrue(topMenuPublic.getLoginButton().isDisplayed());

        topMenuPublic.getLoginButton().click();

        assertEquals("http://demo.redmine.org/login", driver.getCurrentUrl());
        String username = "testUser222";
        loginPage.loginUser(username, "heslo");

        assertTrue(driver.findElement(By.id("loggedas")).getText().contains(username));
    }

    public void b_goToProfile() {
        WebElement userActive = driver.findElement(By.cssSelector("#loggedas > a.active"));
        userActive.click();

        assertEquals("http://demo.redmine.org/users/340939", driver.getCurrentUrl());
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }
}
