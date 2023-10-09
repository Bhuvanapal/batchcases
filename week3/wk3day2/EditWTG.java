package week3.wk3day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditWTG {

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

		WebElement clickEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
		driver.executeScript("arguments[0].click();",clickEdit);

		//Enter Description as 'Automation'
		String desc = "Automation";
		WebElement clickDesc = driver.findElement(By.xpath("//label[text()='Description']/following::textarea"));
		clickDesc.clear();
		clickDesc.sendKeys(desc);


		//Select Group Type as 'Capacity'
				
		WebElement clickDefault = driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input slds-input_faux')]"));
		driver.executeScript("arguments[0].click();",clickDefault);
		
		WebElement clickCapacity = driver.findElement(By.xpath("//span[text()='Capacity']"));
		driver.executeScript("arguments[0].click();",clickCapacity);
		
		//click save
		WebElement clickSave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save']")));
		driver.executeScript("arguments[0].click();", clickSave);
		
		//Click on 'Salesforce Automation by Your Name'and Verify Description as 'Automation'
		WebElement clickSFA = driver.findElement(By.xpath("//a[@data-aura-class='forceOutputLookup']"));
		driver.executeScript("arguments[0].click();", clickSFA);
		String description = driver.findElement(By.xpath("(//lightning-formatted-text[@data-output-element-id='output-field'])[2]")).getText();

		if (desc.equals(description)) {
			System.out.println("Description is given as Automation");

		}
		else {
			System.out.println("Description is not given correctly");
		}



	}

}
