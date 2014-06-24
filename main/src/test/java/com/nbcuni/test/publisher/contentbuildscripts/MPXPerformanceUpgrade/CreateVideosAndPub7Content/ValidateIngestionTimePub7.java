package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.CreateVideosAndPub7Content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateIngestionTimePub7 extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class)
    public void Test() throws Exception {
    	
    	//login to pub7
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
    	
    	//open the search page
 	    webDriver.navigate().to(applib.getApplicationURL() + "/admin/content/file/mpxmedia");
 	   
    	//wait for the asset creation file list to be available
	    String assetCreationFilePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/CreateVideosAndPub7Content/AssetsCreated.txt";
	    assetCreationFilePath = assetCreationFilePath.replace("/", File.separator);
	    for (int second = 0; ; second++){
            if (second >= 120) {
                Assert.fail("Asset Creation File Not Available after 60 seconds");
            }
            
            if (new File(assetCreationFilePath).exists()) {
            	break;
            }
            else {
            	Thread.sleep(1000);
            }
        }
    	
    	List<String> entriesProcessed = new ArrayList<String>();
    	int I = 1;
    	while (I == 1) {
    		
    		try {
    			
    			//read all the entries in the created asset file
        		String type = null;
        		String mediaTitle = null;
        		String publishTimeStamp = null;
        		long publishTime = 0;
        		File assetCreationFile = new File(assetCreationFilePath);
            	BufferedReader bufferedReader = new BufferedReader(new FileReader(assetCreationFile));
            	List<String> allAssets = new ArrayList<String>();
            	String line;
            	while ((line = bufferedReader.readLine()) != null) {
            		
            	   allAssets.add(line);
            	}
            	bufferedReader.close();
            	
            	//if asset hasn't already been processed, search for and process it
                for (String asset : allAssets) {
            	   
            	   if (!entriesProcessed.contains(asset)) {
                		   
            		   String[] data = asset.split(",");
            		   type = data[0];
            		   mediaTitle = data[1];
            		   publishTimeStamp = data[2];
                	   publishTime = Long.valueOf(data[3]).longValue();
                	   
                	   entriesProcessed.add(asset);
                		      
                	   //search for the asset
                   	   SearchFor searchFor = new SearchFor(webDriver, applib);
                   	   searchFor.EnterTitle(mediaTitle);
                   	   searchFor.ClickApplyBtn();
                   	   overlay.switchToDefaultContent();
                   	   int refreshCount = 0;
                   	   Boolean ingestionTimeout = false;
                   	   while (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
                   	
                   		   webDriver.navigate().refresh();
                   		   refreshCount++;
                   		   if (refreshCount == 90) {
                   		   ingestionTimeout = true;
                   		   		break;
                   		   }
                   	   }
                   	   
                       //log the ingestion time (or failed ingestion)
                   	   String ingestionMessage;
                   	   long searchTime = 0;
                   	   String searchTimeStamp = null;
                   	   long ingestionTime = 0;
                   	   String failureTimeStamp = null;
                   	   if (ingestionTimeout == true) {
                   		   failureTimeStamp = new SimpleDateFormat("MMddyy-hh:mm:ss a").format(Calendar.getInstance().getTime());
                   		   ingestionMessage = "searchfailure," + mediaTitle + "," + "Timed Out Searching for Asset at " + failureTimeStamp;
                   	   }
                   	   else {
                   		   searchTime = System.nanoTime();
                   		   searchTimeStamp = new SimpleDateFormat("MMddyy-hh:mm:ss a").format(Calendar.getInstance().getTime());
                   		   ingestionTime = searchTime - publishTime;
                   		   ingestionMessage = type + "," + mediaTitle + "," + "Asset Published at " + publishTimeStamp + "," + "Asset Ingested at " + searchTimeStamp + "," + "ELAPSED TIME = " + TimeUnit.SECONDS.convert(ingestionTime, TimeUnit.NANOSECONDS) + " seconds";
                   		   
                   	   }
                       
                       String assetIngestionFilePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/CreateVideosAndPub7Content/AssetsIngested.txt";
                       assetIngestionFilePath = assetIngestionFilePath.replace("/", File.separator);	    	
                       File assetIngestionFile = new File(assetIngestionFilePath); 
                       if(!assetIngestionFile.exists()){
                    	   assetIngestionFile.createNewFile();
                       }
                           
                       FileWriter fileWritter = new FileWriter(assetIngestionFile.getAbsolutePath(),true);
                       BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                       bufferWritter.write(ingestionMessage + System.lineSeparator());
                       bufferWritter.close();
                	   
            	   }
            	}
    		}
    		catch (Exception e) {
    			
    		}
    		
    	}
    	 
    }
}