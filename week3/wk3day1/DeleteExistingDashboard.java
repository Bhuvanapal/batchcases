package week3.wk3day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeleteExistingDashboard {

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

		Thread.sleep(5000);
		//Click on the Dashboards tab 
		WebElement clickSearchBar = driver.findElement(By.xpath("//span[text()='Dashboards']"));
		driver.executeScript("arguments[0].click();", clickSearchBar);

		//Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[contains(@data-aura-class,'uiInput uiInputText')]")).sendKeys("Bhuvaneshwari",Keys.ENTER);

		Thread.sleep(5000);

		//Click dropdown and click delete
		driver.findElement(By.xpath("(//*[@data-key='down'])[4]")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();

		//7.Click on the Delete option in the displayed popup window
		driver.findElement(By.xpath("(//span[text()='Delete'])[2]")).click();


		//Verify Whether Dashboard is Deleted
		String deleteDB = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();

		if (deleteDB.contains("deleted")) {
			System.out.println("Dashboard has been deleted successfully");	
		}
		else {
			System.out.println("Dashboard has not been deleted");
		}

	}

}
