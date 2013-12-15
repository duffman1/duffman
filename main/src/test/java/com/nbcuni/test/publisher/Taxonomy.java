package com.nbcuni.test.publisher;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class Taxonomy {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Page_Title = "Site-Install";
    private static String Tier1_Structure_Lnk = "//li[contains(@class,'admin-menu-toolbar-category')]//a[text()='Structure']";
    private static String Tier1_Content_Lnk = "//li[contains(@class,'admin-menu-toolbar-category')]//a[text()='Content']";
    private static String Tier1_Structure_Tier2_ContentTypes_Lnk = Tier1_Structure_Lnk + "/../ul//a[text()='Content types']";
    private static String Tier1_Content_Tier2_AddContent_Lnk = Tier1_Content_Lnk + "/../ul//a[text()='Add content']";
    private static String Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='Add content type']";
    
    

    public Taxonomy(final CustomWebDriver custWebDr, final AppLib al2) {
        webDriver = custWebDr;
        al = al2;
        ul = new Util(webDriver);
        try {
            if (!webDriver.getTitle().contains(Page_Title)) {
                al.fail("Page Was Not in the User Login Page screen as expected");
            }
        } catch (Exception e) {
            al.fail(e.toString()); 
        }
    }
    
    
    public void MouseOverTier1StructureLnk() throws Exception {
    	
    	//TODO create a mouse over wrapper action for mouseover 
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Structure_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1ContentLnk() throws Exception {
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Content_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1StructureTier2ContentTypeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Structure_Tier2_ContentTypes_Lnk))).build().perform();
    	
    }
    
    public void MouseOverTier1ContentTier2AddContentLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Content_Tier2_AddContent_Lnk)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(Tier1_Content_Tier2_AddContent_Lnk))).build().perform();
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3AddContentTypeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk)));
    	
    	webDriver.click(Tier1_Structure_Tier2_ContentTypes_Tier3_AddContentType_Lnk);
    	
    }
    
    public void MouseOverTier1StructureTier2ContentTypeTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath(contentTypeLocator))).build().perform();
 
    	
    }
    
    public void ClickTier1StructureTier2ContentTypeTier3ContentTypeTier4ManageFieldsLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeManageDisplayLocator = Tier1_Structure_Tier2_ContentTypes_Lnk + "/..//a[text()='" + contentTypeName + "']" + "/..//a[text()='Manage fields']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeManageDisplayLocator)));
    	
    	webDriver.click(contentTypeManageDisplayLocator);
 
    	
    }
    
    public void ClickTier1ContentTier2AddContentTier3ContentTypeLnk(String contentTypeName) throws Exception {
    	
    	String contentTypeLocator = Tier1_Content_Tier2_AddContent_Lnk + "/..//a[text()='" + contentTypeName + "']";
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contentTypeLocator)));
    	
    	webDriver.click(contentTypeLocator);
 
    	
    }
    
    
  
}

