package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: November 13, 2014
 *********************************************/

public class ContentParent {

    private Driver webDriver;
    private ErrorChecking errorChecking;
    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentParent(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        errorChecking = new ErrorChecking(webDriver);
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Save_Btn = By.xpath("//input[@id='edit-submit']");
    
    private By Cancel_Btn = By.id("edit-cancel");
    
    private By Message_Ctr = By.xpath("//div[@class='messages status']");
    
    private By Warning_Ctr = By.xpath("//div[@class='messages warning']");
    
    private By PageTitle = By.id("page-title");
    
    private By Body_Txt = By.xpath("//body");
    
    private By Throbber_Img = By.xpath("//div[@class='throbber']");
    
    private By ProgressBar_Ctr = By.xpath("//div[@class='bar']");
    
    private By RequiredField_Ast(String field) {
    	return By.xpath("//*[contains(text(), '" + field + "')]/span[text()='*']");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Thread.sleep(500);
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	Thread.sleep(1000);
    	
    }
    
    public void ClickCancelBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' button.");
    	interact.Click(waitFor.ElementVisible(Cancel_Btn));
    	
    }
    
    public void VerifyMessageStatus(String messageStatus) throws Exception {
    	
    	Reporter.log("Verify success message of '" + messageStatus + "' is present.");
    	waitFor.ElementContainsText(Message_Ctr, messageStatus);
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void VerifyMessageWarning(String warningTxt) throws Exception {
    	
    	Reporter.log("Verify warning message '" + warningTxt + "' is present.");
    	waitFor.ElementContainsText(Warning_Ctr, warningTxt);
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void VerifyMessageError(String errorMessage) throws Exception {
    	
    	Reporter.log("Verify error message of '" + errorMessage + "' is present.");
    	errorChecking.VerifyErrorMessagePresent(errorMessage);
    	
    }
    
    public void VerifyRequiredFields(List<String> allFields) throws Exception {
    	
    	for (String field : allFields) {
    		Reporter.log("Verify the '" + field + "' is present as a required field.");
    		waitFor.ElementVisible(RequiredField_Ast(field));
    	}
    	
    }
    public void VerifyPostTitle(String postName) throws Exception {
    	
    	Reporter.log("Verify the post titled '" + postName + "' is present.");
    	waitFor.ElementContainsText(PageTitle, postName);
    	
    }

    public void VerifyPageContentPresent(List<String> txtItems) throws Exception {

    	for (final String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present on the page.");
        	waitFor.ElementContainsText(Body_Txt, text);
        }
    	
    }

    public void VerifyPageContentNotPresent(List<String> txtItems) throws Exception {

    	for (final String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is NOT present on the page.");
        	waitFor.ElementNotContainsText(Body_Txt, text);
        }
    	
    }
    
    public void VerifyPageURL(String url) throws Exception {
    	
    	Reporter.log("Verify page url is '" + url + "'.");
    	waitFor.URL(url);
    	
    }
    
    public void VerifySourceInPage(List<String> srcItems) throws Exception {
    	
    	for (final String source : srcItems) {
    		Reporter.log("Verify '" + source + "' is present in page source.");
    		waitFor.PageSourceContainsText(source);
    	}
    	
    }
    
    public void VerifySourceNotInPage(final String scriptSrc) throws Exception {
    	
    	Reporter.log("Verify '" + scriptSrc + "' is not present in page source.");
    	waitFor.PageSourceNotContainsText(scriptSrc);
    	
    }
    
    public void WaitForThrobberNotPresent() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	Thread.sleep(1500);
    	for (int I = 0; I <= 30; I++) {
    		if (I == 30) { Assert.fail("Throbber is still present after timeout."); }
    		try {
    			if(!webDriver.findElement(Throbber_Img).isDisplayed()) {
    				break;
    			}
    		}
    		catch (Exception e) {
    			break;
    		}
    		Thread.sleep(1000);
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    }
    
    public void WaitForProgressBarNotPresent() throws Exception {
    	
    	waitFor.ElementVisible(ProgressBar_Ctr);
    	new WaitFor(webDriver, 300).ElementNotPresent(ProgressBar_Ctr);
    	
    }
    
}

