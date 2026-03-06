package tests;

import org.testng.annotations.Test;
import pages.SauceDemoPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

public class ShoppingTest extends TestBase {

    @Test
    public void shoppingTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        SauceDemoPage sauceDemoPage = new SauceDemoPage();

        sauceDemoPage.username.sendKeys(ConfigReader.getProperty("username"));
        sauceDemoPage.password.sendKeys(ConfigReader.getProperty("password"));
        sauceDemoPage.loginButton.click();

        sauceDemoPage.addtocart.click();


    }
}
