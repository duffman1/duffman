package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.EditVideos;

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

import org.openqa.selenium.By;
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
	    String assetCreationFilePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/EditVideos/AssetsEdited.txt";
	    assetCreationFilePath = assetCreationFilePath.replace("/", File.separator);
	    for (int second = 0; ; second++){
            if (second >= 800) {
                Assert.fail("Asset Edit File Not Available");
            }
            
            if (new File(assetCreationFilePath).exists()) {
            	break;
            }
            else {
            	Thread.sleep(1000);
            	webDriver.getCurrentUrl();
            }
        }
    	
    	List<String> entriesProcessed = new ArrayList<String>();
    	int I = 1;
    	while (I == 1) {
    		webDriver.navigate().refresh();
    		try {
    			
    			//read all the entries in the created asset file
        		String mediaTitle = null;
        		long creationTime = 0;
        		String finalEditTimeStamp = null;
        		long finalEditTime = 0;
        		String dynamicData = null;
        		webDriver.getCurrentUrl();
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
                	webDriver.getCurrentUrl();
            	   if (!entriesProcessed.contains(asset)) {
            		   String[] data = asset.split(",");
            		   mediaTitle = data[1];
                	   creationTime = Long.valueOf(data[2]).longValue();
                	   finalEditTimeStamp = data[3];
                	   finalEditTime = Long.valueOf(data[4]).longValue();
                	   dynamicData = data[5];
                	   
                	   entriesProcessed.add(asset);
                		      
                	   //search for the asset
                   	   SearchFor searchFor = new SearchFor(webDriver, applib);
                   	   searchFor.EnterTitle(mediaTitle);
                   	   searchFor.ClickApplyBtn();
                   	   overlay.switchToDefaultContent();
                   	   int refreshSearchCount = 0;
                   	   Boolean ingestionCreationTimeout = false;
                   	   int refreshMediaPageCount = 0;
                	   Boolean ingestionEditTimeout = false;
                   	   while (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
                   	
                   		   webDriver.navigate().refresh();
                   		   refreshSearchCount++;
                   		   if (refreshSearchCount == 30) {
                   		   ingestionCreationTimeout = true;
                   		   		break;
                   		   }
                   	   }
                   		   
                   	   if (searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)){
                   		   
                   		   searchFor.ClickSearchTitleLnk(mediaTitle);
                   		   while (!webDriver.findElement(By.tagName("body")).getText().contains(dynamicData)){
                   				   webDriver.navigate().refresh();
                   				   refreshMediaPageCount++;
                   				   if (refreshMediaPageCount == 30) {
                            		   ingestionEditTimeout = true;
                            		   		break;
                   				   }
                   			   }
                   		   webDriver.navigate().back();
                   	    }
                   	   
                   	   
                       //log the edit time (or failed ingestion or failed edit)
                   	   String ingestionMessage;
                   	   long ingestionFinalEditTime = 0;
                   	   long ingestionCreationFailureTime = 0;
                   	   long ingestionEditFailureTime = 0;
                   	   String searchTimeStamp = null;
                   	   long searchTime = 0;
                   	   long creationToFinalEditTime = 0;
                   	   if (ingestionCreationTimeout == true) {
                   		   ingestionCreationFailureTime = System.nanoTime();
                   		   ingestionMessage = "searchCreationFailure," + mediaTitle + "," + ingestionCreationFailureTime;
                   	   }
                   	   else if (ingestionEditTimeout == true) {
                   		   ingestionEditFailureTime = System.nanoTime();
                		   ingestionMessage = "searchEditFailure," + mediaTitle + "," + ingestionEditFailureTime;
                   	   }
                   	   else {
                   		   searchTimeStamp = new SimpleDateFormat("MMddyy-hh:mm:ss a").format(Calendar.getInstance().getTime());
                   		   searchTime = System.nanoTime();
                		   ingestionFinalEditTime = searchTime - finalEditTime;
                		   creationToFinalEditTime = searchTime - creationTime;
                		   ingestionMessage = mediaTitle + "Final Edit to Asset made at " + finalEditTimeStamp + "," + searchTime + "," + "Asset Final Edit Present in Pub7 at " + searchTimeStamp + "," + TimeUnit.SECONDS.convert(ingestionFinalEditTime, TimeUnit.NANOSECONDS) + " seconds, " + "ELAPSED TIME FROM FINAL EDIT IN MPX TO FINAL EDIT IN PUB7 = " + TimeUnit.SECONDS.convert(creationToFinalEditTime, TimeUnit.NANOSECONDS) + " seconds";
                		   
                   	   }
                   	   
                       String assetIngestionFilePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/EditVideos/AssetsIngested.txt";
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