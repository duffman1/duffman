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
 * publisher.nbcuni.com Cover Item Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 22, 2014
 *********************************************/

public class CoverItem {

    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverItem(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        waitFor = new WaitFor(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='media-item']/img")
    private WebElement CoverItem_Img;
    
    @FindBy(how = How.ID, using = "edit-field-cover-item-und-0-select")
    private WebElement Select_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-cover-item-und-0-edit")
    private WebElement Edit_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify that img source of the Cover Item contains '" + imageSrc + "'.");
    	Assert.assertTrue(CoverItem_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	waitFor.ImageVisible(CoverItem_Img);
    	
    }
    
    public void ClickSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Select' button.");
    	Select_Btn.click();
    }

    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the 'Edit' button.");
    	waitFor.ElementVisible(Edit_Btn).click();
    	
    }
    
    
}

