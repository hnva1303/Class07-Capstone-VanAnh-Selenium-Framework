package testcases.bookmovie;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC04_BookMovieTicketsTest extends BaseTest {

    private static final String BASE_URL = "https://demo1.cybersoft.edu.vn";

    @BeforeMethod
    public void resetState() {
        driver.manage().deleteAllCookies();
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("localStorage.clear(); sessionStorage.clear();");
        } catch (Exception ignored) {}
    }

    // BM-01: Home displays movies
    @Test
    public void testHomeShouldDisplayMovies() {
        driver.get(BASE_URL + "/");

        List<WebElement> movies =
                driver.findElements(By.cssSelector("a[href*='/detail/']"));

        Reporter.log("Movies found: " + movies.size(), true);
        Assert.assertTrue(movies.size() > 0,
                "Trang Home phải hiển thị danh sách phim.");
    }

    // BM-02: Click MUA VÉ NGAY -> Movie Detail
    @Test
    public void testClickBuyTicketShouldOpenDetail() {
        driver.get(BASE_URL + "/");

        List<WebElement> buyButtons =
                driver.findElements(By.xpath("//button[contains(.,'MUA VÉ NGAY')]"));

        if (buyButtons.isEmpty())
            throw new SkipException("Không tìm thấy nút MUA VÉ NGAY.");

        buyButtons.get(0).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/detail/"),
                "Click MUA VÉ NGAY phải vào trang chi tiết phim.");
    }

    // BM-03: Movie detail shows title
    @Test
    public void testMovieDetailShouldShowTitle() {
        driver.get(BASE_URL + "/");

        List<WebElement> movieLinks =
                driver.findElements(By.cssSelector("a[href*='/detail/']"));

        if (movieLinks.isEmpty())
            throw new SkipException("Không có phim để test.");

        movieLinks.get(0).click();

        List<WebElement> titles =
                driver.findElements(By.xpath("//h1 | //h2 | //h3"));

        Assert.assertTrue(titles.size() > 0,
                "Trang chi tiết phim phải hiển thị tiêu đề.");
    }

    // BM-04: Movie detail shows showtime info
    @Test
    public void testMovieDetailShouldShowShowtime() {
        driver.get(BASE_URL + "/");

        List<WebElement> movieLinks =
                driver.findElements(By.cssSelector("a[href*='/detail/']"));

        if (movieLinks.isEmpty())
            throw new SkipException("Không có phim để test.");

        movieLinks.get(0).click();

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        List<WebElement> showtimes =
                driver.findElements(By.xpath(
                        "//*[contains(text(),'Rạp') or contains(text(),'Suất')]"));

        Reporter.log("Showtime elements: " + showtimes.size(), true);
        Assert.assertTrue(showtimes.size() > 0,
                "Trang chi tiết phim phải hiển thị lịch chiếu.");
    }
}
