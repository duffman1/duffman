package com.nbcuni.test.publisher.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

import org.apache.commons.codec.binary.Base64;

public class UpdateTestResultsRally {

    @SuppressWarnings("resource")
	public void updateTestResult(String TCID, Boolean passed, String screenshotPath) throws Exception {

    	Config config = new Config();
    	
        //Connection parameters
        String rallyURL = config.getConfigValue("RallyUrl");
        String wsapiVersion = "1.40";
        String applicationName = "Update Individual Rally Test Cases";

        // Credentials
        String userName = config.getConfigValue("RallyUsername");
        String userPassword = config.getConfigValue("RallyPassword");

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

        // Query for Test Case 
        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", TCID));
        QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
        testCaseQueryResponse.getResults().get(0).getAsJsonObject();
        String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
        
        //Update the test results with screenshot
        try {
        
        	String result;
        	if (passed.equals(true)) {
        		result = "Pass";
        	}
        	else {
        		result = "Fail";
        	}
        	
        	JsonObject newTestCaseResult = new JsonObject();
            newTestCaseResult.addProperty("Verdict", result);
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String pub7Date = dateFormat.format(date);
            newTestCaseResult.addProperty("Date", pub7Date);
            newTestCaseResult.addProperty("Build", "latest build");
            newTestCaseResult.addProperty("Notes", "Automated Test Run");
            newTestCaseResult.addProperty("Tester", userRef);
            newTestCaseResult.addProperty("TestCase", testCaseRef);

            if (config.getConfigValue("UpdateIndividualRallyTCs").equals("true")) {
            	CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
            	CreateResponse createResponse = restApi.create(createRequest);

            	if(createResponse.wasSuccessful()){
            		
            		//Read Test Case
            		String ref = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());
            		GetRequest getRequest = new GetRequest(ref);
            		getRequest.setFetch(new Fetch("Date", "Verdict"));
            		GetResponse getResponse = restApi.get(getRequest);
            		getResponse.getObject();
            		
            		if (!screenshotPath.equals("")) {
            		
            			//Read In File Content
            			String screenshotFile = screenshotPath;
            			long attachmentSize;

            			//Open file
            			RandomAccessFile myFileHandle = new RandomAccessFile(screenshotFile, "r");
                    
            			//check screenshot length
            			long longLength = myFileHandle.length();
            			long maxLength = 5000000;
            			if (longLength >= maxLength) throw new IOException("File size >= 5 MB Upper limit for Rally.");
            			int fileLength = (int) longLength;            

            			// Read file and return data
            			byte[] fileBytes = new byte[fileLength];
            			myFileHandle.readFully(fileBytes);
            			String imageBase64String = Base64.encodeBase64String(fileBytes);
            			attachmentSize = fileLength;

            			//create AttachmentContent from image string
            			JsonObject myAttachmentContent = new JsonObject();
            			myAttachmentContent.addProperty("Content", imageBase64String);
            			CreateRequest attachmentContentCreateRequest = new CreateRequest("AttachmentContent", myAttachmentContent);
            			CreateResponse attachmentContentResponse = restApi.create(attachmentContentCreateRequest);
            			String myAttachmentContentRef = attachmentContentResponse.getObject().get("_ref").getAsString();
                    
            			//create the Attachment itself
            			JsonObject myAttachment = new JsonObject();
            			myAttachment.addProperty("TestCaseResult", ref);
            			myAttachment.addProperty("Content", myAttachmentContentRef);
            			myAttachment.addProperty("Name", "ScreenshotOfFailure.jpg");
            			myAttachment.addProperty("Description", "Attachment of Failed Screenshot");
            			myAttachment.addProperty("ContentType","image/jpg");
            			myAttachment.addProperty("Size", attachmentSize);
            			myAttachment.addProperty("User", userRef);          

            			CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);
            			CreateResponse attachmentResponse = restApi.create(attachmentCreateRequest);
            			attachmentResponse.getObject().get("_ref").getAsString();
                    
            			//release file resources
            			myFileHandle.close();
            		}
                   
            		System.out.println("Updated Rally test case result for test case '" + TCID + "'.");
                
            	}
            }
            else {
            	System.out.println("Individual test cases were not updated in Rally per configuration setting.");
            }
        }
        catch (Exception e) {
        	System.out.println(e.getStackTrace());
        	System.out.println("Failed to update test case for " + TCID);
        }
        finally {
        	//Release all resources
        	restApi.close();
        }
                     
    }
}