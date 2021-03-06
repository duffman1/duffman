package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.Config;

import org.testng.Reporter;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

/*********************************************
 * publisher.nbcuni.com MPX Search Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXSearch {

    private Config config;
    private Screen sikuli;
    private MPXAssets mpxAssets;
    
    public MPXSearch() {
        sikuli = new Screen();
        Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        config = new Config();
        mpxAssets = new MPXAssets();
    }
    
    private String getImagePath() {
    	
    	return config.getConfigValueFilePath("PathToSikuliImages");
    }
    
    public void EnterSearchTxt(String txt) throws Exception {
    	
    	Reporter.log("Enter '" + txt + "' in the 'Search' text box.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/Search_Txb.png");
    	sikuli.click(getImagePath() + "Search/Search_Txb.png");
    	mpxAssets.ClearInput();
    	sikuli.type(txt);
    }
    
    public void EnterSearchPlayersTxt(String txt) throws Exception {
    	
    	Reporter.log("Enter '" + txt + "' in the 'Player Search' text box.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/SearchPlayers_Txb.png");
    	sikuli.click(getImagePath() + "Search/SearchPlayers_Txb.png");
    	mpxAssets.ClearInput();
    	sikuli.type(txt);
    	Thread.sleep(1000);
    }
    
    public void ClickSearchByTitleLnk() throws Exception {
    	
    	Reporter.log("Click the 'Search by Title' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/Search_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Search/Search_Txb.png").targetOffset(-30, 0);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/Titles_Lnk.png");
    	sikuli.click(getImagePath() + "Search/Titles_Lnk.png");
    	
    	Thread.sleep(2000); //TODO - replace with dynamic wait
    }
    
    public void ClickSearchByPlayersTitleLnk() throws Exception {
    	
    	Reporter.log("Click the 'Search by Players Title' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/Titles_Ddl.png");
    	Thread.sleep(3000);
    	sikuli.click(getImagePath() + "Search/Titles_Ddl.png");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Search/Titles_Lnk.png");
    	Thread.sleep(3000);
    	sikuli.click(getImagePath() + "Search/Titles_Lnk.png");
    	
    	Thread.sleep(4000); //TODO - replace with dynamic wait
    }
    
    
}

