package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Blocks Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 15, 2013
 *********************************************/

public class Blocks {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Blocks(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Pub Sauce']")
    private WebElement PubSauce_Lnk;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private WebElement SaveBlocks_Btn;
    
    private WebElement BlockLocator_Ddl(String blockName) {
    	return webDriver.findElement(By.xpath("(//td[text()='" + blockName + "']/..//select)[1]"));
    }
    
    private WebElement SelectedBlock_Ctr(String blockName) {
    	return webDriver.findElement(By.xpath("//td[@class='block'][contains(text(), '" + blockName + "')]/../td[2]//select/option[@selected='selected']"));
    }
    
    private WebElement HomePageBlock_Ctr(String blockName) {
    	return webDriver.findElement(By.xpath("//div[@class='region region-sidebar-first']/div[@id='block-dfp-" + blockName + "']"));
    }
   
    
    //PAGE OBJECT METHODS
    public void ClickPubSauceLnk() throws Exception {
    	
    	Reporter.log("Click the 'Pub Sauce' link.");
    	PubSauce_Lnk.click();
    }
    
    public void SelectRegion(String blockName, String regionLocation) throws Exception{
    	
    	Reporter.log("Select the block name '" + blockName + "' from the '" + regionLocation + "' drop down list.");
    	Thread.sleep(1000);
    	new Select(BlockLocator_Ddl(blockName)).selectByVisibleText(regionLocation);
    	
    }
    
    public void ClickSaveBlocksBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save Blocks' button.");
    	SaveBlocks_Btn.click();
    }
    
    public void VerifySelectedRegion(String blockName, String regionLocation) throws Exception {
    	
    	Reporter.log("Assert that the selected block '" + blockName + "' contains the '" + regionLocation + "' text.");
    	Assert.assertTrue(SelectedBlock_Ctr(blockName).getText().contains(regionLocation));
    }
    
    public void VerifyScriptSourceInPage(String scriptSrc) throws Exception {
    	
    	Reporter.log("Assert the page source contains the '" + scriptSrc + "'.");
    	Assert.assertTrue(webDriver.getPageSource().contains(scriptSrc));
    	
    }
    
    public void VerifyHomePageBlockPresent(String blockName) throws Exception {
    	
    	Reporter.log("Assert the home page block '" + blockName + "' is present.");
    	Thread.sleep(500); //stale element exception
    	Assert.assertTrue(HomePageBlock_Ctr(blockName).isDisplayed());
    }
    
}

