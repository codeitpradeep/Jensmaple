package Codeitbypradeep.selpratice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PgmActionAlarts {
	@Test
public void aetselect() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/slider");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"sliderContainer\"]/div[1]/span/input"));
		
		Actions action = new Actions(driver);
		
		action.dragAndDropBy(ele, 30, 0).build().perform();
		
	

		//ele.click();
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
	
	@Test
public void betselect() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/buttons");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		WebElement ele = driver.findElement(By.id("doubleClickBtn"));
		
		WebElement ele2 = driver.findElement(By.id("rightClickBtn"));
		//doubleClickBtn doubleClickMessage    rightClickBtn rightClickMessage
		Actions action = new Actions(driver);
		
		action.doubleClick(ele).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickMessage")));

		
		action.contextClick(ele2).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickMessage")));
		//ele.click();
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
	
	@Test
public void cetselect() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/droppable");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		WebElement ele = driver.findElement(By.id("draggable"));
		
		WebElement ele2 = driver.findElement(By.id("droppable"));
		//doubleClickBtn doubleClickMessage    rightClickBtn rightClickMessage
		Actions action = new Actions(driver);
		
		action.dragAndDrop(ele, ele2).build().perform();
		
		System.out.println(ele2.getText());
		
		
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
	
	@Test
public void detselect() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/menu");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		WebElement manu1 = driver.findElement(By.xpath("//*[@id=\"nav\"]//a[text()='Main Item 2']"));
		
		WebElement manu2 = driver.findElement(By.xpath("//*[@id=\"nav\"]//a[text()='SUB SUB LIST »']"));
		WebElement finalkey = driver.findElement(By.xpath("//*[@id=\"nav\"]//a[text()='Sub Sub Item 2']"));
		
		
		Actions action = new Actions(driver);
		
		action.moveToElement(manu1).build().perform();
		
		action.moveToElement(manu2).build().perform();
		
		finalkey.click();
		
		
		
		//
		
		action.keyDown(Keys.CONTROL);
		action.sendKeys("c");
		action.keyUp(Keys.CONTROL);
		action.build().perform();
	        
		action.sendKeys(Keys.TAB);
		action.build().perform();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.close();
		driver.quit();
			
		}
	
	
	@Test
public void alertsaa() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/alerts");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		
		WebElement ele = driver.findElement(By.id("alertButton"));
		
		ele.click();
		
		driver.switchTo().alert().accept();
		
		WebElement ele1 = driver.findElement(By.id("timerAlertButton"));
		//timerAlertButton
		
		WebElement ele2 = driver.findElement(By.id("confirmButton"));
		//confirmButton
		
		WebElement ele3 = driver.findElement(By.id("promtButton"));
		//promtButton
		
		ele1.click();
		
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		ele2.click();
		
		driver.switchTo().alert().dismiss();
		
		ele3.click();
		
		driver.switchTo().alert().sendKeys("Hiiiii");
		
		String pmptext = driver.switchTo().alert().getText();
		
		driver.switchTo().alert().accept();
		
	
		
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

//*[@id="nav"]//a[text()='Main Item 2']
