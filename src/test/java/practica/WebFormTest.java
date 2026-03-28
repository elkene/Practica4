package practica;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebFormTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void llenarFormulario() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        System.out.println(" Pagina abierta");

        // Text input — usa name porque no tiene id propio
        driver.findElement(By.name("my-text")).sendKeys("Kenneth");
        System.out.println(" Text input llenado");

        // Password
        driver.findElement(By.name("my-password")).sendKeys("pass1234");
        System.out.println(" Password llenado");

        // Textarea
        driver.findElement(By.name("my-textarea")).sendKeys("Esto es una prueba automatizada con Selenium.");
        System.out.println(" Textarea llenado");

        // Dropdown
        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByVisibleText("Two");
        System.out.println(" Dropdown seleccionado: Two");

        // Checkbox (checked por defecto, lo desmarcamos y volvemos a marcar)
        WebElement checkbox = driver.findElement(By.id("my-check-1"));
        if (!checkbox.isSelected()) checkbox.click();
        System.out.println(" Checkbox marcado");

        // Radio button
        driver.findElement(By.id("my-radio-1")).click();
        System.out.println(" Radio button seleccionado");

        // Date picker
        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.sendKeys("03/27/2026");
        System.out.println(" Fecha ingresada");

        // Submit
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println(" Formulario enviado");

        Thread.sleep(2000);

        // Validar que la URL cambió o que aparece mensaje de confirmación
        String url = driver.getCurrentUrl();
        System.out.println(" URL después del submit: " + url);
        Assertions.assertTrue(url.contains("index") || driver.getPageSource().contains("Received"),
                "El formulario no se envió correctamente");
        System.out.println(" TEST PASADO CORRECTAMENTE");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
