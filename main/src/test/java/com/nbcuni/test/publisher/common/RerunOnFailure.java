package com.nbcuni.test.publisher.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RerunOnFailure implements IRetryAnalyzer {
Config config = new Config();
private int retryCount = 0;
private int maxRetryCount = config.getReRunOnFailureCount();

@Override
public boolean retry(ITestResult result) {

	if(retryCount < maxRetryCount && config.getConfigValue("ReRunOnFailure").equals("true")) { 
		
		retryCount++; 
		
		return true;
		
		} 
	
		return false; 
	
	}  
	
} 
