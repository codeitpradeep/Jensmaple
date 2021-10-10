package Codeitbypradeep.selpratice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class pgmSelectMultiple {
	@Test
	public void getselect() {
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demoqa.com/select-menu");
		
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver , 30);
		
		WebElement ele = driver.findElement(By.id("cars"));
		
		Select sel = new Select(ele);
		driver.executeScript("arguments[0].scrollIntoView({block: \"center\"});", ele);
		
		sel.selectByIndex(1);
		sel.selectByIndex(2);
		
		List<WebElement> ls = sel.getAllSelectedOptions(); 
		for(WebElement s:ls) {
			System.out.println(s.getText());
		}
		WebElement ls2 = sel.getFirstSelectedOption();
		
		System.out.println("First - "+ls2.getText());
		
		sel.deselectByVisibleText(ls2.getText());
		
		sel.deselectAll();
		
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
