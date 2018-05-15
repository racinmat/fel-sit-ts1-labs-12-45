package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenuPublic {

    @FindBy(className = "login")
    private WebElement loginButton;

    @FindBy(className = "register")
    private WebElement registerButton;

    public static TopMenuPublic create(WebDriver driver) {
        return PageFactory.initElements(driver, TopMenuPublic.class);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}
