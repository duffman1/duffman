package com.nbcuni.test.publisher.pageobjects.taxonomy;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
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
    	action.moveToElement(webDriver.findElement(By.xpath(locator)), -100, 100).build().perform();
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(locator)))));
    }
    
    public void ClickHomeLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Home_Lnk)));
    	webDriver.click(Home_Lnk);
    		
    }
   
    public void NavigateSite(String menuPath) throws Exception{
    	
    	String[] tierLevel = menuPath.split(">>");
    	
    	String tier1Locator = null;
    	String tier2Locator = null;
    	String tier3Locator = null;
    	String tier4Locator = null;
    	
    	int depth = tierLevel.length;
    	if (depth == 1 && menuPath == "Home") { //home link
    		this.ClickHomeLnk();
    	}
    	else if (depth == 1) { //tier 1 taxonomy
    		tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    		new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier1Locator)))).click();
    	}
    	else if (depth == 2) {
    		
    		tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    		tier2Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']";
    		
    		WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier1Locator))));
    		webDriver.executeScript(MouseOver_Js, tier1Lnk);
    		WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier2Locator))));
    		tier2Lnk.click();
    		this.MouseOffTaxonomyElement(tier2Locator);
    		
    	}
    	else if (depth == 3) {
    		
    		tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    		tier2Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']";
    		tier3Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']" + "/..//a[text()='" + tierLevel[2] + "']";
    		
    		WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier1Locator))));
    		webDriver.executeScript(MouseOver_Js, tier1Lnk);
    		WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier2Locator))));
    		webDriver.executeScript(MouseOver_Js, tier2Lnk);
    		WebElement tier3Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier3Locator))));
    		tier3Lnk.click();
    		this.MouseOffTaxonomyElement(tier3Locator);
    		
    	}
    	else if (depth == 4) {
    		
    		tier1Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']";
    		tier2Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']";
    		tier3Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']" + "/..//a[text()='" + tierLevel[2] + "']";
    		tier4Locator = TaxonomyBase + "//a[text()='" + tierLevel[0] + "']" + "/../ul//a[text()='" + tierLevel[1] +"']" + "/..//a[text()='" + tierLevel[2] + "']" + "/..//a[text()='" + tierLevel[3] + "']";
    		
    		WebElement tier1Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier1Locator))));
    		webDriver.executeScript(MouseOver_Js, tier1Lnk);
    		WebElement tier2Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier2Locator))));
    		webDriver.executeScript(MouseOver_Js, tier2Lnk);
    		WebElement tier3Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier3Locator))));
    		webDriver.executeScript(MouseOver_Js, tier3Lnk);
    		WebElement tier4Lnk = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(
    				By.xpath(tier4Locator))));
    		tier4Lnk.click();
    		this.MouseOffTaxonomyElement(tier4Locator);
    		
    	}
    }
    
    
    
  
}

