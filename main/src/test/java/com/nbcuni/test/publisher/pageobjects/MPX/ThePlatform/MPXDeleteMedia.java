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
 * publisher.nbcuni.com MPX Delete Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXDeleteMedia {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    MPXAssets mpxAssets;
    
    public MPXDeleteMedia(CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        mpxAssets = new MPXAssets(webDriver, applib);
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void GiveFocusToMediaList() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "DeleteMedia/DeleteMedia1.png");
    	s.click(path + "DeleteMedia/DeleteMedia1.png");
    }
    
    public void ClickDeleteBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "DeleteMedia/Delete_Btn.png");
    	s.click(path + "DeleteMedia/Delete_Btn.png");
    }
    
    public void ClickYesBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "DeleteMedia/Yes_Btn.png");
    	s.click(path + "DeleteMedia/Yes_Btn.png");
    }
    
    
    
  
}

