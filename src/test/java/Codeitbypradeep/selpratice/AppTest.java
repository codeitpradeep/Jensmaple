package Codeitbypradeep.selpratice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * 
     * Web
     */
	
	
	
    @Test(threadPoolSize = 3,invocationCount=3)
    public void shouldAnswerWithTrue()
    {
    	//System.setProperty("webdriver.chrome.driver","..\\selpratice\\Scriptdriver\\chromedriver.exe");
    	System.out.println( "Hello World!" );
        WebDriverManager.chromedriver().setup();
        
    	
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        WebDriverWait wait=new WebDriverWait(driver, 20);
        
        driver.get("https://www.w3schools.com/w3css/tryw3css_templates_apartment_rental.htm");
        
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contact")));
        WebElement ele = driver.findElement(By.id("contact"));
        if(ele.isDisplayed()){
        	System.out.println(ele.isDisplayed());
        }
        
        String gettitleurl = driver.getTitle();
        
        String urlpage = driver.getPageSource();
        
        System.out.println(""+gettitleurl
        		+"\n"+urlpage);
        
        learnlocator(driver);
        
        
        driver.close();
        driver.quit();
        
    }

	public void learnlocator(WebDriver driver) {
		WebElement element = null;
		//
		
		
	}
}
