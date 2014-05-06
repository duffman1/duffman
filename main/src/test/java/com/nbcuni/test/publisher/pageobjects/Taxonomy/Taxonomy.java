package com.nbcuni.test.publisher.pageobjects.Taxonomy;

import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: February 14, 2013
 *********************************************/

public class Taxonomy {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Taxonomy(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    private String MouseOver_Js = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
	
    private WebElement Tier1_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]/a[text()='" + tiers[0] + "']"));
    }
    
    private WebElement Tier2_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]/a[text()='" + tiers[0] + "']/../ul//a[text()='" + tiers[1] +"']"));
    }
    
    private WebElement Tier3_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]/a[text()='" + tiers[0] + "']/../ul//a[text()='" + tiers[1] +"']/..//a[text()='" + tiers[2] + "']"));
    }
    
    private WebElement Tier4_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]/a[text()='" + tiers[0] + "']/../ul//a[text()='" + tiers[1] +"']/..//a[text()='" + tiers[2] + "']/..//a[text()='" + tiers[3] + "']"));
    }
    
    private WebElement Tier5_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//li[contains(@class,'admin-menu-toolbar-category')]/a[text()='" + tiers[0] + "']/../ul//a[text()='" + tiers[1] +"']/..//a[text()='" + tiers[2] + "']/..//a[text()='" + tiers[3] + "']/..//a[text()='" + tiers[4] + "']"));
    }
    
    @FindBy(how = How.XPATH, using = "//span[@class='admin-menu-home-icon']")
    private WebElement HomeTier1_Lnk;
    
    private WebElement HomeTier2_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//span[@class='admin-menu-home-icon']/../..//a[text()='" + tiers[1] + "']"));
    }
    
    @FindBy(how = How.XPATH, using = "//span[@class='admin-menu-home-icon']/../..//span[contains(text(), 'Development')]")
    private WebElement HomeDevelopmentTier2_Lnk;
    
    private WebElement HomeDevelopmentTier3_Lnk(String[] tiers) {
    	return webDriver.findElement(By.xpath("//span[@class='admin-menu-home-icon']/../..//span[contains(text(), 'Development')]/..//a[contains(text(), '" + tiers[2] + "')]"));
    }
    
    private WebElement TaxonomyItem_Lnk(String menuName) {
    	return webDriver.findElement(By.xpath("//ul[@id='admin-menu-menu']/..//a[text()=\"" + menuName + "\"]"));
    }
    
    @FindBy(how = How.XPATH, using = "//a[@id='edit-shortcuts']")
    private WebElement Shortcuts_Lnk;
    
    @FindBy(how = How.CSS, using = "li[class='admin-menu-action admin-menu-account'] a")
    private WebElement ActiveUserMenu_Lnk;
    
    
    //PAGE OBJECT METHODS
    public void ClickActiveUserMenuLnk() throws Exception {
    	
    	Reporter.log("Click the activer user menu link for logged in user '" + ActiveUserMenu_Lnk.getText() + "'.");
    	ActiveUserMenu_Lnk.click();
    }
    
    public void MouseOffTaxonomyElement(WebElement locator) throws Exception {
    	
    	//is mouse-off fails, refresh the page to be sure to close taxonomy menu
    	try {
    		Actions action = new Actions(webDriver);
    		action.moveToElement(Shortcuts_Lnk).build().perform();
    		new WebDriverWait(webDriver, 5).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(locator)));
    	}
    	catch (Exception e) {
    		webDriver.navigate().refresh();
    	}
    }
    
    public void NavigateSite(String menuPath) throws Exception{
    	
    	String[] tierLevel = menuPath.split(">>");
    	
    	WebDriverWait wait = new WebDriverWait(webDriver, 10);
    	
    	int depth = tierLevel.length;
    	if (menuPath.startsWith("Home")) { //home link taxonomy
    		
    		if (menuPath.contains("Development")) {
    			
    			if (depth == 3) {
    			
    				//TODO - update for all tier length. Right now almost all tests that leverage the devel module will be tier 3 taxonomy
    				Thread.sleep(250);
            		wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk));
            		webDriver.executeScript(MouseOver_Js, HomeTier1_Lnk);
            		Thread.sleep(250);
            		wait.until(ExpectedConditions.visibilityOf(HomeDevelopmentTier2_Lnk));
            		webDriver.executeScript(MouseOver_Js, HomeDevelopmentTier2_Lnk);
            		Thread.sleep(250);
            		wait.until(ExpectedConditions.visibilityOf(HomeDevelopmentTier3_Lnk(tierLevel))).click();
            		this.MouseOffTaxonomyElement(HomeDevelopmentTier3_Lnk(tierLevel));
    			}
    		}
    		
    		if (depth == 1) {
    			Thread.sleep(250);
    			wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk)).click();
    		}
    		else if (depth == 2) {
    			
    			Thread.sleep(250);
        		wait.until(ExpectedConditions.visibilityOf(HomeTier1_Lnk));
        		webDriver.executeScript(MouseOver_Js, HomeTier1_Lnk);
        		Thread.sleep(250);
        		wait.until(ExpectedConditions.visibilityOf(HomeTier2_Lnk(tierLevel))).click();
        		this.MouseOffTaxonomyElement(HomeTier2_Lnk(tierLevel));
    		}
    		
    	} else {
    	
    		if (depth == 1) { //tier 1 taxonomy
    			Thread.sleep(250);
    			Reporter.log("Click the '" + tierLevel[0] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel))).click();
    		}
    		else if (depth == 2) {
    		
    			Thread.sleep(250);
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			Reporter.log("Mouse over the '" + tierLevel[0] + "' menu link.");
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Click the '" + tierLevel[1] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier2_Lnk(tierLevel));
    		
    		}
    		else if (depth == 3) {
    		
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[0] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[1] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier2_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Click the '" + tierLevel[2] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier3_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier3_Lnk(tierLevel));
    		
    		}
    		else if (depth == 4) {
    		
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[0] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[1] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier2_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[2] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier3_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier3_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Click the '" + tierLevel[3] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier4_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier4_Lnk(tierLevel));
    		}
    		else if (depth == 5) {
        		
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[0] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier1_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier1_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[1] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier2_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier2_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[2] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier3_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier3_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Mouse over the '" + tierLevel[3] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier4_Lnk(tierLevel)));
    			webDriver.executeScript(MouseOver_Js, Tier4_Lnk(tierLevel));
    			Thread.sleep(250);
    			Reporter.log("Click the '" + tierLevel[4] + "' menu link.");
    			wait.until(ExpectedConditions.visibilityOf(Tier5_Lnk(tierLevel))).click();
    			this.MouseOffTaxonomyElement(Tier5_Lnk(tierLevel));
    		}
    	}
    }
    
    public void VerifyContentMenuExist(String menuName) throws Exception {
    	
    	Reporter.log("Verify content menu item '" + menuName + "' is present in the site taxonomy.");
    	TaxonomyItem_Lnk(menuName).getText();
    	
    }
    
    
  
}

