package com.nbcuni.test.publisher.pageobjects;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;



/*********************************************
 * publisher.nbcuni.com Blocks Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Blocks {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Blocks(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By SaveBlocks_Btn = By.xpath("//input[@id='edit-submit']");
    
    private By BlockLocator_Ddl(String blockName) {
    	return By.xpath("(//td[text()='" + blockName + "']/..//select)[1]");
    }
    
    private By SelectedBlock_Ctr(String blockName) {
    	return By.xpath("//td[@class='block'][contains(text(), '" + blockName + "')]/../td[2]//select/option[@selected='selected']");
    }
    
    private By HomePageBlock_Ctr(String blockName) {
    	return By.xpath("//div[@class='region region-sidebar-first']/div[@id='block-dfp-" + blockName + "']");
    }
   
    
    //PAGE OBJECT METHODS
    public void SelectRegion(String blockName, String regionLocation) throws Exception{
    	
    	Reporter.log("Select the block name '" + blockName + "' from the '" + regionLocation + "' drop down list.");
    	interact.Select(waitFor.ElementVisible(BlockLocator_Ddl(blockName)), regionLocation);
    	
    }
    
    public void ClickSaveBlocksBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Blocks' button.");
    	interact.Click(waitFor.ElementVisible(SaveBlocks_Btn));
    	
    }
    
    public void VerifySelectedRegion(String blockName, String regionLocation) throws Exception {
    	
    	Reporter.log("Assert that the selected block '" + blockName + "' contains the '" + regionLocation + "' text.");
    	waitFor.ElementContainsText((SelectedBlock_Ctr(blockName)), regionLocation);
    	
    }
    
    public void VerifyScriptSourceInPage(String scriptSrc) throws Exception {
    	
    	Reporter.log("Assert the page source contains the '" + scriptSrc + "'.");
    	waitFor.PageSourceContainsText(scriptSrc);
    	
    }
    
    public void VerifyHomePageBlockPresent(String blockName) throws Exception {
    	
    	Reporter.log("Assert the home page block '" + blockName + "' is present.");
    	waitFor.ElementVisible(HomePageBlock_Ctr(blockName));
    	
    }
    
}

