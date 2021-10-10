package Codeitbypradeep.selpratice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LocatorPrograms {
	
	@Test
	public void locat() throws InterruptedException{
		String url = "https://www.w3schools.com/w3css/tryw3css_templates_apartment_rental.htm";
		
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		WebDriverWait wait = new  WebDriverWait(driver, 30);
		
		WebElement element = null;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("apartment")));
		
		//By id 
		/*Find the first WebElement using the given method.
		 This method is affected by the 'implicit wait' times in force at the time of execution.
		 The findElement(..) invocation will return a matching row, or 
		 try again repeatedly untilthe configured timeout is reached. 

		 findElement should not be used to look for non-present elements, 
		 use findElements(By)and assert zero length response instead. 
		 Parameters:by The locating mechanism to use
		 Returns:The first matching element on the current page
		 Throws:NoSuchElementException - If no matching elements are found.
		 
		 ID’s are unique for each element so it is common way to locate elements using ID Locator. 
		 As per W3C, ID’s are supposed to be unique on a page and it makes ID’s are the most reliable locator. 
		 ID locators are the fastest and safest locators out of all locators.

		 id = id of the element
		 */
		element = driver.findElement(By.id("apartment"));
		System.out.println("by id "+element);
		
		//By name 
		/*
		We sometimes use Name locator to identify the elements on our webpage. Locating elements using Name is same as 		locating elements using ID locator.

		These are not unique on a page. If there are multiple elements with the same Name locator then the first element 		on the page is selected. Test may fail, if another element with the same Name locator is present on the web page 		or added by the developers in the later stages
		 
		<input class="w3-input w3-border" type="text" placeholder="DD MM YYYY" name="CheckIn" required="">
		 */
		element = driver.findElement(By.name("CheckIn"));
		
		//By classname
		/*
		 * Class Name locator gives the element which matches the values specified in the attribute name “class”.
		 * <i class="fa fa-fw fa-bed"></i>
		 */
		
		//element = driver.findElement(By.className("fa fa-fw fa-bed"));
		
		
		//By tagname
		/*
		 Tag Name locator is used to find the elements matching the specified Tag Name. It is very helpful when we want 		to extract the content within a Tag.
		<button></button>
		 */
		int i=0;
		List<WebElement> ls = driver.findElements(By.tagName("button"));
		System.out.println(ls.size());
		
		
		//By linktext / partial linktext
		/*
		Link Text Locator:
		If there are multiple elements with the same link text then the first one will be selected. This Link Text 		locator works only on links (hyperlinks) so it is called as Link Text locator.
		
		Partial Link Text:
		In some situations, we may need to find links by a portion of the text in a Link Text element. it contains. In 		such situations, we use Partial Link Text to locate elements.
		 */
		
		driver.findElement(By.linkText("w3.css"));
		
		driver.findElement(By.partialLinkText(".css"));
		
		
		Thread.sleep(1000*5);
		
		driver.close();
		
	}
	
	
}
