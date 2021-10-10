package com.qa.TestcaseExecution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;

import com.qa.Codecommands.Funtionalibrary;
import com.qa.projectreports.ExtentTestManager;

public class DriverTestNG extends Funtionalibrary {
	
	
	public static int filecount;
	public static String reportfilename;
	
	public boolean calledMyMethod ;
	
	private void secureKillDriverChrome() {
		try{
			if(property.getProperty("executeWebtest").equalsIgnoreCase("chrome")){
				Runtime.getRuntime().exec("taskkill /F /IM chrome* /T");
				Thread.sleep(2000);
			}
			if(property.getProperty("executeWebtest").equalsIgnoreCase("chrome")){
				Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe /T");
				Thread.sleep(1000);
			}
			
		}catch(Exception e){
			log.info(e.getMessage());
		}
		
	}
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() throws FileNotFoundException, IOException{
		
		initializationScript();
		secureKillDriverChrome();
		//Extend Report
		initialeCreateReport("serial");
		log.info("Starter");
		
	}
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"testname","testdesc","testCategory","Author","suiteType"})
	public void beforeTest(String testname,String testdesc,String testCategory,String Author,String Suitetype){
		
		if(Suitetype.equalsIgnoreCase(sysproperty.getProperty("serialexecution"))){
		secureKillDriverChrome();
		}
		String browser = property.getProperty("startbrowser");
		ExtentTestManager.startTestcaseReport(testname,"<b>"+testdesc+"<b>",browser.toUpperCase()+","+testCategory,Author);

	}

	
	public void initialeCreateReport(String string) throws FileNotFoundException, IOException {
		
		filecount++;
		String datatime =new SimpleDateFormat("_dd_MM_yyyy_HHmmss").format(new Date());
		reportfilename = sysproperty.getProperty("reportname").toUpperCase()+"_"+filecount+"_"+datatime+".html"; 
		ExtentTestManager.ReportStartWithfilename(filecount,reportfilename);
		
		ExtentTestManager.addreportversioninfo("selenium version","new 1.2.4");
		
	}
	
	@AfterTest
	@Parameters({"suiteType"})
	public void AfterTest(String suiteType) throws FileNotFoundException, IOException{
		try{
		ExtentTestManager.endTestCase();
		
		File file = new File("./"+sysproperty.getProperty("reportpath")+"/"+reportfilename);
		
		double reportFileSizeInBytes = file.length();
		double reportFileSizeInKB = (reportFileSizeInBytes / 1024);
		int fileSize=(int) reportFileSizeInKB;
		
		
		if(fileSize > Integer.parseInt(sysproperty.getProperty("reportmaxSize"))){
			
			int ID=(int) Thread.currentThread().getId();
			log.info("Thread -> '"+ID+"' reached New report condition");
			
			
			createreportsync(suiteType);
			
			calledMyMethod = false;
			
		}else{
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				ExtentTestManager.endTestCase();
			}catch(Exception e){
				
			}
		}
		
	}

	public synchronized void createreportsync(String suiteType) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		if(calledMyMethod) {
			return;
		} else {
			calledMyMethod = true;
			//log.info("Synchronized Report Create Method. Thread Creating by Thread -> "+(int)Thread.currentThread().getId()+"");
			reduceHTMLReportSize(reportfilename);
			initialeCreateReport(suiteType);
			secureKillDriverChrome();
			//log.info("*** Report Created Successfully! ***");
		} 
		
	}

	public void reduceHTMLReportSize(String reportfilename) {
		try {
			//log.info("Reducing the HTML Report File Size...!");
			String contents = FileUtils.readFileToString(new File("./"+sysproperty.getProperty("reportpath")+"/"+reportfilename), "UTF-8");
			contents = contents.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\t", "");
			FileWriter output = new FileWriter("./"+sysproperty.getProperty("reportpath")+"/"+reportfilename);
			BufferedWriter bw = new BufferedWriter(output);
			bw.write(contents);
			bw.close();
			//log.info("HTML Report File Compressed...!");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occured while reducing the file Size. Exception "+e);
		}
	}

	@AfterSuite(alwaysRun=true)
		public void AfterSuite(){
			reduceHTMLReportSize(reportfilename);
			secureKillDriverChrome();
			log.info("***** EXECUTION COMPLETED. *****");
			
			try{
				Thread.sleep(2000);
				//ScriptDetails.generateExcelReports();
				////log.info("***** Overview Html Report Generated & Script Details Tracker Updated *****");
			}catch(Exception e){
				//log.info("Exception in afterSuite generateExcelReports. "+e);
				e.printStackTrace();
			}finally{
				try {
					ExtentTestManager.endTestCase();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	

}
