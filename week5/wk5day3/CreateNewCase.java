package week5.wk5day3;

import static org.testng.Assert.expectThrows;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class CreateNewCase {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		Faker faker = new Faker(new Locale("en","IN"));

		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Login']"))).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();

		//Click view All and click sales
		WebElement clickViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		driver.executeScript("arguments[0].click();", clickViewAll);

		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		//click on campaigns tab
		WebElement clickCampaign = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", clickCampaign);
		//click New button
		WebElement clickNew = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']")));
		driver.executeScript("arguments[0].click();", clickNew);
		//Enter Campaign name using Faker
		String campaignName = faker.name().firstName();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class=' input']"))).sendKeys(campaignName);
		//click Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Save'])[2]"))).click();
		//click new case
		WebElement clickNewCase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='New Case']")));
		driver.executeScript("arguments[0].click();", clickNewCase);
		//click new contact search
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search Contacts']"))).click();
		//click new contact 
		WebElement clickNewContact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='New Contact']")));
		driver.executeScript("arguments[0].click();", clickNewContact);
		Thread.sleep(5000);
		//click salutation
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='select'])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Mrs.']"))).click();
		//Enter first name
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='First Name']"))).sendKeys("Bhuvaneshwari");
		//Enter last name
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Last Name']"))).sendKeys("Palani");
		//click save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@title='Save']//span)[2]"))).click();
		//retrieve new
		String retrieveText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='uiPopupTrigger']//a)[5]"))).getText();
		System.out.println(retrieveText);
		//add description
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@role='textbox']"))).sendKeys("A new case was created.");
		//click Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Save'])[3]"))).click();
		//get the case ID
		String caseID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,toastMessage)]/a/div"))).getAttribute("title");
		System.out.println(caseID);
		//wait for invisibility of toast msg
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(@class,toastMessage)]/a/div")));
		//Click on toggle menu button from the left corner
		WebElement clickToggle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='slds-button slds-show']//div")));
		driver.executeScript("arguments[0].click();", clickToggle);
		//Click view All
		WebElement clickViewAll2 = driver.findElement(By.xpath("//button[text()='View All']"));
		driver.executeScript("arguments[0].click();", clickViewAll2);
		//click search bar
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@part='input'])[2]"))).sendKeys("Cases");
		//click cases
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mark[text()='Cases']"))).click();
		//click search and enter ID
		
		WebElement oppSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search this list...']")));
		oppSearch.sendKeys(caseID);
		oppSearch.sendKeys(Keys.ENTER);
				
		//click Case ID
		String caseNum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"))).getText();
		
		if (caseID.equals(caseNum)) {
			System.out.println("Case is created");
		}
		else {
			System.out.println("Case is not created");
		}
		driver.quit();
	}

}
