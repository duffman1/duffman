package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.testng.Reporter;
import org.sikuli.script.*;

/*********************************************
 * publisher.nbcuni.com MPXLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXLogin {

    private Driver webDriver;
    private Screen sikuli;
    private AppLib applib;
    private MPXAssets mpxAssets;
    
    public MPXLogin(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        sikuli = new Screen();
        this.applib = applib;
        mpxAssets = new MPXAssets(applib);
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    }
    
    public void OpenMPXThePlatform() throws Exception {
    	
    	Reporter.log("Open the mpx platform.");
    	webDriver.navigate().to(applib.getMPXUrl());
    }
    
    public void Login(String userName, String passWord) throws Exception {
    	
    	mpxAssets.WaitForImgPresent(getImagePath() + "Login/SignIn_Ctr.png");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Login/UserName_Txb.png");
    	
    	Reporter.log("Enter '" + userName + "' in the 'Username' text box.");
    	sikuli.doubleClick(getImagePath() + "Login/UserName_Txb.png");
        sikuli.type(userName);
        
        Reporter.log("Enter '" + passWord + "' in the 'Password' text box.");
        sikuli.doubleClick(getImagePath() + "Login/PassWord_Txb.png");
        sikuli.type(passWord);
        
        Reporter.log("Click the 'Login' button.");
        sikuli.click(getImagePath() + "Login/SignIn_Btn.png");
        
        mpxAssets.WaitForAllAssetsToLoad();
        
    }
    
}

