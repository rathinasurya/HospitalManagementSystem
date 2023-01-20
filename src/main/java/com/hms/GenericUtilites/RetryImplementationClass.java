package com.hms.GenericUtilites;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplementationClass implements IRetryAnalyzer{
	int count=0;
	int retryLimit=4;
	public boolean retry(ITestResult result) {
		if(count<retryLimit)
		{
			count++;
			return true;
		}
		return false;
	}
	
	

}
