package Test_HRM_AnhTester.Base;




import Main_HRM_AnhTester.Driver.DriverManager;
import Main_HRM_AnhTester.Helpers.PropertiesHelper;
import Test_HRM_AnhTester.Listeners.TestListener;
import Test_HRM_AnhTester.Pages.CommonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest extends CommonPage {

    public BaseTest() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public static void createDriver(@Optional("chrome") String browserName) throws Exception {
//       RecordVideo.startRecord("TC1_login");
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
    }

    public static WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(40));
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(40));
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(40));
        return driver;
    }

    @AfterMethod
    public static void closeDriver(ITestResult result) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//           RecordVideo.stopRecord();
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
