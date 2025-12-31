package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.util.List;

public class PurchasePage extends BasePage {

    private static final String BASE_URL = "https://demo1.cybersoft.edu.vn";

    // Seat buttons (có thể bạn cần chỉnh theo DOM thực tế)
    // Mặc định: button.ghe là ghế, gheDaDat là đã đặt
    private final By availableSeats = By.xpath("//button[not(@disabled)][not(.='ĐẶT VÉ')]");

    // Nút mua vé/đặt vé
    private final By btnBuyTicket = By.xpath("//button[.='ĐẶT VÉ']");

    // SweetAlert2
    private final By alertTitle = By.cssSelector("h2#swal2-title, .swal2-title");
    private final By alertContent = By.cssSelector("#swal2-html-container, .swal2-html-container");
    private final By alertConfirm = By.cssSelector("button.swal2-confirm");

    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToPurchasePage(String purchaseId) {
        driver.get(BASE_URL + "/purchase/" + purchaseId);

        // wait tối thiểu để page render được ghế hoặc nút mua vé
        // (tránh get xong findElements ngay)
        waitForVisibilityOfElementLocated(btnBuyTicket);
    }

    public void selectFirstAvailableSeat() {
        List<WebElement> seats = driver.findElements(availableSeats);
        if (seats.isEmpty()) {
            throw new SkipException("Không có ghế available để chọn (có thể suất chiếu full ghế hoặc locator chưa đúng).");
        }
        seats.get(0).click();
    }

    public void clickBuyTicket() {
        click(btnBuyTicket);
    }

    public void waitForAlert() {
        waitForVisibilityOfElementLocated(alertTitle);
    }

    public String getAlertTitle() {
        return getText(alertTitle);
    }

    public String getAlertContent() {
        // content có thể không luôn tồn tại
        if (driver.findElements(alertContent).isEmpty()) return "";
        return getText(alertContent);
    }

    public void confirmAlertIfAny() {
        if (!driver.findElements(alertConfirm).isEmpty()) {
            click(alertConfirm);
        }
    }
}

