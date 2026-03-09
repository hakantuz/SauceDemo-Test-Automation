package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {

    protected WebDriver driver;
    protected static ExtentReports extentReports;
    protected static ExtentSparkReporter extentHtmlReporter;
    protected static ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        // 1. Rapor Yolu (Klasör yoksa oluşturur)
        String path = System.getProperty("user.dir") + "/test-output";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        // 2. Dosya İsmi (Tarihli)
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String filePath = path + "/Rapor_" + date + ".html";

        // 3. Raporu Başlat
        extentHtmlReporter = new ExtentSparkReporter(filePath);
        extentHtmlReporter.config().setDocumentTitle("SauceDemo Raporu");
        extentHtmlReporter.config().setReportName("Otomasyon Testleri");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Tester", "Hakan Komutan");

        System.out.println("📢 RAPOR BAŞLATILDI: " + filePath);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(java.lang.reflect.Method method) {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        extentTest = extentReports.createTest(method.getName());
        extentTest.info("Test Adımı Başladı.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("❌ HATA: " + result.getName());
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("✅ GEÇTİ: " + result.getName());
        } else {
            extentTest.skip("⚠️ ATLANDI: " + result.getName());
        }

        Driver.closeDriver();

        //HER TESTTEN SONRA KAYDET
        extentReports.flush();
        System.out.println("💾 Rapor anlık olarak kaydedildi.");
    }


}