package com.nbcuni.test.publisher.pageobjects.PreviewSites;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Khan, Faizan
 * @version 1.0 Date: Jan 28, 2014
 *********************************************/
public class PreviewSites {

	 private static CustomWebDriver webDriver;
	    private static AppLib al;
	    private final Util ul;
	    
	    
	  //PAGE OBJECT CONSTRUCTOR    
	    public PreviewSites(final CustomWebDriver custWebDr) {
	        webDriver = custWebDr;
	        ul = new Util(webDriver);
	        
	    }
	    
	    
	  //PAGE OBJECT IDENTIFIERS
	    @FindBy(how = How.XPATH, using =".//a[@id='edit-sps'][contains(text(),'Preview Site')]")
	    private static WebElement PreviewSite_Menu;
	    
	    @FindBy(how = How.XPATH, using =".//input[@value='Enable Preview']")
	    private static WebElement EnablePreview_Btn;
	    
	    @FindBy(how = How.XPATH, using =".//select[@id='edit-active-condition-container-active-condition-selector']")
	    private static WebElement SelectCondition;
	    
	    @FindBy(how = How.XPATH, using =".//*[@id='edit-active-condition-container-active-condition-selector']/option[@selected='selected']")
	    private static WebElement SelectedCondition;
	    @FindBy(how = How.XPATH, using =".//input[@id='edit-active-condition-container-date-condition-widget-preview-date-datepicker-popup-0']")
	    private static WebElement DateTextBoxElement;
	    
	    @FindBy(how = How.XPATH, using =".//input[@id='edit-active-condition-container-date-condition-widget-preview-date-timeEntry-popup-1']")
	    private static WebElement TimeTextBoxElement;
	    
	    @FindBy(how = How.XPATH, using =".//input[@id='edit-cancel']")
	     private static WebElement DisablePreview;	
	    
	    @FindBy(how = How.XPATH, using =".//input[@value='Update Preview']")
	     private static WebElement UpdatePreview;
	    
	    @FindBy(how = How.XPATH, using =".//div[@class='iib page-iib'][@style='display: block;']")
	    private static WebElement PreviewsiteArea_visible;
	    
	    @FindBy(how = How.XPATH, using =".//div[@class='iib page-iib'][@style='display: none;']")
	    private static WebElement PreviewsiteArea_hidden;
	    
	    //PAGE OBJECT METHODS
	    public void ClickEnablePrview() throws Exception { 
	    	Reporter.log("Click on Enable Preview button.");
	    	EnablePreview_Btn.click();
	    	
	    }
	    public void ClickPrviewSiteLink() throws Exception { 
	    	Reporter.log("Click on Preview Site Link.");
	    	PreviewSite_Menu.click();
	    	
	    }
	    public void VerifyEnablePrviewButton(Boolean Bcond) throws Exception { 
	    	Reporter.log("Verifing Enable Preview button exististence.");
	    	if(Bcond){
	    		Assert.assertTrue(EnablePreview_Btn.isDisplayed());
	    	}
	    		else{
	    		Assert.assertFalse(EnablePreview_Btn.isDisplayed());
	    		}
	    	
	    }
	    
	    public void SelectACondition() throws Exception { 
	    	Reporter.log("Select A Condition to Enable Preview.");
	    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(SelectCondition));
	    	Select sectectItem = new Select(SelectCondition);
	    	SelectCondition.sendKeys("Site as of ...");
	    	SelectCondition.sendKeys(Keys.TAB);
	    }
	    
	    public void VerifySelectACondition(String SelectdVal) throws Exception { 	    	
	    	Reporter.log("Verifying Select A Condition.");	  
	    	Assert.assertEquals((SelectedCondition.getText()),SelectdVal);	    	
	    }
	    
	    
	    public void EnterDate(String date) throws Exception {
	    	Reporter.log("Populate date");
	    	DateTextBoxElement.clear();
	    	DateTextBoxElement.sendKeys(date);
	    	DateTextBoxElement.sendKeys(Keys.TAB);
	    }
	    public void VerifyDate(String date) throws Exception {
	    	
	    	Reporter.log("Verifying Populated date");	    	
	    	Assert.assertEquals((DateTextBoxElement.getAttribute("value")),date);
	    	System.out.println("Done");
	    }
	    
	    public void EnterTime(String PTime) throws Exception {
	    	
	    	Reporter.log("Populate time");	
	    	TimeTextBoxElement.clear();
	    	TimeTextBoxElement.sendKeys(PTime);
	    	TimeTextBoxElement.sendKeys(Keys.TAB);
	    }
	    
	    public void VerifyTime(String Ptime) throws Exception {
	    	
	    	Reporter.log("Verifying Populated time");	    	
	    	Assert.assertTrue((TimeTextBoxElement.getAttribute("value")).contains(Ptime));
	    	System.out.println("Done");
	    }
	    
	    public void VerifyDisablePreviewButton(Boolean Bcond) throws Exception {
	    	Reporter.log("Verify Disable Preview button existence");
	    	
	    	if(Bcond){
	    		Assert.assertTrue(DisablePreview.isDisplayed());
	    		
	    	}
	    		else{
	    		Assert.assertFalse(DisablePreview.isDisplayed());
	    		}
	    		   	
	    }
	    public void ClickDisablePreviewButton() throws Exception {
	    	Reporter.log("Click Disable Preview button.");	    	
	    	DisablePreview.click();
	    	
	    }
	    public void VerifyUpdatePreviewButton(Boolean Bcondition) throws Exception {	    	
	    	Reporter.log("Verify Update Preview button existence");
	    	
	    	if(Bcondition){
	    		Assert.assertTrue(UpdatePreview.isDisplayed());
	    	}
	    		else{
	    		Assert.assertFalse(UpdatePreview.isDisplayed());
	    		}
	    	
	    }
	    
	    public void VerifyPreviewSiteAreaVisible() throws Exception {
	    	Reporter.log("Verify Preview Site visible.");	    	
	    	PreviewsiteArea_visible.isDisplayed();
	    	
	    }
	    public void VerifyPreviewSiteAreaHidden() throws Exception {
	    	
	    	Reporter.log("Verify Site Preview area existence on the page.");	
	    	PreviewsiteArea_hidden.isDisplayed();
	    	
	    }

	    public void ClickUpdatePrview(){
	    	Reporter.log("Click on Update Preview Button");
	    	UpdatePreview.click();
	    }
}
