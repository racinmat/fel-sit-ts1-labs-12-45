package helpers;

import com.sun.nio.sctp.InvalidStreamException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

//    static By username = By.id("username");
//    static By password = By.id("password");
//    static By submit = By.name("login");

//    @FindBy(id = "username")
    WebElement username;

//    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "a.login")
    WebElement submitButton;

    public static LoginPageObject create(WebDriver driver) {
        return PageFactory.initElements(driver, LoginPageObject.class);
    }

    public void loginUser(String username, String password) {
//        WebElement usernameEl = driver.findElement(LoginPageObject.username);
//        WebElement passwordEl = driver.findElement(LoginPageObject.password);
//        WebElement submitButton = driver.findElement(LoginPageObject.submit);

        this.username.sendKeys(username);
        this.password.sendKeys(password);

        submitButton.click();
    }
}
