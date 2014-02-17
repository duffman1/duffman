package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import org.testng.Reporter;
import org.sikuli.script.*;

/*********************************************
 * publisher.nbcuni.com MPX Delete Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXDeleteMedia {

    private static AppLib applib;
    private static Screen sikuli;
    private static MPXAssets mpxAssets;
    
    public MPXDeleteMedia(AppLib applib) {
        sikuli = new Screen();
        MPXDeleteMedia.applib = applib;
        mpxAssets = new MPXAssets(applib);
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    }
    
    public void GiveFocusToMediaList() throws Exception {
    	
    	Reporter.log("Click the media item to give focus to it.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "DeleteMedia/DeleteMedia1.png");
    	sikuli.click(getImagePath() + "DeleteMedia/DeleteMedia1.png");
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

