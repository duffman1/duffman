package com.nbcuni.test.publisher.common.Reports;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nbcuni.test.publisher.common.Config;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class UpdateTestResultsRally {

    @SuppressWarnings({"deprecation", "rawtypes" })
	public void updateTestResult(Map allResults) throws Exception{

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
        	
        	//get the tcid and result from the maps.
        	Map.Entry tcResult = (Map.Entry)iterator.next();
        	System.out.println("Attempting to update Rally test case for object: " + tcResult);
        	String tcID = tcResult.getKey().toString();
        	String passFail = tcResult.getValue().toString();
        	
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
