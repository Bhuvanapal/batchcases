package week2.wk2day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeleteLegalEntity {

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
		//Click dropdown	
		WebElement ddWidget = driver.findElement(By.xpath("//div[@data-aura-class='forceVirtualAction']/*"));
		driver.executeScript("arguments[0].click();",ddWidget);
	
		
		//click delete
		WebElement clickDelete = driver.findElement(By.xpath("//a[@title='Delete']"));
		driver.executeScript("arguments[0].click();",clickDelete);
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();

		String entityName = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();


		if (entityName.contains("Salesforce automation done by Bhuvaneshwari")) {
			System.out.println("legal entity has been deleted successfully");	
		}
		else {
			System.out.println("Legal entity has not been deleted");	
		}






	}

}
