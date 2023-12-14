package test_HRM_anhtester.pages;


import main_HRM_anhtester.helpers.ExcelHelper;

public class CommonPage {
    public ExcelHelper excel;
    public ManageClientsPage manageClientsPage;
    public LoginPage loginPage;

    public ExcelHelper getExcel() {
        if (excel == null) {
            excel = new ExcelHelper();
        }
        return excel;
    }


    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }
    public ManageClientsPage getManageClientsPage() {
        if (manageClientsPage == null) {
            manageClientsPage = new ManageClientsPage();
        }
        return manageClientsPage;
    }

}
