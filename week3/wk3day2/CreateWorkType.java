package week3.wk3day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateWorkType {

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

		//Enter Work Type Group Name as 'Salesforce Automation by Your Name'
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));

		String name = "Salesforce Automation by Bhuvaneshwari";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='slds-input'])[2]"))).sendKeys(name);

		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

		//verify Work Type Group Name
		String wTGName = driver.findElement(By.xpath("//lightning-formatted-text[@data-output-element-id='output-field']")).getText();

		if (name.equals(wTGName)) {
			System.out.println("Work type group has been created successfully");

		}
		else {
			System.out.println("Work type group is not created");
		}




	}

}
