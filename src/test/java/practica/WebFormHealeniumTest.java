package practica;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebFormHealeniumTest {

    SelfHealingDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("heal-enabled", "true");
        System.setProperty("hlm.server.url", "http://localhost:7878");
        System.setProperty("hlm.imitator.url", "http://localhost:8000");


        WebDriverManager.chromedriver().setup();
        WebDriver delegate = new ChromeDriver();
        delegate.manage().window().maximize();
        System.setProperty("healenium.healing.enabled", "true");

        driver = SelfHealingDriver.create(delegate);

        System.out.println("Healing enabled: " + System.getProperty("healenium.healing.enabled"));
    }

    @Test
    void llenarFormularioConHealenium() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        System.out.println("[Healenium]  Pagina abierta");

        driver.findElement(By.name("my-text")).sendKeys("Kenneth");
        System.out.println("[Healenium]  Text input llenado");

        driver.findElement(By.name("my-password")).sendKeys("pass1234");

        driver.findElement(By.name("my-textarea")).sendKeys("Prueba con Healenium activo.");
        Thread.sleep(1200);

        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByVisibleText("Two");



        WebElement checkbox = driver.findElement(By.id("my-check-1"));
        checkbox.click();

        driver.findElement(By.id("my-radio-1")).click();

        driver.findElement(By.name("my-date")).sendKeys("03/27/2026");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("[Healenium]  Submit ejecutado");

        Thread.sleep(2000);
        System.out.println("[Healenium]  TEST FINALIZADO");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}