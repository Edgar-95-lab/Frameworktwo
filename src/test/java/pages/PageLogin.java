package pages;

import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Helpers.sleepSeconds;

public class PageLogin {
    private WebDriver driver;
    private By userField;
    private By passwordField;
    private By loginButton;

    public PageLogin(WebDriver driver){
        this.driver = driver;
        userField = By.name("user-name");
        passwordField = By.name("password");
        loginButton = By.xpath("/html/body/div[2]/div[1]/div/div/form/input[3]");
    }

    public void login(String user, String password){
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        sleepSeconds(2);
    }
}
