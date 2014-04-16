package com.nbcuni.test.publisher.contentbuildscripts.NBCcomIssue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateAndValidateVideo extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void CreateAndValidateVideo_Test() throws Exception {

    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//login to mpx
    	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
    	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(applib);
        mpxSelectAccount.SelectAccount("DB TV");
    	
        //create 100 mpx assets
    	for(int CCount=0;CCount<99;CCount++) {
    		
    		//create asset on mpx
    		MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
        	mpxAddMedia.UploadDefaultVideo();
            String mediaTitle = "Automation" + random.GetCharacterString(10);
            mpxAddMedia.GiveFocusToMediaItem();
            mpxAddMedia.EnterTitle(mediaTitle);
            mpxAddMedia.ClickSaveBtn();
            MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
            mpxPublishMedia.PublishDefaultVideo();
    		
            //open pub and run cron
            applib.openApplication();
            Cron cron = new Cron(webDriver, applib);
            cron.RunCron(true);
            
    	    //validate video is present
    	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
    	    overlay.SwitchToActiveFrame();
    	    SearchFor searchFor = new SearchFor(webDriver, applib);
    	    searchFor.EnterTitle(mediaTitle);
    	    searchFor.ClickApplyBtn();
    	    overlay.switchToDefaultContent();
    	    if (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
    	    	//re-run cron as sometimes media assets aren't in the first ingested queue
    	    	cron.RunCron(false);
        	    taxonomy.NavigateSite("Content>>Files>>mpxMedia");
        	    searchFor.EnterTitle(mediaTitle);
        	    searchFor.ClickApplyBtn();
    	    }
    	    
    	    //if video is not present, log it
    	    if (!searchFor.GetFirstMPXMediaSearchResult().equals(mediaTitle)) {
    	    	Reporter.log("Video titled '" + mediaTitle + "' was not properly ingested.");
    	    	String filePath = System.getProperty("user.dir") + "/test-nbccom/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/NBCcomIssue/ErrorLog.txt";
    	        filePath = filePath.replace("/", File.separator);
    	    	File file = new File(filePath); 
        		if(!file.exists()){
        			file.createNewFile();
        		}
        		FileWriter fileWritter = new FileWriter(file.getName(),true);
        	    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        	    bufferWritter.write("Video titled '" + mediaTitle + "' was not properly ingested.");
        	    bufferWritter.close();
    	    }
    	} 
    }
}
