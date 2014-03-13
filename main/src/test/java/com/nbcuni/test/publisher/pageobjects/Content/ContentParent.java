package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ContentParent {

    private static CustomWebDriver webDriver;
    private static ErrorChecking errorChecking;
    
    //PAGE OBJECT CONSTRUCTOR
    public ContentParent(CustomWebDriver webDriver, AppLib applib) {
        ContentParent.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        errorChecking = new ErrorChecking(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private static WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-cancel")
    private static WebElement Cancel_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages status']")
    private static WebElement Message_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages warning']")
    private static WebElement Warning_Ctr;
    
    @FindBy(how = How.XPATH, using = "//div[@class='messages error']")
    private static WebElement Error_Ctr;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Edit Draft']")
    private static WebElement EditDraft_Tab;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Revisions']")
    private static WebElement Revisions_Tab;
    
    @FindBy(how = How.ID, using = "page-title")
    private static WebElement PageTitle;
    
    @FindBy(how = How.XPATH, using = "//a[text()='View']")
    private static WebElement View_Tab;
    
    @FindBy(how = How.XPATH, using = "//body")
    private static WebElement Body_Txt;
    
    private static WebElement RequiredField_Ast(String field) {
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
    	
    	Reporter.log("Verify there are no errors present.");
    	errorChecking.VerifyNoMessageErrorsPresent();
    }
    
    public void VerifyMessageWarning(String warningTxt) throws Exception {
    	
    	Reporter.log("Verify warning message '" + warningTxt + "' is present.");
    	Assert.assertTrue(Warning_Ctr.getText().contains(warningTxt));
    	
    	Reporter.log("Verify there are no errors present.");
    	errorChecking.VerifyNoMessageErrorsPresent();
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
        String bodyTxt = Body_Txt.getText();

        for (String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present on the page.");
        	if (!bodyTxt.contains(text)) {
        		Assert.fail("The text '" + text + "' is not present on the page.");
        	}
            
        }
    }

    public void VerifyPageContentNotPresent(List<String> txtItems) throws Exception {

    	Thread.sleep(500); //stale element exception
        String bodyTxt = Body_Txt.getText();
        
        for (String text : txtItems) {
        	
        	Reporter.log("Verify the text '" + text + "' is NOT present on the page.");
            Assert.assertFalse(bodyTxt.contains(text));

        }
    }
    
    public void Scroll(String scrollCount) throws Exception {
    	
    	Reporter.log("Scroll by '" + scrollCount + "'.");
    	webDriver.executeScript("window.scrollBy(0," + scrollCount + ");"); 
    }
    
}

