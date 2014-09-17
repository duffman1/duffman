package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Embed Youtube Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: September 16, 2014
 *********************************************/

public class EmbedYoutubeVideo {

    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmbedYoutubeVideo(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        new Config();
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//label[text()='Paste YouTube Video URL']/..//input")
    private WebElement PasteYouTubeVideoURL_Txb;
    
    @FindBy(how = How.XPATH, using = "//span[text()='OK']")
    private WebElement OK_Btn;
    
    
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
    
    public void ClickOkBtn() throws Exception {
    	
    	Reporter.log("Click the 'OK' button.");
    	OK_Btn.click();
    	wait.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return !PasteYouTubeVideoURL_Txb.isDisplayed();
   		 	}
    	});
    }
    
}

