package week6.wk6day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateFollowUpEvent extends BaseClass{

	@Test(dependsOnMethods = "week6.wk6day1.CreateNewTask.runCreateNewTask")
	public void runCreateFollowUpEvent () {

	
		//Click the dropdown of first result and click Create Follow-Up Event
		WebElement ddWidget = driver.findElement(By.xpath("//table//span[contains(@class,'slds-icon-utility-down')]"));
		driver.executeScript("arguments[0].click();",ddWidget);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Create Follow-Up Event']"))).click();
		//Enter subject as meeting
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class,'slds-combobox__input slds-input')]"))).click();
		WebElement clickMeeting = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Meeting']")));
		driver.executeScript("arguments[0].click();",clickMeeting);
		//Select due date From today to 13 days
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='slds-input'])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Today']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='slds-input'])[3]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[contains(@class,'is-today')]//following::td/span)[13]"))).click();

		//click save
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Save']"))).click();




	}











}
