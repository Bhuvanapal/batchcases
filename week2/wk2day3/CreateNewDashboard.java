package week2.wk2day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateNewDashboard {

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

		//Click on the New Dashboard option
		WebElement clickdB = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		driver.executeScript("arguments[0].click();", clickdB);

		//Click on the New Dashboard option
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();

		//Switch to frame
		driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(0);

		//Enter Name as 'Salesforce Automation by Your Name ' and Click on Create
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Bhuvaneshwari");

		driver.findElement(By.xpath("//button[text()='Create']")).click();

		//Switch to frame
		driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(0);		
		//Click done button
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		
		Thread.sleep(5000);

		//verify dashboard name
		
		String attributeValue = driver.findElement(By.xpath("//span[contains(@title,'Salesforce Automation')]")).getAttribute("title");
		 
		if (attributeValue.equalsIgnoreCase("Salesforce Automation by Bhuvaneshwari")) {
		System.out.println("New dashboard has been created successfully"); } else {
		System.out.println("dashboard has not been created"); }
		
		driver.quit();










	}

}
