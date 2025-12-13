package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends CommonPage {

    private By byLblUserProfile = By.xpath("//a[@href='/account']/h3");;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getUserProfileName() {
        return getText(byLblUserProfile);
    }
}
