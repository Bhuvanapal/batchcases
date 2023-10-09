package week5.wk5day2;

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

public class AddToCampaign {

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

		//---------Create New Campaign---------
		//click on campaigns tab
		WebElement clickCampaign = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", clickCampaign);
		//click New button
		WebElement clickNew = driver.findElement(By.xpath("//div[@title='New']"));
		driver.executeScript("arguments[0].click();", clickNew);
		//Enter Campaign name using Faker
		String campaignName = faker.name().name();
		WebElement clickCampName = driver.findElement(By.xpath("//input[@class=' input']"));
		driver.executeScript("arguments[0].click();", clickCampName);
		clickCampName.sendKeys(campaignName);
		//Choose Start date as Tomorrow
		WebElement clickStDate = driver.findElement(By.xpath("//a[@class='datePicker-openIcon display']"));
		driver.executeScript("arguments[0].click();", clickStDate);
		WebElement clickTmrDate = driver.findElement(By.xpath("(//td[contains(@class,'is-today')]/following-sibling::td/span)[1]"));
		driver.executeScript("arguments[0].click();", clickTmrDate);
		//End date as Tomorrow+1
		WebElement clickEndDate = driver.findElement(By.xpath("(//a[@class='datePicker-openIcon display'])[2]"));
		driver.executeScript("arguments[0].click();", clickEndDate);
		WebElement clickTmr1 = driver.findElement(By.xpath("(//td[contains(@class,'is-today')]/following-sibling::td/span)[2]"));
		driver.executeScript("arguments[0].click();", clickTmr1);
		//click Save
		WebElement clickSave = driver.findElement(By.xpath("(//span[text()='Save'])[2]"));
		driver.executeScript("arguments[0].click();", clickSave);
		//click Upload button
		WebElement clickUpload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='fileInput']")));
		clickUpload.sendKeys("C:\\Users\\Bhuvaneshwari\\Desktop\\Internship\\Automation\\dummy.pdf");
		//click Done
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Done']"))).click();
		//Verify campaign
		String campaign = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		System.out.println(campaign);

		if (campaign.contains(campaignName)) {
			System.out.println("Campaign is created");	
		}
		else {
			System.out.println("Campaign is not created");
		}

		//----------Add Opportunity-------
		//click New
		WebElement oppNew = driver.findElement(By.xpath("(//div[@title='New'])[2]"));
		driver.executeScript("arguments[0].click();", oppNew);
		//Click opportunity name
		String oppName = faker.name().name();
		driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::input")).sendKeys(oppName);
		//click close date
		WebElement clickClDate = driver.findElement(By.xpath("//label[text()='Close Date']/following::input"));
		driver.executeScript("arguments[0].click();", clickClDate);
		WebElement clickTmrDate2 = driver.findElement(By.xpath("(//td[contains(@class,'is-today')]/following-sibling::td/span)[1]"));
		driver.executeScript("arguments[0].click();", clickTmrDate2);
		//click stage
		WebElement clickStage = driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input slds-input_faux')]"));
		driver.executeScript("arguments[0].click();", clickStage);
		WebElement clickNeedAnal = driver.findElement(By.xpath("//span[text()='Needs Analysis']"));
		driver.executeScript("arguments[0].click();", clickNeedAnal);
		//click saveOpp
		WebElement saveOpp = driver.findElement(By.xpath("//button[text()='Save']"));
		driver.executeScript("arguments[0].click();", saveOpp);
		//click on Opportunity tab
		WebElement clickOppTab = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click();", clickOppTab);
		//search for the opportunity name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@part='input']"))).sendKeys(oppName + Keys.ENTER);
		//verify opportunity name
		String verOppName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"))).getText();

		if (verOppName.equals(oppName)) {
			System.out.println("Opportunity Name is added to Campaign");
		}
		else {
			System.out.println("Opportunity Name is not added to Campaign");
		}

		//-------Add Leads-----------
		//click on campaigns tab
		WebElement clickCampaign2 = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", clickCampaign2);
		//search for the campaign name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@part='input']"))).sendKeys(campaignName + Keys.ENTER);
		//click campaign name
		WebElement clickCampaignName = driver.findElement(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"));
		driver.executeScript("arguments[0].click();", clickCampaignName);
		//click AddLeads
		WebElement clickAddLeads = driver.findElement(By.xpath("//div[@title='Add Leads']"));
		driver.executeScript("arguments[0].click();", clickAddLeads);
		//click leads search bar
		Thread.sleep(2000);
		WebElement clickLeadsSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search Leads']")));
		driver.executeScript("arguments[0].click();", clickLeadsSearch);
		//click New Lead
		WebElement clickNewLead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='New Lead']")));
		driver.executeScript("arguments[0].click();", clickNewLead);
		String leadFirstName = faker.name().firstName();
		String leadLastName = faker.name().lastName();
		//click salutation
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		//Enter first name
		driver.findElement(By.xpath("//input[contains(@class,'firstName compoundBorderBottom')]")).sendKeys(leadFirstName);
		//Enter last name
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(leadLastName);
		//Enter company name		
		driver.findElement(By.xpath("(//input[@class=' input'])[3]")).sendKeys("TestLeaf");
		//click save
		driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();
		//wait for disappearance of toast msg
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));
		//click Next
		WebElement clickNext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
		driver.executeScript("arguments[0].click();", clickNext);
		//click submit
		WebElement clickSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
		driver.executeScript("arguments[0].click();", clickSubmit);
		//move to leads Tab
		WebElement clickLeads = driver.findElement(By.xpath("//span[text()='Leads']"));
		driver.executeScript("arguments[0].click();", clickLeads);
		//search for the lead name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@part='input']"))).sendKeys(leadLastName + Keys.ENTER);
		//verify Lead name
		String verLeadName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"))).getText();

		if (verLeadName.contains(leadLastName)) {
			System.out.println("Lead Name is added to Campaign");
		}
		else {
			System.out.println("Lead Name is not added to Campaign");
		}

		//-----------Add contacts----
		//click on campaigns tab
		WebElement clickCampaign3 = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", clickCampaign3);
		//search for the campaign name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@part='input']"))).sendKeys(campaignName + Keys.ENTER);
		//click campaign name
		WebElement clickCampaignName2 = driver.findElement(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"));
		driver.executeScript("arguments[0].click();", clickCampaignName2);
		//click contacts
		WebElement clickAddContacts = driver.findElement(By.xpath("//div[@title='Add Contacts']"));
		driver.executeScript("arguments[0].click();", clickAddContacts);
		//click leads search bar
		Thread.sleep(2000);
		WebElement clickContactsSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search Contacts']")));
		driver.executeScript("arguments[0].click();", clickContactsSearch);
		//click New Contact
		WebElement clickNewContact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='New Contact']")));
		driver.executeScript("arguments[0].click();", clickNewContact);
		//click salutation
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		//Enter last name
		String contactLastName = faker.name().lastName();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(contactLastName);
		//click save
		driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();
		//wait for disappearance of toast msg
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));
		//click Next
		WebElement clickContactNext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
		driver.executeScript("arguments[0].click();", clickContactNext);
		//click submit
		WebElement clickContactSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
		driver.executeScript("arguments[0].click();", clickContactSubmit);
		//move to contacts Tab
		WebElement clickContacts = driver.findElement(By.xpath("//span[text()='Contacts']"));
		driver.executeScript("arguments[0].click();", clickContacts);
		//search for the lead name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@part='input']"))).sendKeys(contactLastName + Keys.ENTER);
		//verify Lead name
		String verContactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]"))).getText();

		if (verContactName.contains(contactLastName)) {
			System.out.println("Contact is added to Campaign");
		}
		else {
			System.out.println("Contact is not added to Campaign");
		}
		driver.quit();




	}

}
