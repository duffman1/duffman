package com.nbcuni.test.publisher.contentbuildscripts;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import org.testng.annotations.Test;

public class Build1000MPXAssets extends ParentTest{
	
    @Test()
    public void BuildAssets_Test() throws Exception{
    	
    	
    	//Step 1
    	applib.openApplication();
    	
        MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
        	mpxLogin.OpenMPXThePlatform();
        	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
        	
        	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(applib);
        	mpxSelectAccount.SelectAccount("DB TV");
        	
        	int I = 0;
        	while (I < 1000) {
        		I++;
        	
        	webDriver.getTitle();
        	
        	//Step 4 (test creates new mpx asset)
        	MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
        	AddFile addFile = new AddFile(webDriver, applib);
        	mpxAddMedia.ClickUploadBtn();
        	mpxAddMedia.ClickChooseFilesBtn();
        	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
        		mpxAddMedia.ClickMoviesUploadBtn();
        		mpxAddMedia.ClickTestMovieBtn();
        		mpxAddMedia.ClickOpenBtn();
        	}
        	else {
        		//addFile.EnterPathToFile_Win(applib.getPathToMedia());
            	//addFile.ClickGoBtn_Win();
            	addFile.EnterFileName_Win("DefAutMed.m4v");
            	addFile.ClickOpenBtn();
        	}
        	mpxAddMedia.ClickUploadFromDialogBtn();
        	String mediaTitle = "Automation" + random.GetCharacterString(10);
        	mpxAddMedia.GiveFocusToMediaItem();
        	mpxAddMedia.EnterTitle(mediaTitle);
        	
        	//Step 5
        	mpxAddMedia.ClickSaveBtn();
        	
        	//Step 6
        	MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
        	mpxPublishMedia.ClickPublishBtn();
        	mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
        	mpxPublishMedia.ClickPublishFromDialogBtn();
        	if (mpxPublishMedia.PublishSuccessful() == false) {
            	mpxPublishMedia.ClickOKBtn();
            	mpxPublishMedia.ClickPublishBtn();
                mpxPublishMedia.ClickPublishToPub7PrimaryCbx();
                mpxPublishMedia.ClickPublishFromDialogBtn();
            }
        	
        }
        
    }
}
