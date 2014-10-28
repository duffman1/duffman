package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cover Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 28, 2014
 *********************************************/

public class CoverMedia {

    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverMedia(final Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "div[id='edit-field-cover-media-und-0'] img")
    private WebElement CoverMedia_Img;
    
    @FindBy(how = How.ID, using = "edit-field-cover-media-und-0-edit")
    private WebElement Edit_Btn;
    
    @FindBy(how = How.CSS, using = "a[id*= 'cover-media-und-0-select']")
    private WebElement Select_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Cover Media contains '" + imageSrc + "'.");
    	Assert.assertTrue(CoverMedia_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(CoverMedia_Img);
    	
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Media 'Edit' button.");
    	waitFor.ElementVisible(Edit_Btn).click();
    	
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Media 'Select' button.");
    	waitFor.ElementVisible(Select_Btn).click();
    
    }
    
    
}

