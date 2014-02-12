package com.nbcuni.test.publisher.pageobjects.Taxonomy;

import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: February 14, 2013
 *********************************************/

public class Taxonomy {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Taxonomy(CustomWebDriver webDriver) {
        Taxonomy.webDriver = webDriver;
        
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    private static String MouseOver_Js = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
	
    private static WebElement Tier1_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]//a[contains(text(),'" + tiers[0] + "')]"));
    }
    
    private static WebElement Tier2_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]//a[contains(text(),'" + tiers[0] + "')]/../ul//a[contains(text(),'" + tiers[1] +"')]"));
    }
    
    private static WebElement Tier3_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]//a[contains(text(),'" + tiers[0] + "')]/../ul//a[contains(text(),'" + tiers[1] +"')]/..//a[contains(text(),'" + tiers[2] + "')]"));
    }
    
    private static WebElement Tier4_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]//a[contains(text(),'" + tiers[0] + "')]/../ul//a[contains(text(),'" + tiers[1] +"')]/..//a[contains(text(),'" + tiers[2] + "')]/..//a[contains(text(),'" + tiers[3] + "')]"));
    }
    
    @FindBy(how = How.XPATH, using = "//span[@class='admin-menu-home-icon']")
    private static WebElement HomeTier1_Lnk;
    
    private static WebElement HomeTier2_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//span[@class='admin-menu-home-icon']/../..//a[text()='" + tiers[1] + "']"));
    }
    
    @FindBy(how = How.XPATH, using = "//span[@class='admin-menu-home-icon']/../..//span[contains(text(), 'Development')]")
    private static WebElement HomeDevelopmentTier2_Lnk;
    
    private static WebElement HomeDevelopmentTier3_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//span[@class='admin-menu-home-icon']/../..//span[contains(text(), 'Development')]/..//a[contains(text(), '" + tiers[2] + "')]"));
    }
    
    @FindBy(how = How.XPATH, using = "//a[@id='edit-shortcuts']")
    private static WebElement Shortcuts_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void MouseOffTaxonomyElement(WebElement locator) throws Exception {
    	Actions action = new Actions(webDriver);
    	action.moveToElement(Shortcuts_Lnk).build().perform();
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(locator)));
    }
    
    public void NavigateSite(String menuPath) throws Exception{
    	
    	String[] tierLevel = menuPath.split(">>");
    	
    	WebDriverWait wait = new WebDriverWait(webDriver, 10);
    	
    	int depth = tierLevel.length;
    	if (menuPath.startsWith("Home")) { //home link taxonomy
    		
    		if (menuPath.contains("Development")) {
    			
    			if (depth == 3) {
    			
    				//TODO - update for all tier length. Right now almost all tests that leverage the devel module will be tier 3 taxonomy
    				Thread.sleep(500);
            		wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk));
            		webDriver.executeScript(MouseOver_Js, HomeTier1_Lnk);
            		Thread.sleep(500);
            		wait.until(ExpectedConditions.visibilityOf(HomeDevelopmentTier2_Lnk));
            		webDriver.executeScript(MouseOver_Js, HomeDevelopmentTier2_Lnk);
            		Thread.sleep(500);
            		wait.until(ExpectedConditions.visibilityOf(HomeDevelopmentTier3_Lnk(tierLevel))).click();
            		this.MouseOffTaxonomyElement(HomeDevelopmentTier3_Lnk(tierLevel));
    			}
    		}
    		
    		if (depth == 1) {
    			wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk)).click();
    		}
    		else if (depth == 2) {
    			
    			Thread.sleep(500);
        		wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk));
        		webDriver.executeScript(MouseOver_Js, HomeTier1_Lnk);
        		Thread.sleep(500);
        		wait.until(ExpectedConditions.visibilityOf(HomeTier2_Lnk(tierLevel))).click();
        		this.MouseOffTaxonomyElement(HomeTier2_Lnk(tierLevel));
    		}
    		
    	} else {
    	
    		if (depth == 1) { //tier 1 taxonomy
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel))).click();
    		}
    		else if (depth == 2) {
    		
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier2_Lnk(tierLevel));
    		
    		}
    		else if (depth == 3) {
    		
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier2_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier3_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier3_Lnk(tierLevel));
    		
    		}
    		else if (depth == 4) {
    		
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier2_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier3_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier3_Lnk(tierLevel));
    			Thread.sleep(500);
    			wait.until(ExpectedConditions.visibilityOf(Tier4_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier4_Lnk(tierLevel));
    		}
    	}
    }
    
    public void VerifyContentMenuExist(String MenuName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    			By.xpath(".//ul[@id='admin-menu-menu']/..//a[text()='" + MenuName + "']"))));
    	
    }
    
    
  
}

