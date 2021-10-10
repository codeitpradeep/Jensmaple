package Codeitbypradeep.selpratice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewIsseldisenable {
	
	@Test
	public void getselect() {
	
	System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
	
	RemoteWebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	driver.get("https://demoqa.com/dynamic-properties");
	
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	WebDriverWait wait = new WebDriverWait(driver , 30);
	
	WebElement ele = driver.findElement(By.id("enableAfter"));
	
	WebElement ele2 = driver.findElement(By.id("visibleAfter"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enableAfter")));
	
	System.out.println(ele.isEnabled()+"isEnabled");
	
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
	
	ele.click();
	
	
	
	
	System.out.println(ele.isEnabled()+"isEnabled");
	System.out.println(ele.isDisplayed()+"isDisplayed");
	
	
	
	System.out.println(ele2.isDisplayed()+"isDisplayed");
	//
	
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	driver.close();
	driver.quit();
		
	}

}
