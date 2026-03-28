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
        WebDriverManager.chromedriver().setup();
        WebDriver delegate = new ChromeDriver();
        delegate.manage().window().maximize();
        // Envolver el driver con Healenium
        driver = SelfHealingDriver.create(delegate);
    }

    @Test
    void llenarFormularioConHealenium() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        System.out.println("[Healenium] ✔ Pagina abierta");

        driver.findElement(By.name("my-text")).sendKeys("Kenneth");
        System.out.println("[Healenium] ✔ Text input llenado");

        driver.findElement(By.name("my-password")).sendKeys("pass1234");

        driver.findElement(By.name("my-textarea")).sendKeys("Prueba con Healenium activo.");

        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByVisibleText("Two");

        // LOCATOR INTENCIONALMENTE MODIFICADO
        // Original: By.id("my-check-1")
        // Modificado: By.id("my-check-BROKEN")  <-- esto es el id roto
        WebElement checkbox = driver.findElement(By.id("my-check-BROKEN"));
        if (!checkbox.isSelected()) checkbox.click();
        System.out.println("[Healenium] ✔ Checkbox encontrado via self-healing");

        driver.findElement(By.id("my-radio-1")).click();

        driver.findElement(By.name("my-date")).sendKeys("03/27/2026");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("[Healenium] ✔ Submit ejecutado");

        Thread.sleep(2000);
        System.out.println("[Healenium] ✔ TEST FINALIZADO");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
