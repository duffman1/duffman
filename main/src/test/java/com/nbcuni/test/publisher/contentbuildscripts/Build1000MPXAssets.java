package com.nbcuni.test.publisher.contentbuildscripts;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSearch;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Build1000MPXAssets extends ParentTest{
	
    @Test()
    public void BuildAssets_Test() throws Exception{
    	
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	
        MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
        	mpxLogin.OpenMPXThePlatform();
        	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
        	
        	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
        	mpxSelectAccount.SelectAccount("DB TV");
        	
        	int I = 0;
        	while (I < 1000) {
        		I++;
        	
        	webDriver.getTitle();
        	
        	//Step 4 (test creates new mpx asset)
        	MPXAddMedia mpxAddMedia = new MPXAddMedia(webDriver, applib);
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
        	MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(webDriver, applib);
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
