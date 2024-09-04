package tests;

import helpers.Helpers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageLogin;
import pages.PageProducts;

import java.io.File;
import java.io.IOException;

import static helpers.Helpers.sleepSeconds;
import static helpers.Screenshooter.takeScreenshot;
import static helpers.WebDriverManager.goToURL;
import static helpers.WebDriverManager.setWindowSize;

public class Tests {
    private WebDriver driver;
    @BeforeMethod
    public void setUp(){
        DesiredCapabilities caps = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        setWindowSize("maximized", driver);
        goToURL("https://www.saucedemo.com/v1/index.html", driver);
        sleepSeconds(2);
    }

    @Test(description = "Caso de prueba de login")
    public void testLogin(){
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.login("standard_user","secret_sauce");
        By elementIdentifier = By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/div");
        String valueIdentifier = driver.findElement(elementIdentifier).getText().trim();
        Assert.assertEquals(valueIdentifier,"Products", "El valor actual no coincide con el valor esperado,");
    }

    @Test(description = "Caso de prueba de dropdown")
    public void testFilter(){
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.login("standard_user","secret_sauce");
        PageProducts pageProducts = new PageProducts(driver);
        pageProducts.selectOption("Price (low to high)");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (!result.isSuccess()){
            takeScreenshot("Error", driver);
        }
        driver.close();
    }

}
