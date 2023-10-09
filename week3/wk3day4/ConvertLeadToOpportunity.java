package week3.wk3day4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class ConvertLeadToOpportunity {

	public static void main(String[] args) throws IOException, InterruptedException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Faker faker = new Faker(new Locale("en","IN"));
		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();


		//Click view All and click Work Type Groups from App Launcher
		WebElement clickViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		driver.executeScript("arguments[0].click();", clickViewAll);

		//click Marketing
		WebElement clickMarketing = driver.findElement(By.xpath("//p[text()='Marketing']"));
		driver.executeScript("arguments[0].click();", clickMarketing);

		//click Leads
		WebElement clickLeads = driver.findElement(By.xpath("//span[text()='Leads']"));
		driver.executeScript("arguments[0].click();", clickLeads);

		//click New
		WebElement clickNew = driver.findElement(By.xpath("//div[@title='New']"));
		driver.executeScript("arguments[0].click();", clickNew);

		//Create new Lead with Mandatory fields

		driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input')]")).click();
		driver.findElement(By.xpath("//span[text()='Mr.']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Raja");
		driver.findElement(By.xpath("//label[text()='Company']/following::input")).sendKeys("ABCD");
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		//locate the dropdown near Submit for Approval button and click on the Convert link 
		WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Show more actions'])[2]")));
		driver.executeScript("arguments[0].click();",dd);

		WebElement clickConvert = driver.findElement(By.xpath("//span[text()='Convert']"));
		driver.executeScript("arguments[0].click();",clickConvert);

		//Click on the Opportunity Name input field, clear and enter a new opportunity name

		WebElement clickOpp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Opportunity']")));
		clickOpp.click();
		WebElement clickOppName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Opportunity Name']/following::input)[1]")));
		clickOppName.click();
		clickOppName.clear();
		clickOppName.sendKeys("Bhuvaneshwari");

		//click convert
		WebElement clickConvert2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Convert']")));
		clickConvert2.click();

		//click on the Go to Leads button
		WebElement clickGoToLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Go to Leads']")));
		clickGoToLeads.click();

		//Search the verified lead name in the Search box and verify the text ‘No items to display’
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@part='input']")));
		search.sendKeys("Raja");
		search.sendKeys(Keys.ENTER);
		String text = driver.findElement(By.xpath("//span[text()='No items to display.']")).getText();
		System.out.println(text);

		if (text.contains("No items to display.")) {
			System.out.println("The lead has been converted");
		}
		else {
			System.out.println("The lead has not been converted");
		}

		//Navigate to the Opportunities tab and search for the opportunity linked with the converted lead
		WebElement OppTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Opportunities']")));
		driver.executeScript("arguments[0].click();",OppTab);

		WebElement oppSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search this list...']")));
		oppSearch.sendKeys("Bhuvaneshwari");
		oppSearch.sendKeys(Keys.ENTER);

		//Search the opportunity name created and click on the created opportunity name
		WebElement clickOppName2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-aura-class='forceOutputLookup']")));
		driver.executeScript("arguments[0].click();",clickOppName2);
		Thread.sleep(2000);

		//Take a snap of the web page
		File source = driver.getScreenshotAs(OutputType.FILE);        
		//Create folder to save the img file
		File dest=new File("./Snap/img.png");
		//Merge source and destination
		FileUtils.copyFile(source, dest);
		

	}


}
