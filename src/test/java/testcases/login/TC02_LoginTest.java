package testcases.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class TC02_LoginTest extends BaseTest {

    //class variable
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void testValidLogin() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        System.out.println("driver = " + driver);
        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");

        //Step 2: Click 'Đăng Nhập' link
        homePage.navigateLoginPage();

        //Step 3: Enter account
        String newAcount = "Testaefad88de3ff4ca2b9d3679f1199415c";
        loginPage.enterAccount(newAcount);

        //Step 4: Enter password
        loginPage.enterPassword("Test123456@");

        //Step 5: Click login
        loginPage.clickLogin();

        //Step 6: Verify login successully
        //VP1: "Đăng nhập thành công" message displays
        String actualLoginMsg = loginPage.getMessage();
        Assert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");

        //VP2: User profile name displays
        String actualDisplayName = homePage.getUserProfileName();
        Assert.assertEquals(actualDisplayName, "John Kenny", "User Profile name");

    }

    @Test
    public void testinvalidLogin() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        System.out.println("driver = " + driver);
        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");

        //Step 2: Click 'Đăng Nhập' link
        homePage.navigateLoginPage();

        //Step 3: Enter account
        String newAcount = "Testaefad88de3ff4ca2b9d3679f1199415c";
        loginPage.enterAccount(newAcount);

        //Step 4: Click login
        loginPage.clickLogin();

        //Step 5: Verify login fail
        //VP1: "Đây là trường bắt buộc" message displays
        String actualLoginfailedMsg = loginPage.getMessageFailed();
        Assert.assertEquals(actualLoginfailedMsg, "Đây là trường bắt buộc !", "Login failed");
    }

    @Test
    public void testLoginfailedAccount() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        System.out.println("driver = " + driver);
        Reporter.log("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");

        //Step 2: Click 'Đăng Nhập' link
        homePage.navigateLoginPage();

        //Step 3: Enter password
        loginPage.enterPassword("Test123456@");

        //Step 4: Click login
        loginPage.clickLogin();

        //Step 5: Verify login failed
        //VP1: "Đây là trường bắt buộc" message displays
        String actualLoginfailedMsg = loginPage.getMessagefailedAccount();
        Assert.assertEquals(actualLoginfailedMsg, "Đây là trường bắt buộc !", "Login failed");
    }

    @Test
    public void testLoginWithInvalidAccount() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        driver.get("https://demo1.cybersoft.edu.vn");
        homePage.navigateLoginPage();

        loginPage.enterAccount("invalid_account_123");
        loginPage.enterPassword("Test123456@");
        loginPage.clickLogin();

        String actualLoginPasswordErrorMsg = loginPage.getMessageErrorPassword();
        Assert.assertEquals(actualLoginPasswordErrorMsg, "Tài khoản hoặc mật khẩu không đúng!", "Invalid account message");
    }
}
