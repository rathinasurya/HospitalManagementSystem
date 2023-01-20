package com.hms.GenericUtilites;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass extends BaseClass implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String MethodName=result.getMethod().getMethodName();
		test=report.createTest(MethodName);
		Reporter.log(MethodName+"------->TestScript Execution Started");
	}

	public void onTestSuccess(ITestResult result) {
		String MethodName=result.getMethod().getMethodName();
		test.log(Status.PASS, MethodName+"-----> passed");
		Reporter.log(MethodName+"---->TestScript Execution Successfull");
	}

	public void onTestFailure(ITestResult result) {

		String ScreenShotName = result.getName() + " "+new JavaUtility().getSystemDateAndTimeInFormat();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshot/" + ScreenShotName + ".png");
		String path=dst.getAbsolutePath();
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path);
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log("TestScript Execution Failed");

	}

	public void onTestSkipped(ITestResult result) {
		String MethodName=result.getMethod().getMethodName();
		test.log(Status.SKIP, MethodName+"-----> skipped");
		Reporter.log(MethodName+"---->TestScript Execution skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReport=new ExtentSparkReporter("./ExtentReport/report.html");
		htmlReport.config().setDocumentTitle("SDET-45 HealthCare ExtentReport");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setReportName("HMS Report");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Base-Url", "http://rmgtestingserver/domain/Hospital_Management_System/");
		report.setSystemInfo("Reporter Name", "Rathinasurya");
				
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
