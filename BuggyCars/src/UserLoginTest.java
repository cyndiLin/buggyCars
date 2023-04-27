import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserLoginTest {

	public static void main(String[] args) {
		String successMessage = "Hi, Cyndi";
		String userName = "seleniumTestUser";
		String passWord = "Password1!";
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buggy.justtestit.org");
		
		//enter user name and details then click login
		driver.findElement(By.name("login")).sendKeys(userName); 
		driver.findElement(By.name("password")).sendKeys(passWord); 
		driver.findElement(By.className("btn-success")).click(); 
		
		//assert on successful login
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Hi')]")));
		
		
		WebElement e = driver.findElement(By.xpath("//*[contains(text(),'Hi')]"));
		String loginMessage = e.getText();
		
		Assert.assertEquals(successMessage, loginMessage);
	}

}