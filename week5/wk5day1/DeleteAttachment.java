package week5.wk5day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteAttachment {

	public static void main(String[] args) {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Login']"))).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();

		//Click view All and click sales
		WebElement clickViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		driver.executeScript("arguments[0].click();", clickViewAll);

		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		//click on campaigns tab
		WebElement clickCampaign = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", clickCampaign);

		//Click August Automation link		
		WebElement clickLink =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-aura-class='forceOutputLookup']"))); 
		driver.executeScript("arguments[0].click();", clickLink);

		//click view all
		WebElement clickViewAll2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Attachments'])[2]"))); 
		driver.executeScript("arguments[0].click();", clickViewAll2);

		//click dropdown
		WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[contains(@class,'forceRecordLayout ')]/following::div[@data-aura-class='forceVirtualAction']/*")));
		driver.executeScript("arguments[0].click();",dd);

		//click delete 
		WebElement clickDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Delete']")));
		driver.executeScript("arguments[0].click();", clickDelete);

		WebElement clickDelete2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Delete']")));
		driver.executeScript("arguments[0].click();",clickDelete2);

		String toastMsg = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		System.out.println(toastMsg);

		if (toastMsg.contains("Record was deleted.")) {
			System.out.println("Attachment was deleted successfully");
		}
		else {
			System.out.println("Attachment was not deleted");
		}

	}

}
