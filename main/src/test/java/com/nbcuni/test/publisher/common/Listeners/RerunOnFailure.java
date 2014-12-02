package com.nbcuni.test.publisher.common.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.nbcuni.test.publisher.common.Config;

public class RerunOnFailure implements IRetryAnalyzer {
Config config = new Config();
private int retryCount = 0;
private int maxRetryCount = config.getConfigValueInt("ReRunOnFailureCount");

@Override
public boolean retry(ITestResult result) {

	if(retryCount < maxRetryCount && config.getConfigValueString("ReRunOnFailure").equals("true")) { 
		
		retryCount++; 
		
		return true;
		
		} 
	
		return false; 
	
	}  
	
} 
