package com.qa.TestcaseExecution;

import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.Utils.AccessExcel;
import com.qa.Utils.ConstantsValue;
import com.qa.pages.calc_Webapp;
import com.qa.projectreports.ExtentTestManager;

public class Component extends DriverTestNG {
	
	
	
	@Test
	@Parameters({"testname","testdatasheet","suiteType"})
	private synchronized void runTestComponents(String testcaseName,String newtestdatasheet,String suiteType) throws Exception{
		
		
		String testdatasheet = newtestdatasheet;
		String TESTCASE_NAME= testcaseName;
		AccessExcel testCasedata = new AccessExcel(testdatasheet);
		
		boolean CheckTestsheet = testCasedata.CheckTestCaseNamedSheetExist(testdatasheet, testcaseName);
		System.out.println("New -"+CheckTestsheet);
		if(CheckTestsheet){
			Component comobject = new Component();
			Method comMethodname = null;
			
			for(int testcompcount = 1; testcompcount <= testCasedata.getRowCount(TESTCASE_NAME) ; testcompcount++ ){
				
				String Componentname = testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.Get_AppComp_colName, testcompcount);
				
				String Componentdesc = testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_Desc_colvalue, testcompcount);
				
				log.info(Componentname +" - "+Componentdesc);
				//System.out.println(Componentname+" - "+Componentdesc);
				
				//App_Component_Execute Check [Yes / No]
				if(testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_YesNo_colvalue, testcompcount).toString().equalsIgnoreCase(ConstantsValue.RunStatusYes)){
					log.info("[Test Execute Status -"+testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_YesNo_colvalue, testcompcount)+"]");
				
					
					//Disable Steps 
					String compDisableSteps = testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.Get_ApppComp_disableSteps_colName, testcompcount);
					ExtentTestManager.startComponent(Componentname, Componentdesc);
					
					if(!compDisableSteps.equals("")){
						log.info("Steps Disable - "+compDisableSteps);
						
						int noofDataSets = testCasedata.getRowCount(Componentname);
						if(noofDataSets >= 1){
							
							ExtentTestManager.reportStepInfo("<b><u>Steps in "+Componentname+" has "+noofDataSets+"<u><b>");
							
							log.info("Steps in "+Componentname+" has "+noofDataSets);
						}
						for(int datastepsetcount = 1;datastepsetcount <= noofDataSets ; datastepsetcount++ ){
						
							if(testCasedata.getCellData(Componentname, ConstantsValue.GetPageComp_YesNo_colvalue, datastepsetcount).equalsIgnoreCase(ConstantsValue.RunStatusYes)){
								
								String testObjective = testCasedata.getCellData(Componentname, ConstantsValue.GetPageComp_Objective_colvalue, testcompcount);
								if(noofDataSets > 1){
									ExtentTestManager.reportStepInfo("Proceeding Records "+datastepsetcount);
									log.info("Proceeding Records "+datastepsetcount);
								}
								ExtentTestManager.reportStepInfo("Objectives "+testObjective);
								log.info("Objectives "+testObjective);
								
								//Invoke Component 
								//Class<?> arg1 = null;
								try{
								@SuppressWarnings("rawtypes")
								Class[] paratype =new Class[4];
								paratype[0] = String.class;
								paratype[1] = String.class;
								paratype[2] = String.class;
								paratype[3] = int.class;
								
								
								comMethodname = comobject.getClass().getMethod(Componentname,paratype);
								comMethodname.invoke(comobject,testdatasheet,Componentname,compDisableSteps,noofDataSets );
								}catch(NoSuchMethodException e){
									ExtentTestManager.reportStepError("No Such Method Component in Our FrameWork"+Componentname);
									log.info("No Such Method Component in Our FrameWork"+Componentname+" "+e);
								}catch(IllegalAccessException e){
									log.info("Illegal Access "+Componentname+" "+e);
								}catch(SkipException e){
									ExtentTestManager.reportStepSkip("<b>Skipping the '"+Componentname+"'</b>");
									log.info("SkipException "+Componentname+" "+e);
								}catch(Exception e){
									log.info("Exception "+Componentname+" "+e);
									e.printStackTrace();
								}finally{
									
									log.info("Component Ends.\n");
								}
								
							}else if(testCasedata.getCellData(Componentname, ConstantsValue.GetPageComp_YesNo_colvalue, datastepsetcount).toString().equalsIgnoreCase(ConstantsValue.RunStatusNo)){
								ExtentTestManager.reportStepInfo("[ "+testCasedata.getCellData(Componentname, ConstantsValue.GetPageComp_YesNo_colvalue, datastepsetcount).toString()+" step "+datastepsetcount+" ] at the Test Set in "+Componentname+" page");
								log.info("[ "+testCasedata.getCellData(Componentname, ConstantsValue.GetPageComp_YesNo_colvalue, datastepsetcount).toString()+" step "+datastepsetcount+" ] at the Test Set in "+Componentname+" page");
							}
							
						}
						ExtentTestManager.appendComponent();
						log.info("Component Ends.\n");
						
						
					}else{
						ExtentTestManager.reportStepFail("Please enter valid Skipping events in "+ConstantsValue.Get_ApppComp_disableSteps_colName, false);
						log.info("Please enter valid Skipping events in "+ConstantsValue.Get_ApppComp_disableSteps_colName);
					}
				
				
				
				}else{
					if(testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_YesNo_colvalue, testcompcount).equalsIgnoreCase(ConstantsValue.RunStatusNo))
					log.info("[Test Execute Status -"+testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_YesNo_colvalue, testcompcount)+"]");
					//ExtentTestManager.startComponent(Componentname, Componentdesc);
					//ExtentTestManager.reportStepSkip("<b>[Test Execute Status -"+testCasedata.getCellData(TESTCASE_NAME, ConstantsValue.GetAppcomp_YesNo_colvalue, testcompcount)+"]<b>");
					//ExtentTestManager.appendComponent();
				}
				
			}
		}
		
	}
	
	
	private String getMethodName() {
		try{
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement ele = stacktrace[2];
		String methodname = ele.getMethodName();
		
		//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+methodname);
		
		return methodname;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	private synchronized boolean[] initializeSteps(int totalSteps) {
		boolean[] steps = new boolean[totalSteps];
		for(int i=0;i<totalSteps;i++){
			steps[i]=false;
		}
		
		return steps;
	}
	private int[] setswitchDisableSteps(int totalSteps, String compDisableSteps) {
		int steps = 0;
		String disablesteps = null;
		disablesteps = compDisableSteps ;
		steps = totalSteps ;
		int val  = 0,newval=0;
		val =0;
		
		String[] initnum = disablesteps.split(",");
		
		int[] getnewval;
		
		if(disablesteps.equalsIgnoreCase("0")||disablesteps.equalsIgnoreCase("0.0")){
			getnewval = new int[totalSteps];
			for(int dis = 0;dis<=steps-1 ; dis++){
				val = val+1;
				getnewval[newval++] = val;
						
			}
		}else{
			int reducelen = totalSteps - initnum.length;
			getnewval = new int[reducelen]; 
		for(int dis = 0;dis<=steps-1 ; dis++){
			val = val+1;
			if(!(disablesteps.contains(Integer.toString(val)))){
				getnewval[newval++] = val;
			}		
		}
		}
		for(int cc=0;cc<=getnewval.length-1;cc++){
			System.out.println("@"+cc+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+getnewval[cc]);
		}
		
		// TODO Auto-generated method stub
		return getnewval;
	}
	
//////////////////////////////-------- Page object declaration part STARTS ----------//////////////////////////////	
	
	calc_Webapp calc_webapp = new calc_Webapp();
	
//////////////////////////////--------- Page object declaration part ENDS----------////////////////////////////////	
	
	private synchronized void checkvalidatestats(int[] reordersteps, boolean[] statussteps, boolean defaultBreak,
			String methodName) {
		
		try{
		boolean testStepsStatus=true;
		for(int ckst = 0 ; ckst < reordersteps.length ; ckst++){
			boolean stepvaluecheck = statussteps[ckst];
			if(stepvaluecheck != true){
				testStepsStatus = false;
			}
		}
		
		if(defaultBreak){
			testStepsStatus = false ;
		}
		
		if(testStepsStatus){
			ExtentTestManager.reportStepInfo("<b>"+methodName+" Passed successfully </b>");
			log.info("--------------------------"+methodName+ " Passed successfully--------------------------");
		}else{
			ExtentTestManager.reportStepInfo("<b>"+methodName+" Failed Execution </b>");
			log.info("--------------------------"+methodName+ " Failed Execution--------------------------");
		}
		}catch(Exception e){
			log.info(e);
			e.printStackTrace();
			throw e;
		}
	}	
	
	
	//(comobject,testdatasheet,Componentname,compDisableSteps,noofDataSets )
	public  synchronized void Lanchpage(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		boolean[] statussteps = null;
		int[] reordersteps = null;
		boolean defaultBreak = false;
		
		testcaseDataFilepath = testdatasheet;
		testcaseComponentName = getMethodName();
		testcasecounter = noofDataSets;
		
		log.info(Componentname);
		
		int totalSteps = 6 ; 
		statussteps = initializeSteps(totalSteps);
		try{
			reordersteps = setswitchDisableSteps(totalSteps,compDisableSteps);
			System.out.println("&&&&&&&&&&&"+reordersteps.length);
			for(int step=0;step<=reordersteps.length-1;step++){
				String stepno = "step"+reordersteps[step];
				
				switch(stepno){
				case "step1" :statussteps[step] =LaunchingWebApplication("URL", 1); 
				break;
				case "step2" :statussteps[step] =pageLoadcheckState() ;
				break;
				case "step3" :statussteps[step] =WaitforElement(calc_webapp.calc_Webapp_page("numone"), "One key", 1);
				break;
				case "step4" :statussteps[step] =WebClickDOMElement(calc_webapp.calc_Webapp_page("numone"), "One key", 1);
				Thread.sleep(1000*10);
				break;
				case "step5" :statussteps[step] =true; 
				break;
				case "step6" :statussteps[step] =CloseWebApplication(); 
				break;
				default:
					
					defaultBreak = true;
					break;
				}
			}
			
			
			checkvalidatestats(reordersteps,statussteps,defaultBreak,Thread.currentThread().getStackTrace()[1].getMethodName()); 
		}catch(Exception e){
			log.info("---"+Thread.currentThread().getStackTrace()[1].getMethodName()+" occured an "+e);
			//e.printStackTrace();
		}
		
	}
	

	



	


	


	


	public  synchronized void SQLDB_Delete(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
		}
	public  synchronized void Preconditions_REG_Alert(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	public  synchronized void Launch_App_CPOS(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	public  synchronized void RM_GER_REG_16328(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	public  synchronized void RM_GER_VAL_16328(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	public  synchronized void RM_GER_FINAL_16328(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	public  synchronized void DB_COMMON_POSTCONDITION(String testdatasheet,String Componentname,String compDisableSteps,int noofDataSets){
		log.info(Componentname);
	}
	//SQLDB_Delete 	Preconditions_REG_Alert Launch_App_CPOS RM_GER_REG_16328 RM_GER_VAL_16328 RM_GER_FINAL_16328 DB_COMMON_POSTCONDITION Logout_App_CPOS SQLDB_Update Close_SQL_Connection
	
	
	
}
