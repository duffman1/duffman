package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade.PublishUpdate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
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
    	MPXLogin mpxLogin = new MPXLogin(webDriver);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login(config.getConfigValueString("MPXUsername"), config.getConfigValueString("MPXPassword"));
    	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver);
        mpxSelectAccount.SelectAccount("DB TV");
        MPXAssets mpxAssets = new MPXAssets();
	    mpxAssets.WaitForAllAssetsToLoad();
		
	    
    	String mediaTitle = null;
    	for(int CCount=0;CCount<5;CCount++) {
    		
    		String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/PublishUpdate/AssetsEdited.txt";
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
                mpxAddMedia.GiveFocusToMediaItem();
                mpxAddMedia.EnterTitle(mediaTitle);
                mpxAddMedia.ClickSaveBtn(true);
                MPXPublishMedia mpxPublishMedia = new MPXPublishMedia();
                mpxPublishMedia.PublishDefaultVideo();
        		
        	    //record the creation time
                long creationTime = System.nanoTime();
            	
            	webDriver.getCurrentUrl();
            	
            	//add a thumbnail and publish update
            	mpxAddMedia.ClickFilesLnk();
                mpxAddMedia.ClickUploadBtn();
            	mpxAddMedia.ClickChooseFilesBtn();
            	AddFile addFile = new AddFile(webDriver);
            	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
            		addFile.ClickPicturesUploadBtn();
                	addFile.ClickNBCLogoLnk();
            	}
            	else {
            		addFile.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
                	addFile.ClickGoBtn_Win();
                	addFile.EnterFileName_Win("nbclogosmall.jpg");
            	}
            	addFile.ClickOpenBtn();
            	mpxAddMedia.ClickUploadFromDialogBtn();
                mpxAddMedia.ClickAllMediaLnk();
                //mpxAddMedia.ClickSaveBtn(false);
                mpxPublishMedia.ClickAdditionalOptionsArrow();
        	    mpxPublishMedia.ClickPublishUpdateLnk();
        	    mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
        	    mpxPublishMedia.ClickUpdateBtn();
                
            	//log the data
        	    long finalEditTime = System.nanoTime();
        	    FileWriter fileWritter2 = new FileWriter(file.getAbsolutePath(),true);
            	BufferedWriter bufferWritter2 = new BufferedWriter(fileWritter2);
            	bufferWritter2.write("edited," + mediaTitle + "," + creationTime + "," + finalEditTime + "," + System.lineSeparator());
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
    			
    		}
    		
    	}
    	
    	
    }
}