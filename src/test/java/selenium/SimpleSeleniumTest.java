package selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleSeleniumTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Azathoth\\IdeaProjects\\TS1labs\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        driver.get("https://www.google.com");

        WebElement searchBar = driver.findElement(By.name("q"));
        assertTrue(searchBar.isDisplayed());

        searchBar.sendKeys("čvut");
        searchBar.submit();

        WebElement firstSearchResult = driver.findElement(
            By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > h3 > a"));

        assertTrue(firstSearchResult.isDisplayed());
        assertEquals("ČVUT", firstSearchResult.getText());

        firstSearchResult.click();

        assertEquals("https://www.cvut.cz/", driver.getCurrentUrl());
    }

    @Test
    public void testSeznamSearch() {
        driver.get("https://www.seznam.cz/");

        WebElement searchBar = driver.findElement(By.id("fulltext-input"));
//        WebElement searchBar = driver.findElement(By.cssSelector("#fulltext-input"));

        List<String> thingsWeWantToSearch = new ArrayList<>();
        thingsWeWantToSearch.add("čvut");
        thingsWeWantToSearch.add("fel");
        thingsWeWantToSearch.add("fit");
        thingsWeWantToSearch.add("zču");
        thingsWeWantToSearch.add("čzu");
        thingsWeWantToSearch.add("uk");

        for (String weWantToSearch : thingsWeWantToSearch) {
            searchBar.sendKeys(weWantToSearch);
            searchBar.click();

//            WebElement resultsContainer = driver.findElement(By.xpath("//*[@data-dot=\"results\"]"));
//            WebElement resultsContainer = driver.findElement(By.cssSelector("#app-root > div > div.Page.js-page > div.Page-content > div.Page-content-columnLeft > div"));
//            List<WebElement> results = resultsContainer.findElements(By.tagName("div"));
//            System.out.println(results.get(0).getText());
        }
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
