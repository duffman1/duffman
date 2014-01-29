package com.nbcuni.test.publisher.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
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
    
    //PAGE OBJECT CONSTRUCTOR
    public Blocks(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Pub Sauce']")
    private static WebElement PubSauce_Lnk;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private static WebElement SaveBlocks_Btn;
    
    private static WebElement BlockLocator_Ddl(String blockName) {
    	
    	return webDriver.findElement(By.xpath("//select[@id='edit-blocks-dfp-" + blockName + "-region']"));
    }
    
    private static WebElement SelectedBlock_Ctr(String blockName) {
    	
    	return webDriver.findElement(By.xpath("//td[@class='block'][contains(text(), '" + blockName + "')]/../td[2]//select/option[@selected='selected']"));
    }
    
    private static WebElement HomePageBlock_Ctr(String blockName) {
    	return webDriver.findElement(By.xpath("//div[@class='region region-sidebar-first']/div[@id='block-dfp-" + blockName + "']"));
    }
   
    
    //PAGE OBJECT METHODS
    public void ClickPubSauceLnk(){
    	
    	Reporter.log("Click the 'Pub Sauce' link.");
    	PubSauce_Lnk.click();
    }
    
    public void SelectRegion(String blockName, String regionLocation) throws Exception{
    	
    	Reporter.log("Select the block name '" + blockName + "' from the '" + regionLocation + "' drop down list.");
    	Thread.sleep(2000);//TODO replace this with a better wait
    	new Select(BlockLocator_Ddl(blockName)).selectByVisibleText(regionLocation);
    	
    }
    
    public void ClickSaveBlocksBtn(){
    	
    	Reporter.log("Click the 'Save Blocks' button.");
    	SaveBlocks_Btn.click();
    }
    
    public void VerifySelectedRegion(String blockName, String regionLocation){
    	
    	Reporter.log("Assert that the selected block '" + blockName + "' contains the '" + regionLocation + "' text.");
    	Assert.assertTrue(SelectedBlock_Ctr(blockName).getText().contains(regionLocation));
    }
    
    public void VerifyScriptSourceInPage(String scriptSrc) {
    	
    	Reporter.log("Assert the page source contains the '" + scriptSrc + "'.");
    	Assert.assertTrue(webDriver.getPageSource().contains(scriptSrc));
    	
    }
    
    public void VerifyHomePageBlockPresent(String blockName) {
    	
    	Reporter.log("Assert the home page block '" + blockName + "' is present.");
    	Assert.assertTrue(HomePageBlock_Ctr(blockName).isDisplayed());
    }
    
   
  
}

