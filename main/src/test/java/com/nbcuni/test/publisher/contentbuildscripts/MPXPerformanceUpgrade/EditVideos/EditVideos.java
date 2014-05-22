package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.EditVideos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;

import org.testng.annotations.Test;

public class EditVideos extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class)
    public void Test() throws Exception {
    	
    	//login to mpx
    	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
    	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
        mpxSelectAccount.SelectAccount("DB TV");
        MPXAssets mpxAssets = new MPXAssets(applib);
	    mpxAssets.WaitForAllAssetsToLoad();
		
	    
    	String mediaTitle = null;
    	Boolean needToScrollUp = false;
    	for(int CCount=0;CCount<3;CCount++) {
    		
    		String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/EditVideos/AssetsEdited.txt";
    	    filePath = filePath.replace("/", File.separator);
    	    File file = new File(filePath); 
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	
    		try {
    			//create asset
    			MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
            	mpxAddMedia.UploadDefaultVideo();
                mediaTitle = "Automation" + random.GetCharacterString(10);
                if (needToScrollUp == false) {
                	mpxAddMedia.GiveFocusToMediaItem();
                }
                else {
                	mpxAddMedia.EnterDescription("blah");
                	mpxAssets.Scroll("Up", 75);
                }
                
                mpxAddMedia.EnterTitle(mediaTitle);
                mpxAddMedia.ClickSaveBtn(true);
                MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
                mpxPublishMedia.PublishDefaultVideo();
        		
        	    //record the creation time
                long creationTime = System.nanoTime();
            	
            	webDriver.getCurrentUrl();
            	
            	String mediaDescription = "null";
            	for(int ECount=0;ECount<50;ECount++) {
            		
            		webDriver.getCurrentUrl();
            		if (ECount == 0) {
            			mpxAddMedia.GiveFocusToMediaItem();
            		}
            		mediaDescription = random.GetCharacterString(20);
            	    mpxAddMedia.EnterDescription(mediaDescription);
            	    mpxAddMedia.ClickSaveBtn(false);
            	    needToScrollUp = false;
            	}
            	needToScrollUp = true;
            	
            	//log the data
        	    long finalEditTime = System.nanoTime();
        	    FileWriter fileWritter2 = new FileWriter(file.getAbsolutePath(),true);
            	BufferedWriter bufferWritter2 = new BufferedWriter(fileWritter2);
            	bufferWritter2.write("edited," + mediaTitle + "," + creationTime + "," + finalEditTime + "," + mediaDescription + System.lineSeparator());
            	bufferWritter2.close();
            	
    		}
    		catch (Exception | AssertionError e) {
    			
    			//log the failure
        	    long failureTime = System.nanoTime();
        	    FileWriter fileWritter3 = new FileWriter(file.getAbsolutePath(),true);
            	BufferedWriter bufferWritter3 = new BufferedWriter(fileWritter3);
        	    bufferWritter3.write("mpxfailure," + mediaTitle + "," + failureTime + System.lineSeparator());
        	    bufferWritter3.close();
        	    
            	//refresh mpx
    			mpxLogin.OpenMPXThePlatform();
    			mpxAssets.WaitForAllAssetsToLoad();
    			
    			needToScrollUp = false;
    			
    		}
    		
    	}
    	
    	
    }
}