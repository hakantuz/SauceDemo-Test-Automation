package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    // 1- Driver objesini oluşturduk ama henüz boş (null)
    private static WebDriver driver;


    private Driver() {
    }

    // 2- Driver istendiğinde çalışacak ana metodumuz
    public static WebDriver getDriver() {

        // Eğer driver daha önce oluşturulmamışsa (null ise) oluştur
        if (driver == null) {

            // Config dosyasından hangi tarayıcıyı istediğimizi oku
            String browserTipi = ConfigReader.getProperty("browser");

            switch (browserTipi) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    // 1. BUNU EKLEYİNCE GEÇMİŞİ VE ŞİFRELERİ TAKMAZ
                    options.addArguments("--incognito");

                    // 2. GÜVENLİK DUVARLARINI İNDİR
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-features=PasswordLeakDetection");

                    // 3. TARAYICIYI BOT GİBİ GÖSTERME (Otomasyon uyarısını gizle)
                    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                    // 4. ŞİFRE YÖNETİCİSİNİ KALBİNDEN VUR (Prefs Ayarları)
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);

                    // 5. EN ÖNEMLİ HAMLE: Options'ı buraya parametre olarak girmelisin!
                    // Eğer parantez içi boşsa (new ChromeDriver()) yukarıdakilerin hiçbiri çalışmaz.
                    driver = new ChromeDriver(options);

                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }

            // Standart ayarlar (Tam ekran yap, elementleri 15 sn bekle)
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        // Hazır olan driver'ı geri gönder
        return driver;
    }

    // 3- Driver'ı kapatmak için kullanılacak metod
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Havuz boşalsın ki sonraki testte yeniden açılabilsin
        }
    }
}
