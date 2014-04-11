package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import org.testng.Reporter;
import org.sikuli.script.*;

/*********************************************
 * publisher.nbcuni.com MPX Publish Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXPublishMedia {

    private AppLib applib;
    private Screen sikuli;
    private MPXAssets mpxAssets;
    
    public MPXPublishMedia(AppLib applib) {
        sikuli = new Screen();
        this.applib = applib;
        mpxAssets = new MPXAssets(applib);
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    }
    
    public void ClickPublishBtn() throws Exception {
    	
    	Reporter.log("Click the 'Publish' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Publish/Publish_Btn.png");
    	sikuli.click(getImagePath() + "Publish/Publish_Btn.png");
    	
    	//Windows requires slight mousemove for publish dialog to be visible in flash - no idea why...
    	if (System.getProperty("os.name").contains("Windows")) {
    		Pattern pattern = new Pattern(getImagePath() + "Publish/Publish_Btn.png").targetOffset(-20, 0);
    		Region region = sikuli.exists(pattern, 1);
    		sikuli.click(region, 1);
    	}
    }
    
    public void ClickPublishToPub7PrimaryCbx() throws Exception {
    	
    	Reporter.log("Click the 'Publish to Pub 7 Primary' check box.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Publish/Pub7PrimaryPublishProfile_Lbl.png");
    	Pattern pattern = new Pattern(getImagePath() + "Publish/Pub7PrimaryPublishProfile_Lbl.png").targetOffset(-18, 0);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    }
    
    public void ClickPublishToAllCbx() throws Exception {
    	
    	Reporter.log("Click the 'Publish to All' check box.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Publish/All_Lbl.png");
    	Pattern pattern = new Pattern(getImagePath() + "Publish/All_Lbl.png").targetOffset(-18, 0);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    }
    
    public void ClickPublishFromDialogBtn() throws Exception {
    	
    	Reporter.log("Click the 'Publish' button from the upload dialog.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Publish/PublishFromDialog_Btn.png");
    	sikuli.click(getImagePath() + "Publish/PublishFromDialog_Btn.png");
    	
    	mpxAssets.WaitForImgNotPresent(getImagePath() + "Common/Spinner.png");
    	
    }
    
    public boolean PublishSuccessful() {
    	
    	boolean publishSuccessful = true;
    	if (sikuli.exists(getImagePath() + "Publish/PublishError_Ctr.png", 1) != null) {
    		publishSuccessful = false;
    	}
    	
    	return publishSuccessful;
    }
    
    public void ClickOKBtn() throws Exception {
    	
    	Reporter.log("Click the 'OK' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/OK_Btn.png");
    	sikuli.click(getImagePath() + "Common/OK_Btn.png");
    	
    }

    public void ClickAdditionalOptionsArrow() throws Exception {
    	
    	Reporter.log("Click the 'Additional Options' arrow.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/AdditionalOptions_Arr.png");
    	sikuli.click(getImagePath() + "Common/AdditionalOptions_Arr.png");
    }
    
    public void ClickPublishUpdateLnk() throws Exception {
    	
    	Reporter.log("Click the 'Publish Update' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Publish/PublishUpdate_Lnk.png");
    	sikuli.click(getImagePath() + "Publish/PublishUpdate_Lnk.png");
    }
    
    public void ClickUpdateBtn() throws Exception {
    	
    	Reporter.log("Click the 'Update' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Update_Btn.png");
    	sikuli.click(getImagePath() + "Common/Update_Btn.png");
    	
    	Thread.sleep(1000);
    	mpxAssets.WaitForImgNotPresent(getImagePath() + "Common/Spinner.png");
    }
    
    public void PublishDefaultVideo() throws Exception {
    	
    	this.ClickPublishBtn();
        this.ClickPublishToPub7PrimaryCbx();
        this.ClickPublishFromDialogBtn();
        
        if (this.PublishSuccessful() == false) {
    		Thread.sleep(5000);
        	this.ClickOKBtn();
        	this.ClickPublishBtn();
            this.ClickPublishToPub7PrimaryCbx();
            this.ClickPublishFromDialogBtn();
        }
    }
    
}

