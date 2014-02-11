package com.nbcuni.test.publisher.contentbuildscripts;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.EditMPXVideo;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAddMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXDeleteMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXPublishMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSearch;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.AddFile;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DeleteMPXVideos extends ParentTest{
	
    @Test()
    public void DeleteMPXVideos_Test() throws Exception{
    	
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	
        MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
        	mpxLogin.OpenMPXThePlatform();
        	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
        	
        	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
        	mpxSelectAccount.SelectAccount("DB TV");
        	
        	int I = 0;
        	while (I < 400) {
        		I++;
        	
        	
        	webDriver.getTitle();
        	
        	MPXDeleteMedia mpxDeleteMedia = new MPXDeleteMedia(webDriver, applib);
        	mpxDeleteMedia.ClickDeleteBtn();
        	mpxDeleteMedia.ClickYesBtn();
        	Thread.sleep(5000); //add dynamic wait
        	
        }
        
    }
}
