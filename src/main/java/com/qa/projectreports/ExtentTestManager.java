package com.qa.projectreports;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.Codecommands.Funtionalibrary;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {

	static Map<Integer,ExtentTest> extentTestMap = new HashMap<Integer,ExtentTest>();
	static Map<Integer,ExtentTest> extentCompMap = new HashMap<Integer,ExtentTest>();
	private static Logger log = Logger.getLogger(Funtionalibrary.class.getName());
	private static ExtentReports extent;
	//private static Properties property;
	
	public static Properties sysproperty;
	
	
	public synchronized static void ReportStartWithfilename(int filecount, String reportfilename) throws FileNotFoundException, IOException {
		System.out.println("Here");
		
		String configpath="./config/SystemConfig.properties";
		sysproperty = new Properties();
		sysproperty.load(new FileInputStream(new File(configpath)));
		
		PropertyConfigurator.configure(sysproperty.getProperty("log4jconfigPath"));
		
		extent = ExtentManager.getReport("./"+sysproperty.getProperty("reportpath")+"/"+reportfilename);
		extent.loadConfig(new File(sysproperty.getProperty("extentReportConfigFile")));
		
	}


	public static synchronized void addreportversioninfo(String string, String string2) {
		// TODO Auto-generated method stub
		extent.addSystemInfo(string, string2);
	}


	public static synchronized void startTestcaseReport(String testname, String testdesc, String category, String author) {
		// TODO Auto-generated method stub
		ExtentTest testcase = extent.startTest(testname, testdesc);
		
		String[] arrCategory = category.split(",");
		for (String eachCategory : arrCategory) {
			if( !(eachCategory.equals("")) ){
				testcase.assignCategory(eachCategory.trim());
			}
		}
		
		String[] arrAuthor = author.split(",");
		for (String eachAuthor : arrAuthor) {
			if( !(eachAuthor.equals("")) ){
				testcase.assignAuthor(eachAuthor.trim());
			}
		}
		
		extentTestMap.put((int)(long)(Thread.currentThread().getId()), testcase);
		
	}


	public static synchronized void endTestCase() throws FileNotFoundException, IOException {
		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
		extent.flush();
	log.info("*** End of TestCase ***\n");
	}

	public static synchronized ExtentTest getComponentThread() {
		return (ExtentTest) extentCompMap.get((int) (long) (Thread.currentThread().getId()));
	}
	public static synchronized ExtentTest getTestCaseThread() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}


	
	//-----------------      Report action ---------------------//
	/*
	 * startComponent
	 * reportStepInfo
	 * reportStepError
	 * reportStepSkip
	 * appendComponent
	 * reportStepFail
	 */
	
	public static synchronized ExtentTest startComponent(String componentName, String componentDesc){

		ExtentTest rComponent = extent.startTest(componentName, componentDesc);
		extentCompMap.put((int) (long) (Thread.currentThread().getId()), rComponent);
//		log.info("*** Component Execution Starts ***");
//		log.info("Component Name : "+componentName+" | Component Desc : "+componentDesc);
		return rComponent;
	}
	public static synchronized void reportStepInfo(String message){

		ExtentTest testStep = getComponentThread();
		testStep.log(LogStatus.INFO, message);
//		log.info("INFO : "+message );

	}
	public static synchronized void reportStepError(String message){

		ExtentTest testStep = getComponentThread();
		testStep.log(LogStatus.ERROR, message );
//		log.info("ERROR : "+message);

	}
	public static synchronized void reportStepSkip(String message){

		ExtentTest testStep = getComponentThread();
		testStep.log(LogStatus.SKIP, message);
//		log.info("INFO : "+message );

	}
	public static synchronized void appendComponent(){
		ExtentTest testCase = getTestCaseThread();
		ExtentTest component = getComponentThread();
		testCase.appendChild(component);
		extent.flush();
//		log.info("*** End of Component ***");
	}
	public static synchronized void reportStepFail(RemoteWebDriver driver, String message, boolean takeScreenShot){

		ExtentTest testStep = getComponentThread();

		//Delimit the XML Values
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < message.length(); i++){
			char c = message.charAt(i);
			switch(c){
			case '<': sb.append("&lt;"); break;
			case '>': sb.append("&gt;"); break;
			case '\"': sb.append("&quot;"); break;
			case '&': sb.append("&amp;"); break;
			case '\'': sb.append("&apos;"); break;
			default:
				if(c>0x7e) {
					sb.append("&#"+((int)c)+";");
				}else
					sb.append(c);
			}
		}
		String messageToWrite =  sb.toString();

		if(takeScreenShot){

			String  ssFileName=new SimpleDateFormat ("dd_MM_yyyy_hh_mm_ss_a").format(new Date());
			//Make a directory if not available for Extent Reports
			if(!(new File(System.getProperty("user.dir")+"/"+sysproperty.getProperty("reportpath")+"/"+"_Screenshots/").exists())){
				new File(System.getProperty("user.dir")+"/"+sysproperty.getProperty("reportpath")+"/"+"_Screenshots/").mkdir();
			}


			//Create Screenshots based in driver value
			if(driver.getSessionId() == null || driver.getSessionId().toString().length()==0 || driver==null){
				try {
					Robot robot = new Robot();
					String fileName =System.getProperty("user.dir")+"/"+sysproperty.getProperty("reportpath")+"/"+"_Screenshots/"+ssFileName+".jpg";
					Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
					ImageIO.write(screenFullImage, "jpg", new File(fileName));
				} catch (Exception e) {
				//	log.error("Exception while creating ScreenShot using Robot. Exception is --> "+ e);
				}
			}else{
				try {
					FileUtils.moveFile(driver.getScreenshotAs(OutputType.FILE) , new File("./"+sysproperty.getProperty("reportpath")+"/"+"_Screenshots/"+ssFileName+".jpg"));
				} catch (IOException e) {
				//	log.error("Exception while creating ScreenShot using WebDriver. Exception is --> "+ e);
				}
			}

			testStep.log(LogStatus.FAIL, messageToWrite+testStep.addScreenCapture("./_Screenshots/"+ssFileName+".jpg"));
		}else{
			testStep.log(LogStatus.FAIL, messageToWrite);
		}

//		log.info("FAIL : "+message);
	}

	public static synchronized void reportStepFail(String message, boolean takeScreenShot){
		RemoteWebDriver driver = null;
		//Delimit the XML Values
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < message.length(); i++){
			char c = message.charAt(i);
			switch(c){
			case '<': sb.append("&lt;"); break;
			case '>': sb.append("&gt;"); break;
			case '\"': sb.append("&quot;"); break;
			case '&': sb.append("&amp;"); break;
			case '\'': sb.append("&apos;"); break;
			default:
				if(c>0x7e) {
					sb.append("&#"+((int)c)+";");
				}else
					sb.append(c);
			}
		}
		String messageToWrite =  sb.toString();

		reportStepFail(driver, messageToWrite, takeScreenShot);
	}

	public static synchronized void reportStepPass(String message){

		ExtentTest testStep = getComponentThread();
		//Delimit the XML Values
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < message.length(); i++){
			char c = message.charAt(i);
			switch(c){
			case '<': sb.append("&lt;"); break;
			case '>': sb.append("&gt;"); break;
			case '\"': sb.append("&quot;"); break;
			case '&': sb.append("&amp;"); break;
			case '\'': sb.append("&apos;"); break;
			default:
				if(c>0x7e) {
					sb.append("&#"+((int)c)+";");
				}else
					sb.append(c);
			}
		}
		String messageToWrite =  sb.toString();
		testStep.log(LogStatus.PASS, messageToWrite);
//		log.info("PASS : "+message );

	}
	


	
	
	
	
}
