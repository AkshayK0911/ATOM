package com.org.ATOM;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class UserManagementAdmin {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;

	@BeforeSuite
	public void setup() {
		// Initialize ChromeDriver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test(priority = 0)
	public void loginWithGoogle() throws InterruptedException {

		driver.get("https://atom.dev.dmclinical.com/auth/login");

		// Click on the Login with Google button
		WebElement loginWithGoogleButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@tabindex='1' and text()= ' DM User Login ']")));
		loginWithGoogleButton.click();

		// Switch to the Google sign-in popup window
		String mainWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Enter email and click Next
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
		emailInput.sendKeys("anups.gw@dmclinical.com");
		driver.findElement(By.id("identifierNext")).click();

		// Enter password and click Next
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
		passwordInput.sendKeys("AnUP$7852");
		driver.findElement(By.id("passwordNext")).click();

		Thread.sleep(1000);

		driver.switchTo().window(mainWindowHandle);

		WebElement welcomePopup = driver.findElement(By.xpath("//div/button[@class='btn btn-outline-dark']"));
		welcomePopup.click();

	}

	@Test(priority = 1)
	public void userManagementModule() throws InterruptedException {

		WebElement leads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leads")));
		leads.click();

		Thread.sleep(5000);
		WebElement userManagement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
		userManagement.click();

		WebElement addUser = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[@class='btn btn-outline-primary']")));
		addUser.click();

	}

	@Test(priority = 2)
	public void addUserAdmin() throws InterruptedException {

		Thread.sleep(5000);
		WebElement userManagement1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
		userManagement1.click();

		WebElement addUser = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[@class='btn btn-outline-primary']")));
		addUser.click();

		WebElement fname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name-column")));
		fname.sendKeys("Automation");

		WebElement lname = driver.findElement(By.id("last-name-id-column"));
		lname.sendKeys("Test");

		WebElement eMail = driver.findElement(By.id("email-id-column"));
		eMail.sendKeys("Automation@dmclinical.com");

		WebElement roleDropdown = driver.findElement(By.xpath("(//div[@class='ng-value-container'])[1]"));
		roleDropdown.click();

		WebElement roleOptions = driver.findElement(By.linkText("Admin"));

		Select select = new Select(roleOptions);

		select.selectByIndex(0);
		WebElement selectedOption = select.getFirstSelectedOption();
		System.out.println("Selected option: " + selectedOption.getText());

		WebElement statusDropdown = driver.findElement(By.xpath("(//div[@class='ng-value-container'])[2]"));
		statusDropdown.click();

		WebElement statusOptions = driver.findElement(By.linkText("Active"));

		Select select1 = new Select(statusOptions);

		select1.selectByIndex(0);

		WebElement selectedOption1 = select1.getFirstSelectedOption();
		System.out.println("Selected option: " + selectedOption1.getText());

		WebElement deptDropdown = driver.findElement(By.xpath("(//div[@class='ng-value-container'])[3]"));
		deptDropdown.click();

		WebElement deptOptions = driver.findElement(By.linkText("Executive"));

		Select select2 = new Select(deptOptions);

		select1.selectByIndex(0);

		WebElement selectedOption2 = select2.getFirstSelectedOption();
		System.out.println("Selected option: " + selectedOption2.getText());

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		WebElement createUserPopup = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()= ' Yes ']")));
		createUserPopup.click();

	}

//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}

}
