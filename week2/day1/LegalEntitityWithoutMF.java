package week2.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LegalEntitityWithoutMF {

	public static void main(String[] args) {
		
				
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");


		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();

		//Click view All and click Legal Entities from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement legEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].click();",legEntity);

		WebElement lgSvg = driver.findElement(By.xpath("//span[text()='Legal Entities']"
				+ "/following::*[name()='svg' and @data-key='chevrondown']/ancestor::a"));

		driver.executeScript("arguments[0].click();",lgSvg);

		WebElement newLegEnt = driver.findElement(By.xpath("//span[text()='New Legal Entity']"));
		driver.executeScript("arguments[0].click();",newLegEnt);
		
		//Enter the Company name as 'Testleaf'
		
		driver.findElement(By.xpath("(//input[@class='slds-input'])[3]")).sendKeys("Testleaf");
		
		//Enter description as Salesforce
		
		driver.findElement(By.xpath("(//textarea[@class='slds-textarea'])[2]")).sendKeys("Salesforce");
		
		//Select Status as 'Active'
		driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input slds-input_faux')]")).click();
		
		WebElement ddActive = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Active']"));
		driver.executeScript("arguments[0].click();",ddActive);
		
		//click Save
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//Verify the Alert message (Complete this field) displayed for Name
		String text = driver.findElement(By.xpath("//div[@class='genericNotification']/following::a")).getText();
		
		if (text.contains("Name")) {
			System.out.println("Complete the Mandatory field: Name");
		}
		else {
			System.out.println("New legal entity is created successfully");
		}

		driver.quit();
		
	}

}
