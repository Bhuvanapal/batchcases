package week6.wk6day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateFollowUpTask extends BaseClass{
	@Test(dependsOnMethods = "week6.wk6day1.CreateNewTask.runCreateNewTask")
	public void runCreateFollowUpTask () {
		//Click the dropdown of first result and click Create Follow-Up task
		WebElement ddWidget = driver.findElement(By.xpath("//table//span[contains(@class,'slds-icon-utility-down')]"));
		driver.executeScript("arguments[0].click();",ddWidget);
		WebElement clickTask = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-aura-class='uiMenuItem']//a")));
		driver.executeScript("arguments[0].click();",clickTask);

		//Select a subject as Call		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class,'slds-combobox__input slds-input')]"))).click();
		WebElement clickcall = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Call']")));
		driver.executeScript("arguments[0].click();",clickcall);

		//Set Priority as High and Status as In Progress
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='select'])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='High']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='select']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='In Progress']"))).click();

		//click save
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save'])[2]"))).click();



	}

}
