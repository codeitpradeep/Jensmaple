package com.qa.Codecommands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import com.qa.Utils.AccessExcel;
import com.qa.projectreports.ExtentTestManager;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Funtionalibrary {

	protected RemoteWebDriver driver;
	
	protected String testcaseDataFilepath;
	protected String testcaseComponentName;
	protected int testcasecounter;
	//Properties
	public static Properties sysproperty; 
	public static Properties property; 
	
	protected static Logger log = Logger.getLogger(Funtionalibrary.class.getName());
	
	public void initializationScript() {
		// TODO Auto-generated method stub
		sysproperty = new Properties();
		property = new Properties();
		String configpath="./config/SystemConfig.properties";
		String configpathSTDev="./config/ST_AutomationDev.properties";
		String configpathSTReg="./config/ST_AutomationRegression.properties";
		try {
			sysproperty.load(new FileInputStream(new File(configpath)));
			
			PropertyConfigurator.configure(sysproperty.getProperty("log4jconfigPath"));
			
			if(sysproperty.getProperty("TESTEXEC").equalsIgnoreCase("DEV")){
				property.load(new FileInputStream(new File(configpathSTDev)));
			}else if(sysproperty.getProperty("TESTEXEC").equalsIgnoreCase("REG")){
				property.load(new FileInputStream(new File(configpathSTReg)));
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log4jconfigPath
	}
	
	public synchronized  String getTextfromExcel(String testcaseDataFilepath2, String testcaseComponentName2, String colname,
			int testcasecounter2) {
		String ExcelcellData = null ;
		try{
			AccessExcel readCellText = new AccessExcel(testcaseDataFilepath2);
			ExcelcellData = readCellText.getCellData(testcaseComponentName2, colname, testcasecounter2);
		}catch(Exception e){
			log.info("Error get from reading Excel file");
			throw e;
		}
		if(ExcelcellData == null){
			log.info("Excel "+colname+" not avaliable in "+testcaseComponentName2);
			throw new RuntimeException("Excel "+colname+" not avaliable in "+testcaseComponentName2);
		}
		return ExcelcellData;
	}
	
	
	/**
	 * FrameWork build by Selenium .
	 * Hybrid Data Driven 
	 * @author Pradeep
	 */
	
	/**
	 * LaunchingWebApplication - 
	 * @param url
	 * @param eventflag
	 * @return boolean
	 * Desc - We use Chrome to launch appication .
	 */
	public synchronized boolean LaunchingWebApplication(String url,  int eventflag){
		boolean getfunctionflag = false;
		String getvaluefromExcel = null;
		String getURLFunc = null , 
				browser = null;
		
		try{
		if(eventflag == 1){
			getvaluefromExcel = getTextfromExcel(testcaseDataFilepath,testcaseComponentName,url,testcasecounter);
		}
		
		getURLFunc = property.getProperty(getvaluefromExcel);
		log.info("@@@#######@@@@@@@@@@@#########@@@@@@@@@@$$$$$$$$$$$$$$@@@@@@@@"+getURLFunc);
		browser = property.getProperty("executeWebtest");
		//executeWebtest
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
	        
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.get(getURLFunc);
	        
	        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	        getfunctionflag = true;
	        ExtentTestManager.reportStepPass("Launch Application successfull <b>"+getURLFunc+" in "+browser+"</b>");
		}
		}catch(WebDriverException e){
			log.info("WebDriver Exception : "+e.getMessage());
			ExtentTestManager.reportStepFail(driver, "Unable to Launch", true);
		}
		
		return getfunctionflag;	
	}

	/**
	 * CloseWebApplication -
	 * @return boolean
	 * Desc - .quit() to close Web browser
	 */
	public synchronized boolean CloseWebApplication(){
		boolean getfunctionflag = false;
		String browser = null;
		
		try{
		
		browser = property.getProperty("executeWebtest");
		//executeWebtest
		if(browser.equalsIgnoreCase("chrome")){
			driver.close();
			driver.quit();
			log.info("Closing Browser successful ");
			ExtentTestManager.reportStepPass("Closing Browser successful <b> in "+browser+"</b>");
	        getfunctionflag = true;
		}else if(getfunctionflag){
			Thread.sleep(1000);
			
			getfunctionflag = true;
		}
		}catch(Exception e){
			log.info("Closing Exception : "+e.getMessage());
			getfunctionflag = false;
			ExtentTestManager.reportStepFail(driver, "Unable to Closing"+e.getMessage(), true);
		}
		driver = null;
		return getfunctionflag;	
	}

	/**
	 * pageLoadcheckState - 
	 * @return
	 * Desc - 
	 */
	public synchronized boolean pageLoadcheckState(){
		boolean getfunctionflag = false;
		int stageflag = 0;
		try{
		JavascriptExecutor js =  (JavascriptExecutor) driver ;
		
		for(int i=0;i <= 60;i++){
			switch(js.executeScript("return document.readyState").toString().toLowerCase()){
			case "loading": 
				// Doc still loading
				break;
			case "interactive":
				// Document has finished loading . Access Dom success .
				// But sub-resources such as scripts, images, stylesheets and frames are still loading.
				break;
			case "complete": stageflag = 1;
				break;
				default:
					getfunctionflag = false;
					break;
			}
		}
		if(stageflag == 1){
			ExtentTestManager.reportStepPass("Page DOM and Sourse Loading successfull ");
			log.info("Page DOM and Sourse Loading successfull ");
			getfunctionflag = true;
		}else{
			log.info("Page DOM and Sourse Con't Loading successfull ");
			getfunctionflag = false;
		}
		}catch(Exception e){
			log.info("Page load occurs Error"+e.getStackTrace());
			getfunctionflag = false;
		}
		
		
		return getfunctionflag;		
	}

	public synchronized boolean WebClickDOMElement(String domelement,String eventname,int flag){
		WebElement element = null;
		boolean getfunctionflag = false;
		
		try{
		String browser = property.getProperty("executeWebtest");
		if(browser.equalsIgnoreCase("chrome")){
			element = LocatorTypes(domelement);
			element.click();
			getfunctionflag = true;
		}else{
			element = LocatorTypes(domelement);
			element.click();
			getfunctionflag = true;
			
			Select s = new Select(element);
			s.selectByIndex(flag);
		}
		ExtentTestManager.reportStepPass("WebElement  Clicked success at ["+eventname+"]");
		
		log.info("WebElement Clicked success at ["+eventname+"]");
		}catch(StaleElementReferenceException e){
			return WebClickDOMElement(domelement, eventname, flag);
		}catch(Exception e){
			ExtentTestManager.reportStepFail(driver, "Exception occurs in WebClickDOMElement", true);
			log.info("Exception occurs in WebClickDOMElement"+e.getStackTrace());
		}
		
		
		return getfunctionflag;		
	}

	public synchronized WebElement LocatorTypes(String domelement) {
		WebElement element = null;
		String dom = domelement;
		if(domelement == null){
			log.info("Locator or Key not found Page Object class");
		}
		
		try{
		String domlocatortype = dom.split("=")[0];
		String domlocatorvalue = dom.split("=")[1];
		
		
		String loc = domlocatortype;
		switch(loc.toLowerCase()){
		case "classname" :element = driver.findElement(By.className(domlocatorvalue));
		break;
		case "cssselector" :element = driver.findElement(By.cssSelector(domlocatorvalue));
		break;
		case "id" :element = driver.findElement(By.id(domlocatorvalue));
		break;
		case "linktext" :element = driver.findElement(By.linkText(domlocatorvalue));
		break;
		case "name" :element = driver.findElement(By.name(domlocatorvalue));
		break;
		case "partiallinktext" :element = driver.findElement(By.partialLinkText(domlocatorvalue));
		break;
		case "tagName" :element = driver.findElement(By.tagName(domlocatorvalue));
		break;
		case "xpath" :element = driver.findElement(By.xpath(domlocatorvalue));
		break;
		default:
			throw new IllegalArgumentException("Check in POM");
		}
		}catch(StaleElementReferenceException e){
			return LocatorTypes(dom);
		}catch(NoSuchElementException e){
			log.info("No Such Element in DOM page "+domelement+ " found or Display");
			return null;
		}catch(org.openqa.selenium.UnhandledAlertException e){
			return null;
		}catch(Exception e){
			log.info("Exception occurs "+domelement+ " is "+e.getMessage());
			return null;
		}
		return element;
	}
	public synchronized boolean WaitforElement(String domelement, String datadesc , int i) {
		boolean funtionalflag = false ;
		WebElement element = null;
		String dom = domelement;
		String data = datadesc;
		int flag = i;
		if(domelement == null){
			log.info("Locator or Key not found Page Object class");
		}
		
		try{
			String domlocatortype = dom.split("=")[0];
			String domlocatorvalue = dom.split("=")[1];
			
			WebDriverWait wait = new WebDriverWait(driver,Integer.parseInt(sysproperty.getProperty("waitforDOMelement")));
			String loc = domlocatortype;
			switch(loc.toLowerCase()){
			case "classname" :element = 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			}
					
			break;
			case "cssselector" :element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			} 
			break;
			case "id" :element =element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			} 
			break;
			case "linktext" :element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			} 
			break;
			case "name" :element  =wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			}  
			break;
			case "partiallinktext" :element  =wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			}  
			break;
			case "tagName" :element  =wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			}  
			break;
			case "xpath" :element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(domlocatorvalue)));
			if(element.isDisplayed()){
				funtionalflag = true ;
			}else{
				funtionalflag = false ;
			}  
			break;
			default:
				throw new IllegalArgumentException("Check in POM");
			}
			if(funtionalflag){
				ExtentTestManager.reportStepPass("Web Element "+data+" waited and displayed ");
				
			}
			}catch(StaleElementReferenceException e){
				return WaitforElement(dom, data, flag);
			}catch(NoSuchElementException e){
				log.info("No Such Element in DOM page "+domelement+ " found or Display");
				funtionalflag = false ;
			}
			if(!funtionalflag){
				ExtentTestManager.reportStepFail(driver, "Exception occurs in WebClickDOMElement", true);
				
			}
		
		
		return funtionalflag;
	}
	
	
	
	/**
	 * Selenium Ends
	 */
}
