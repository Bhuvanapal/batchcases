package week6.wk6day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DeleteAllTasks extends BaseClass{
	@Test(dependsOnMethods = {"week6.wk6day1.CreateFollowUpEvent.runCreateFollowUpEvent","week6.wk6day1.CreateFollowUpTask.runCreateFollowUpTask"})
	public void runDeleteAllTasks() throws InterruptedException {

		List<WebElement> allTasks = driver.findElements(By.xpath("//table//span[contains(@class,'slds-icon-utility-down')]"));
		System.out.println("Total tasks: " +allTasks.size());
		
		for (int i = 0; i < allTasks.size(); i++) {
			driver.findElement(By.xpath("//table//span[contains(@class,'slds-icon-utility-down')]")).click();
			driver.findElement(By.xpath("//a[@title='Delete']")).click();
			driver.findElement(By.xpath("//span[text()='Delete']")).click();
			Thread.sleep(3000);
		}

	}
	
}
//