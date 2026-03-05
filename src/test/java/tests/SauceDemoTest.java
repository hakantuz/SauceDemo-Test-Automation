package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;


public class SauceDemoTest extends TestBase {

    @Test
    public void loginTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

        SauceDemoPage sauceDemoPage = new SauceDemoPage();

        sauceDemoPage.username.sendKeys(ConfigReader.getProperty("username"));
        sauceDemoPage.password.sendKeys(ConfigReader.getProperty("password"));
        sauceDemoPage.loginButton.click();

        String ekrandakiYazi = sauceDemoPage.products.getText();
        String beklenenYazi = "Products";
        Assert.assertEquals(ekrandakiYazi, beklenenYazi, "HATA: Products başlığı görüntülenemedi! Yanlış sayfadayız.");
        System.out.println("Başlık Doğrulandı: " + ekrandakiYazi);











    }



}
