package com.org.ATOMProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.org.ATOMTestData.TestData;

import org.testng.annotations.*;

public class ATOMAutomation {
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

		String url = TestData.url;
		String userName = TestData.userNameAdmin;
		String password = TestData.passwordAdmin;

		driver.get(url);

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
		emailInput.sendKeys(userName);
		driver.findElement(By.id("identifierNext")).click();

		// Enter password and click Next
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
		passwordInput.sendKeys(password);
		driver.findElement(By.id("passwordNext")).click();

		Thread.sleep(1000);

		driver.switchTo().window(mainWindowHandle);

		WebElement welcomePopup = driver.findElement(By.xpath("//div/button[@class='btn btn-outline-dark']"));
		welcomePopup.click();

	}

	@Test(priority = 1)
	public void CreateLead() throws InterruptedException {

		String user1 = TestData.user1;
		String referenceID1 = TestData.referenceID1;
		String protocolNum1 = TestData.protocolNum1;

		Actions actions = new Actions(driver);

		WebElement leads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leads")));
		leads.click();

		Thread.sleep(8000);

		WebElement userManagement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
		userManagement.click();

		leads.click();

		WebElement createLead = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[@class='btn btn-outline-primary']")));
		createLead.click();

		Thread.sleep(3000);

		WebElement leadName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name-column")));
		leadName.sendKeys(user1, Keys.TAB);

		WebElement externalReferenceID = driver.findElement(By.id("external-reference-id-column"));
		externalReferenceID.sendKeys(referenceID1, Keys.TAB);

		actions.sendKeys(Keys.ENTER).perform();
		WebElement options = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='CRO']")));

		List<WebElement> dropdownOptions = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']")));

		// Print the text of each option
		for (WebElement option : dropdownOptions) {
			System.out.println("Source Dropdown Options Are: " + option.getText());
		}
		actions.sendKeys(Keys.ENTER).perform();

		actions.sendKeys(Keys.TAB);

		actions.sendKeys(Keys.ENTER).perform();

		WebElement optionsA = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='Allergy/Immunology']")));

		List<WebElement> dropdownOptions1 = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']")));

		// Print the text of each option
		for (WebElement option : dropdownOptions1) {
			System.out.println("Therapeautic Area Dropdown Options Are: " + option.getText());
		}

		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.TAB);

		WebElement otherInfoButton = driver
				.findElement(By.xpath("//button[@type='button']/span[text()='Other Information']"));
		otherInfoButton.click();

		WebElement protocolNum = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("protocol-number-column")));
		protocolNum.sendKeys(protocolNum1, Keys.TAB);

		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.TAB);
		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.TAB);
		actions.sendKeys(Keys.ENTER).perform();
		actions.sendKeys(Keys.TAB);

		actions.sendKeys(Keys.TAB);
		actions.sendKeys(Keys.ENTER).perform();

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		WebElement uploadAttachment = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text() ='No']")));
//		String mainWindowHandle1 = driver.getWindowHandle();
//		for (String windowHandle1 : driver.getWindowHandles()) {
//			if (!windowHandle1.equals(mainWindowHandle1)) {
//				driver.switchTo().window(windowHandle1);
//				break;
//			}
//		}
		uploadAttachment.click();

	}

//	@AfterSuite
//	public void tearDown() {
//		driver.close();
//	}

}
