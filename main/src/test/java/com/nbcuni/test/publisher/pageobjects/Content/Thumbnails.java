package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Thumbnails Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 28, 2014
 *********************************************/

public class Thumbnails {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public Thumbnails(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private WebElement Thumbnail_Img(String src, String imageIndex) {
    	return webDriver.findElement(By.xpath("(//img[contains(@src, '" + src + "')])[" + imageIndex + "]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void VerifyThumbnailImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Verify the the thumbnail image is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			Thumbnail_Img(imageSrc, imageIndex));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
}

