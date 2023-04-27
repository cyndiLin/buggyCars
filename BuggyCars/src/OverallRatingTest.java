import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverallRatingTest {

	public static void main(String[] args) {
		String [] columnHeading = {"Make","Model","Rank", "Votes","Engine","Comments"};
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://buggy.justtestit.org");
		
		//wait for page to finish loading
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Overall')]")));
		
		//navigate to Overall Rating page by clicking the clickable area beneath Overall Rating title
		driver.findElement(By.xpath("//h2[contains(text(),'Overall Rating')]/following-sibling::a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Alfa')]")));

		//assert column titles to exist and correct
		List<WebElement> columnTitles = driver.findElements(By.cssSelector("[class=\"cars table table-hover\"] thead tr th"));
		for(int i=0; i<columnHeading.length; i++) {
			String actualColumnTitle = columnTitles.get(i+1).getText();
			Assert.assertTrue(actualColumnTitle.contains(columnHeading[i]));
		}
		
		//assert minimum of one row in Overall ratings table
		List<WebElement> overallRatingsList = driver.findElements(By.cssSelector("[class=\"cars table table-hover\"] tbody tr"));
		Assert.assertTrue(1 <= overallRatingsList.size());		
		
	}

}
