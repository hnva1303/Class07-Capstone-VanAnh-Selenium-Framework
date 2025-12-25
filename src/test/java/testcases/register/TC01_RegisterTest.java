package testcases.register;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import reports.ExtentReportManager;

import java.util.UUID;

public class TC01_RegisterTest extends BaseTest {

    //khoi tao page
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;

    //priority số nhỏ sẽ chạy trước priority số lớn
    @Test(priority = 0)
    public void testRegister() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        LOG.info(driver + " - testRegister running...");

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        ExtentReportManager.info("Step 1: Go to https://demo1.cybersoft.edu.vn");
        LOG.info("Step 1: Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");
        //Click on 'Dang Ky'
        homePage.navigateRegisterPage();

        //Step 2: Enter value on 'Tai khoan' textbox
        ExtentReportManager.info("Step 2: Enter value on 'Tai khoan' textbox");
        LOG.info("Step 2: Enter value on 'Tai khoan' textbox");
        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        LOG.info(newAcount);
        registerPage.enterAccount(newAcount);

        //Step 3: Enter value on 'Mat Khau' textbox
        ExtentReportManager.info("Step 3: Enter value on 'Mat Khau' textbox");
        LOG.info("Step 3: Enter value on 'Mat Khau' textbox");
        registerPage.enterPassword("Test123456@");

        //Step 4: Enter value on 'Nhap Lai Mat Khau' textbox
        ExtentReportManager.info("Step 4: Enter value on 'Nhap Lai Mat Khau' textbox");
        LOG.info("Step 4: Enter value on 'Nhap Lai Mat Khau' textbox");
        registerPage.enterConfirmPassword("Test123456@");

        //Step 5: Enter value on 'Ho ten' textbox
        ExtentReportManager.info("Step 5: Enter value on 'Ho ten' textbox");
        LOG.info("Step 5: Enter value on 'Ho ten' textbox");
        registerPage.enterName("John Kenny");

        //Step 6: Enter value on 'Email' textbox
        ExtentReportManager.info("Step 6: Enter value on 'Email' textbox");
        LOG.info("Step 6: Enter value on 'Email' textbox");
        String email = newAcount + "@example.com";
        registerPage.enterEmail(email);

        //Step 7: Click 'Dang Ky' button
        ExtentReportManager.info("Step 7: Click 'Dang Ky' button");
        LOG.info("Step 7: Click 'Dang Ky' button");
        registerPage.clickRegister();

        //Step 8: Verify register successfully
        ExtentReportManager.info("Step 8: Verify register successfully");
        ExtentReportManager.info("VP: \"Đăng ký thành công\" message displays");
        LOG.info("Step 8: Verify register successfully");
        LOG.info("VP: \"Đăng ký thành công\" message displays");
        //VP: "Đăng ký thành công" message displays
        String actualMsg = registerPage.getMessage();
        Assert.assertEquals(actualMsg, "Đăng ký thành công", "Register message");

        //VP: Verify new account login successfully
        ExtentReportManager.info("VP: Verify new account login successfully");
        LOG.info("VP: Verify new account login successfully");
        driver.get("https://demo1.cybersoft.edu.vn");
        homePage.navigateLoginPage();

        //Enter account
        //Low Level Action --> High Level Action (Business action)
//        loginPage.enterAccount(newAcount);
//        loginPage.enterPassword("Test123456@");
//        loginPage.clickLogin();
        loginPage.login(newAcount, "Test123456@");

        ExtentReportManager.info("VP1: \"Đăng nhập thành công\" message displays");
        LOG.info("VP1: \"Đăng nhập thành công\" message displays");
        //VP1: "Đăng nhập thành công" message displays
        String actualLoginMsg = loginPage.getMessage();
        Assert.assertEquals(actualLoginMsg, "Đăng nhập thành công", "Login message");

        ExtentReportManager.info("VP2: User profile name displays");
        LOG.info("VP2: User profile name displays");
        //VP2: User profile name displays
        String actualDisplayName = homePage.getUserProfileName();
        Assert.assertEquals(actualDisplayName, "John Kenny", "User Profile name");
    }

    @Test(priority = 1)
    public void testRegisterDuplicateAccount() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        LOG.info(driver + " - testRegisterDuplicateAccount running...");

        //Step 1: Go to https://demo1.cybersoft.edu.vn
        ExtentReportManager.info("Go to https://demo1.cybersoft.edu.vn");
        LOG.info("Go to https://demo1.cybersoft.edu.vn");
        driver.get("https://demo1.cybersoft.edu.vn");
        homePage.navigateRegisterPage();

        //Step 2: Create new account and register successfully (Pre-condition)
        ExtentReportManager.info("Create new account and register successfully (Pre-condition)");
        LOG.info("Create new account and register successfully (Pre-condition)");

        String account = "Test" + UUID.randomUUID();
        String newAcount = account.replace("-", "");
        LOG.info(newAcount);

        registerPage.enterAccount(newAcount);
        registerPage.enterPassword("Test123456@");
        registerPage.enterConfirmPassword("Test123456@");
        registerPage.enterName("John Kenny");
        String email1 = newAcount + "@example.com";
        registerPage.enterEmail(email1);

        //Step 3: Click 'Dang Ky' button
        ExtentReportManager.info("Click 'Dang Ky' button");
        LOG.info("Click 'Dang Ky' button");
        registerPage.clickRegister();

        //Step 4: Verify register successfully
        ExtentReportManager.info("Verify register successfully");
        ExtentReportManager.info("\"Đăng ký thành công\" message displays");
        LOG.info("Verify register successfully");
        String msg1 = registerPage.getMessage();
        Assert.assertEquals(msg1, "Đăng ký thành công", "Register message (1st)");

        //Step 5: Go to homepage and navigate Register again
        ExtentReportManager.info("Go to homepage and navigate Register again");
        LOG.info("Go to homepage and navigate Register again");
        driver.get("https://demo1.cybersoft.edu.vn");
        homePage.navigateRegisterPage();

        //Step 6: Register AGAIN with SAME account but different email
        ExtentReportManager.info("Register AGAIN with SAME account but different email");
        LOG.info("Register AGAIN with SAME account but different email");

        registerPage.enterAccount(newAcount);
        registerPage.enterPassword("Test123456@");
        registerPage.enterConfirmPassword("Test123456@");
        registerPage.enterName("John Kenny");
        String email2 = newAcount + "1@example.com";
        registerPage.enterEmail(email2);

        //Step 7: Click 'Dang Ky' button
        ExtentReportManager.info("Click 'Dang Ky' button");
        LOG.info("Click 'Dang Ky' button");
        registerPage.clickRegister();

        //Step 8: Verify register FAILED (Duplicate account)
        ExtentReportManager.info("Verify register FAILED (Duplicate account)");
        ExtentReportManager.info("VP2: Message should be \"Tài khoản đã tồn tại!\"");
        LOG.info("Verify register FAILED (Duplicate account)");

        String msg2 = registerPage.getMessage();
        Assert.assertNotEquals(msg2, "Tài khoản đã tồn tại!", "Register should fail when duplicate account");
    }
}
