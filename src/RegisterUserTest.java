import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;

import org.junit.Assert;


public class RegisterUserTest {

	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		String successMessage = "Registration is successful";
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buggy.justtestit.org");
		
		//click on Register, enter user details and submit
		driver.findElement(By.className("btn-success-outline")).click(); 
		driver.findElement(By.id("username")).sendKeys("seleniumTestUser"+timeStamp); 
		driver.findElement(By.id("firstName")).sendKeys("Cyndi"); 
		driver.findElement(By.id("lastName")).sendKeys("Lin"); 
		driver.findElement(By.id("password")).sendKeys("Password1!"); 
		driver.findElement(By.id("confirmPassword")).sendKeys("Password1!");  
		driver.findElement(By.className("btn-default")).click();
		
		//assert on successful registration
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Registration')]")));
		
		WebElement e = driver.findElement(By.className("alert-success"));
		String loginMessage = e.getText();
		
		Assert.assertEquals(successMessage, loginMessage);
	}

}
