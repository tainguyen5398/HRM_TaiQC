package test_HRM_anhtester.pages;

import main_HRM_anhtester.driver.DriverManager;
import main_HRM_anhtester.helpers.PropertiesHelper;
import main_HRM_anhtester.utils.KeyWork;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

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

    public void addNewClient(String FirstName, String LastName, String Password, String ContactNumber, String Email, String Username ) {
        KeyWork.scrollToElement(clickManageClientsTilte);
        KeyWork.clickElementWithJS(clickManageClientsTilte);
        KeyWork.clickElement(clickOnAddClientButton);
        KeyWork.setText(inputFirstNameTextbox, FirstName);
        KeyWork.setText(inputLastNameTextbox, LastName);
        KeyWork.setText(inputPasswordTextbox, Password);
        KeyWork.setText(inputContractNumberTextbox, ContactNumber);
        KeyWork.setText(inputEmailTextbox, Email);
        KeyWork.setText(inputUsernameTextbox, Username);
        KeyWork.clickElement(clickBrowseAttachmentButton);
        KeyWork.uploadFileRobotClass(PropertiesHelper.getValue("ProfilePicture"));
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
            WebElement elementCheck = KeyWork.getWebElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div[1]/div/p"));

            if ((elementCheck.getText()).equals(value)) {
                KeyWork.sleep(1);
                KeyWork.moveToElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[1]/a[1]/button[1]"));
                KeyWork.clickElementWithJS(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[1]/a[1]/button[1]"));
                System.out.println("Da tim thay user");
                KeyWork.sleep(2);
                break;
            }
            else {
                if (i == rowtotal) {
                    KeyWork.LogConsole("Đã tìm toàn bộ danh sách Client NHƯNG không thấy Client mong muốn");
                }
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

    public void editClient(String FirstName, String LastName, String Email, String Username, String ContactNumber, String Address, String AddressLine2, String City) {
        KeyWork.setText(inputFirstNameTextbox, FirstName);
        KeyWork.setText(inputLastNameTextbox, LastName);
        KeyWork.setText(inputEmailTextbox, Email);
        KeyWork.setText(inputUsernameTextbox, Username);
        KeyWork.clickElement(clickOnStatusDropdown);
        KeyWork.clickElement(clickOnValueStatusDropdown);
        KeyWork.setText(inputContractNumberTextbox, ContactNumber);
        KeyWork.clickElement(clickOnCountryDropdown);
        KeyWork.clickElement(clickOnValueCountryDropdown);
        KeyWork.setText(inputAddressTextbox, Address);
        KeyWork.setText(inputAddressLine2Textbox, AddressLine2);
        KeyWork.setText(inputCityTextbox, City);
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
            WebElement elementCheck = KeyWork.getWebElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div[1]/div/p"));

            if ((elementCheck.getText()).equals(value)) {
                KeyWork.sleep(1);
                KeyWork.moveToElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[2]/button[1]"));
                KeyWork.clickElementWithJS(By.xpath("//tbody/tr[" + i + "]/td[1]/div[2]/span[2]/button[1]"));
                System.out.println("Da tim thay user");
                KeyWork.sleep(2);
                break;
            }
            else {
                if (i == rowtotal) {
                    KeyWork.LogConsole("Không tìm thấy Client để có thể xóa");
                }
            }
        }
    }

    final By clickOnConfirmDeleteButton = By.xpath("//div[@class='modal-footer']//button[@type='submit']");

    public void deleteClient() {
        KeyWork.clickElementWithJS(clickOnConfirmDeleteButton);
    }

    private final By getDeleteClientSuccessMessage = By.xpath("//div[contains(text(),'Client deleted.')]");

    public void verifyDeleteClient() {
        KeyWork.verifyElementText(getDeleteClientSuccessMessage, "Client deleted.");
    }
}
