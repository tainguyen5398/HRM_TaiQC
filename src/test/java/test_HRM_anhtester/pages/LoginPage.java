package test_HRM_anhtester.pages;

import main_HRM_anhtester.helpers.PropertiesHelper;
import main_HRM_anhtester.utils.KeyWork;
import org.openqa.selenium.By;

public class LoginPage extends CommonPage{

    private By inputYourUsernameTextbox = By.xpath("//input[@id='iusername']");
    private By inputEnterPasswordTextbox = By.xpath("//input[@id='ipassword']");
    private By clickOnLoginButton = By.xpath("//button[@type='submit']");
    private By getHomeTitle = By.xpath("//span[normalize-space()='Home']");

    public void loginAdmin(String Username, String Password){
        KeyWork.openURL(PropertiesHelper.getValue("URL"));
        KeyWork.setText(inputYourUsernameTextbox,Username);
        KeyWork.setText(inputEnterPasswordTextbox,Password);
        KeyWork.clickElement(clickOnLoginButton);
        KeyWork.verifyElementText(getHomeTitle,"Home","Đăng nhập không thành công");
    }
}
