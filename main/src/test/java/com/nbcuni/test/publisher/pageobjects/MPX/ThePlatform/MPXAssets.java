package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import org.sikuli.basics.Settings;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.nbcuni.test.publisher.common.Config;

/*********************************************
 * publisher.nbcuni.com MPX Assets Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXAssets {

    private Screen sikuli;
    private Config config;
    
    public MPXAssets() {
    	sikuli = new Screen();
    	Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        config = new Config();
        
    }
    
    public void WaitForAllAssetsToLoad() throws Exception {
    	
    	this.WaitForImgPresent(config.getConfigValueFilePath("PathToSikuliImages") + "Common/SaveCancel_Btns.png");
    	Thread.sleep(5000);
    }
    
    public void WaitForImgPresent(String imgPath) throws Exception {
    	
    	for (int second = 0; ; second++){
            if (second >= (double) config.getConfigValueInt("SikuliImageWaitTime")) {
                Assert.fail("Image '" + imgPath + "' is not present after timeout");
            }
            
            if (sikuli.exists(imgPath, .9) != null) {
            	break;
            }
            Thread.sleep(1000);
        }
    	
    	Thread.sleep(config.getConfigValueInt("MPXAssetBufferPause"));
    }
    
    public void WaitForImgNotPresent(String imgPath) throws Exception {
    	
    	for (int second = 0; ; second++){
            if (second >= (double) config.getConfigValueInt("SikuliImageWaitTime")) {
                Assert.fail("Image '" + imgPath + "' is still present after timeout");
            }
            
            if (sikuli.exists(imgPath, .9) == null) {
            	break;
            }
            Thread.sleep(1000);
        }
    	
    	Thread.sleep(config.getConfigValueInt("MPXAssetBufferPause"));
    }
    
    public void ScrollDownForImgPresent(String imgPath) throws Exception {
    	
    	for (int duration = 0; ; duration++){
            if (duration >= 10) {
                Assert.fail("MPX image '" + imgPath + "' is not present after 10 scrolls down");
            }
        
            if (sikuli.exists(imgPath, 1) == null) {
            	if (System.getProperty("os.name").contains("Windows")) {
            		sikuli.wheel(Button.WHEEL_DOWN, 15);
            	}
            	else {
            		sikuli.wheel(Button.WHEEL_UP, 15); //in java 7 sikuli mouse down is actually the wheel_up for mac
            	}
            }
            else {
            	break;
            }
            
        }
    }
    
    public void ScrollUpForImgPresent(String imgPath) throws Exception {
    	
    	for (int duration = 0; ; duration++){
            if (duration >= 10) {
                Assert.fail("MPX image '" + imgPath + "' is not present after 10 scrolls up");
            }
        
            if (sikuli.exists(imgPath, 1) == null) {
            	if (System.getProperty("os.name").contains("Windows")) {
            		sikuli.wheel(Button.WHEEL_UP, 15);
            	}
            	else {
            		sikuli.wheel(Button.WHEEL_DOWN, 15); //in java 7 sikuli mouse down is actually the wheel_up for mac
            	}
            }
            else {
            	break;
            }
            
        }
    }
    
    public void Scroll(String DownOrUp, int wheelGradiant) throws Exception {
    	
    	if (DownOrUp == "Down") {
    		if (System.getProperty("os.name").contains("Windows")) {
    			sikuli.wheel(Button.WHEEL_DOWN, wheelGradiant);
    		}
    		else {
    			sikuli.wheel(Button.WHEEL_UP, wheelGradiant);
    		}
    	}
    	else {
    		if (System.getProperty("os.name").contains("Windows")) {
    			sikuli.wheel(Button.WHEEL_UP, wheelGradiant);
    		}
    		else {
    			sikuli.wheel(Button.WHEEL_DOWN, wheelGradiant);
    		}
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

