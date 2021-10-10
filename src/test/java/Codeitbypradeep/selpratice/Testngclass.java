package Codeitbypradeep.selpratice;

import org.testng.annotations.*;

public class Testngclass {
	
	
	@BeforeSuite
	public void beforethesuite() {
		System.out.println("SuiteBefore");
	}
	@BeforeTest
	public void beforethetest() {
		System.out.println("BeforeTest");
	}
	
	
	@Test(groups="TestTwo")
	public void testthesuite() {
		System.out.println("Test");
		//System.out.println("Test"+5/0);
	}
	@Test(invocationCount=5)
	public void testthesuiteinvocation() {
		System.out.println("invocationCount ---- 9");
		//System.out.println("Test"+5/0);
	}
	//java.lang.ArithmeticException: / by zero

	@Test(expectedExceptions = ArithmeticException.class)
	public void testthesuiteException() {
		System.out.println("invocationCount ---- Exp"+9/0);
		//System.out.println("Test"+5/0);
	}
	@Test(priority = 2)
	public void testthesuite3() {
		System.out.println("Test p-2");
	}
	@Parameters( {"suite_pradeep"} )
	@Test(priority = 1)
	public void testthesuite2(String s) {
		System.out.println("Test p-1 "+s);
		//System.out.println("Test p-1 "+4/0);
	}
	@Test(priority = 3,dependsOnMethods = {"testthesuite2"})
	public void testthesuite4() {
		System.out.println("Test dependsOnMethods--------------- 4");
	}
	
	@Test(dependsOnGroups = {"TestTwo"})
	public void testthemethod5() {
		System.out.println("Test dependsOnGroups--------------- 5");
	}
	
	
	@DataProvider(name = "dataproviderindata")
	public Object[][] getdatapro() {
				
		return new Object[][] {{"Pradeep"},{"Vinoth"}};
	}
	@Test(dataProvider = "dataproviderindata")
	public void Testgetfromdataprovider(String name){
		System.out.println("Data from TestNg --  "+name);
	}
	
	
	@AfterTest
	public void afterthetest() {
		System.out.println("AfterTest");
	}
	@AfterSuite
	public void afterthesuite() {
		System.out.println("SuiteAfter");
	}

}
