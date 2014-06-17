package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.CreateVideos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;

import org.testng.annotations.Test;

public class CreateVideos extends ParentTest{
	
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
    	for(int CCount=0;CCount<5;CCount++) {
    		
    		String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/CreateVideos/AssetsCreated.txt";
    	    filePath = filePath.replace("/", File.separator);
    	    File file = new File(filePath); 
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
        	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        	
    		try {
    			//create asset
        		MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
            	mpxAddMedia.UploadDefaultVideo();
                mediaTitle = "Automation" + random.GetCharacterString(10);
                mpxAddMedia.GiveFocusToMediaItem();
                mpxAddMedia.EnterTitle(mediaTitle);
                mpxAddMedia.ClickSaveBtn(true);
                MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
                mpxPublishMedia.PublishDefaultVideo();
        		
        	    //log the creation time
                long publishTime = System.nanoTime();
                String publishTimeStamp = new SimpleDateFormat("MMddyy-hh:mm:ss a").format(Calendar.getInstance().getTime());
            	bufferWritter.write("created," + mediaTitle + "," + publishTimeStamp + "," + publishTime + System.lineSeparator());
            	bufferWritter.close();
            	
            	webDriver.getCurrentUrl();
    		}
    		catch (Exception | AssertionError e) {
    			
    			//log the failure
        	    long failureTime = System.nanoTime();
        	    bufferWritter.write("mpxfailure," + mediaTitle + "," + failureTime + System.lineSeparator());
            	
            	//refresh mpx
    			mpxLogin.OpenMPXThePlatform();
    			mpxAssets.WaitForAllAssetsToLoad();
    			bufferWritter.close();
    		}
    		
    	}
    	
    	
    }
}