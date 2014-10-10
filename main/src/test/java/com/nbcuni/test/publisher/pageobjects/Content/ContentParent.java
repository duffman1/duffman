package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ContentParent {

    private Driver webDriver;
    private ErrorChecking errorChecking;
    private Config config;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentParent(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        errorChecking = new ErrorChecking(webDriver);
        config = new Config();
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-cancel")
    private WebElement Cancel_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages status']")
    private WebElement Message_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages warning']")
    private WebElement Warning_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']")
    private WebElement Error_Ctr;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Edit Draft']")
    private WebElement EditDraft_Tab;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Revisions']")
    private WebElement Revisions_Tab;
    
    @FindBy(how = How.ID, using = "page-title")
    private WebElement PageTitle;
    
    @FindBy(how = How.XPATH, using = "//a[text()='View']")
    private WebElement View_Tab;
    
    @FindBy(how = How.XPATH, using = "//body")
    private WebElement Body_Txt;
    
    @FindBy(how = How.XPATH, using = "//div[@class='throbber']")
    private WebElement Throbber_Img;

    private WebElement RequiredField_Ast(String field) {
    	return webDriver.findElement(By.xpath("//*[contains(text(), '" + field + "')]/span[text()='*']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void ClickCancelBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cancel' button.");
    	Cancel_Btn.click();
    }
    
    public void VerifyMessageStatus(String messageStatus) throws Exception {
    	
    	Reporter.log("Verify success message of '" + messageStatus + "' is present.");
    	if (!Message_Ctr.getText().contains(messageStatus)) {
    		Assert.fail("The message status '" + messageStatus + "' is not present.");
    	}
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    }
    
    public void VerifyMessageWarning(String warningTxt) throws Exception {
    	
    	Reporter.log("Verify warning message '" + warningTxt + "' is present.");
    	Assert.assertTrue(Warning_Ctr.getText().contains(warningTxt));
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    }
    
    public void VerifyMessageError(String errorMessage) throws Exception {
    	
    	Reporter.log("Verify error message of '" + errorMessage + "' is present.");
    	errorChecking.VerifyErrorMessagePresent(errorMessage);
    }
    
    public void VerifyRequiredFields(List<String> allFields) throws Exception {
    	
    	for (String field : allFields) {
    		Reporter.log("Verify the '" + field + "' is present as a required field.");
    		RequiredField_Ast(field).isDisplayed();
    	}
    }
    public void VerifyPostTitle(String postName) throws Exception {
    	
    	Reporter.log("Verify the post titled '" + postName + "' is present.");
    	Assert.assertTrue(PageTitle.getText().contains(postName));
    	
    }

    public void VerifyPageContentPresent(List<String> txtItems) throws Exception {

    	Thread.sleep(500); //stale element exception
        for (final String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present on the page.");
        	try {
        		wait.until(new ExpectedCondition<Boolean>() {
            		public Boolean apply(WebDriver webDriver) {
            			return Body_Txt.getText().contains(text);
           		 	}
            	});
        	}
        	catch (Exception e) {
        		Assert.fail("Page content with text '" + text + "' is not present.");
        	}
        }
    }

    public void VerifyPageContentNotPresent(List<String> txtItems) throws Exception {

    	Thread.sleep(500); //stale element exception
        for (final String text : txtItems) {
        	
        	Reporter.log("Verify the text '" + text + "' is NOT present on the page.");
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			return !Body_Txt.getText().contains(text);
       		 	}
        	});
        }
    }
    
    public void VerifySourceInPage(List<String> srcItems) throws Exception {
    	
    	Thread.sleep(500); //stale element exception
    	final String pageSrc = webDriver.getPageSource();
    	
    	for (final String source : srcItems) {
    		Reporter.log("Verify '" + source + "' is present in page source.");
    		try {
    			wait.until(new ExpectedCondition<Boolean>() {
            		public Boolean apply(WebDriver webDriver) {
            			return pageSrc.contains(source);
           		 	}
            	});
    		}
    		catch (Exception e) {
    			Assert.fail("Page source of '" + source + "' is not present.");
    		}
    	}
    }
    
    public void VerifySourceNotInPage(final String scriptSrc) throws Exception {
    	
    	Reporter.log("Verify '" + scriptSrc + "' is not present in page source.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return !webDriver.getPageSource().contains(scriptSrc);
   		 	}
    	});
    	
    }
    
    public void Scroll(String scrollCount) throws Exception {
    	
    	Reporter.log("Scroll by '" + scrollCount + "'.");
    	webDriver.executeScript("window.scrollBy(0," + scrollCount + ");"); 
    }
    
    public void WaitForThrobberNotPresent() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	Thread.sleep(1000);
    	for (int I = 0; I < 30; I++) {
    		if (I == 30) { Assert.fail("Throbber is still present after timeout."); }
    		try {
    			Throbber_Img.getLocation();
    		}
    		catch (Exception e) {
    			break;
    		}
    	}
    	Thread.sleep(500);
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
}

