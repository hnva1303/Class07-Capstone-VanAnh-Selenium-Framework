package testcases.bookmovie;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PurchasePage;

public class TC04_BookMovieTicketsTest extends BaseTest {

    private static final String PURCHASE_ID = "42102";

    // TODO: thay bằng account test thật
    private static final String EMAIL = "your_email@example.com";
    private static final String PASSWORD = "your_password";

    private LoginPage loginPage;
    private PurchasePage purchasePage;

    @BeforeMethod
    public void resetState() {
        driver.manage().deleteAllCookies();
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("localStorage.clear(); sessionStorage.clear();");
        } catch (Exception ignored) {}

        loginPage = new LoginPage(driver);
        purchasePage = new PurchasePage(driver);
    }

    // 1) Login -> purchase -> chọn ghế -> mua vé -> success alert
    @Test
    public void testPurchaseWithLoginSelectSeatShouldShowSuccess() {
        loginPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);

        purchasePage.navigateToPurchasePage(PURCHASE_ID);
        purchasePage.selectFirstAvailableSeat();
        purchasePage.clickBuyTicket();

        purchasePage.waitForAlert();
        String title = purchasePage.getAlertTitle();
        String content = purchasePage.getAlertContent();

        String msg = (title + " " + content).toLowerCase();
        Assert.assertTrue(msg.contains("thành công") || msg.contains("success"),
                "Kỳ vọng success alert. Thực tế: " + title + " | " + content);

        purchasePage.confirmAlertIfAny();
    }

    // 2) Login -> purchase -> không chọn ghế -> mua vé -> error alert chưa chọn vé/ghế
    @Test
    public void testPurchaseWithLoginNoSeatShouldShowError() {
        loginPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);

        purchasePage.navigateToPurchasePage(PURCHASE_ID);
        purchasePage.clickBuyTicket();

        purchasePage.waitForAlert();
        String title = purchasePage.getAlertTitle();
        String content = purchasePage.getAlertContent();

        String msg = (title + " " + content).toLowerCase();
        Assert.assertTrue(msg.contains("chưa chọn") || msg.contains("vui lòng chọn"),
                "Kỳ vọng error alert khi chưa chọn ghế. Thực tế: " + title + " | " + content);

        purchasePage.confirmAlertIfAny();
    }

    // 3) Không login -> purchase -> chọn ghế -> mua vé -> alert bắt đăng nhập
    @Test
    public void testPurchaseWithoutLoginShouldRequireLogin() {
        purchasePage.navigateToPurchasePage(PURCHASE_ID);
        purchasePage.selectFirstAvailableSeat();
        purchasePage.clickBuyTicket();

        purchasePage.waitForAlert();
        String title = purchasePage.getAlertTitle();
        String content = purchasePage.getAlertContent();

        String msg = (title + " " + content).toLowerCase();
        Assert.assertTrue(msg.contains("đăng nhập") || msg.contains("login"),
                "Kỳ vọng alert bắt đăng nhập. Thực tế: " + title + " | " + content);

        purchasePage.confirmAlertIfAny();
    }
}
