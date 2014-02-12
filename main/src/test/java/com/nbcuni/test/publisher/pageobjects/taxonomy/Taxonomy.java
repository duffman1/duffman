package com.nbcuni.test.publisher.pageobjects.taxonomy;



import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
    
    private static String Home_Lnk = "//span[@class='admin-menu-home-icon']";
    private static String TaxonomyBase = "//li[contains(@class,'admin-menu-toolbar-category')]";
    private static String MouseOver_Js = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
	
    public Taxonomy(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
  
    public void MouseOffTaxonomyElement(String locator) throws Exception {
    	//TODO call a script that closes the taxonomy bar
    	Actions action = new Actions(webDriver);
    	action.moveToElement(webDriver.findElement(By.xpath("//a[@id='edit-shortcuts']"))).build().perform();
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(locator)))));
    }
    
    public void NavigateSite(String menuPath) throws Exception{
    	
    	String[] tierLevel = menuPath.split(">>");
    	
    	String tier1Locator = null;
    	String tier2Locator = null;
    	String tier3Locator = null;
    	String tier4Locator = null;
    	
    	int depth = tierLevel.length;
    	if (menuPath.startsWith("Home")) { //home link taxonomy
    		
    		if (menuPath.contains("Development")) {
    			
    			if (depth == 3) {
    			
    				//TODO - update for all tier length. Right now almost all tests that leverage the devel module will be tier 3 taxonomy
    				tier1Locator = Home_Lnk;
        			tier2Locator = Home_Lnk + "/../..//span[contains(text(), 'Development')]";
        			tier3Locator = tier2Locator + "/..//a[contains(text(), '" + tierLevel[2] + "')]";
            		Thread.sleep(500);
            		WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
            				By.xpath(tier1Locator))));
            		webDriver.executeScript(MouseOver_Js, tier1Lnk);
            		Thread.sleep(500);
            		WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
            				By.xpath(tier2Locator))));
            		webDriver.executeScript(MouseOver_Js, tier2Lnk);
            		Thread.sleep(500);
            		WebElement tier3Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
            				By.xpath(tier3Locator))));
            		tier3Lnk.click();
            		this.MouseOffTaxonomyElement(tier3Locator);
    			}
    		}
    		
    		if (depth == 1) {
    			new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Home_Lnk))).click();
    		}
    		else if (depth == 2) {
    			
    			tier1Locator = Home_Lnk;
    			tier2Locator = Home_Lnk + "/../..//a[text()='" + tierLevel[1] + "']";
    			Thread.sleep(500);
        		WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
        				By.xpath(tier1Locator))));
        		webDriver.executeScript(MouseOver_Js, tier1Lnk);
        		Thread.sleep(500);
        		WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
        				By.xpath(tier2Locator))));
        		tier2Lnk.click();
        		this.MouseOffTaxonomyElement(tier2Locator);
    		}
    		
    	} else {
    	
    		if (depth == 1) { //tier 1 taxonomy
    			tier1Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]";
    			Thread.sleep(500);
    			new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier1Locator)))).click();
    		}
    		else if (depth == 2) {
    		
    			tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    			tier2Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]";
    			Thread.sleep(500);
    			WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier1Locator))));
    			webDriver.executeScript(MouseOver_Js, tier1Lnk);
    			Thread.sleep(500);
    			WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier2Locator))));
    			tier2Lnk.click();
    			this.MouseOffTaxonomyElement(tier2Locator);
    		
    		}
    		else if (depth == 3) {
    		
    			tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    			tier2Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]";
    			tier3Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]" + "/..//a[contains(text(),'" + tierLevel[2] + "')]";
    			Thread.sleep(500);
    			WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier1Locator))));
    			webDriver.executeScript(MouseOver_Js, tier1Lnk);
    			Thread.sleep(500);
    			WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier2Locator))));
    			webDriver.executeScript(MouseOver_Js, tier2Lnk);
    			Thread.sleep(500);
    			WebElement tier3Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier3Locator))));
    			tier3Lnk.click();
    			this.MouseOffTaxonomyElement(tier3Locator);
    		
    		}
    		else if (depth == 4) {
    		
    			tier1Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]";
    			tier2Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]";
    			tier3Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]" + "/..//a[contains(text(),'" + tierLevel[2] + "')]";
    			tier4Locator = TaxonomyBase + "//a[contains(text(),'" + tierLevel[0] + "')]" + "/../ul//a[contains(text(),'" + tierLevel[1] +"')]" + "/..//a[contains(text(),'" + tierLevel[2] + "')]" + "/..//a[contains(text(),'" + tierLevel[3] + "')]";
    			Thread.sleep(500);
    			WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier1Locator))));
    			webDriver.executeScript(MouseOver_Js, tier1Lnk);
    			Thread.sleep(500);
    			WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier2Locator))));
    			webDriver.executeScript(MouseOver_Js, tier2Lnk);
    			Thread.sleep(500);
    			WebElement tier3Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier3Locator))));
    			webDriver.executeScript(MouseOver_Js, tier3Lnk);
    			Thread.sleep(500);
    			WebElement tier4Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    					By.xpath(tier4Locator))));
    			tier4Lnk.click();
    			this.MouseOffTaxonomyElement(tier4Locator);
    		}
    	}
    }
    
    public void VerifyContentMenuExist(String MenuName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    			By.xpath(".//ul[@id='admin-menu-menu']/..//a[text()='" + MenuName + "']"))));
    	
    }
    
    
  
}

