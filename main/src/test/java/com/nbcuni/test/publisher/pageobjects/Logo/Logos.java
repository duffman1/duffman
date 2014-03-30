package com.nbcuni.test.publisher.pageobjects.Logo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Logos Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 20, 2014
 *********************************************/

public class Logos {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public Logos(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "img[alt='Home']")
    private WebElement HomeLogo_Img;
    
    private WebElement Logo_Img(String logoTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//img"));
    }
    
    private WebElement EditExtendMenu_Btn(String logoTitle) {
		return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='operations']"));
	}
	
	private WebElement EditMenuDelete_Btn(String logoTitle) {
		return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='Delete']"));
	}
    
    //PAGE OBJECT METHODS
	public void ClickEditExtendMenuBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' extend menu button for logo titled '" + logoTitle + "'.");
    	EditExtendMenu_Btn(logoTitle).click();
    	
    }
    
    public void ClickEditMenuDeleteBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for logo titled '" + logoTitle + "'.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			EditMenuDelete_Btn(logoTitle))).click();
    }
    
    public void VerifyHomePageLogoImgPresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(HomeLogo_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++) {
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            				HomeLogo_Img);
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }

    public void VerifyLogoImgPresent(String logoTitle, String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(Logo_Img(logoTitle).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++) {
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            				Logo_Img(logoTitle));
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    
}

