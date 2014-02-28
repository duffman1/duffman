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
		
		//get the results of each test suite that was executed
	    for (ISuite suite : suites) {
	    
	    Map<String, ISuiteResult> suiteResults = suite.getResults();
	       
	    IResultMap passedTests = null;
	    IResultMap failedTests = null;
	    for (ISuiteResult sr : suiteResults.values()) {
	          
	    	ITestContext suiteTestContext = sr.getTestContext();
	        passedTests = suiteTestContext.getPassedTests();
	        failedTests = suiteTestContext.getFailedTests();
	        
	    }
	        
		//remove any failed results that pass on an automatic retry
        List<ITestNGMethod> methodsToRemove = new ArrayList<ITestNGMethod>();

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

                        methodsToRemove.add(failed_result.getMethod());
                        break;
                    }

                }
            }
        }

        for(ITestNGMethod failedMethodToRemove : methodsToRemove)
        {
            failedTests.removeResult(failedMethodToRemove);
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
		
		//send auto email with report
		SendEmailReport sendEmailReport = new SendEmailReport();
		try {
			sendEmailReport.SendEmail(filePath, fileExtension, passedTests.size(), failedTests.size());
		} catch (Exception e) {
			System.out.println("Failed to send report email.");
		}
		
    }
}
}