package Test_HRM_AnhTester.Pages;


import Main_HRM_AnhTester.Helpers.ExcelHelper;

public class CommonPage {
    public ExcelHelper excel;


    public ExcelHelper getExcel() {
        if (excel == null) {
            excel = new ExcelHelper();
        }
        return excel;
    }

}
