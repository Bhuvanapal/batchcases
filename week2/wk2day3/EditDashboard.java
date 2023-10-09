package week2.wk2day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EditDashboard {

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


		//Click view All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		
		//Click on the Dashboard option
		WebElement clickdB = driver.findElement(By.xpath("//a[@data-label='Dashboards']"));
		driver.executeScript("arguments[0].click();", clickdB);

		Thread.sleep(5000);
		//Click on the Dashboards tab 
		WebElement clickSearchBar = driver.findElement(By.xpath("//span[text()='Dashboards']"));
		driver.executeScript("arguments[0].click();", clickSearchBar);

		//Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[contains(@data-aura-class,'uiInput uiInputText')]")).sendKeys("Bhuvaneshwari",Keys.ENTER);

		Thread.sleep(5000);

		//Click dropdown and click edit
		driver.findElement(By.xpath("(//*[@data-key='down'])[4]")).click();
		driver.findElement(By.xpath("//span[text()='Edit']")).click();

		//Switch to frame
		driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(0);

		//Click on the Edit Dashboard Properties icon
		WebElement properties = driver.findElement(By.xpath("//span[text()='Edit Dashboard Properties']"));
		driver.executeScript("arguments[0].click();", properties);
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Bhuvaneshwari");
		WebElement description = driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']"));
		description.sendKeys("Salesforce");

		WebElement clickMe = driver.findElement(By.xpath("//label[@class='slds-radio__label']"));
		driver.executeScript("arguments[0].click();", clickMe);
		
		WebElement clickAnotherPerson = driver.findElement(By.xpath("//span[text()='Another person']"));
		driver.executeScript("arguments[0].click();", clickAnotherPerson);
		

		//click save
		WebElement clickSave = driver.findElement(By.xpath("(//button[text()='Save'])[2]"));
		driver.executeScript("arguments[0].click();", clickSave);

		//click done
		WebElement clickDone = driver.findElement(By.xpath("//button[text()='Done']"));
		driver.executeScript("arguments[0].click();", clickDone);

		//verify if dashboard is edited
		String description1 = driver.findElement(By.xpath("//div[@class='slds-col slds-align-bottom']/p")).getText();

		if (description1.equals("Salesforce")) {
			System.out.println("Dashboard has been edited successfully");

		}
		else {
			System.out.println("Dashboard has not been edited");
		}
	}

}
