package com.qa.projectreports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public synchronized static ExtentReports getReport(String filePath) {
		extent = null;
		extent = new ExtentReports(filePath, true);
		return extent;
	}

}
