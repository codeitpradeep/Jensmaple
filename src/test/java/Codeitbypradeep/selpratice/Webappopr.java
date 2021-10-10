package Codeitbypradeep.selpratice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Webappopr {

	@Test
	public void executeall(){
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		//WebDriver dr = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.phptravels.net/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated());
		
		//
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='Tab']/child::li[@class='nav-item']")));
		
		List<WebElement> weblist = driver.findElements(By.xpath("//*[@id='Tab']/child::li[@class='nav-item']"));
		
		for(WebElement driverdata: weblist){
			
			System.out.println(driverdata.getText());
			
		}
		//Flights
		String flightbutton = "//*[@id='Tab']//following::button[@id='hotels-tab'][@aria-controls='flights']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(flightbutton)));
		driver.findElement(By.xpath(flightbutton)).click();
		// //*[@id="autocomplete"]  //*[@id='autocomplete2']
		
		String textbox1 , textbox2 ,Tamanrasset,Gusau,formbuttton;
		textbox1 = "//*[@id='autocomplete']";
		textbox2 = "//*[@id='autocomplete2']";
		Tamanrasset = "//strong[contains(text(),'Tamanrasset')]//preceding::div[@class='autocomplete-result']";
		Gusau = "//strong[contains(text(),'Gusau')]//preceding::div[@class='autocomplete-result']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textbox1)));
		
		
		driver.findElement(By.xpath(textbox1)).sendKeys("madras");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tamanrasset)));
		driver.findElement(By.xpath(Tamanrasset)).click();
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Tamanrasset)));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textbox2)));
		driver.findElement(By.xpath(textbox2)).sendKeys("usa");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Gusau)));
		driver.findElement(By.xpath(Gusau)).click();
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Gusau)));
		
		
		formbuttton = "//*[@id='flights-search']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formbuttton)));
		driver.findElement(By.xpath(formbuttton)).click();
		
		
		String newpagetext = "//*[@class='breadcrumb-content']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newpagetext)));
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(newpagetext)));
		
		
		
		driver.close();
		driver.quit();
		
		
	}
	
	
}
