package Codeitbypradeep.selpratice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class testqapratice {
	@Test
	public void dfdsfasfd()
	{
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		WebDriverWait wait  = new WebDriverWait(driver, 30);
		
		
		driver.get("https://demoqa.com/text-box");
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		String testone = "userName";
		WebElement tagtextu =driver.findElement(By.id(testone));
		
		tagtextu.isDisplayed();
		
		
		String tagtext =driver.findElement(By.id(testone)).getTagName();
		String cssvalue =driver.findElement(By.id(testone)).getCssValue("padding");
		String attribute =driver.findElement(By.id(testone)).getAttribute("type");
		//placeho
		String attribute2 =driver.findElement(By.id(testone)).getAttribute("placeholder");
		
		System.out.println(tagtext+"\n"+cssvalue+"\n"+attribute+"\n"+attribute2);
		
		driver.findElement(By.id(testone)).sendKeys("Pradeep");
		
		String submit = "submit";
		
		JavascriptExecutor js = driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id(submit)));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(submit)));
		
		driver.findElement(By.id(submit)).click();
		
		String newtext = "//*[@id='name']";

		String anstext = driver.findElement(By.xpath(newtext)).getText();
		System.out.println(anstext);
		
		String itsvalue = driver.findElement(By.id(testone)).getAttribute("value");//Pradeep

		System.out.println(itsvalue);
		
		
		driver.findElement(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]")).click();		
		//Select s =new Select(driver.findElement(By.xpath(newtext)));
		
		//Actions a= new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]//following::*[@class='text'][contains(text(),'Text Box')]")));
		
		driver.findElement(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]//following::*[@class='text'][contains(text(),'Check Box')]")).click();		
		
		
		driver.findElement(By.xpath("//button[@class='rct-collapse rct-collapse-btn']")).click();		
		
		
		driver.findElement(By.xpath("(//span[@class='rct-checkbox']//child::*[@stroke=\"currentColor\"])[1]")).click();
		
		if(driver.findElement(By.xpath("(//span[@class='rct-checkbox']//child::*[@stroke=\"currentColor\"])[2]")).isSelected()) {
			System.out.println("True");
		}
		
		
		//Radio button
		driver.findElement(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]")).click();		
		
		driver.findElement(By.xpath("(//*[@class='header-wrapper']//child::span[@class='pr-1'])[1]//following::*[@class='text'][contains(text(),'Radio Button')]")).click();		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"yesRadio\"]")));
	
		WebElement el = driver.findElement(By.id("yesRadio"));
		System.out.println(el.isSelected()+" radiobutton");
		driver.findElement(By.xpath("//*[@id=\"yesRadio\"]//following::label[@for='yesRadio']")).click();
		
		System.out.println(el.isSelected()+"sel radiobutton");
		System.out.println(el.isEnabled()+" Here");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(driver.findElement(
				By.xpath("//*[@id='noRadio']")).isSelected()+" isSelected");
		System.out.println(driver.findElement(
				By.xpath("//*[@id='noRadio']")).isDisplayed()+" isDisplayed");
		System.out.println(driver.findElement(
				By.xpath("//*[@id='noRadio']")).isEnabled()+" isEnabled");
		driver.findElement(By.xpath("//*[@id=\"noRadio\"]//following::label[@for='noRadio']")).click();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
		driver.quit();
		
	}

}
