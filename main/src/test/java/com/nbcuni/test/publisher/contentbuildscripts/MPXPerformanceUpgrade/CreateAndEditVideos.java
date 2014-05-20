package com.nbcuni.test.publisher.contentbuildscripts.MPXPerformanceUpgrade;

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
import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateAndEditVideos extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void Test() throws Exception {
    	
    	//login to mpx
    	MPXLogin mpxLogin = new MPXLogin(webDriver, applib);
    	mpxLogin.OpenMPXThePlatform();
    	mpxLogin.Login(applib.getMPXUsername(), applib.getMPXPassword());
    	MPXSelectAccount mpxSelectAccount = new MPXSelectAccount(webDriver, applib);
        mpxSelectAccount.SelectAccount("DB TV");
        MPXAssets mpxAssets = new MPXAssets(applib);
	    mpxAssets.WaitForAllAssetsToLoad();
		
    	for(int CCount=0;CCount<3;CCount++) {
    		
    		//create asset
    		MPXAddMedia mpxAddMedia = new MPXAddMedia(applib);
        	mpxAddMedia.UploadDefaultVideo();
            String mediaTitle = "Automation" + random.GetCharacterString(10);
            mpxAddMedia.GiveFocusToMediaItem();
            mpxAddMedia.EnterTitle(mediaTitle);
            mpxAddMedia.ClickSaveBtn();
            MPXPublishMedia mpxPublishMedia = new MPXPublishMedia(applib);
            mpxPublishMedia.PublishDefaultVideo();
    		
    	    //log the creation time
            long creationTime = System.nanoTime();
    	    Reporter.log(mediaTitle + "," + creationTime);
    	    String filePath = System.getProperty("user.dir") + "/src/test/java/com/nbcuni/test/publisher/contentbuildscripts/MPXPerformanceUpgrade/AssetsCreated.txt";
    	    	
    	    filePath = filePath.replace("/", File.separator);
    	    System.out.println(filePath);
    	    File file = new File(filePath); 
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
        	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        	bufferWritter.write(mediaTitle + "," + creationTime + System.lineSeparator());
        	bufferWritter.close();
    	    
    	    
    	} 
    	
    	
    }
}