package week3.wk3day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateWTGWithoutMF {

	public static void main(String[] args) {
	
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();


		//Click view All and click Work Type Groups from App Launcher
		WebElement clickViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		driver.executeScript("arguments[0].click();", clickViewAll);
		WebElement clickWTG = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		driver.executeScript("arguments[0].click();", clickWTG);

		//Click on New Work Type Group
		WebElement clickNew = driver.findElement(By.xpath("//div[text()='New']"));
		driver.executeScript("arguments[0].click();", clickNew);

		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		//verification
		String mandField = driver.findElement(By.xpath("//ul[contains(@class,'errorsList slds-list_dotted')]/*/a")).getText();
		
	if (mandField.contains("Work Type Group Name")) {
		System.out.println("Mandatory field is not given");
	}
	
	else {
		System.out.println("Mandatory field is given");
	}	
		
		
	}

}
