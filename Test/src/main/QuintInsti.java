package main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.constants.constants;

public class QuintInsti {
	public WebDriver driver = null;

	@Test
	public void login() {
		String geckoDriver = "C:/Projects/Tools/geckodriver-v0.19.1-win32/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", geckoDriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(constants.url);
		driver.findElement(By.xpath(constants.login)).click();

		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> itererator = windowId.iterator();
		String mainpup = itererator.next();
		String newpup = itererator.next();
		driver.switchTo().window(newpup);

		driver.findElement(By.xpath(constants.email)).sendKeys(constants.username);
		driver.findElement(By.xpath(constants.pass)).sendKeys(constants.password);
		driver.findElement(By.xpath(constants.signin)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().window(mainpup);

		// step 2
		driver.findElement(By.cssSelector(constants.profileicon)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(constants.profilelink)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void profile() throws IOException {

		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> itererator = windowId.iterator();
		String mainpup = itererator.next();
		String new2pup = itererator.next();
		driver.switchTo().window(new2pup);

		driver.findElement(By.name("email")).click();
		String value = driver.findElement(By.name("email")).getAttribute("value");
		System.out.println(value); // Text from email text box is printed to console

		// Thread.sleep(1000);

		WebElement web = driver.findElement(By.name("mobile"));
		web.clear();
		web.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		web.sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.name("mobile")).sendKeys("222222");
		driver.findElement(By.cssSelector(constants.saveprofile)).click();
		String message = driver.findElement(By.id("swal2-content")).getText();
		System.out.println(message);
		Assert.assertEquals(message, "Phone is not valid");

		driver.findElement(By.cssSelector(constants.confirmwindow)).click();
		// Thread.sleep(1000);
		web.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		web.sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.name("mobile")).sendKeys("9768226717");
		driver.findElement(By.cssSelector(constants.saveprofile)).click();
		driver.findElement(By.cssSelector(constants.confirmwindow)).click();

		// step 5 - select year of Experience
		WebElement dropdown = driver.findElement(By.xpath(constants.YOE));
		dropdown.click();
		driver.findElement(By.cssSelector(constants.YOE8)).click();

		// Step 6 - click save and capture success message (saved to 'successmsg'
		// veriable)
		driver.findElement(By.cssSelector(constants.saveprofile)).click(); // click save button
		String successmsg = driver.findElement(By.id("swal2-content")).getText();
		System.out.println(successmsg);
		driver.findElement(By.cssSelector("button.swal2-confirm.swal2-styled")).click(); // click on alert window

		// step 7 - take a screen shot of the page
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("C:\\Projects\\Workspace\\ss.png"));
	}

	@Test
	public void services() {
		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> itererator = windowId.iterator();
		String pup1 = itererator.next();
		String pup2 = itererator.next();
		driver.findElement(By.linkText("My Services")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement cource = driver.findElement(By.cssSelector(constants.QUANTRA));
		cource.click();
		String quantraTitle = driver.getTitle(); // get page title here
		System.out.println(quantraTitle);
		// get page title here
		// Thread.sleep(1000);
		driver.switchTo().window(pup2);
		driver.findElement(By.cssSelector(constants.EPAT)).click();
		String epatTitle = driver.getTitle(); // get page title here
		System.out.println(epatTitle);
	}

	@Test
	public void totout() {
		driver.findElement(By.cssSelector(".link")).click();
		driver.quit();
	}
}
