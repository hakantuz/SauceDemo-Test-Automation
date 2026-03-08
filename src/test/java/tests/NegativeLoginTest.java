package tests;

import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

public class NegativeLoginTest extends TestBase {

    // Senaryo 1: Doğru Kullanıcı Adı ama YANLIŞ Şifre
    @Test
    public void yanlisSifreTesti(){

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        SauceDemoPage sauceDemoPage = new SauceDemoPage();

        sauceDemoPage.login(ConfigReader.getProperty("username"), "yanlisSifre123");
        Assert.assertTrue(sauceDemoPage.errorMessage.isDisplayed(), "HATA: Sistem yanlış şifreye tepki vermedi!" );

        String gerceklesenMesaj = sauceDemoPage.errorMessage.getText();
        String beklenenMesaj = "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(gerceklesenMesaj, beklenenMesaj, "Hata mesajı yanlış!");

        System.out.println("✅ Yanlış Şifre Testi Başarılı: " + gerceklesenMesaj);

    }

    // Senaryo 2: Kilitli Kullanıcı (Locked Out User)
    @Test
    public void kilitliKullaniciTesti() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));
        SauceDemoPage sauceDemoPage = new SauceDemoPage();

        sauceDemoPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(sauceDemoPage.errorMessage.isDisplayed(), "HATA: Kilitli kullanıcıya uyarı verilmedi!");

        String gerceklesenMesaj = sauceDemoPage.errorMessage.getText();
        String beklenenMesaj = "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertTrue(gerceklesenMesaj.contains("locked out"), "Hata mesajında 'locked out' ibaresi yok!");

        System.out.println("✅ Kilitli Kullanıcı Testi Başarılı: " + gerceklesenMesaj);

    }
}
