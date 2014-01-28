package com.nbcuni.test.publisher.pageobjects.Facebook;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Share Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class Share {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String PostToFacebookWall_Cbx = "//input[@id='edit-facebook-enabled']";
    private static String ProvideBriefMessage_Txa = "//textarea[@id='edit-facebook-stream-post']";
    private static String Share_Btn = "//input[@value='Share']";
    
    public Share(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickPostToFacebookWallCbx() throws Exception {
    	
    	WebElement Cbx = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(PostToFacebookWall_Cbx))));
    	
    	Cbx.click();
    }
    
    public void EnterBriefMessage(String messageTxt) throws Exception {
    	
    	WebElement txa = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(ProvideBriefMessage_Txa))));
    	
    	txa.sendKeys(messageTxt);
    }
    
    public void ClickShareBtn() throws Exception {
    	
    	WebElement Btn = new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(Share_Btn))));
    	
    	Btn.click();
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

            boolean elementPresent = true;
            for (int time = 0; ; time++){
                 if (time >= 60) {
                     Assert.fail("Element is still present after timeout");}
                  try{
                  webDriver.findElement(By.xpath(Share_Btn));
                  elementPresent = true;
                  }
                  catch (Exception e){ elementPresent = false;  }
                  if (elementPresent == false){ break;}
                  Thread.sleep(1000);
                  }

            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    
   
  
}

