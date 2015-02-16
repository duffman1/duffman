package com.nbcuni.test.publisher.common.Reports;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nbcuni.test.publisher.common.Config;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class UpdateTestResultsRally {

    @SuppressWarnings({ "resource", "deprecation", "rawtypes" })
	public void updateTestResult(Map allResults, Map allScreenshotPaths, Map allLogFilePaths) throws Exception{

    	Config config = new Config();
    	
        //Connection parameters
        String rallyURL = config.getConfigValueString("RallyUrl");
        String wsapiVersion = "1.40";
        String applicationName = "Update Individual Rally Test Cases";

        // Credentials
        String userName = config.getConfigValueString("RallyUsername");
        String userPassword = config.getConfigValueString("RallyPassword");
        
        RallyRestApi restApi = new RallyRestApi(
                        new URI(rallyURL),
                        userName,
                        userPassword);
        restApi.setWsapiVersion(wsapiVersion);
        restApi.setApplicationName(applicationName);
        
        //User settings
        String testerUserName = userName;

        //Read User
        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", testerUserName));
        QueryResponse userQueryResponse = restApi.query(userRequest);
        JsonArray userQueryResults = userQueryResponse.getResults();
        JsonElement userQueryElement = userQueryResults.get(0);
        JsonObject userQueryObject = userQueryElement.getAsJsonObject();
        String userRef = userQueryObject.get("_ref").getAsString();

        Iterator iterator = allResults.entrySet().iterator();
        while (iterator.hasNext()) {
        	
        	//get the tcid, result, screenshot, and step log file from the maps.
        	Map.Entry tcResult = (Map.Entry)iterator.next();
        	String tcID = tcResult.getKey().toString();
        	String passFail = tcResult.getValue().toString();
        	String screenshotPath = null;
        	try {
        		screenshotPath = allScreenshotPaths.get(tcID).toString();
        	}
        	catch (NullPointerException e) { }
        	String logFilePath = allLogFilePaths.get(tcID).toString();
        	
        	// Query for Test Case 
            QueryRequest testCaseRequest = new QueryRequest("TestCase");
            testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
            testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", tcID));
            QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
            testCaseQueryResponse.getResults().get(0).getAsJsonObject();
            String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
            
            //update test case, status, and screenshot.
            try {
                
            	String result;
            	if (passFail.equals("passed")) {
            		result = "Pass";
            	}
            	else {
            		result = "Fail";
            	}
            	
            	JsonObject newTestCaseResult = new JsonObject();
                newTestCaseResult.addProperty("Verdict", result);
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                String pub7Date = dateFormat.format(date);
                newTestCaseResult.addProperty("Date", pub7Date);
                newTestCaseResult.addProperty("Build", "latest build");
                newTestCaseResult.addProperty("Notes", "Automated Test Run");
                newTestCaseResult.addProperty("Tester", userRef);
                newTestCaseResult.addProperty("TestCase", testCaseRef);
                CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
                CreateResponse createResponse = restApi.create(createRequest);

                if(createResponse.wasSuccessful()){
                		
                	//Read Test Case
                	String ref = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());
                	GetRequest getRequest = new GetRequest(ref);
                	getRequest.setFetch(new Fetch("Date", "Verdict"));
                	GetResponse getResponse = restApi.get(getRequest);
                	getResponse.getObject();
                		
                	//Read In File Contents
            		String logFile = logFilePath;
            		long logAttachmentSize;
                		
            		//Open files
            		RandomAccessFile logFileHandle = new RandomAccessFile(logFile, "r");
                    
            		long maxLength = 5000000;
            			
            		//check log file length
            		long logLongLength = logFileHandle.length();
            		if (logLongLength >= maxLength) throw new IOException("File size >= 5 MB Upper limit for Rally.");
            		int logFileLength = (int) logLongLength;  

            		//Read file and return data
            		byte[] logFileBytes = new byte[(int) logFileLength];
            		logFileHandle.readFully(logFileBytes);
            		String logImageBase64String = Base64.encodeBase64String(logFileBytes);
            		logAttachmentSize = logFileLength;

            		//create log AttachmentContent from image string
            		JsonObject logAttachmentContent = new JsonObject();
            			
            		logAttachmentContent.addProperty("Content", logImageBase64String);
            		CreateRequest logAttachmentContentCreateRequest = new CreateRequest("AttachmentContent", logAttachmentContent);
            		CreateResponse logAttachmentContentResponse = restApi.create(logAttachmentContentCreateRequest);
            		for (String error : logAttachmentContentResponse.getErrors()) {
            			System.out.println(error);
            		}
            		String logAttachmentContentRef = logAttachmentContentResponse.getObject().get("_ref").getAsString();
            			
            		//create the log Attachment itself
            		JsonObject logAttachment = new JsonObject();
            		logAttachment.addProperty("TestCaseResult", ref);
            		logAttachment.addProperty("Content", logAttachmentContentRef);
            		logAttachment.addProperty("Name", "testStepLog.jpg");
            		logAttachment.addProperty("Description", "Attachment of Logged Test Steps");
            		logAttachment.addProperty("ContentType","text/plain");
            		logAttachment.addProperty("Size", logAttachmentSize);
            		logAttachment.addProperty("User", userRef);          
            		CreateRequest logAttachmentCreateRequest = new CreateRequest("Attachment", logAttachment);
            		CreateResponse logAttachmentResponse = restApi.create(logAttachmentCreateRequest);
            		logAttachmentResponse.getObject().get("_ref").getAsString();
                    
            		//release file resources
            		logFileHandle.close();
            			
            		//attach screenshot if failure
                	if (screenshotPath != null) {
                		
                		//Read In File Contents
                		String screenshotFile = screenshotPath;
                		long screenshotAttachmentSize;
                			
                		//Open files
                		RandomAccessFile screenshotFileHandle = new RandomAccessFile(screenshotFile, "r");
                			
                		//check screenshot length
                		long screenshotLongLength = screenshotFileHandle.length();
                		if (screenshotLongLength >= maxLength) throw new IOException("File size >= 5 MB Upper limit for Rally.");
                		int screenshotFileLength = (int) screenshotLongLength; 
                			
                		//Read file and return data
                		byte[] screenshotFileBytes = new byte[screenshotFileLength];
                		screenshotFileHandle.readFully(screenshotFileBytes);
                		String screenshotImageBase64String = Base64.encodeBase64String(screenshotFileBytes);
                		screenshotAttachmentSize = screenshotFileLength;
                			
                		//create Screenshot AttachmentContent from image string
                		JsonObject screenshotAttachmentContent = new JsonObject();
                		screenshotAttachmentContent.addProperty("Content", screenshotImageBase64String);
                		CreateRequest screenshotAttachmentContentCreateRequest = new CreateRequest("AttachmentContent", screenshotAttachmentContent);
                		CreateResponse screenshotAttachmentContentResponse = restApi.create(screenshotAttachmentContentCreateRequest);
                		String screenshotAttachmentContentRef = screenshotAttachmentContentResponse.getObject().get("_ref").getAsString();
                        
                		//create the Screenshot Attachment itself
                		JsonObject screenshotAttachment = new JsonObject();
                		screenshotAttachment.addProperty("TestCaseResult", ref);
                		screenshotAttachment.addProperty("Content", screenshotAttachmentContentRef);
                		screenshotAttachment.addProperty("Name", "ScreenshotOfFailure.jpg");
                		screenshotAttachment.addProperty("Description", "Attachment of Failed Screenshot");
                		screenshotAttachment.addProperty("ContentType","image/jpg");
                		screenshotAttachment.addProperty("Size", screenshotAttachmentSize);
                		screenshotAttachment.addProperty("User", userRef);          
                		CreateRequest screenshotAttachmentCreateRequest = new CreateRequest("Attachment", screenshotAttachment);
                		CreateResponse screenshotAttachmentResponse = restApi.create(screenshotAttachmentCreateRequest);
                		screenshotAttachmentResponse.getObject().get("_ref").getAsString();
                        
                		//release file resources
                		screenshotFileHandle.close();
                			
                	}
                       
                	System.out.println("Updated Rally test case result for test case '" + tcID + "'.");
                    
                }
            }
            catch (Exception e) {
            	e.printStackTrace();
            	System.out.println("Failed to update test case for " + tcID);
            }
        }
        
        //Release all resources
        restApi.close();
                    
    }
}
