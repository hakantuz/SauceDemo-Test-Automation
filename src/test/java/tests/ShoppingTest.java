package tests;

import org.testng.Assert;
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

        sauceDemoPage.loginOl();

        sauceDemoPage.addToCart.click();
        sauceDemoPage.basket.click();

        String sepettekiUrun = sauceDemoPage.backpackUrunBasligi.getText();
        String beklenenUrun = "Sauce Labs Backpack";
        Assert.assertEquals(sepettekiUrun, beklenenUrun, "HATA: Ürün ismi yanlış! Başka bir çanta gelmiş olabilir.");
        System.out.println("✅ Ürün Doğrulandı: " + sepettekiUrun + " başarıyla sepete eklenmiş.");

        sauceDemoPage.checkout.click();

        sauceDemoPage.formuDoldur();

        sauceDemoPage.finish.click();

        String actualMessage = sauceDemoPage.complete.getText();
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals(actualMessage, expectedMessage, "HATA: Sipariş tamamlanamadı, mesaj eşleşmedi!");
        System.out.println("✅ TEST BAŞARILI: " + actualMessage);

        sauceDemoPage.backHome.click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("inventory"), "HATA: Back Home butonu çalışmadı, ana sayfaya dönülemedi!");
        System.out.println("✅ TEST TAMAMLANDI: Başarıyla ana sayfaya dönüldü.");

    }
}
