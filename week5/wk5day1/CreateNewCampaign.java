package week5.wk5day1;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class CreateNewCampaign {

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

		//click New button
		WebElement clickNew = driver.findElement(By.xpath("//div[@title='New']"));
		driver.executeScript("arguments[0].click();", clickNew);

		//Enter Campaign name as 'August Automation'
		WebElement clickCampName = driver.findElement(By.xpath("//input[@class=' input']"));
		driver.executeScript("arguments[0].click();", clickCampName);
		clickCampName.sendKeys("August Automation");

		//Choose Start date as Tomorrow
		WebElement clickStDate = driver.findElement(By.xpath("//a[@class='datePicker-openIcon display']"));
		driver.executeScript("arguments[0].click();", clickStDate);
		WebElement clickTmrDate = driver.findElement(By.xpath("(//td[contains(@class,'is-today')]/following-sibling::td/span)[1]"));
		driver.executeScript("arguments[0].click();", clickTmrDate);

		//End date as Tomorrow+1
		WebElement clickEndDate = driver.findElement(By.xpath("(//a[@class='datePicker-openIcon display'])[2]"));
		driver.executeScript("arguments[0].click();", clickEndDate);
		WebElement clickTmr1 = driver.findElement(By.xpath("(//td[contains(@class,'is-today')]/following-sibling::td/span)[2]"));
		driver.executeScript("arguments[0].click();", clickTmr1);

		//click Save
		WebElement clickSave = driver.findElement(By.xpath("(//span[text()='Save'])[2]"));
		driver.executeScript("arguments[0].click();", clickSave);

		//Verify campaign
		String campaign = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		System.out.println(campaign);

		if (campaign.contains("August Automation")) {
			System.out.println("Campaign is created");	
			}
			else {
				System.out.println("Campaign is not created");
			}
		
		
		
		
	}

}
