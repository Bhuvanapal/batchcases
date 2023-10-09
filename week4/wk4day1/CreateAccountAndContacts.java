package week4.wk4day1;

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

public class CreateAccountAndContacts {

	public static void main(String[] args) throws InterruptedException {


		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");


		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		Faker faker = new Faker(new Locale("en","IN"));



		//Login to Login | Salesforce 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bhuvaneshwari@testleaf.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password$1");
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.className("slds-icon-waffle")).click();


		//Click view All and click Legal Entities from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();

		// Navigate to the 'Accounts' tab from the Accounts dashboard		

		driver.findElement(By.xpath("//p[text()='Accounts']")).click();
		driver.findElement(By.xpath("//div[@title='New']")).click();

		String firstName = faker.name().firstName();
		driver.findElement(By.xpath("(//input[@part='input'])[2]")).sendKeys(firstName);

		driver.findElement(By.xpath("//button[text()='Save']")).click();

		String accName = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]/a")).getAttribute("title");
		System.out.println(accName);

		//click NewContact
		driver.findElement(By.xpath("//button[text()='New Contact']")).click();

		//Select salutation
		driver.findElement(By.xpath("//div[@class='uiPopupTrigger']//a")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();

		//enter last name
		String lastName = faker.name().lastName();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);

		//enter email
		driver.findElement(By.xpath("//input[@inputmode='email']")).sendKeys(firstName+lastName+"@testleaf.com");

		//enter phoneNumber
		String phoneNumber = faker.phoneNumber().phoneNumber();
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneNumber);

		//enter title
		driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("SDET");

		//click save

		Thread.sleep(5000);

		WebElement clickSave = driver.findElement(By.xpath("(//span[text()='Save'])[3]"));
		driver.executeScript("arguments[0].click();", clickSave);

		//click contacts
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement clickContacts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Contacts']")));
		driver.executeScript("arguments[0].click();", clickContacts);

		//Find the lastname in the searchbar
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys(lastName+Keys.ENTER);

		//click dropdown
		Thread.sleep(5000);

		WebElement ddWidget = driver.findElement(By.xpath("//div[@data-aura-class='forceVirtualAction']/*"));
		driver.executeScript("arguments[0].click();",ddWidget);


		//click Delete
		WebElement clickDelete = driver.findElement(By.xpath("//a[@title='Delete']"));
		driver.executeScript("arguments[0].click();",clickDelete);
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();

		//verify if the account is deleted
		String contactName = driver.findElement(By.xpath("//lightning-formatted-rich-text/span")).getText();
		
		if (contactName.contains("No items")) {
			System.out.println("The contact has been deleted successfully");
		}
		else {
			System.out.println("The contact has not been deleted");
		}


	}

}
