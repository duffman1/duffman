package com.nbcuni.test.publisher.pageobjects.FileTypes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Manage File Display Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 24, 2014
 *********************************************/

public class ManageFileDisplay {

	@SuppressWarnings("unused")
	private Driver webDriver;
    private Config config;
    private Integer timeout;
    private Interact interact;
    private WaitFor waitFor;
    
    //PAGE OBJECT CONSTRUCTOR
    public ManageFileDisplay(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        interact = new Interact(webDriver, timeout);
        waitFor = new WaitFor(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Image_Cbx = By.id("edit-displays-file-field-image-status");
    
    private By PubMPXImage_Cbx = By.id("edit-displays-pub-mpx-image-status");
    
    private By PubMPXVideo_Cbx = By.id("edit-displays-pub-mpx-video-by-guid-status");
    
    private By PubMPXVideoDeprecated_Cbx = By.id("edit-displays-pub-mpx-video-status");
    
    private By PubMPXVideoPlayerForAccount_Ddl = By.xpath("//select[contains(@id, 'edit-displays-pub-mpx-video-by-guid-settings-pub-mpx-video-player')]");
    
    private By PubMPXVideoPlayerForAccountDeprecated_Ddl = By.xpath("//select[contains(@id, 'edit-displays-pub-mpx-video-settings-pub-mpx-video-player')]");
    
    private By PubMPXImageStyle_Ddl = By.id("edit-displays-pub-mpx-image-settings-pub-mpx-image-image-style");
    
    private By PubMPXVideo_Lnk = By.xpath("//a/strong[text()='Pub MPX Video']");
    
    private By PubMPXImage_Lnk = By.xpath("//a/strong[text()='Pub MPX Image']");
    
    private By ImageStyle_Ddl = By.id("edit-displays-file-field-image-settings-image-style");
    
    private By SaveConfiguration_Btn = By.id("edit-actions-submit");
    
    
    //PAGE OBJECT METHODS
    public void CheckImageCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(Image_Cbx);
    	if (!element.isSelected()) {
    		Reporter.log("Check the 'Image' checkbox");
    		interact.Click(element);
    	}
    }

    public void CheckPubMPXVideoCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXVideo_Cbx);
    	if (!element.isSelected()) {
    		Reporter.log("Check the 'Pub MPX Video' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void UnCheckPubMPXVideoCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXVideo_Cbx);
    	if (element.isSelected()) {
    		Reporter.log("Un-Check the 'Pub MPX Video' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void UnCheckPubMPXVideoDeprecatedCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXVideoDeprecated_Cbx);
    	if (element.isSelected()) {
    		Reporter.log("Un-check the ' Pub MPX Video (Deprecated)' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void CheckPubMPXVideoDeprecatedCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXVideoDeprecated_Cbx);
    	if (!element.isSelected()) {
    		Reporter.log("Check the ' Pub MPX Video (Deprecated)' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void CheckPubMPXImageCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXImage_Cbx);
    	if (!element.isSelected()) {
    		Reporter.log("Check the 'Pub MPX Image' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void UnCheckPubMPXImageCbx() throws Exception {
    	
    	WebElement element = waitFor.ElementVisible(PubMPXImage_Cbx);
    	if (element.isSelected()) {
    		Reporter.log("Un-Check the 'Pub MPX Image' checkbox");
    		interact.Click(element);
    	}
    }
    
    public void ClickPubMPXVideoLnk() throws Exception {
    	
    	Reporter.log("Click the 'Pub MPX Video' link.");
    	interact.Click(waitFor.ElementVisible(PubMPXVideo_Lnk));
    	
    }
    
    public void ClickPubMPXImageLnk() throws Exception {
    	
    	Reporter.log("Click the 'Pub MPX Image' link.");
    	interact.Click(waitFor.ElementVisible(PubMPXImage_Lnk));
    	
    }
    
    public void SelectMPXVideoPlayer(String playerName) throws Exception {
    	
    	Reporter.log("Select the '" + playerName + "' player from the 'Pub MPX Video Player for Account...' drop down list.");
    	WebElement element = waitFor.ElementVisible(PubMPXVideoPlayerForAccount_Ddl);
        interact.Click(element);

        List<WebElement> options = element.findElements(By.tagName("option"));
        for(WebElement option : options){
            String optTxt = option.getText();
            if(optTxt.contains(playerName)){
                option.click();
                break;
            }
        }
    }
    
    public void SelectMPXVideoPlayerDeprecated(String playerName) throws Exception {
    	
    	Reporter.log("Select the '" + playerName + "' player from the 'Pub MPX Video Player for Account...' drop down list.");
    	WebElement element = waitFor.ElementVisible(PubMPXVideoPlayerForAccountDeprecated_Ddl);
        interact.Click(element);

        List<WebElement> options = element.findElements(By.tagName("option"));
        for(WebElement option : options){
            String optTxt = option.getText();
            if(optTxt.contains(playerName)){
                option.click();
                break;
            }
        }
    }
    
    public void SelectMPXImageStyle(String style) throws Exception {
    	
    	Reporter.log("Select the '" + style + "' option from the 'Pub MPX Image Style' drop down list.");
    	interact.Select(waitFor.ElementVisible(PubMPXImageStyle_Ddl), style);
    	
    }
    
    public void SelectImageStyle(String style) throws Exception {
    	
    	Reporter.log("Select the '" + style + "' option from the 'Image Style' drop down list.");
    	interact.Select(waitFor.ElementVisible(ImageStyle_Ddl), style);
    	
    }
    
    public void ClickSaveConfigurationBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	interact.Click(waitFor.ElementVisible(SaveConfiguration_Btn));
    	
    }
    
    
}

