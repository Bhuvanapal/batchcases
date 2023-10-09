package week4.wk4day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrintAttachments {

	public static void main(String[] args) throws InterruptedException {


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

		//click view profile

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='uiImage']"))).click();


		//click switch to salesforce classic
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='Switch to Salesforce Classic']")).click();

		driver.findElement(By.id("userNav-arrow")).click();

		//WebElement clickName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bhuvaneshwari Palani']")));
		//driver.executeScript("arguments[0].click();", clickName);


		//click developer console
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Developer Console']"))).click();
		
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> listWindow=new ArrayList<String>(windowHandles);

		driver.switchTo().window(listWindow.get(1));

		//click file
		driver.findElement(By.xpath("//span[@class='x-btn-inner']")).click();

		//click open
		driver.findElement(By.xpath("//div[text()='Open']")).click();

		//click objects
		driver.findElement(By.xpath("//div[text()='Objects']")).click();

		//click filter
		WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder,'Filter the repository')]")));
		filter.sendKeys("Attachment");

		//click Attachment
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Attachment']"))).click();

		//click open
		driver.findElement(By.id("button-1191-btnInnerEl")).click();

		List<WebElement> allRows  = driver.findElements(By.xpath("(//table[@class='x-grid-table x-grid-table-resizer'])[1]/tbody/tr[@class='x-grid-row ']"));

		System.out.println(allRows.size());
		for (int i = 2; i <= allRows.size(); i++) {
			String text = driver.findElement(By.xpath("(//table[@class='x-grid-table x-grid-table-resizer'])[1]/tbody/tr["+i+"]/td[1]/div")).getText();
			System.out.println(text);

		}

		driver.close();

		//click switch to lightning based experience
		driver.switchTo().window(listWindow.get(0));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='linkElements']//a"))).click();
		
		driver.quit();
		

	}

}
