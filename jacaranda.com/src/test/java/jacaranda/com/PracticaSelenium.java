package jacaranda.com;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticaSelenium {
	WebDriver driver; 
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void chehckLoginWithCredentials() throws Exception {
		driver.get("https://www.saucedemo.com/");
		try {
			WebElement username = driver.findElement(By.id("user-name"));
			WebElement password = driver.findElement(By.id("password"));
			username.sendKeys("standard_user");
			password.sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			Assert.assertTrue(driver.getCurrentUrl() == "https://www.saucedemo.com/inventory.html","Error, la url no es la esperada");	
		}catch(org.openqa.selenium.NoSuchElementException e) {
			 Thread.currentThread().interrupt();
		}
	}
	
	@Test
	public void chehckLoginWithCredentialsErroneas() throws Exception {
		driver.get("https://www.saucedemo.com/");
		try {
			WebElement username = driver.findElement(By.id("user-name"));
			WebElement password = driver.findElement(By.id("password"));
			username.sendKeys("standard_us");
			password.sendKeys("secret_e");
			driver.findElement(By.id("login-button")).click();
			Assert.assertTrue(driver.getCurrentUrl() != "https://www.saucedemo.com/inventory.html","Error, la url no es la esperada");	
		}catch(Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	@Test
	public void waitHello() {
		driver.get(" https://the-internet.herokuapp.com/dynamic_loading/1");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		WebElement boton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start")));
		boton.click();
		
		driver.findElement(By.id("finish"));
	}
	
	
	
	@AfterClass // Limpieza: Se ejecuta al final
	public void teardown() {
		driver.quit();
	}
	
}
