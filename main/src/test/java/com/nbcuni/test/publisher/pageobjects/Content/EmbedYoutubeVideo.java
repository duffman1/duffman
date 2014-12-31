package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Embed Youtube Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: September 17, 2014
 *********************************************/

public class EmbedYoutubeVideo {

	private Driver webDriver;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmbedYoutubeVideo(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//label[text()='Paste YouTube Video URL']/..//input")
    private WebElement PasteYouTubeVideoURL_Txb;
    
    @FindBy(how = How.XPATH, using = "//label[text()='Height']/..//input")
    private WebElement Height_Txb;
    
    @FindBy(how = How.XPATH, using = "//label[text()='Width']/..//input")
    private WebElement Width_Txb;
    
    @FindBy(how = How.XPATH, using = "//span[text()='OK']")
    private WebElement OK_Btn;
    
    private WebElement Video_Frm(String videoSrc, String height, String width) {
    	return webDriver.findElement(By.xpath("//iframe[@src='" + videoSrc + "'][@height='" + height + "'][@width='" + width + "']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterYoutubeURL(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'Paste YouTube Video URL' text box.");
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return PasteYouTubeVideoURL_Txb.isDisplayed();
   		 	}
    	});
    	PasteYouTubeVideoURL_Txb.sendKeys(url);
    }
    
    public void EnterHeight(String height) throws Exception {
    	
    	Reporter.log("Enter '" + height + "' in the 'Height' text box.");
    	Height_Txb.clear();
    	Height_Txb.sendKeys(height);
    }
    
    public void EnterWidth(String width) throws Exception {
    	
    	Reporter.log("Enter '" + width + "' in the 'Width' text box.");
    	Width_Txb.clear();
    	Width_Txb.sendKeys(width);
    }
    
    public void ClickOkBtn() throws Exception {
    	
    	Reporter.log("Click the 'OK' button.");
    	OK_Btn.click();
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return !PasteYouTubeVideoURL_Txb.isDisplayed();
   		 	}
    	});
    }
    
    public void VerifyVideoPresent(String videoSrc, String height, String width) throws Exception {
    	
    	Reporter.log("Verify the youtube video with src '" + videoSrc + "', height '" + height + "', and '" + width + "' is visible.");
    	wait.until(ExpectedConditions.visibilityOf(Video_Frm(videoSrc, height, width)));
    	
    }
    
}

