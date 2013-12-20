package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Blocks Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Blocks {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Blocks_Frm = "//iframe[@title='Blocks dialog']";
    private static String PubSauce_Lnk = "//a[text()='Pub Sauce']";
    private static String SaveBlocks_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    
   
    public Blocks(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void SwitchToBlocksFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(Blocks_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void ClickPubSauceLnk(){
    	
    	webDriver.click(PubSauce_Lnk);
    }
    
    public void SelectRegion(String blockName, String regionLocation) throws Exception{
    	
    	String selectLocator = "//select[@id='edit-blocks-dfp-" + blockName + "-region']";
    	Thread.sleep(2000);//TODO replace this with a better wait
    	webDriver.selectFromDropdown(By.xpath(selectLocator), regionLocation);
    	
    }
    
    public void ClickSaveBlocksBtn(){
    	
    	webDriver.click(SaveBlocks_Btn);
    }
    
    public void VerifyBlockSettingsUpdated() {
    	
    	ul.verifyObjectContainsText(Message_Ctr, "The block settings have been updated.");
    }
    
    public void VerifySelectedRegion(String blockName, String regionLocation){
    	
    	ul.verifyTextFromAnObject("//td[@class='block'][contains(text(), '" + blockName + "')]/../td[2]//select/option[@selected='selected']", regionLocation);
    }
    
    public void VerifyScriptSourceInPage(String scriptSrc) {
    	
    	String pageSource = webDriver.getPageSource();
    	ul.verifyTextContainsExpectedText(pageSource, scriptSrc);
    	
    }
    
   
  
}

