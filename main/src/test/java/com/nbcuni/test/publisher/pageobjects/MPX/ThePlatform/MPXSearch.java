package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.sikuli.script.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com MPX Search Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXSearch {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(this.webDriver, this.applib);
    
    public MPXSearch(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void EnterSearchTxt(String txt) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Search/Search_Txb.png");
    	s.click(path + "Search/Search_Txb.png");
    	mpxAssets.ClearInput();
    	s.type(txt);
    }
    
    public void ClickSearchByTitleLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Search/Search_Txb.png");
    	Pattern pImage = new Pattern(path + "Search/Search_Txb.png").targetOffset(-10, 0);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.WaitForImgPresent(path + "Search/Titles_Lnk.png");
    	s.click(path + "Search/Titles_Lnk.png");
    	
    	Thread.sleep(2000); //TODO - replace with dynamic wait
    }
    
    
}

