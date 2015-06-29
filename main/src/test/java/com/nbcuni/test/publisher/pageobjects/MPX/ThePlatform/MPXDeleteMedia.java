package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.Config;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import java.util.Iterator;

/*********************************************
 * publisher.nbcuni.com MPX Delete Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXDeleteMedia {

    private Screen sikuli;
    private Config config;
    private MPXAssets mpxAssets;
    
    public MPXDeleteMedia() {
        sikuli = new Screen();
        Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        config = new Config();
        mpxAssets = new MPXAssets();
    }
    
    private String getImagePath() {
    	
    	return config.getConfigValueFilePath("PathToSikuliImages");
    }
    
    public void GiveFocusToMediaList() throws Exception {
    	
    	Reporter.log("Click the media item to give focus to it.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "DeleteMedia/DeleteMedia1.png");
    	sikuli.click(getImagePath() + "DeleteMedia/DeleteMedia1.png");
    }
    
    public void ClickEachAutomationItem() throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "DeleteMedia/Automation_Ttl.png");
    	
    	Iterator<Match> matches = sikuli.findAll(getImagePath() + "DeleteMedia/Automation_Ttl.png");
    	while (matches.hasNext()) {
            Object match = matches.next();
            sikuli.click(match);
    	}
    }

    public void ClickDeleteBtn() throws Exception {
    	
    	Reporter.log("Click the 'Delete' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "DeleteMedia/Delete_Btn.png");
    	sikuli.click(getImagePath() + "DeleteMedia/Delete_Btn.png");
    }
    
    public void ClickYesBtn() throws Exception {
    	
    	Reporter.log("Click the 'Yes' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "DeleteMedia/Yes_Btn.png");
    	sikuli.click(getImagePath() + "DeleteMedia/Yes_Btn.png");
    }
    
}

