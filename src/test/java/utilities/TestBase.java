package utilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

// abstract yapıyoruz ki kimse bu sınıftan nesne üretemesin, sadece miras alabilsin.
public abstract class TestBase {

    // @BeforeMethod: Her test metodundan (@Test) ÖNCE çalışır.
    @BeforeMethod
    public void setUp() {
        // Driver'ı çağır (Singleton yapımız sayesinde zaten varsa yenisini açmaz)
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    // @AfterMethod: Her test metodundan SONRA çalışır.
    @AfterMethod
    public void tearDown() {
        // Test bittiğinde ortalığı temizle ve tarayıcıyı kapat
        Driver.closeDriver();
    }

    // --- ORTAK KULLANILAN METOTLAR (Reusable Methods) ---

    // Java'nın o gıcık Thread.sleep hatasını (Exception) her yerde try-catch yapmamak için:
    public void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            System.out.println("Bekleme işleminde hata oluştu!");
            throw new RuntimeException(e);
        }
    }
}