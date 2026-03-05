package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

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
                    driver = new ChromeDriver();
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
