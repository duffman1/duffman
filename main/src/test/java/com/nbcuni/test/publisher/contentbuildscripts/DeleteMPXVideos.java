package com.nbcuni.test.publisher.contentbuildscripts;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXDeleteMedia;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXLogin;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXSelectAccount;
import org.testng.annotations.Test;

public class DeleteMPXVideos extends ParentTest{
	
    @Test()
    public void DeleteMPXVideos_Test() throws Exception{
    	
    	
    	//Step 1
    	applib.openApplication();
    	
        MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
        	mpxLogin.OpenMPXThePlatform();
        	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
        	
        	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(applib);
        	mpxSelectAccount.SelectAccount("DB TV");
        	
        	int I = 0;
        	while (I < 400) {
        		I++;
        	
        	
        	webDriver.getTitle();
        	
        	MPXDeleteMedia mpxDeleteMedia = new MPXDeleteMedia(applib);
        	mpxDeleteMedia.ClickDeleteBtn();
        	mpxDeleteMedia.ClickYesBtn();
        	Thread.sleep(5000); //add dynamic wait
        	
        }
        
    }
}
