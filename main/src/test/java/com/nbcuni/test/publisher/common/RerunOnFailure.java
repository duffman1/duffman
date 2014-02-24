package com.nbcuni.test.publisher.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RerunOnFailure implements IRetryAnalyzer {
private int retryCount = 0;
private int maxRetryCount = 1;

public boolean retry(ITestResult result) {

	if(retryCount < maxRetryCount) { 
		
		retryCount++; 
		
		return true;
		
		} 
	
		return false; 
	
	}  
	
} 
