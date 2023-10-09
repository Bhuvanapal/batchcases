package week2.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SortOrderBasedonLMD {

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

		//Click view All and click Legal Entities from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		WebElement legEntity = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		driver.executeScript("arguments[0].click();",legEntity);

		//String beforeSort = driver.findElement(By.xpath("//span[@data-aura-class='uiOutputDateTime']")).getText();
		//System.out.println(beforeSort);

		WebElement sort = driver.findElement(By.xpath("//span[@title='Last Modified Date']/following::span"));
		driver.executeScript("arguments[0].click();",sort);

		//String afterSort = driver.findElement(By.xpath("//span[@data-aura-class='uiOutputDateTime']")).getText();

		//System.out.println(afterSort);

		String text1 = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDateTime'])[1]")).getText();

		String text2 = driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputDateTime'])[2]")).getText();

		String[] split1 = text1.split(",");

		String[] split2 = text2.split(",");

		if (split1[0].equals(split2[0])) {
			if (split1[1].equals(split2[1])) {
				System.out.println("Entity is not sorted");
			}
			else {
				String time1 = split1[1].replaceAll("[^0-9]", "");
				String time2 = split2[1].replaceAll("[^0-9]", "");
				int num1 = Integer.parseInt(time1);
				int num2 = Integer.parseInt(time2);
				if (num1>num2) {
					System.out.println("Entity is sorted");
				}
				else {
					System.out.println("Entity is not sorted");
				}
			}


		}else {
			System.out.println("Entity is sorted");	
		}

		driver.quit();




	}

}
