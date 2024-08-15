import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTest() throws InterruptedException {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79000000000");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector("[role=button]")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals (expected,actual.trim());
    }
}
