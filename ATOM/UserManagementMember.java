package com.org.ATOM;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class UserManagementMember {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;

	@BeforeSuite
	public void setup() {
		// Initialize ChromeDriver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.manage().window().maximize();
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
		emailInput.sendKeys("shivamp.gw@dmclinical.com");
		driver.findElement(By.id("identifierNext")).click();

		// Enter password and click Next
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
		passwordInput.sendKeys("SH!vamp9874");
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

	}

//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}

}
