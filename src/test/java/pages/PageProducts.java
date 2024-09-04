package pages;

import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static helpers.Helpers.sleepSeconds;

public class PageProducts {
    private WebDriver driver;
    private By filterDrop;

    public PageProducts(WebDriver driver){
        this.driver = driver;
        filterDrop = By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select");
    }

    public void selectOption(String opt){
        Select selectFilter = new Select(driver.findElement(filterDrop));
        //selectFilter.selectByIndex(opt);
        selectFilter.selectByVisibleText(opt);
        sleepSeconds(5);
    }
}
