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
 * publisher.nbcuni.com MPX Publish Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXPublishMedia {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(this.webDriver, this.applib);
    
    public MPXPublishMedia(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void ClickPublishBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Publish/Publish_Btn.png", 30);
    	s.click(path + "Publish/Publish_Btn.png");
    }
    
    public void ClickPublishToAllCbx() throws Exception {
    	
    	String path = this.getImagePath();
    	Pattern pImage = new Pattern(path + "Publish/All_Lbl.png").targetOffset(-18, 0);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    }
    
    public void ClickPublishFromDialogBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Publish/PublishFromDialog_Btn.png", 30);
    	s.click(path + "Publish/PublishFromDialog_Btn.png");
    	
    	mpxAssets.WaitForImgPresent(path + "Common/Spinner.png");
    	mpxAssets.WaitForImgNotPresent(path + "Common/Spinner.png");
    	
    }
    
    public void ClickAdditionalOptionsArrow() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Common/AdditionalOptions_Arr.png", 30);
    	s.click(path + "Common/AdditionalOptions_Arr.png");
    }
    
    public void ClickPublishUpdateLnk() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Publish/PublishUpdate_Lnk.png", 30);
    	s.click(path + "Publish/PublishUpdate_Lnk.png");
    }
    
    public void ClickUpdateBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	s.wait(path + "Common/Update_Btn.png", 30);
    	s.click(path + "Common/Update_Btn.png");
    	
    	mpxAssets.WaitForImgPresent(path + "Common/Spinner.png");
    	mpxAssets.WaitForImgNotPresent(path + "Common/Spinner.png");
    }
    
    
  
}

