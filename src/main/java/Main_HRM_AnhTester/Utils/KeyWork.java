package Main_HRM_AnhTester.Utils;



import Main_HRM_AnhTester.Driver.DriverManager;
import Main_HRM_AnhTester.Reports.AllureReportManager;
import Main_HRM_AnhTester.Reports.ExtentReportTestManager;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class KeyWork {

    private static final int TIMEOUT = 10;
    private static final double STEP_TIME = 0.5;
    private static final int PAGE_LOAD_TIMEOUT = 20;
    
    private static Robot robot;
    private static JavascriptExecutor js;


    //Hàm thay thế Thread.sleep
    public static void sleep(double second){
        try{
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    //Hàm log thay thế system.out.println
    public static void LogConsole(Object message){
        System.out.println(message);
    }

    //Hàm thay thế DriverManager.getDriver().findElement ( = driver().findElement )
    public static WebElement getWebElement(By by){
        return DriverManager.getDriver().findElement(by);
    }
    //Hàm thay thế DriverManager.getDriver().findElements ( = driver().findElements )
    public static List<WebElement> getWebElements(By by){
        return DriverManager.getDriver().findElements(by);
    }
    //Kiểm tra element có tồn tại không ?
    public static Boolean checkElementExist(By by){
        waitForPageLoaded();
        sleep(2);
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);
        if(listElement.size() > 0){
            Log.info("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            Log.info("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    // Hàm Get Size listElement
    public static int getSize(By by, int number) {
        waitForPageLoaded();
        List<WebElement> row = DriverManager.getDriver().findElements(by);
        int rowtotal = row.size() - number;
        System.out.println("Số phần tử là: " +rowtotal);
        return rowtotal;
    }
    //Hàm so sánh bằng
    public static void Compare2Values(int A, int B, String message) {
        waitForPageLoaded();
        boolean C = A==B;
        Assert.assertTrue(C,message);
    }
    //Hàm mở URL
    @Step("Open URL: {0}")
    public static void openURL(String URL){
        DriverManager.getDriver().get(URL);
        sleep(STEP_TIME);
        //Ghi log chi tiết các steps
        Log.info("Open: " + URL);
        //Ghi chi tiết các steps trong Extent Report
        ExtentReportTestManager.logMessage(Status.PASS, "Open: " + URL);
        //Ghi chi tiết các steps trong Allure Report
        AllureReportManager.saveTextLog("Open: " + URL);
        waitForPageLoaded();
    }

    // Hàm đợi element nhìn thấy với thời gian TIMEOUT cố định
    public static void waitForElementVisible(By by){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT),Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("TIMEOUT: Can not element visible: " + by.toString());
            Assert.fail("TIMEOUT: Can not element visible: " + by.toString());
        }
    }
    //Hàm đợi element nhìn thấy và chủ động tùy chỉnh thời gian timeout
    public static void waitForElementVisible(By by, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout),Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("TIMEOUT: Can not element visible: " + by.toString());
            Assert.fail("TIMEOUT: Can not element visible: " + by.toString());
        }
    }

    // Hàm đợi đến khi element đó tồn tại với thời gian TIMEOUT cố định
    public static void waitForElementPresent(By by){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT),Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Element does not exist: " + by.toString());
            Assert.fail("Element does not exist: " + by.toString());
        }
    }

    // Hàm đợi đến khi element đó tồn tại với thời gian tùy chỉnh timeout
    public static void waitForElementPresent(By by, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout),Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Element does not exist: " + by.toString());
            Assert.fail("Element does not exist: " + by.toString());
        }
    }

    //Hàm đợi đến khi click được element đó với thời gian TIMEOUT cố định
    public static void waitForElementClickable(By by){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT),Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            Log.error("Element not ready to Click: " + by.toString());
            Assert.fail("Element not ready to Click: " + by.toString());
        }
    }

    //Hàm đợi đến khi click được element đó với thời gian timeout tùy chỉnh
    public static void waitForElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            Log.error("Element not ready to Click: " + by.toString());
            Assert.fail("Element not ready to Click: " + by.toString());
        }
    }

    //Hàm click element giống như element.click nhưng bổ sung thêm đợi load trang và element đã có thể click
    @Step("Click element: {0}" )
    public static void clickElement(By by){
        waitForPageLoaded();
        waitForElementVisible(by);
        waitForElementClickable(by);
//        sleep(STEP_TIME);
//        highLightElement(by);
        getWebElement(by).click();
        Log.info("Click on the element: " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Click on the element: " + by);
        AllureReportManager.saveTextLog("Click on the element: " + by);
    }

    //Hàm click với javscript áp dụng khi 1 element không hiển thị trên UI (Không cần scroll tới, Js sẽ hỗ trợ click)
    @Step("Click element: {0} by JS")
    public static void clickElementWithJS(By by){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        waitForPageLoaded();
        waitForElementVisible(by);
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        js.executeScript("arguments[0].click();", getWebElement(by));
        Log.info("Click on the element with JS: " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Click on the element: " + by + " by JS");
        AllureReportManager.saveTextLog("Click on the element: " + by + " by JS");
    }

    //Hàm setText: hàm này kết hợp sẵn Clear và senkeys và có bổ sung hàm đợi load trang và element sẵn sàng thao tác
    @Step("Set text {1} on {0}.")
    public static void setText(By by, String value){
        waitForPageLoaded();
        waitForElementVisible(by);
//        sleep(STEP_TIME);
//        highLightElement(by);
        getWebElement(by).clear();
        getWebElement(by).sendKeys(value);
        Log.info("Set Text: " + value + " on the element " + by);
        ExtentReportTestManager.logMessage(Status.PASS, "Set Text: " + value + " on the element " + by);
        AllureReportManager.saveTextLog("Set Text: " + value + " on the element " + by);
    }

    //Hàm xác minh Element Text và xuất message chủ động
    //Sử dụng hàm này bằng cách gọi nó dùng lại và truyền vào element muốn xác minh - Text muốn so sanh - Thông báo khi lỗi
    @Step("Get text of {0} equal comparison with {1}.")
    public static void verifyElementText(By by, String textValue, String message){
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        Log.info("ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        ExtentReportTestManager.logMessage(Status.PASS, "ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        AllureReportManager.saveTextLog("ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        Log.info("EXPECTED RESULT Text is: " + textValue.trim());
        ExtentReportTestManager.logMessage(Status.PASS, "EXPECTED RESULT Text is: " + textValue.trim());
        AllureReportManager.saveTextLog("EXPECTED RESULT Text is: " + textValue.trim());
        Boolean expected = getWebElement(by).getText().trim().equals(textValue.trim());
        if(expected == true){
            ExtentReportTestManager.logMessage(Status.PASS, "ACTUAL RESULT the same as EXPECTED RESULT");
        }else {
            ExtentReportTestManager.logMessage(Status.FAIL, "ACTUAL RESULT not the same as EXPECTED RESULT");
        }
        Assert.assertTrue(expected,message);
    }

    //Hàm xác minh Element Text và xuất message cố định
    //Sử dụng hàm này bằng cách gọi nó dùng lại và truyền vào element muốn xác minh - Text muốn so sanh
    //Không cần truyền vào message vì đã thiết lập cố định thông báo ở hàng cuối cùng của hàm này
    @Step("Get text of {0} equal comparison with {1}.")
    public static void verifyElementText(By by, String textValue){
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        Log.info("ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        ExtentReportTestManager.logMessage(Status.PASS, "ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        AllureReportManager.saveTextLog("ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText().trim());
        Log.info("EXPECTED RESULT Text is: " + textValue.trim());
        ExtentReportTestManager.logMessage(Status.PASS, "EXPECTED RESULT Text is: " + textValue.trim());
        AllureReportManager.saveTextLog("EXPECTED RESULT Text is: " + textValue.trim());
        Boolean expected = getWebElement(by).getText().trim().equals(textValue.trim());
        if(expected == true){
            ExtentReportTestManager.logMessage(Status.PASS, "ACTUAL RESULT the same as EXPECTED RESULT");
        }else {
            ExtentReportTestManager.logMessage(Status.FAIL, "ACTUAL RESULT not the same as EXPECTED RESULT");
        }
        Assert.assertTrue(expected, "ACTUAL RESULT: Text of the element is: " + getWebElement(by).getText() + "\nDoes not: " + textValue);
    }
    public static void verifyElementAttribute(By by, String textValue){
        waitForPageLoaded();
        waitForElementVisible(by);
        Boolean expected = getWebElement(by).getAttribute("value").trim().equals(textValue.trim());
        Assert.assertTrue(expected, "ACTUAL RESULT: Text of the element is: " + getWebElement(by).getAttribute("value") + "\nDoes not: " + textValue);
    }
    public static void verifyCheckboxSelected(By by){
        waitForPageLoaded();
        waitForElementVisible(by);
        Boolean expected = getWebElement(by).isSelected();
        Assert.assertFalse(expected,"Checkbox is selected");
    }

    //Hàm xác minh URL và xuất massage chủ động
    //Hàm này để xác mình URL hiện tại có chứa giá trị mình truyền vào hay không
    //Muốn sử dụng chỉ cần Gọi và truyền vào nó giá trị URL muốn so sánh và truyền thông báo khi có lỗi
    @Step("Get current URL contains {0} or not.")
    public static void verifyURL(String myURL, String message){
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(TIMEOUT),Duration.ofMillis(500));
        wait.until(ExpectedConditions.urlContains(myURL));
        sleep(STEP_TIME);
        Log.info("The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        ExtentReportTestManager.logMessage(Status.PASS, "The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        AllureReportManager.saveTextLog("The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        Log.info("The address that needs to be contained in the URL is: " + myURL);
        ExtentReportTestManager.logMessage(Status.PASS, "The address that needs to be contained in the URL is: " + myURL);
        AllureReportManager.saveTextLog("The address that needs to be contained in the URL is: " + myURL);
        Boolean expected = DriverManager.getDriver().getCurrentUrl().contains(myURL);
        if (expected == true) {
            ExtentReportTestManager.logMessage(Status.PASS,"The current URL contains:"  +myURL);
        } else{
            ExtentReportTestManager.logMessage(Status.FAIL,"The current URL doesn't contains"  +myURL);
        }
        Assert.assertTrue(expected,message);
    }

    //Hàm xác minh URL và xuất massage cố định
    //Hàm này để xác mình URL hiện tại có chứa giá trị mình truyền vào hay không
    //Muốn sử dụng chỉ cần Gọi và truyền vào nó giá trị URL muốn so sánh.
    //Message đã được thiết lập cố định ở dòng cuối cùng của Hàm này
    @Step("Get current URL contains {0} or not.")
    public static void verifyURL(String myURL){
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(TIMEOUT),Duration.ofMillis(500));
        wait.until(ExpectedConditions.urlContains(myURL));
        sleep(STEP_TIME);
        Log.info("The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        ExtentReportTestManager.logMessage(Status.PASS, "The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        AllureReportManager.saveTextLog("The current URL is: " + DriverManager.getDriver().getCurrentUrl());
        Log.info("The address that needs to be contained in the URL is: " + myURL);
        ExtentReportTestManager.logMessage(Status.PASS, "The address that needs to be contained in the URL is: " + myURL);
        AllureReportManager.saveTextLog("The address that needs to be contained in the URL is: " + myURL);
        Boolean expected = DriverManager.getDriver().getCurrentUrl().contains(myURL);
        if (expected == true) {
            ExtentReportTestManager.logMessage(Status.PASS,"The current URL contains:" + myURL);
        } else{
            ExtentReportTestManager.logMessage(Status.FAIL,"The current URL doesn't contains"  +myURL);
        }
        Assert.assertTrue(expected, "The current URL is: " + DriverManager.getDriver().getCurrentUrl() + "\ndoesn't contains" + myURL );
    }


    //Hàm để chọn và tải tệp từ máy tính
    //Muốn sử dụng chỉ cần gọi lại tên hàm và truyền vào cho nó Vị trí đầy đủ của File muốn tải lên
    @Step("Upload file {0} to the system")
    public static void UploadFile(String FileLocation) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        StringSelection stringSelection = new StringSelection(FileLocation);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static boolean pressCtrlC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_C);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            Log.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Log.error("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            Log.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            Log.info("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

}