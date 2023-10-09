package week3.wk3day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteWTG {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));

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

		//Search the Work Type Group 'Salesforce Automation by Your Name'
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Bhuvaneshwari" + Keys.ENTER);

		Thread.sleep(5000);
		//Click dropdown and click edit	
		WebElement ddWidget = driver.findElement(By.xpath("//div[@data-aura-class='forceVirtualAction']/*"));
		driver.executeScript("arguments[0].click();",ddWidget);
		
		//click delete
		WebElement clickDelete = driver.findElement(By.xpath("//a[@title='Delete']"));
		driver.executeScript("arguments[0].click();",clickDelete);

		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		//verify the popup message

		String wTGName = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		if (wTGName.contains("Salesforce Automation by Bhuvaneshwari")) {
			System.out.println("Work Type Group has been deleted successfully");	
		}
		else {
			System.out.println("Work Type Group has not been deleted");	
		}


	}

}
