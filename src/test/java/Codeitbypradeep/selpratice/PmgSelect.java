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

public class PmgSelect {
	@Test
	public void getselect() {
	
	System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
	
	RemoteWebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	driver.get("https://mdbootstrap.com/docs/b4/jquery/forms/select/");
	
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	
	WebDriverWait wait = new WebDriverWait(driver , 30);
	
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='p-1']//child::select[@class='browser-default custom-select']")));
	
	WebElement ele = driver.findElement(By.xpath("//section[@class='p-1']//child::select[@class='browser-default custom-select']"));
	
	
	System.out.println(ele.getTagName());
	
	driver.executeScript("arguments[0].scrollIntoView({block: \"center\"});", ele);
	Select select = new Select(ele);
	
	select.selectByVisibleText("One");
	select.selectByValue("1");
	select.selectByIndex(1);
	
	List<WebElement> lstele = select.getOptions();
	
	List<WebElement> getss = ele.findElements(By.tagName("option"));
	
	System.out.println(lstele.size());
	
	for(WebElement lis:getss) {
		System.out.println(lis.getText());
	}
	
	
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(ele.isSelected()+" Selected- "+ele.getAttribute("value"));
	
	driver.close();
	driver.quit();
		
	}
}
