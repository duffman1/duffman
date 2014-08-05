package com.nbcuni.test.publisher.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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
    	SimpleDateFormat attachmentDateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
	    List<String> failedScreenshots = new ArrayList<String>();
	    for(ITestResult consecutive_failed_result : consecutiveFailedTests.getAllResults())
	    {
	    	Date date = new Date(consecutive_failed_result.getEndMillis());
	    	String screenshotPath = config.getPathToScreenshots() + consecutive_failed_result.getMethod().getMethodName() + "_" + attachmentDateTimeFormat.format(date) + ".png";
	    	failedScreenshots.add(screenshotPath);
	    }
	    
	    //get the screenshot path of each failed concurrent method
	    for(ITestResult concurrent_failed_result : concurrentFailedTests.getAllResults())
	    {
	    	Date date = new Date(concurrent_failed_result.getEndMillis());
	    	String screenshotPath = config.getPathToScreenshots() + concurrent_failed_result.getMethod().getMethodName() + "_" + attachmentDateTimeFormat.format(date) + ".png";
	    	failedScreenshots.add(screenshotPath);
	    }
	    
	    //generate the report
  	  	super.generateReport(xmlSuites, suites, outputDirectory);

  	  	//copy the emailable report to the reports directory
  	  	File resultsFile = new File(outputDirectory + File.separator + "emailable-report.html");
  	  	String storeReportsTo = config.getPathToReports();
  	  	Date date = new Date();
  	  	String environmentTitle = config.getConfigValue("AppURL").replace("http://", "").replace(".nbcupublisher7.publisher7.com", "").toUpperCase();
  	  	String filePath = storeReportsTo + environmentTitle + "-" + attachmentDateTimeFormat.format(date) + ".html";
  	  	try {
  	  		FileUtils.copyFile(resultsFile, new File(filePath));
  	  		System.out.println("Report saved to: " + filePath);
		
  	  	} catch (IOException e) { System.out.println("Failed to copy emailable-report.html to reports directory."); }

  	  	/*
  	  	//copy the emailable har file to the reports directory
  	  	File harResultsFile = new File(outputDirectory + File.separator + "DefaultHar.har");
  	  	String harFilePath = filePath.replace(".html", ".har");
  	  	try {
	  		FileUtils.copyFile(harResultsFile, new File(harFilePath));
	  		System.out.println("Har Report saved to: " + harFilePath);
		
	  	} catch (IOException e) { System.out.println("Failed to copy DefaultHar.har to reports directory."); }
		*/
  	  	
  	  	//create a new zip report file and attach html report, har report, and failed screenshots
  	  	String zipFilePath = filePath.replace(".html", ".zip");
  	  	String zipFileName = environmentTitle + "-" + attachmentDateTimeFormat.format(date) + ".zip";
  	  	try {
  	  		FileOutputStream fileOut = null;
  	  		fileOut = new FileOutputStream(zipFilePath);
  	  		ZipOutputStream zipOut = new ZipOutputStream(fileOut);
  	  		zipOut.setLevel(5);
      
  	  		AddFilesToZip addFilesToZip = new AddFilesToZip();
  	  		List<File> allFilesToZip = new ArrayList<File>();
  	  		allFilesToZip.add(new File(filePath));
  	  		//allFilesToZip.add(new File(harFilePath));
  	  		
  	  		for (String failedScreenshot : failedScreenshots) {
	  			allFilesToZip.add(new File(failedScreenshot));
	  		}
  	  		addFilesToZip.addFilesToZip(new File(zipFilePath), allFilesToZip);
  	  		
  	  		zipOut.close();
  	  	}
  	  	catch (Exception e) {
  	  		System.out.println("Failed to create report zip file and attach reports/screenshots.");
  	  	}
  	    
  	  	//upload zip report file to rally
  	  	if (config.getConfigValue("UploadReportToRally").equals("true")) {
  	  		try {
  	  			uploadReport.uploadFileAttachment(zipFilePath, zipFileName);
		
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to upload zip report to Rally.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report was not uploaded to Rally per configuration setting.");
  	  	}
  	  	
  	  	if (config.getConfigValue("UpdateIndividualRallyTCs").equals("true")) {
  	  	
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
  	  	    List<String> tcUpdatedConsec = new ArrayList<String>();
  	  	  	for (ITestResult consecutive_failed_result : consecutiveFailedTests.getAllResults()) {
  	  	  	
  	  	  		String methodName = consecutive_failed_result.getMethod().getMethodName();
  	  	  		if (methodName.contains("_TC")) {
  	  	  		String screenshotPath = config.getPathToScreenshots() + methodName + "_" + attachmentDateTimeFormat.format(new Date(consecutive_failed_result.getEndMillis())) + ".png";
  		    	
  	  	  			String[] tcID = methodName.split("_");
  	  	  		
  	  	  			try {
  	  	  				if (!tcUpdatedConsec.contains(methodName)) {
  	  	  					updateTestCase.updateTestResult(tcID[1], false, screenshotPath);
  	  	  					tcUpdatedConsec.add(methodName);
  	  	  				}
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
  	  	  	List<String> tcUpdatedConcur = new ArrayList<String>();
  	  	  	for (ITestResult concurrent_failed_result : concurrentFailedTests.getAllResults()) {
  	  	  	
  	  	  		String methodName = concurrent_failed_result.getMethod().getMethodName();
  	  	  		if (methodName.contains("_TC")) {
  	  	  		String screenshotPath = config.getPathToScreenshots() + methodName + "_" + attachmentDateTimeFormat.format(new Date(concurrent_failed_result.getEndMillis())) + ".png";
  		    	
  	  	  			String[] tcID = methodName.split("_");
  	  	  		
  	  	  			try {
  	  	  				if (!tcUpdatedConcur.contains(methodName)) {
  	  	  					updateTestCase.updateTestResult(tcID[1], false, screenshotPath);
  	  	  					tcUpdatedConcur.add(methodName);
  	  	  				}
  	  	  				
  	  	  			} catch (Exception e) {
  	  	  				System.out.println("Failed to update individual concurrent suite test cases in Rally.");
  	  	  			}
  	  	  		}
  	  	  		
  	  	  	}
  	  	}
  	  	else {
  	  		System.out.println("Individual test cases were not updated in Rally per configuration setting.");
  	  	}
  	  	
  	  	//send auto email with zip report
  	    Integer passedTestCount = consecutivePassedTests.size() + concurrentPassedTests.size();
	  	Integer failedTestCount = consecutiveFailedTests.size() + concurrentFailedTests.size();
	  	SendEmailReport sendEmailReport = new SendEmailReport();
	  	
  	  	if (config.getConfigValue("SendReportAutoEmails").equals("true")) {
  	  		try {
  	  			sendEmailReport.SendEmail(zipFilePath, zipFileName, passedTestCount, failedTestCount);
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to send report email.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report result email not sent per configuration setting.");
  	  	}
  	  	
  	  	
  	  	//send irc chat with results
  	  	if (config.getConfigValue("SendReportIRCChat").equals("true")) {
  	  		SendIRCReport sendIRCReport = new SendIRCReport();
  	  		try {
  	  			sendIRCReport.SendReport(filePath, zipFileName, passedTestCount, failedTestCount, failedScreenshots);
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to send report IRC chat.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report result IRC chat not sent per configuration setting.");
  	  	}
  	  	
	}
}