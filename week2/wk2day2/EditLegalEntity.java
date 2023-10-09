package week2.wk2day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EditLegalEntity {

	public static void main(String[] args) throws InterruptedException {

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

		Thread.sleep(5000);

		WebElement legEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].click();",legEntity);

		//Search the Legal Entity 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Salesforce automation done by Bhuvaneshwari" + Keys.ENTER);


		Thread.sleep(5000);
		//Click dropdown and click edit	
		WebElement ddWidget = driver.findElement(By.xpath("//div[@data-aura-class='forceVirtualAction']/*"));
		driver.executeScript("arguments[0].click();",ddWidget);

		WebElement clickEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
		driver.executeScript("arguments[0].click();",clickEdit);


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
		
		//Click the Name link
		WebElement nameLink = driver.findElement(By.xpath("//a[@data-aura-class='forceOutputLookup']"));
		driver.executeScript("arguments[0].click();",nameLink);
		
		//verify status as active
		String status = driver.findElement(By.xpath("//lightning-formatted-text[text()='Active']")).getText();

			
		if (status.contains("Active")) {
		System.out.println("Changes updated to legal entity successfully");	
		}
		else {
			System.out.println("Legal entity has not been updated");	
		}










	}

}
