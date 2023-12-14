package test_HRM_anhtester.testcases;

import main_HRM_anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import test_HRM_anhtester.base.BaseTest;

import org.testng.annotations.Test;


public class ManageClientsTest extends BaseTest {

    @Test
    public void addNewClient() {

        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );

        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","addNewClient");
        getManageClientsPage().addNewClient(
                getExcel().getCellData("FirstName",1),
                getExcel().getCellData("LastName",1),
                getExcel().getCellData("Password",1),
                getExcel().getCellData("ContactNumber",1),
                getExcel().getCellData("Email",1),
                getExcel().getCellData("Username",1)
        );
        getManageClientsPage().verifyAddNewClient();
    }

    @Test (priority = 1)
    public void editClient(){
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );

        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","editNewClient");
        getManageClientsPage().findToClientEdit(
                getExcel().getCellData("Value",1)
        );

        getManageClientsPage().editClient(
                getExcel().getCellData("FirstName",1),
                getExcel().getCellData("LastName",1),
                getExcel().getCellData("Email",1),
                getExcel().getCellData("Username",1),
                getExcel().getCellData("ContactName",1),
                getExcel().getCellData("Address",1),
                getExcel().getCellData("AddressLine2",1),
                getExcel().getCellData("City",1)
        );
         getManageClientsPage().verifyEditClient();
    }

    @Test(priority = 2)
    public void deleteClient(){
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );

        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","deleteClient");
        getManageClientsPage().findToClientDelete(
                getExcel().getCellData("Value",1)
        );

        getManageClientsPage().deleteClient();
        getManageClientsPage().verifyDeleteClient();
    }
}
