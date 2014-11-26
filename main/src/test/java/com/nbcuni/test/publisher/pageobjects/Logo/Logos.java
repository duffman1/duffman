package com.nbcuni.test.publisher.pageobjects.Logo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;

/*********************************************
 * publisher.nbcuni.com Logos Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 20, 2014
 *********************************************/

public class Logos {

	private Driver webDriver;
	private Config config;
	private Interact interact;
	private WaitFor waitFor;
	private Delete delete;
	
    //PAGE OBJECT CONSTRUCTOR
    public Logos(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        interact = new Interact(webDriver, timeout);
        waitFor = new WaitFor(webDriver, timeout);
        delete = new Delete(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddLogo_Lnk = By.linkText("Add Logo");
    
    private By HomeLogo_Img = By.cssSelector("img[alt='Home']");
    
    private By AllLogo_Ttls = By.xpath("//div[@class='view-content']//tbody/tr/td[contains(@class, 'title')]");
    
    private By Logo_Img(String logoTitle) {
    	return By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//img");
    }
    
    private By EditExtendMenu_Btn(String logoTitle) {
		return By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='operations']");
	}
	
	private By EditMenuDelete_Btn(String logoTitle) {
		return By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='Delete']");
	}
    
    //PAGE OBJECT METHODS
	public void ClickAddLogoLnk() throws Exception {
		
		Reporter.log("Click the 'Add Logo' link.");
		interact.Click(waitFor.ElementVisible(AddLogo_Lnk));
		
	}
	
	public void ClickEditExtendMenuBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' extend menu button for logo titled '" + logoTitle + "'.");
    	interact.Click(waitFor.ElementVisible(EditExtendMenu_Btn(logoTitle)));
    	
    }
    
    public void ClickEditMenuDeleteBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for logo titled '" + logoTitle + "'.");
    	interact.Click(waitFor.ElementVisible(EditMenuDelete_Btn(logoTitle)));
    	
    }
    
    public void VerifyHomePageLogoImgPresent(final String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	WebElement ele = waitFor.ElementContainsAttribute(HomeLogo_Img, "src", imageSrc);
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }

    public void VerifyLogoImgPresent(String logoTitle, String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	WebElement ele = waitFor.ElementContainsAttribute(Logo_Img(logoTitle), "src", imageSrc);
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void DeleteAllLogos() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    	if (webDriver.findElements(AllLogo_Ttls).size() > 0) {
    		
    		List<String> allLogoTtls = new ArrayList<String>();
    		for (WebElement logo : waitFor.ElementsVisible(AllLogo_Ttls)) {
    			 allLogoTtls.add(logo.getText().trim());
    		}
    		
    		for (String title : allLogoTtls) {
    			this.ClickEditExtendMenuBtn(title);
    			this.ClickEditMenuDeleteBtn(title);
    			delete.ClickDeleteBtn();
    		}
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
    
}

