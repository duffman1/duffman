package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;

/*********************************************
 * publisher.nbcuni.com MPX Select Account Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: March 18, 2014
 *********************************************/

public class MPXSelectAccount {

    private AppLib applib;
    private Screen sikuli;
    private MPXAssets mpxAssets;
    
    public MPXSelectAccount(AppLib applib) {
        sikuli = new Screen();
        this.applib = applib;
        mpxAssets = new MPXAssets(applib);
    }
    
    private String getImagePath() {
    	
    	return applib.getPathToSikuliImages();
    	
    }
    
    public void SelectAccount(String accountName) throws Exception {
    	
    	if (sikuli.exists(getImagePath() + "SelectAccount/DefaultAccountSelected.png", 1) == null) {
    		
    		Reporter.log("Enter the account name '" + accountName + "' in the account select text box.");
        	mpxAssets.WaitForImgPresent(getImagePath() + "SelectAccount/Account_Txb.png");
        	Pattern pattern = new Pattern(getImagePath() + "SelectAccount/Account_Txb.png").targetOffset(45, 0);
        	Region region = sikuli.exists(pattern, 1);
        	sikuli.doubleClick(region, 1);
        	mpxAssets.ClearInput();
        	sikuli.type(accountName);
        	Thread.sleep(2000);
        	
        	Reporter.log("Click the account option from the drop down list.");
        	Pattern pattern2 = new Pattern(getImagePath() + "SelectAccount/Account_Txb.png").targetOffset(40, 15);
        	Region region2 = sikuli.exists(pattern2, 1);
        	sikuli.click(region2, 1);
        	
        	Reporter.log("Click the 'Account' title to leave focus off of the selection.");
        	sikuli.click(getImagePath() + "SelectAccount/Account_Txb.png");
        	
        	Reporter.log("Wait for account to switch to the newly selected account.");
            mpxAssets.WaitForImgNotPresent(getImagePath() + "SelectAccount/SwitchingAccounts_Ctr.png");
    	}
    	
    }
    
}

