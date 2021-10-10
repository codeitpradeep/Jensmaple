package Codeitbypradeep.selpratice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Pgmwindw {

	
	@Test
public void alertsaa() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/browser-windows");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		//tabButton
		
		driver.findElement(By.id("tabButton")).click();
		String parentwindw = driver.getWindowHandle();
		
	
		
		System.out.println(parentwindw);
		
		
		List<String> list = new ArrayList<>(driver.getWindowHandles());
		
		System.out.println(list.get(1));
		driver.switchTo().window(list.get(1));
		
		driver.navigate().to("https://demoqa.com/browser-windows");
		driver.findElement(By.id("tabButton")).click();
		
		driver.close();
		
		
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//driver.close();
		driver.quit();
			
		}


	@Test
public void blertsaa() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/browser-windows");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		//tabButton
		
		driver.findElement(By.id("windowButton")).click();
		String parentwindw = driver.getWindowHandle();
		
	
		
		System.out.println(parentwindw);
		
		
		List<String> list = new ArrayList<>(driver.getWindowHandles());
		
		System.out.println(list.get(1));
		driver.switchTo().window(list.get(1));
		
		driver.navigate().to("https://demoqa.com");
		driver.manage().window().maximize();
		//driver.findElement(By.id("windowButton")).click();
		
		driver.close();
		
		driver.switchTo().window(parentwindw);
		
		driver.close();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//driver.close();
		driver.quit();
			
		}

	
	@Test
public void clertsaa() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/frames");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		//tabButton
		WebElement hd = driver.findElement(By.xpath("//*[@id=\"framesWrapper\"]"));
		
		List<WebElement> ele = hd.findElements(By.tagName("iframe"));

		System.out.println(ele.size());
		
		for(WebElement a:ele){
			System.out.println(a.getText());
		}
		
		
		driver.close();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//driver.close();
		driver.quit();
			
		}

	
	@Test
public void dlertsaa() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/nestedframes");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		
		
		
		//tabButton
		WebElement hd = driver.findElement(By.xpath("//*[@id=\"frame1\"]"));
		
		
		driver.switchTo().frame(hd);
		System.out.println(driver.findElement(By.xpath("//*[text()='Parent frame']")).getText());
		
		List<WebElement> ele = driver.findElements(By.tagName("iframe"));

		System.out.println(ele.size());
		driver.switchTo().frame(0);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		
		driver.switchTo().defaultContent();
		//driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]")).click();		
		driver.switchTo().parentFrame();
		
		
		
		try {
			Thread.sleep(5000*2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.close();
		driver.quit();
			
		}


}

