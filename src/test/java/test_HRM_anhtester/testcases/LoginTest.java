package test_HRM_anhtester.testcases;

import test_HRM_anhtester.base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void TC_loginAdmin(String Username, String Password){
        getLoginPage().loginAdmin(Username,Password);
    }
}
