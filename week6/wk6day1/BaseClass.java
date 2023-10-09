package week6.wk6day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseClass {

	public ChromeDriver driver;
	public WebDriverWait wait;


	@Parameters({"username","password"})
	@BeforeMethod
	public void preCondition(String uname, String pwd) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Login']"))).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();
		//div[@class='slds-r4']/following-sibling::div[1]
		Thread.sleep(2000);
		//click on content
		WebElement clickContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-label='Content']")));
		//clickContent.click();
		driver.executeScript("arguments[0].click();", clickContent);
		//click viewall from todays task
		WebElement ttViewall = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Today’s Tasks']/following::span[@class='viewAllLabel']")));
		driver.executeScript("arguments[0].click();", ttViewall);
		//Click the Display as dropdown under Recently Viewed and select Table
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Display as Split View']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='menuitemcheckbox']"))).click();
		//li[@title='Table']
		Thread.sleep(2000);
	}

	@AfterMethod
	public void postCondition() {
		driver.close();
	}









}
