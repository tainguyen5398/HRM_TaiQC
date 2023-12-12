package test_HRM_anhtester.testcases;

import org.testng.Assert;
import test_HRM_anhtester.base.BaseTest;

import org.testng.annotations.Test;


public class ManageClientsTest extends BaseTest {

    @Test
    public void addNewClient() {
        getLoginPage().loginAdmin("admin_example","123456");
        getManageClientsPage().addNewClient("tainguyen5398@gmail.com");
        getManageClientsPage().verifyAddNewClient();
    }

    @Test (priority = 1)
    public void editClient(){
        getLoginPage().loginAdmin("admin_example","123456");
        getManageClientsPage().findToClientEdit("tainguyen5398@gmail.com");
        getManageClientsPage().editClient();
         getManageClientsPage().verifyEditClient();
    }

    @Test(priority = 2)
    public void deleteClient(){
        getLoginPage().loginAdmin("admin_example","123456");
        getManageClientsPage().findToClientDelete("nhandang@gmail.com");
        getManageClientsPage().deleteClient();
        getManageClientsPage().verifyDeleteClient();
    }
}
