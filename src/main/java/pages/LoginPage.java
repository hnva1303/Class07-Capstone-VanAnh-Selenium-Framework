package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

    private By byTxtAccountLogin = By.id("taiKhoan");;
    private By byTxtPasswordLogin = By.id("matKhau");;
    private By byBtnLogin = By.xpath("//button[.='Đăng nhập']");;
    private By byLblLoginMsg = By.id("swal2-title");;
    private By byLblLoginfailedMsg = By.xpath("//p[@id='matKhau-helper-text']");
    private By byLblLoginfailedAccount = By.xpath("//p[@id='taiKhoan-helper-text']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccount(String account) {
        LOG.info("enterAccount: " + account);
        sendKeys(byTxtAccountLogin, account);
    }

    public void enterPassword(String password) {
        LOG.info("enterPassword: " + password);
        sendKeys(byTxtPasswordLogin, password);
    }

    public void clickLogin() {
        LOG.info("clickLogin");
        click(byBtnLogin);
    }

    public String getMessage() {
        LOG.info("getMessage");
        return getText(byLblLoginMsg);
    }

    public String getMessageFailed() {
        LOG.info("getMessage1");
        return getText(byLblLoginfailedMsg);
    }

    public String getAccountFailed() {
        LOG.info("getAccount1");
        return getText(byLblLoginfailedAccount);
    }

    public void login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        clickLogin();
    }
}
