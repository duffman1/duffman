package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com MPX Assets Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXAssets {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    
    public MPXAssets(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void WaitForAllAssetsToLoad() throws Exception {
    	
    	String path = this.getImagePath();
    	
    	boolean imgPresent = false;

        for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("MPX image is not present after timeout");}
            try{
                s.find(path + "Common/Loading_Txt.png");
                imgPresent = true;
            }
            catch (Exception e){}
            if (imgPresent == true){ break;}
            Thread.sleep(500);
        }
        
        for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("MPX image is still present after timeout");}
            try{
                s.find(path + "Common/Loading_Txt.png");
                imgPresent = true;
            }
            catch (Exception e){
            	imgPresent = false;
            }
            if (imgPresent == false){ break;}
            Thread.sleep(500);
        }

    	
    }
    
    public void WaitForImgPresent(String imgPath) throws Exception {
    	
    	boolean imgPresent = false;

        for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("MPX image is not present after timeout");}
            try{
                s.find(imgPath);
                imgPresent = true;
            }
            catch (Exception e){}
            if (imgPresent == true){ break;}
            Thread.sleep(500);
        }
    
    }
    
    public void WaitForImgNotPresent(String imgPath) throws Exception {
    	
    	boolean imgPresent = false;

    	for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("MPX image is still present after timeout");}
            try{
                s.find(imgPath);
                imgPresent = true;
            }
            catch (Exception e){
            	imgPresent = false;
            }
            if (imgPresent == false){ break;}
            Thread.sleep(500);
        }
    
    }
    
    public void ScrollDownForImgPresent(String imgPath) throws Exception {
    	
    	boolean imgPresent = false;

        for (int duration = 0; ; duration++){
            if (duration >= 120) {
                Assert.fail("MPX image is not present after timeout");}
            try{
                s.find(imgPath);
                imgPresent = true;
            }
            catch (Exception e) { 
            	
            	s.wheel(Button.WHEEL_DOWN, 15);
            }
            if (imgPresent == true){ break;}
            Thread.sleep(200);
        }
    
    }
    
    public void ClearInput() throws Exception {
    	
    	s.type("a", KeyModifier.CMD);
    	s.type(Key.BACKSPACE);
    }
    
}

