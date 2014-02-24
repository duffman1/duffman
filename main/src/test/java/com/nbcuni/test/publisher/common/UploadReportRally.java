package com.nbcuni.test.publisher.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.DeleteRequest;
import com.rallydev.rest.request.GetRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.request.UpdateRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.DeleteResponse;
import com.rallydev.rest.response.GetResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.response.UpdateResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.binary.Base64;

public class UploadReportRally {

    public static void main(String[] args) throws URISyntaxException, IOException {

        // Create and configure a new instance of RallyRestApi
        // Connection parameters
        String rallyURL = "https://rally1.rallydev.com";
        String wsapiVersion = "1.40";
        String applicationName = "RestExample_AddAttachmentToUserStory";

        // Credentials
        String userName = "user@company.com";
        String userPassword = "topsecret";

        RallyRestApi restApi = new RallyRestApi(
                        new URI(rallyURL),
                        userName,
                        userPassword);
        restApi.setWsapiVersion(wsapiVersion);
        restApi.setApplicationName(applicationName);

        // User settings
        String testerUserName = "attachmentuser@company.com";

        // Workspace and Project Settings
        String myWorkspace = "My Workspace";
        String myProject = "My Project";

        // FormattedID of Existing Test Case to Query
        String existStoryFormattedID = "US43";       

        //Read User
        QueryRequest userRequest = new QueryRequest("User");
        userRequest.setFetch(new Fetch("UserName", "Subscription", "DisplayName"));
        userRequest.setQueryFilter(new QueryFilter("UserName", "=", testerUserName));
        QueryResponse userQueryResponse = restApi.query(userRequest);
        JsonArray userQueryResults = userQueryResponse.getResults();
        JsonElement userQueryElement = userQueryResults.get(0);
        JsonObject userQueryObject = userQueryElement.getAsJsonObject();
        String userRef = userQueryObject.get("_ref").getAsString();

        // Get reference to Workspace of interest
        QueryRequest workspaceRequest = new QueryRequest("Workspace");
        workspaceRequest.setFetch(new Fetch("Name", "Owner", "Projects"));
        workspaceRequest.setQueryFilter(new QueryFilter("Name", "=", myWorkspace));
        QueryResponse workspaceQueryResponse = restApi.query(workspaceRequest);
        String workspaceRef = workspaceQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();

        // Get reference to Project of interest
        QueryRequest projectRequest = new QueryRequest("Project");
        projectRequest.setFetch(new Fetch("Name", "Owner", "Projects"));
        projectRequest.setQueryFilter(new QueryFilter("Name", "=", myProject));
        QueryResponse projectQueryResponse = restApi.query(projectRequest);
        String projectRef = projectQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();      

        // Query for existing User Story
        QueryRequest  existUserStoryRequest = new QueryRequest("HierarchicalRequirement");
        existUserStoryRequest.setFetch(new Fetch("FormattedID","Name"));
        existUserStoryRequest.setQueryFilter(new QueryFilter("FormattedID", "=", existStoryFormattedID));
        QueryResponse userStoryQueryResponse = restApi.query(existUserStoryRequest);
        JsonObject existUserStoryJsonObject = userStoryQueryResponse.getResults().get(0).getAsJsonObject();
        String existUserStoryRef = userStoryQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();

        // Read In Image Content
        String imageFilePath = "/Users/username/Documents/";
        String imageFileName = "image1.png";
        String fullImageFile = imageFilePath + imageFileName;
        String imageBase64String;
        long attachmentSize;

        // Open file
        RandomAccessFile myImageFileHandle = new RandomAccessFile(fullImageFile, "r");

        try {
            // Get and check length
            long longlength = myImageFileHandle.length();
            // Max upload size for Rally attachments is 5MB
            long maxAttachmentLength = 5120000;
            if (longlength > maxAttachmentLength) throw new IOException("File size too big for Rally attachment, > 5 MB");

            // Read file and return data
            byte[] fileBytes = new byte[(int) longlength];
            myImageFileHandle.readFully(fileBytes);
            imageBase64String = Base64.encodeBase64String(fileBytes);
            attachmentSize = longlength;

            // First create AttachmentContent from image string
            JsonObject myAttachmentContent = new JsonObject();
            myAttachmentContent.addProperty("Content", imageBase64String);
            CreateRequest attachmentContentCreateRequest = new CreateRequest("AttachmentContent", myAttachmentContent);
            CreateResponse attachmentContentResponse = restApi.create(attachmentContentCreateRequest);
            String myAttachmentContentRef = attachmentContentResponse.getObject().get("_ref").getAsString();
            System.out.println("Attachment Content created: " + myAttachmentContentRef);            

            // Now create the Attachment itself
            JsonObject myAttachment = new JsonObject();
            myAttachment.addProperty("Artifact", existUserStoryRef);
            myAttachment.addProperty("Content", myAttachmentContentRef);
            myAttachment.addProperty("Name", "AttachmentFromREST.png");
            myAttachment.addProperty("Description", "Attachment From REST");
            myAttachment.addProperty("ContentType","image/png");
            myAttachment.addProperty("Size", attachmentSize);
            myAttachment.addProperty("User", userRef);          

            CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", myAttachment);
            CreateResponse attachmentResponse = restApi.create(attachmentCreateRequest);
            String myAttachmentRef = attachmentResponse.getObject().get("_ref").getAsString();
            System.out.println("Attachment  created: " + myAttachmentRef);  

            if (attachmentResponse.wasSuccessful()) {
                System.out.println("Successfully created Attachment");
            } else {
                String[] attachmentContentErrors;
                attachmentContentErrors = attachmentResponse.getErrors();
                        System.out.println("Error occurred creating Attachment: ");
                for (int i=0; i<attachmentContentErrors.length;i++) {
                        System.out.println(attachmentContentErrors[i]);
                }                   
            }
        } catch (Exception e) {
                System.out.println("Exception occurred while attempting to create Content and/or Attachment: ");
                e.printStackTrace();            
        }

        finally {
            //Release all resources
            restApi.close();
        }                
    }
}