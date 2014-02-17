package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;
import org.testng.Assert;
import com.nbcuni.test.publisher.common.AppLib;

/*********************************************
 * publisher.nbcuni.com MPX Assets Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXAssets {

    private static AppLib applib;
    private static Screen sikuli;
    
    public MPXAssets(AppLib applib) {
    	sikuli = new Screen();
        MPXAssets.applib = applib;
        
    }
    
    public void WaitForAllAssetsToLoad(String path) throws Exception {
    	
    	//TODO - come up with a better dynamic wait for all mpx assets loaded
    	Thread.sleep(5000);
    	
    }
    
    public void WaitForImgPresent(String imgPath) throws Exception {
    	
    	sikuli.wait(imgPath, applib.getSikuliImageWaitTime()); 
    }
    
    public void WaitForImgNotPresent(String imgPath) throws Exception {
    	
    	boolean imgPresent = false;

    	for (int second = 0; ; second++){
            if (second >= applib.getSikuliImageWaitTime()) {
                Assert.fail("MPX image '" + imgPath + "' is still present after timeout");}
            try{
            	sikuli.find(imgPath);
                imgPresent = true;
            }
            catch (Exception e){
            	imgPresent = false;
            }
            if (imgPresent == false){ break;}
            Thread.sleep(1000);
        }
    	
    }
    
    public void ScrollDownForImgPresent(String imgPath) throws Exception {
    	
    	boolean imgPresent = false;

        for (int duration = 0; ; duration++){
            if (duration >= 10) {
                Assert.fail("MPX image '" + imgPath + "' is not present after 10 scrolls down");}
            try{
                sikuli.find(imgPath);
                imgPresent = true;
            }
            catch (Exception e) { 
            	
            	sikuli.wheel(Button.WHEEL_DOWN, 15);
            }
            if (imgPresent == true){ break;}
        }
    }
    
    public void Scroll(String DownOrUp, int wheelGradiant) throws Exception {
    	
    	if (DownOrUp == "Down") {
    		sikuli.wheel(Button.WHEEL_DOWN, wheelGradiant);
    	}
    	else {
    		sikuli.wheel(Button.WHEEL_UP, wheelGradiant);
    	}
          
    }
    
    public void ClearInput() throws Exception {
    	if (System.getProperty("os.name").contains("Windows")) {
    		sikuli.type("a", KeyModifier.CTRL);
    		
    	}
    	else {
    		sikuli.type("a", KeyModifier.CMD);
    	}
    	sikuli.type(Key.BACKSPACE);
    }
    
}

