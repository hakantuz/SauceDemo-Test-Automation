package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter; // <-- ARTIK SPARK DEĞİL, HTML REPORTER
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
    protected static ExtentHtmlReporter extentHtmlReporter; // <-- DEĞİŞEN YER
    protected static ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        try {
            // 1. Klasör Yolu
            String targetFolder = System.getProperty("user.dir") + File.separator + "test-output";
            File dir = new File(targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 2. Dosya Yolu
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String filePath = targetFolder + File.separator + "Rapor_" + date + ".html";

            // 3. RAPOR MOTORUNU BAŞLAT (v4 Mantığı)
            extentHtmlReporter = new ExtentHtmlReporter(filePath);
            extentHtmlReporter.config().setDocumentTitle("SauceDemo Raporu");
            extentHtmlReporter.config().setReportName("Otomasyon Testleri");

            extentReports = new ExtentReports();
            extentReports.attachReporter(extentHtmlReporter);
            extentReports.setSystemInfo("QA Engineer", "Hakan");
            extentReports.setSystemInfo("Browser", "Chrome");

            System.out.println("✅ [SİSTEM] V4 HTML Rapor motoru hazır! Yol: " + filePath);

        } catch (Exception e) {
            System.out.println("❌ [HATA] " + e.getMessage());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(java.lang.reflect.Method method) {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        if (extentReports != null) {
            extentTest = extentReports.createTest(method.getName());
            extentTest.info("Test adımı başladı.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (extentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail("❌ HATA: Test başarısız.");
                extentTest.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("✅ BAŞARILI: Test geçti.");
            } else {
                extentTest.skip("⚠️ ATLANDI.");
            }
        }

        Driver.closeDriver();

        if (extentReports != null) {
            extentReports.flush();
            System.out.println("💾 [SİSTEM] Rapor dosyaya YAZILDI!");
        }
    }
}