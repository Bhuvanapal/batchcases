package week4.wk4day3;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class VerifyEmail {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		Faker faker = new Faker(new Locale("en","IN"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Login']"))).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();

		//Click view All and click sales
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		//Click on the Leads tab and Create new Lead with Mandatory fields
		WebElement clickLeads = driver.findElement(By.xpath("//span[text()='Leads']"));
		driver.executeScript("arguments[0].click();", clickLeads);

		driver.findElement(By.xpath("//div[text()='New']")).click();

		String lastName = faker.name().lastName();
		String companyName = faker.company().name();

		driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input')]")).click();
		driver.findElement(By.xpath("//span[text()='Mr.']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);
		driver.findElement(By.xpath("//label[text()='Company']/following::input")).sendKeys(companyName);
		//WebElement clickstatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Lead Status']")));
		//driver.executeScript("arguments[0].click();", clickstatus);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Working - Contacted'])[1]"))).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		//Click on the New Task
		driver.findElement(By.xpath("//span[@value='NewTask']")).click();

		//Select Email from Subject, Choose today's date as close date. And click on the save button
		driver.findElement(By.xpath("//input[@role='combobox']")).click();
		driver.findElement(By.xpath("(//span[@title='Email'])[2]")).click();

		driver.findElement(By.xpath("//label[text()='Due Date']/following::input")).click();
		WebElement clickToday = driver.findElement(By.xpath("//button[text()='Today']"));
		driver.executeScript("arguments[0].click();", clickToday);

		driver.findElement(By.xpath("(//button[contains(@class,'slds-button slds-button--brand')])[2]")).click();

		//Click on the Email and click on the send button
		driver.findElement(By.xpath("//span[@value='SendEmail']")).click();
		WebElement clickSend = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'slds-button slds-button--brand')])[2]")));
		driver.executeScript("arguments[0].click();", clickSend);

		//Click on the dropdown for Email
		Thread.sleep(2000);
		WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Show more actions'])[3]")));
		driver.executeScript("arguments[0].click();",dd);

		//Choose change status and Select Completed from the dropdown, Finally click on the save button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='Change Status'])[2]"))).click();
		driver.findElement(By.xpath("//a[text()='Not Started']")).click();
		driver.findElement(By.xpath("//a[text()='Completed']")).click();
		driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();

		//verify email task is completed
		Thread.sleep(2000);
		WebElement clickEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//del[text()='Email']")));
		driver.executeScript("arguments[0].click();",clickEmail);
		String status = driver.findElement(By.xpath("//span[contains(@class,'test-id__field-value')]/span")).getText();

		if (status.equals("Completed")) {
			System.out.println("Email task is completed");
		}
		else {
			System.out.println("Email task is not completed");
		}


	}

}
