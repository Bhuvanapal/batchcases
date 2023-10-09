package week6.wk6day1;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateNewTask extends BaseClass {


	@Test
	public void runCreateNewTask() throws InterruptedException {

		//click new task
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New Task']"))).click();
		//select subject as email
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='slds-combobox__input slds-input']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Email']"))).click();
		//Set Priority as High and Status as In Progress
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='select'])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='High']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='select']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='In Progress']"))).click();

		//Click on the image of Name field, click on Contacts and select Contact
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Contacts']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@class,'default input')])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'primaryLabel slds-truncate')]"))).click();

		//Click on the image of Related To field, click on Product and Select Product
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Accounts']"))).click();
		WebElement clickProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Products']")));
		driver.executeScript("arguments[0].click();", clickProduct);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search Products']"))).click();
		Thread.sleep(2000);
		WebElement selectProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Search Products']//following::div[contains(@class,'primaryLabel slds-truncate')]")));
		selectProduct.click();
		//click save
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Save'])[2]"))).click();



	}

}
