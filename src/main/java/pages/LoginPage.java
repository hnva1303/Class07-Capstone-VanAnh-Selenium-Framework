package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

    private static final String BASE_URL = "https://demo1.cybersoft.edu.vn";

    private By byTxtAccountLogin = By.id("taiKhoan");;
    private By byTxtPasswordLogin = By.id("matKhau");;
    private By byBtnLogin = By.xpath("//button[.='Đăng nhập']");;
    private By byLblLoginMsg = By.id("swal2-title");;
    private By byLblLoginfailedMsg = By.xpath("//p[@id='matKhau-helper-text']");
    private By byLblLoginfailedAccount = By.xpath("//p[@id='taiKhoan-helper-text']");
    private By byLblLoginPasswordErrorMsg = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'Tài khoản hoặc mật khẩu không đúng!')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get(BASE_URL + "/sign-in");
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

    public String getMessagefailedAccount() {
        LOG.info("getAccount1");
        return getText(byLblLoginfailedAccount);
    }

    public String getMessageErrorPassword() {
        LOG.info("getMessage2");
        return getText(byLblLoginPasswordErrorMsg);
    }

    public void login(String account, String password) {
        enterAccount(account);
        enterPassword(password);
        clickLogin();
    }
}
