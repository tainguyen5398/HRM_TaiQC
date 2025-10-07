package test_HRM_anhtester.testcases;

import test_HRM_anhtester.base.BaseTest;

import org.testng.annotations.Test;


public class ManageClientsTest extends BaseTest {

    @Test
    public void addNewClient() {
        //Login to HRM as Admin
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );

        //Add new Client
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","addNewClient");
        getManageClientsPage().addNewClient(
                getExcel().getCellData(2,2),
                getExcel().getCellData(3,2),
                getExcel().getCellData(4,2),
                getExcel().getCellData(5,2),
                getExcel().getCellData(6,2),
                getExcel().getCellData(7,2)
        );
        //Verify the result add new client
        getManageClientsPage().verifyAddNewClient();
    }

    @Test (priority = 1)
    public void editClient(){
        //Login to HRM as Admin
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );

        //Add new Client to Edit
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","editNewClient");
        getManageClientsPage().addNewClient(
                getExcel().getCellData(2,2),
                getExcel().getCellData(3,2),
                getExcel().getCellData(4,2),
                getExcel().getCellData(5,2),
                getExcel().getCellData(6,2),
                getExcel().getCellData(7,2)
        );
        getManageClientsPage().verifyAddNewClient();

        //Find to Client to Edit
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","editNewClient");
        getManageClientsPage().findToClientEdit(
                getExcel().getCellData(6,2)
        );

        //Edit Client
        getManageClientsPage().editClient(
                getExcel().getCellData(2,6),
                getExcel().getCellData(3,6),
                getExcel().getCellData(4,6),
                getExcel().getCellData(5,6),
                getExcel().getCellData(6,6),
                getExcel().getCellData(7,6),
                getExcel().getCellData(8,6),
                getExcel().getCellData(9,6)
        );
         getManageClientsPage().verifyEditClient();
    }

    @Test(priority = 2)
    public void deleteClient(){
        //Login to HRM as Admin
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/LoginPage.xlsx","loginAdmin");
        getLoginPage().loginAdmin(
                getExcel().getCellData("Username",1),
                getExcel().getCellData("Password",1)
        );
        //Add new Client to Delete
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","deleteClient");
        getManageClientsPage().addNewClient(
                getExcel().getCellData(2,2),
                getExcel().getCellData(3,2),
                getExcel().getCellData(4,2),
                getExcel().getCellData(5,2),
                getExcel().getCellData(6,2),
                getExcel().getCellData(7,2)
        );
        getManageClientsPage().verifyAddNewClient();

        //Find to Client to Delete
        getExcel().setExcelFile("src/main/java/main_HRM_anhtester/datatest/excel/ManageClient.xlsx","deleteClient");
        getManageClientsPage().findToClientDelete(
                getExcel().getCellData(6,2)
        );

        //Delete Client and Verify the result
        getManageClientsPage().deleteClient();
        getManageClientsPage().verifyDeleteClient();
    }
}
