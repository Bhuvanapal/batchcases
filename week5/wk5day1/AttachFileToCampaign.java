package week5.wk5day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AttachFileToCampaign {

	public static void main(String[] args) {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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

		//Click August Automation link		
		WebElement clickLink =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-aura-class='forceOutputLookup']"))); 
		driver.executeScript("arguments[0].click();", clickLink);

		//click Upload button
		WebElement clickUpload = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='fileInput']")));
		clickUpload.sendKeys("C:\\Users\\Bhuvaneshwari\\Desktop\\Internship\\Automation\\dummy.pdf");

		//click Done
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Done']"))).click();
		
		
		//Verify the file name displayed as link
		//String fiName = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText'])[3]")).getText();
		String fileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'itemTitle slds-text-body--regulardesktop')]"))).getText();
		
		 
		 System.out.println(fileName);
		 
		 if (fileName.equals("dummy")) {
		 System.out.println("File uploaded successfully"); }
		 else {
		 System.out.println("File not uploaded"); }
		 
	}

}
