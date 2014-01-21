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
 * publisher.nbcuni.com MPXLogin Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MPXLogin {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    MPXAssets mpxAssets = new MPXAssets(this.webDriver, this.applib);
    
    public MPXLogin(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void OpenMPXThePlatform() throws Exception {
    	
    	webDriver.navigate().to("http://mpx.theplatform.com/");
    }
    
    public void Login(String userName, String passWord) throws Exception {
    	
    	Screen s = new Screen();
        String path = this.getImagePath();
    	
    	s.wait(path + "Login/UserName_Txb.png", 30);
    	mpxAssets.WaitForImgPresent(path + "Login/UserName_Txb.png");
    	s.doubleClick(path + "Login/UserName_Txb.png");
        s.type(userName);
        s.doubleClick(path + "Login/PassWord_Txb.png");
        s.type(passWord);
        s.click(path + "Login/SignIn_Btn.png");
    }
    
    
  
}

