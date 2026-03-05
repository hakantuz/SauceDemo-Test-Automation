package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // 1- Properties objesi oluştur (Java'nın kendi sınıfı)
    private static Properties properties;

    // 2- Bu sınıf çalıştığı an devreye girecek "static blok"
    static {
        String path = "configuration.properties"; // Dosya yolu
        try {
            // Dosyayı okumak için akış (stream) aç
            FileInputStream fileInputStream = new FileInputStream(path);

            // Properties objesini başlat
            properties = new Properties();

            // Dosyadaki bilgileri (key=value) yükle
            properties.load(fileInputStream);

            // Dosyayı kapat (Hafızayı yormasın)
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("️HATA: configuration.properties dosyası okunamadı");
            e.printStackTrace();
        }
    }

    // 3- Dışarıdan veri istendiğinde (getProperty) çalışacak metot
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
