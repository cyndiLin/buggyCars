import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopularMakeTest {

	public static void main(String[] args) {
		String pageTitle = "Alfa Romeo";
		int minimumRows = 1;
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buggy.justtestit.org");
		
		//wait for page to finish loading
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Popular')]")));
		
		//navigate to Popular Make by clicking the clickable area beneath Popular Make title
		driver.findElement(By.xpath("//h2[contains(text(),'Popular Make')]/following-sibling::a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Alfa')]")));
		

		//assert page title is correct
		Assert.assertEquals(pageTitle, driver.findElement(By.className("card-header")).getText());
		
		//assert Alfa Romeo description paragraph exists
		Assert.assertNotNull(driver.findElement(By.className("col-md-9")).getText());
		
		//assert there are more than one row in the table
		List<WebElement> tableRows = driver.findElements(By.cssSelector("[class='cars table table-hover'] tbody tr"));
		Assert.assertTrue(minimumRows <= tableRows.size());
	}

}
