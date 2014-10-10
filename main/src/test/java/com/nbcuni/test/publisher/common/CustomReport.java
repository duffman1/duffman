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
		
		//get the results of the suite run
		ISuite consecutiveSuite = suites.get(0);
		Map<String, ISuiteResult> suiteResults = consecutiveSuite.getResults();
	       
	    IResultMap passedTests = null;
	    IResultMap failedTests = null;
	    for (ISuiteResult sr : suiteResults.values()) {
	          
	    	ITestContext suiteTestContext = sr.getTestContext();
	        passedTests = suiteTestContext.getPassedTests();
	        failedTests = suiteTestContext.getFailedTests();
	        
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
  	  	String zipFileName = environmentTitle + "-" + status + "-" + attachmentDateTimeFormat.format(date) + ".zip";
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
  	  	String rallyAttachmentContentId = null;
  	  	String rallyReportAttachmentURL = null;
  	  	if (config.getConfigValueString("UploadReportToRally").equals("true")) {
  	  		try {
  	  			rallyAttachmentContentId = uploadReport.uploadFileAttachment(zipFilePath, zipFileName);
  	  			rallyAttachmentContentId = rallyAttachmentContentId.replace("https://rally1.rallydev.com/slm/webservice/1.40/attachment/", "").replace(".js", "");
  	  			rallyReportAttachmentURL = "https://rally1.rallydev.com/slm/attachment/" + rallyAttachmentContentId + "/" + zipFileName;
  	  		} catch (Exception e) {
  	  			System.out.println("Failed to upload zip report to Rally.");
  	  		}
  	  	}
  	  	else {
  	  		System.out.println("Report was not uploaded to Rally per configuration setting.");
  	  	}
  	  	
  	  	if (config.getConfigValueString("UpdateIndividualRallyTCs").equals("true")) {
  	  	
  	  		//update individual passed test cases in Rally
  	  	  	for (ITestResult passed_result : passedTests.getAllResults()) {
  	  	  	
  	  	  		String methodName = passed_result.getMethod().getMethodName();
  	  	  		if (methodName.contains("_TC")) {
  	  	  			String[] tcID = methodName.split("_");
  	  	  		
  	  	  			try {
  	  	  				updateTestCase.updateTestResult(tcID[1], true, "");
  	  	  			} catch (Exception e) {
  	  	  				System.out.println("Failed to update individual passed test cases in Rally.");
  	  	  			}
  	  	  		}
  	  	  		
  	  	  	}
  	  	  	
  	  	  	//update individual failed test cases in Rally
  	  	    List<String> tcUpdatedConsec = new ArrayList<String>();
  	  	  	for (ITestResult failed_result : failedTests.getAllResults()) {
  	  	  	
  	  	  		String methodName = failed_result.getMethod().getMethodName();
  	  	  		if (methodName.contains("_TC")) {
  	  	  		String screenshotPath = config.getConfigValueFilePath("PathToScreenshots") + methodName + "_" + attachmentDateTimeFormat.format(new Date(failed_result.getEndMillis())) + ".png";
  		    	
  	  	  			String[] tcID = methodName.split("_");
  	  	  		
  	  	  			try {
  	  	  				if (!tcUpdatedConsec.contains(methodName)) {
  	  	  					updateTestCase.updateTestResult(tcID[1], false, screenshotPath);
  	  	  					tcUpdatedConsec.add(methodName);
  	  	  				}
  	  	  			} catch (Exception e) {
  	  	  				System.out.println("Failed to update individual failed test cases in Rally.");
  	  	  			}
  	  	  		}
  	  	  		
  	  	  	}
  	  	}
  	  	else {
  	  		System.out.println("Individual test cases were not updated in Rally per configuration setting.");
  	  	}
  	  	
  	  	//send auto email with zip report
  	    SendEmailReport sendEmailReport = new SendEmailReport();
	  	
  	  	if (config.getConfigValueString("SendReportAutoEmails").equals("true")) {
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
  	  	UploadReportsGitHub uploadReportsGitHub = new UploadReportsGitHub();
  	  	if (config.getConfigValueString("AppURL").contains(".pr.")) {
  	  		if (config.getConfigValueString("GithubUpdatePRResults").equals("true")) {
  	  			uploadReportsGitHub.UploadReport(passedTestCount, failedTestCount, rallyReportAttachmentURL);
  	  		}
  	  		else {
  	  			System.out.println("Github Pull Request results not updated per configuration setting.");
  	  		}
  	  	}
	}
}