package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Logo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: March 19, 2014
 *********************************************/

public class Logo {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Logo(final Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "div[id='edit-field-logo-und-0'] img")
    private WebElement Logo_Img;
    
    @FindBy(how = How.ID, using = "edit-field-cover-logo-und-0-edit")
    private WebElement Edit_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-logo-und-0-select")
    private WebElement Select_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Logo contains '" + imageSrc + "'.");
    	Assert.assertTrue(Logo_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			Logo_Img);
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Logo 'Edit' button.");
    	Edit_Btn.click();
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Logo 'Select' button.");
    	Select_Btn.click();
    }
    
    
}

