package week2.day1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SortDateClass {


	public static void main(String[] args) throws ParseException {


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

		//Click view All and click Legal Entities from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement legEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].click();",legEntity);

		WebElement sort = driver.findElement(By.xpath("//span[@title='Last Modified Date']/following::span"));
		driver.executeScript("arguments[0].click();",sort);


		String firstLMD = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDateTime'])[1]")).getText();

		String secondLMD = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDateTime'])[2]")).getText();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, h:mm a");  

		Date firstDate = dateFormat.parse(firstLMD);
		Date secondDate = dateFormat.parse(secondLMD);
		
		if (firstDate.equals(secondDate)) {
			System.out.println("Legal entities have the same last modified date");
		}else if (firstDate.after(secondDate)) {
			System.out.println("Legal entities are sorted by last modified date");
		}
			else {
				System.out.println("Legal entities are not sorted by last modified date");
			}
			
		}
		
		
		


	}





