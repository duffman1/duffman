package com.nbcuni.test.publisher.common;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.reporters.EmailableReporter;
import org.testng.xml.XmlSuite;

public class CustomReport extends EmailableReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		Config config = new Config();
		UploadReportRally uploadReport = new UploadReportRally();
		UpdateTestResultsRally updateTestCase = new UpdateTestResultsRally();
		
		//get the results of the consecutive suite run
		ISuite consecutiveSuite = suites.get(0);
		Map<String, ISuiteResult> consecutiveSuiteResults = consecutiveSuite.getResults();
	       
	    IResultMap consecutivePassedTests = null;
	    IResultMap consecutiveFailedTests = null;
	    for (ISuiteResult sr : consecutiveSuiteResults.values()) {
	          
	    	ITestContext consecutiveSuiteTestContext = sr.getTestContext();
	        consecutivePassedTests = consecutiveSuiteTestContext.getPassedTests();
	        consecutiveFailedTests = consecutiveSuiteTestContext.getFailedTests();
	        
	    }
		
	    //get the results of the concurrent suite run
	    ISuite concurrentSuite = suites.get(1);
	    Map<String, ISuiteResult> concurrentSuiteResults = concurrentSuite.getResults();
	  	       
	    IResultMap concurrentPassedTests = null;
	    IResultMap concurrentFailedTests = null;
	    for (ISuiteResult sr : concurrentSuiteResults.values()) {
	  	          
	  	    ITestContext concurrentSuiteTestContext = sr.getTestContext();
	  	    concurrentPassedTests = concurrentSuiteTestContext.getPassedTests();
	  	    concurrentFailedTests = concurrentSuiteTestContext.getFailedTests();
	  	        
	    }
		
	    //remove any failed consecutive results that pass on an automatic retry
	    List<ITestNGMethod> consecutiveMethodsToRemove = new ArrayList<ITestNGMethod>();

	    for(ITestResult consecutive_failed_result : consecutiveFailedTests.getAllResults())
	    {
	    	String failed_testName = consecutive_failed_result.getMethod().getMethodName();
	    	String failingTest_className = consecutive_failed_result.getClass().getName();
	    	for(ITestResult passed_result : consecutivePassedTests.getAllResults())
	    	{
	    		String passing_testName = passed_result.getMethod().getMethodName();
	    		String passingTest_className = consecutive_failed_result.getClass().getName();
	    		if(failed_testName.equals(passing_testName) &&  
	    				passingTest_className.equals(failingTest_className))
	    		{
	    			if(passed_result.getEndMillis() > consecutive_failed_result.getEndMillis())
	    			{

                      consecutiveMethodsToRemove.add(consecutive_failed_result.getMethod());
                      break;
	    			}

	    		}
	    	}
	    }

	    for(ITestNGMethod failedMethodToRemove : consecutiveMethodsToRemove)
	    {
	    	consecutiveFailedTests.removeResult(failedMethodToRemove);
	    }
	  
	    //remove any failed concurrent results that pass on an automatic retry
	    List<ITestNGMethod> concurrentMethodsToRemove = new ArrayList<ITestNGMethod>();

	    for(ITestResult concurrent_failed_result : concurrentFailedTests.getAllResults())
	    {
	    	String failed_testName = concurrent_failed_result.getMethod().getMethodName();
	    	String failingTest_className = concurrent_failed_result.getClass().getName();
	    	for(ITestResult passed_result : concurrentPassedTests.getAllResults())
	    	{
	    		String passing_testName = passed_result.getMethod().getMethodName();
	    		String passingTest_className = concurrent_failed_result.getClass().getName();
	    		if(failed_testName.equals(passing_testName) &&  
                      passingTest_className.equals(failingTest_className))
	    		{
	    			if(passed_result.getEndMillis() > concurrent_failed_result.getEndMillis())
	    			{

	    				concurrentMethodsToRemove.add(concurrent_failed_result.getMethod());
	    				break;
	    			}
	    		}
	    	}
	    }

	    for(ITestNGMethod failedMethodToRemove : concurrentMethodsToRemove)
	    {
	    	concurrentFailedTests.removeResult(failedMethodToRemove);
	    }
	  
	    //get the screenshot path of each failed consecutive method
	    List<String> failedScreenshots = new ArrayList<String>();
	    for(ITestResult consecutive_failed_result : consecutiveFailedTests.getAllResults())
	    {
	    	if (!failedScreenshots.contains(config.getPathToScreenshots() + consecutive_failed_result.getMethod().getMethodName() + ".png")) {
	    		failedScreenshots.add(config.getPathToScreenshots() + consecutive_failed_result.getMethod().getMethodName() + ".png");
	    	}
	    }
	    
	    //get the screenshot path of each failed concurrent method
	    for(ITestResult concurrent_failed_result : concurrentFailedTests.getAllResults())
	    {
	    	if (!failedScreenshots.contains(config.getPathToScreenshots() + concurrent_failed_result.getMethod().getMethodName() + ".png")) {	
	    		failedScreenshots.add(config.getPathToScreenshots() + concurrent_failed_result.getMethod().getMethodName() + ".png");
	    	}
	    }
	    
	    //generate the report
  	  	super.generateReport(xmlSuites, suites, outputDirectory);

  	  	//copy the emailable report to the reports directory
  	  	File resultsFile = new File(outputDirectory + File.separator + "emailable-report.html");

  	  	String storeReportsTo = null;
  	  	try {
  	  		storeReportsTo = config.getPathToReports();
  	  	} catch (Exception e) { System.out.println("Failed to get configuration path to reports directory."); }
	
  	  	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  	  	Date date = new Date();
  	  	String fileExtension = dateFormat.format(date).replace("/", "");
  	  	fileExtension = fileExtension.replace(" ", "");
  	  	fileExtension = fileExtension.replace(":", "") + ".html";
  	  	String filePath = storeReportsTo + fileExtension;

  	  	try {
  	  		FileUtils.copyFile(resultsFile, new File(filePath));
  	  		System.out.println("Report saved to: " + filePath);
		
  	  	} catch (IOException e) { System.out.println("Failed to copy emailable-report.html to reports directory."); }

  	  	//upload report to rally
  	  	try {
  	  		uploadReport.uploadFileAttachment(filePath, fileExtension);
		
  	  	} catch (Exception e) {
		
  	  		System.out.println("Failed to upload report attachment to Rally.");
  	  	}
  	  	
  	  	//update individual consecutive passed test cases in Rally
  	  	for (ITestResult consecutive_passed_result : consecutivePassedTests.getAllResults()) {
  	  	
  	  		String methodName = consecutive_passed_result.getMethod().getMethodName();
  	  		if (methodName.contains("_TC")) {
  	  			String[] tcID = methodName.split("_");
  	  		
  	  			try {
  	  				updateTestCase.updateTestResult(tcID[1], true, "");
  	  			} catch (Exception e) {
  	  				System.out.println("Failed to update individual consecutive suite passed test cases in Rally.");
  	  			}
  	  		}
  	  		
  	  	}
  	  	
  	  	//update individual consecutive failed test cases in Rally
  	  	for (ITestResult consecutive_failed_result : consecutiveFailedTests.getAllResults()) {
  	  	
  	  		String methodName = consecutive_failed_result.getMethod().getMethodName();
  	  		if (methodName.contains("_TC")) {
  	  			String screenshotPath = config.getPathToScreenshots() + methodName + ".png";
  	  			String[] tcID = methodName.split("_");
  	  		
  	  			try {
  	  				updateTestCase.updateTestResult(tcID[1], false, screenshotPath);
  	  			} catch (Exception e) {
  	  				System.out.println("Failed to update individual consecutive suite failed test cases in Rally.");
  	  			}
  	  		}
  	  		
  	  	}
  	  	
  	  	//update individual concurrent passed test cases in Rally
  	  	for (ITestResult concurrent_passed_result : concurrentPassedTests.getAllResults()) {
  	  	
  	  		String methodName = concurrent_passed_result.getMethod().getMethodName();
  	  		if (methodName.contains("_TC")) {
  	  			String[] tcID = methodName.split("_");
  	  		
  	  			try {
  	  				updateTestCase.updateTestResult(tcID[1], true, "");
  	  			} catch (Exception e) {
  	  				System.out.println("Failed to update individual concurrent suite passed test cases in Rally.");
  	  			}
  	  		}
  	  		
  	  	}
  	  	
  	  	//update individual concurrent failed test cases in Rally
  	  	for (ITestResult concurrent_failed_result : concurrentFailedTests.getAllResults()) {
  	  	
  	  		String methodName = concurrent_failed_result.getMethod().getMethodName();
  	  		if (methodName.contains("_TC")) {
  	  			String screenshotPath = config.getPathToScreenshots() + methodName + ".png";
  	  			String[] tcID = methodName.split("_");
  	  		
  	  			try {
  	  				updateTestCase.updateTestResult(tcID[1], false, screenshotPath);
  	  			} catch (Exception e) {
  	  				System.out.println("Failed to update individual concurrent suite test cases in Rally.");
  	  			}
  	  		}
  	  		
  	  	}
  	  	
  	  	//send auto email with report
  	  	Integer passedTestCount = consecutivePassedTests.size() + concurrentPassedTests.size();
  	  	Integer failedTestCount = consecutiveFailedTests.size() + concurrentFailedTests.size();
  	  	SendEmailReport sendEmailReport = new SendEmailReport();
  	  	try {
  	  		sendEmailReport.SendEmail(filePath, fileExtension, passedTestCount, failedTestCount, failedScreenshots);
  	  	} catch (Exception e) {
  	  		System.out.println("Failed to send report email.");
  	  	}
  	  	
  	  	//send irc chat with results
  	  	SendIRCReport sendIRCReport = new SendIRCReport();
  	  	try {
			sendIRCReport.SendReport(filePath, fileExtension, passedTestCount, failedTestCount, failedScreenshots);
		} catch (Exception e) {
			System.out.println("Failed to send report IRC chat.");
		}
	  
	}
}