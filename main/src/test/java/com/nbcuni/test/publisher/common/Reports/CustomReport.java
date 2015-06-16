package com.nbcuni.test.publisher.common.Reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.nbcuni.test.publisher.common.Config;

public class CustomReport extends EmailableReporter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		Config config = new Config();
		UploadReportRally uploadReport = new UploadReportRally();
		
		//get the results of the suite run, and the included/excluded groups
		ISuite testSuite = suites.get(0);
		Map<String, ISuiteResult> suiteResults = testSuite.getResults();
	       
	    IResultMap passedTests = null;
	    IResultMap failedTests = null;
	    List<String> includedGroups = new ArrayList<String>();
	    List<String> excludedGroups = new ArrayList<String>();
	    
	    for (ISuiteResult sr : suiteResults.values()) {
	          
	    	ITestContext suiteTestContext = sr.getTestContext();
	        passedTests = suiteTestContext.getPassedTests();
	        failedTests = suiteTestContext.getFailedTests();
	        
	        for (String includedGroup : sr.getTestContext().getIncludedGroups()) {
	        	includedGroups.add(includedGroup);
	        }
	        
	        for (String excludedGroup: sr.getTestContext().getExcludedGroups()) {
	        	excludedGroups.add(excludedGroup);
	        }
	    }
	    
	    //remove any failed results that pass on an automatic retry
	    List<ITestNGMethod> consecutiveMethodsToRemove = new ArrayList<ITestNGMethod>();

	    for(ITestResult failed_result : failedTests.getAllResults())
	    {
	    	String failed_testName = failed_result.getMethod().getMethodName();
	    	String failingTest_className = failed_result.getClass().getName();
	    	for(ITestResult passed_result : passedTests.getAllResults())
	    	{
	    		String passing_testName = passed_result.getMethod().getMethodName();
	    		String passingTest_className = failed_result.getClass().getName();
	    		if(failed_testName.equals(passing_testName) &&  
	    				passingTest_className.equals(failingTest_className))
	    		{
	    			if(passed_result.getEndMillis() > failed_result.getEndMillis())
	    			{

                      consecutiveMethodsToRemove.add(failed_result.getMethod());
                      break;
	    			}

	    		}
	    	}
	    }

	    for(ITestNGMethod failedMethodToRemove : consecutiveMethodsToRemove)
	    {
	    	failedTests.removeResult(failedMethodToRemove);
	    }
	  
	    //get the count of passed/failed tests
  	  	Integer passedTestCount = passedTests.size();
	  	Integer failedTestCount = failedTests.size();
	  
	    //get the screenshot path of each failed method
    	SimpleDateFormat attachmentDateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
	    List<String> failedScreenshots = new ArrayList<String>();
	    for(ITestResult failed_result : failedTests.getAllResults())
	    {
	    	Date date = new Date(failed_result.getEndMillis());
	    	String screenshotPath = config.getConfigValueFilePath("PathToScreenshots") + failed_result.getMethod().getMethodName() + "_" + attachmentDateTimeFormat.format(date) + ".png";
	    	failedScreenshots.add(screenshotPath);
	    }
	    
	    //generate the report
  	  	super.generateReport(xmlSuites, suites, outputDirectory);

  	  	//copy the emailable report to the reports directory and rename
  	  	String status = "PASSED";
  	  	if (failedTestCount != 0) {
  	  		status = "FAILED";
  	  	}
  	  	File resultsFile = new File(outputDirectory + File.separator + "emailable-report.html");
  	  	String storeReportsTo = config.getConfigValueFilePath("PathToReports");
  	  	Date date = new Date();
  	  	String environmentTitle = config.getConfigValueString("AppURL").replace("http://", "").toUpperCase();
  	  	String filePath = storeReportsTo + environmentTitle + "-" + status + "-" + attachmentDateTimeFormat.format(date) + ".html";
  	  	try {
  	  		FileUtils.copyFile(resultsFile, new File(filePath));
  	  		System.out.println("Report saved to: " + filePath);
		
  	  	} catch (IOException e) { System.out.println("Failed to copy emailable-report.html to reports directory."); }

  	  	//create a new zip report file and attach html report and failed screenshots
  	  	String zipFilePath = filePath.replace(".html", ".zip");
  	  	String zipFileName = environmentTitle + "-" + status + "-" + attachmentDateTimeFormat.format(date) + ".zip";
  	  	try {
  	  		FileOutputStream fileOut = null;
  	  		fileOut = new FileOutputStream(zipFilePath);
  	  		ZipOutputStream zipOut = new ZipOutputStream(fileOut);
  	  		zipOut.setLevel(5);
      
  	  		AddFilesToZip addFilesToZip = new AddFilesToZip();
  	  		List<File> allFilesToZip = new ArrayList<File>();
  	  		allFilesToZip.add(new File(filePath));
  	  		
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
  	  	if (config.getConfigValueString("UploadReportToRally").equals("true")) {
  	  		try {
  	  			uploadReport.uploadFileAttachment(zipFilePath, zipFileName);
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to upload zip report to Rally.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report was not uploaded to Rally per configuration setting.");
  	  	}
  	  	
  	  	//update individual test cases in Rally	
  	  	if (config.getConfigValueString("UpdateIndividualRallyTCs").equals("true")) {
  	  	
  	  		HashMap results = new HashMap();
  	  		HashMap screenshotPaths = new HashMap();
  	  		HashMap logFilePaths = new HashMap();
  	  		
  	  	  	for (ITestResult passed_result : passedTests.getAllResults()) {
  	  	  	
  	  	  		String methodName = passed_result.getMethod().getMethodName();
  	  	  		if (methodName.contains("_TC")) {
  	  	  			String[] tcID = methodName.split("_");
  	  	  			results.put(tcID[1], "passed");
  	  	  			String logFilePath = config.getConfigValueFilePath("PathToScreenshots") + methodName + "_" + attachmentDateTimeFormat.format(new Date(passed_result.getEndMillis())) + ".txt";
  	  	  			logFilePaths.put(tcID[1], logFilePath);
  	  	  		}
  	  	  		
  	  	  	}
  	  	  	
  	  	  	List<String> tcUpdatedFailed = new ArrayList<String>();
  	  	  	for (ITestResult failed_result : failedTests.getAllResults()) {
  	  	  	
	  	  		String methodName = failed_result.getMethod().getMethodName();
	  	  		if (methodName.contains("_TC")) {
	  	  			String screenshotPath = config.getConfigValueFilePath("PathToScreenshots") + methodName + "_" + attachmentDateTimeFormat.format(new Date(failed_result.getEndMillis())) + ".png";
	  	  			String logFilePath = config.getConfigValueFilePath("PathToScreenshots") + methodName + "_" + attachmentDateTimeFormat.format(new Date(failed_result.getEndMillis())) + ".txt";
	  	  			String[] tcID = methodName.split("_");
	  	  		
	  	  			if (!tcUpdatedFailed.contains(methodName)) {
	  	  				results.put(tcID[1], "failed");
	  	  				screenshotPaths.put(tcID[1], screenshotPath);
	  	  				logFilePaths.put(tcID[1], logFilePath);
	  	  				tcUpdatedFailed.add(methodName);
	  	  			}
	  	  		}
	  	  		
	  	  	}
  	  	  	
  	  	  	UpdateTestResultsRally updateTestResultsRally = new UpdateTestResultsRally();
  	  	    try {
				updateTestResultsRally.updateTestResult(results);
			} catch (Exception e) { e.printStackTrace(); }
			
  	  	}
  	  	else {
  	  		System.out.println("Individual test cases were not updated in Rally per configuration setting.");
  	  	}
  	  	
  	  	//send auto email with zip report
  	    SendEmailReport sendEmailReport = new SendEmailReport();
	  	
  	  	if (config.getConfigValueString("SendReportAutoEmails").equals("true")) {
  	  		try {
  	  			sendEmailReport.SendEmail(zipFilePath, zipFileName, passedTestCount, failedTestCount, includedGroups, excludedGroups);
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to send report email.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report result email not sent per configuration setting.");
  	  	}
  	  	
  	  	//send irc chat with results
  	  	if (config.getConfigValueString("SendReportIRCChat").equals("true")) {
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
  	  	
  	  	//update github for pull request environment executions
  	  	/*TODO
  	  	UploadReportsGitHub uploadReportsGitHub = new UploadReportsGitHub();
  	  	if (config.getConfigValueString("AppURL").contains(".pr.")) {
  	  		if (config.getConfigValueString("GithubUpdatePRResults").equals("true")) {
  	  			uploadReportsGitHub.UploadReport(passedTestCount, failedTestCount, rallyReportAttachmentURL);
  	  		}
  	  		else {
  	  			System.out.println("Github Pull Request results not updated per configuration setting.");
  	  		}
  	  	}
  	  	*/
	}
}