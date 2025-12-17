package jacaranda.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrimerSecriptPrueba {
	WebDriver driver;

	// Configuración: Se ejecuta primero
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // Recomendado maximizar la ventana
	}
	
	@Test
	public void isPresentFuhter() {
		driver.get("https://www.iana.org/help/example-domains");
		try {
			WebElement further = driver.findElement(By.name("Further Reading"));
			Assert.assertTrue(further.isDisplayed(), "NO COINCIDE");
			Thread.sleep(3000); 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();	
		}
	}

	@Test // La Prueba: Aquí va el código de navegación y validación
	public void  verificarTituloPaginaTest() {
		driver.get("https://www.example.com");
		// ¡TEMPORALMENTE para demostración visual!
		try {
			Thread.sleep(3000); // Pausa el script por 5 segundos (5000 milisegundos)
			WebElement link = driver.findElement(By.linkText("Learn more"));
			link.click();
			Assert.assertEquals(driver.getTitle(),"Example Domains","NO COINCIDE");
			Assert.assertTrue(driver.getTitle().equals("Example Domains"), "NO COINCIDE");
			//maten a julio
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@AfterClass // Limpieza: Se ejecuta al final
	public void teardown() {
		driver.quit();
	}
}
