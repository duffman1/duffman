package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Media Items Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class MediaItems {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public MediaItems(CustomWebDriver webDriver) {
        MediaItems.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//div[@class='media-thumbnail']/img")
    private static WebElement MediaItem_Img;
    
    @FindBy(how = How.XPATH, using = "//a[@id='edit-field-media-items-und-0-edit']")
    private static WebElement Edit_Btn;
    
    
    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Media Item contains '" + imageSrc + "'.");
    	Assert.assertTrue(MediaItem_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			MediaItem_Img);
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the 'Edit' button.");
    	Edit_Btn.click();
    }
    
    
}
