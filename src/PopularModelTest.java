import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopularModelTest {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buggy.justtestit.org");
		
		//wait for page to finish loading
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Popular')]")));
		
		//navigate to Popular Model page by clicking the clickable area beneath Popular Model title
		driver.findElement(By.xpath("//h2[contains(text(),'Popular Model')]/following-sibling::a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Alfa')]")));
		
		//assert a popular model name and logo is displayed
		List<WebElement> modelAndLogo = driver.findElements(By.cssSelector("[class='col-lg-2'] div"));
		Assert.assertTrue(2 == modelAndLogo.size());

		//assert page has popular model picture displayed
		Assert.assertNotNull(driver.findElement(By.className("col-lg-6")));
		
		//assert Specification and Votes sections displayed	
		List<WebElement> specAndVotes = driver.findElements(By.cssSelector("[class='col-lg-4'] div [class='card-block'] h4"));
		String specificationTitle = specAndVotes.get(0).getText();
		String votesTitle = specAndVotes.get(1).getText();
		Assert.assertEquals("Specification", specificationTitle);
		Assert.assertTrue(votesTitle.contains("Votes:"));
		
		//assert Model description displayed
		Assert.assertNotNull(driver.findElement(By.xpath("//my-model/div/div[2]")).getText());
		
		//assert there are comments in the comments section
		List<WebElement> commentsList = driver.findElements(By.cssSelector("[class='table'] tbody tr"));
		Assert.assertTrue(1 <= commentsList.size());
		
	}

}
