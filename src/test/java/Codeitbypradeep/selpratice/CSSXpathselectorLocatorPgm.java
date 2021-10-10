package Codeitbypradeep.selpratice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CSSXpathselectorLocatorPgm {
	
	@Test
	public void getmain(){
		System.setProperty("webdriver.chrome.driver", ".//Scriptdriver//chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.phptravels.net");
		
		driver.manage().timeouts().pageLoadTimeout( 30 , TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("section-tab")));
		if(driver.findElement(By.className("section-tab")).isDisplayed()){
			System.out.println(driver.findElement(By.className("section-tab")).isDisplayed());
		}
		//Find elements via the driver's underlying W3C Selector engine. If the browser does notimplement the Selector API, a best effort is made to emulate the API. In this case, we strivefor at least CSS2 support, but offer no guarantees.
		
		//1 . Tag and ID  div#TabContent
		driver.findElement(By.cssSelector("div#TabContent"));
		
		
		//2 . Tag and Class div.section-tab
		driver.findElement(By.cssSelector("div.section-tab"));
		
		//3 . Tag and attribute ul[role="tablist"]
		driver.findElement(By.cssSelector("ul[role='tablist']"));
		
		
		//4 . Tag class/id  attribute  button#hotels-tab[data-bs-target="#flights"]
		driver.findElement(By.cssSelector("button#hotels-tab[data-bs-target='#flights']"));
		
		
		//5 . sub Stings ^ , $ , contains(*)
		driver.findElement(By.cssSelector("div[class^='section-t']"));
		driver.findElement(By.cssSelector("div[class$='tion-tab']"));
		//driver.findElement(By.cssSelector("div:conatins('tion-ta')"));
		
		
		
		
		//  ============   XPATH  ====================
		/*
		 XPath is designed to allow the navigation of XML documents, with the purpose of selecting individual elements, attributes, or some other part of an XML document for specific processing.
		 */
		/*Single / :Absolute XPath starts from the root node and ends with desired descendant element’s node. It starts with the top HTML node and ends with the input node. It starts with a single forward-slash(/)
		 * Single slash is used to create XPath with absolute path i.e. the XPath would be created to start selection from the document node/start node/parent node.
		*/
		
		
		/*slash(//) : Relative XPath starts from any node in between the HTML page to the current element’s node(last node of the element). It starts with a double forward-slash(//) 
		 * This mechanism is also known as finding elements using Relative XPath.

Double slash is used to create XPath with relative path i.e. the XPath would be created to start selection from anywhere within the document. – Search in a whole page (DOM) for the preceding string
		 
		 */
		
		//Single attribute
		driver.findElement(By.xpath("//*[@id='tours-tab']"));
		
		//Multiple 
		driver.findElement(By.xpath("//button[@id='tours-tab'][@type='button']"));
		
		//AND OR 
		driver.findElement(By.xpath("//span[@id='select2-hotels_city-container' or @class='selectio']"));
		driver.findElement(By.xpath("//span[@id='select2-hotels_city-container' and @class='select2-selection__rendered']"));
		
		
		//contains
		driver.findElement(By.xpath("//*[contains(@aria-controls,'hotels')]"));
		
		//[last()] [last()-1...2...3...]
		System.out.printf("%s",driver.findElement(By.xpath("//*[@class='nav-item'][last()]")).getText());
		
		//
		System.out.printf("%s",driver.findElement(By.xpath("//*[@class='nav-item'][last()]")).getText());
		
		System.out.printf("%s",driver.findElement(By.xpath("//*[@class='nav-item'][position()=2]")).getText());
		
		
		System.out.printf("%s",driver.findElement(By.xpath("(//*[contains(text(),'flights')])[2]")).getText());
					
		//h4[@class='info__title' and contains(text(),'You')]//following-sibling::p
		//following-sibling
		/*
		 <div>
		 	<h4>.....<h4>
		 	<p>......</p>
		 </div> 
		 
		 */
		System.out.printf("%s",driver.findElement(By.xpath(
				"//h4[@class='info__title' and contains(text(),'You')]//following-sibling::p")).getText());
		
		//h4[@class='info__title' and contains(text(),'You')]//ancestor::div[5]
		
		System.out.printf("%s",driver.findElement(By.xpath(
				"//h4[@class='info__title' and contains(text(),'You')]//ancestor::div[5]")).getText());
		
		
		
		//h4[@class='info__title' and contains(text(),'You')]//parent::div
		/*
		 <div>
		 	<h4>.....<h4>
		 	<p>......</p>
		 </div> 
		 
		 */
		System.out.printf("%s",driver.findElement(By.xpath(
				"//h4[@class='info__title' and contains(text(),'You')]//parent::div")).getText());
		
		
		
		//h4[@class='info__title' and contains(text(),'You')]//following::p[2]
		/*
		 <div>
		 	<h4>.....<h4>
		 	<p>......</p>
		 </div> 
		 <div>
		 	<h4>.....<h4>
		 	<p>......</p>
		 </div>
		 <div>
		 	<p>......</p>
		 	<p>......</p>
		 </div>
		 
		 */
		System.out.printf("%s",driver.findElement(By.xpath(
				"//h4[@class='info__title' and contains(text(),'You')]//following::p[2]")).getText());
		
		
		//h4[@class='info__title' and contains(text(),'You')]//preceding::button[7]
		
		
		/*
		 <div>
		 	<button>.....<>
		 	<p>......</p>
		 </div> 
		 <div>
		 	<button>.....<>
		 	<p>......</p>
		 </div>
		 <div>
		 	<h4>......<>
		 	<p>......</p>
		 </div>
		 
		 */
		System.out.printf("%s",driver.findElement(By.xpath(
				"//h4[@class='info__title' and contains(text(),'You')]//preceding::button[7]")).getText());
		
		
		
		//  (//*[@id='tours-tab']//descendant::i)[1]
		/*
		 <button id='tours-tab'>
		 	<div>
		 		<i>....
		 	</div>
		 	<div>
		 		<i>....
		 		<i>....
		 		<i>....
		 	</div>
		 </button>
		 */
		System.out.printf("%s",driver.findElement(By.xpath(
				"(//*[@id='tours-tab']//descendant::i)[1]")).getText());
		
		driver.close();
		driver.quit();
		
	}

}
