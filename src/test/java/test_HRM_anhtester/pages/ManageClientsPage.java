package test_HRM_anhtester.pages;

import main_HRM_anhtester.driver.DriverManager;
import main_HRM_anhtester.utils.KeyWork;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManageClientsPage extends CommonPage {

    //Element List "Add New Client"
    private final By clickManageClientsTilte = By.xpath("/html/body/nav/div/div/ul/li[17]/a");
    private final By clickOnAddClientButton = By.xpath("//div[1]//div[1]/div[1]/a[2]");
    private final By inputFirstNameTextbox = By.xpath("//input[@placeholder='First Name']");
    private final By inputLastNameTextbox = By.xpath("//input[@placeholder='Last Name']");
    private final By inputPasswordTextbox = By.xpath("//input[@placeholder='Password']");
    private final By inputContractNumberTextbox = By.xpath("//input[@placeholder='Contact Number']");
    private final By inputEmailTextbox = By.xpath("//input[@placeholder='Email']");
    private final By selectGenderDropdown = By.xpath("//input[@placeholder='First Name']");
    private final By inputUsernameTextbox = By.xpath("//input[@placeholder='Username']");
    private final By clickBrowseAttachmentButton = By.xpath("//*[@id=\"xin-form\"]/div[1]/div[2]/div/div[2]/div/div/div/div");
    private final By clickOnSaveButton = By.xpath("//div[@class='card-footer text-right']//button[@type='submit']");

    public void addNewClient(String email) {
        KeyWork.scrollToElement(clickManageClientsTilte);
        KeyWork.clickElementWithJS(clickManageClientsTilte);
        KeyWork.clickElement(clickOnAddClientButton);
        KeyWork.setText(inputFirstNameTextbox, "Nguyen");
        KeyWork.setText(inputLastNameTextbox, "Tai");
        KeyWork.setText(inputPasswordTextbox, "111111");
        KeyWork.setText(inputContractNumberTextbox, "1");
        KeyWork.setText(inputEmailTextbox, email);
        KeyWork.setText(inputUsernameTextbox, "tainguyen");
        KeyWork.clickElement(clickBrowseAttachmentButton);
        KeyWork.uploadFileRobotClass("C:\\HRM_TaiQC\\src\\main\\java\\Main_HRM_AnhTester\\DataTest\\file\\ProfilePicture.jpg");
        KeyWork.clickElement(clickOnSaveButton);
    }

    //Element List "Verify Add New Client"
    private final By getAddNewClientSuccessMessage = By.xpath("//div[contains(text(),'Client added.')]");

    public void verifyAddNewClient() {
        KeyWork.verifyElementText(getAddNewClientSuccessMessage, "Client added.");
    }

    final By getEmailClientText = By.xpath("//table/tbody/tr");
    final By selectValuePagingDropdown = By.xpath("//select[@name='xin_table_length']");

    public void findToClientEdit(String value) {
        KeyWork.scrollToElement(clickManageClientsTilte);
        KeyWork.clickElementWithJS(clickManageClientsTilte);
        KeyWork.selectValueDropdown(selectValuePagingDropdown, "100");
        KeyWork.moveToElement(getEmailClientText);
        int rowtotal = KeyWork.getSize(getEmailClientText, 0);
        KeyWork.sleep(2);
        for (int i = 1; i <= rowtotal; i++) {
            KeyWork.LogConsole("dòng" + i);
            WebElement elementCheck = KeyWork.getWebElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div[1]/div/p"));

            if ((elementCheck.getText()).equals(value)){
                KeyWork.sleep(1);
                KeyWork.moveToElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[1]/a[1]/button[1]"));
                KeyWork.clickElementWithJS(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[1]/a[1]/button[1]"));
                System.out.println("Da tim thay user");
                KeyWork.sleep(2);
                break;
            }
        }
    }
    final By clickOnStatusDropdown = By.xpath("//form//div[1]/div[5]/div/span");
    final By clickOnValueStatusDropdown = By.xpath("//span/span[2]/ul/li[2]");
    final By clickOnCountryDropdown = By.xpath("//form/div[1]//div[8]/div/span");
    final By clickOnValueCountryDropdown = By.xpath("//span[2]/ul/li[237]");
    final By inputAddressTextbox = By.xpath("//input[@placeholder='Address']");
    final By inputAddressLine2Textbox = By.xpath("//input[@placeholder='Address Line 2']");
    final By inputCityTextbox = By.xpath("//input[@placeholder='City']");
    final By clickOnSaveEditClientButton = By.xpath("//div[@class='card-body']//button[@type='submit']");
    public void editClient(){
        KeyWork.setText(inputFirstNameTextbox, "Dang");
        KeyWork.setText(inputLastNameTextbox, "Nhan");
        KeyWork.setText(inputEmailTextbox, "nhandang@gmail.com");
        KeyWork.setText(inputUsernameTextbox, "nhandang");
        KeyWork.clickElement(clickOnStatusDropdown);
        KeyWork.clickElement(clickOnValueStatusDropdown);
        KeyWork.setText(inputContractNumberTextbox, "0708663153");
        KeyWork.clickElement(clickOnCountryDropdown);
        KeyWork.clickElement(clickOnValueCountryDropdown);
        KeyWork.setText(inputAddressTextbox,"865 Quang Trung");
        KeyWork.setText(inputAddressLine2Textbox,"P14 Go Vap");
        KeyWork.setText(inputCityTextbox,"TP HCM");
        KeyWork.clickElement(clickOnSaveEditClientButton);
    }

    private final By getEditClientSuccessMessage = By.xpath("//div[contains(text(),'Client updated.')]");

    public void verifyEditClient() {
        KeyWork.verifyElementText(getEditClientSuccessMessage, "Client updated.");
    }

    public void findToClientDelete(String value) {
        KeyWork.scrollToElement(clickManageClientsTilte);
        KeyWork.clickElementWithJS(clickManageClientsTilte);
        KeyWork.selectValueDropdown(selectValuePagingDropdown, "100");
        KeyWork.moveToElement(getEmailClientText);
        int rowtotal = KeyWork.getSize(getEmailClientText, 0);
        KeyWork.sleep(2);
        for (int i = 1; i <= rowtotal; i++) {
            KeyWork.LogConsole("dòng" + i);
            WebElement elementCheck = KeyWork.getWebElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div[1]/div/p"));

            if ((elementCheck.getText()).equals(value)){
                KeyWork.sleep(1);
                KeyWork.moveToElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[2]/button[1]"));
                KeyWork.clickElementWithJS(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[2]/button[1]"));
                System.out.println("Da tim thay user");
                KeyWork.sleep(2);
                break;
            }
        }
    }

    final By clickOnConfirmDeleteButton = By.xpath("//div[@class='modal-footer']//button[@type='submit']");
    public void deleteClient(){
        KeyWork.clickElementWithJS(clickOnConfirmDeleteButton);
    }

    private final By getDeleteClientSuccessMessage = By.xpath("//div[contains(text(),'Client deleted.')]");

    public void verifyDeleteClient() {
        KeyWork.verifyElementText(getDeleteClientSuccessMessage, "Client deleted.");
    }
}
