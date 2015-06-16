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

import org.apache.commons.codec.binary.Base64;

public class UploadReportRally {

    @SuppressWarnings({ "resource", "deprecation" })
	public void uploadFileAttachment(String pathToReport, String reportName) throws Exception {

    	Config config = new Config();
    	
        //Connection parameters
        String rallyURL = config.getConfigValueString("RallyUrl");
        String wsapiVersion = "1.40";
        String applicationName = "Attach Automated Test Reports";

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

        //Task ID - update in config with each iteration change
        String tcID = config.getConfigValueString("RallyTCID");       

        //Read User
        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", testerUserName));
        QueryResponse userQueryResponse = restApi.query(userRequest);
        JsonArray userQueryResults = userQueryResponse.getResults();
        JsonElement userQueryElement = userQueryResults.get(0);
        JsonObject userQueryObject = userQueryElement.getAsJsonObject();
        String userRef = userQueryObject.get("_ref").getAsString();

        //Query for Test Case 
        QueryRequest testCaseRequest = new QueryRequest("TestCase");
        testCaseRequest.setFetch(new Fetch("FormattedID","Name"));
        testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", tcID));
        QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
        testCaseQueryResponse.getResults().get(0).getAsJsonObject();
        String testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
        
        //update report library with status and report
        try {
            
        	String result;
        	if (reportName.contains("PASSED")) {
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

            //Read Test Case
            String ref = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());
            GetRequest getRequest = new GetRequest(ref);
            getRequest.setFetch(new Fetch("Date", "Verdict"));
            GetResponse getResponse = restApi.get(getRequest);
            getResponse.getObject();
            		
            //Read In File Content
            String reportFile = pathToReport;
            String fileBase64String;
            long attachmentSize;

            //Open file
            RandomAccessFile myFileHandle = new RandomAccessFile(reportFile, "r");
            
            //Get and check length
            long filelength = myFileHandle.length();
            //Max upload size for Rally attachments is 5MB
            long maxAttachmentLength = 5120000;
            if (filelength > maxAttachmentLength) throw new IOException("File size too large for Rally attachment, > 5 MB");
            
            //Read file and return data
            byte[] fileBytes = new byte[(int) filelength];
            myFileHandle.readFully(fileBytes);
            fileBase64String = Base64.encodeBase64String(fileBytes);
            attachmentSize = filelength;
            
            //First create AttachmentContent from string
            JsonObject myAttachmentContent = new JsonObject();
            myAttachmentContent.addProperty("Content", fileBase64String);
            CreateRequest attachmentContentCreateRequest = new CreateRequest("AttachmentContent", myAttachmentContent);
            CreateResponse attachmentContentResponse = restApi.create(attachmentContentCreateRequest);
            String myAttachmentContentRef = attachmentContentResponse.getObject().get("_ref").getAsString();
            System.out.println("Attachment Content created: " + myAttachmentContentRef);
            
            //create the Attachment itself
            JsonObject myAttachment = new JsonObject();
            myAttachment.addProperty("TestCaseResult", ref);
            myAttachment.addProperty("Content", myAttachmentContentRef);
            myAttachment.addProperty("Name", reportName);
            myAttachment.addProperty("Description", "Attachment From Automated Test Suite");
            myAttachment.addProperty("ContentType","application/zip");
            myAttachment.addProperty("Size", attachmentSize);
            myAttachment.addProperty("User", userRef);          

            CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);
            CreateResponse attachmentResponse = restApi.create(attachmentCreateRequest);
            attachmentResponse.getObject().get("_ref").getAsString();
                    
            //release file resources
            myFileHandle.close();
            		
                   
            System.out.println("Successfully created Rally report attachment to TC '" + tcID + "'.");
                
        }
        catch (Exception e) {
        	System.out.println(e);
        	System.out.println("Failed to update test report for TC '" + tcID + "'.");
        }
        
    }
}