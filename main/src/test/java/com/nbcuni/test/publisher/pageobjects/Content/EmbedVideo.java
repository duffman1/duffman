package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Embed MPX Video Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 13, 2015
 *********************************************/

public class EmbedVideo {

	private Driver webDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public EmbedVideo(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By MPXVideoURL_Txb = By.xpath("//label[text()='MPX Video URL']/..//input");
    
    private By YouTubeVideoURL_Txb = By.xpath("//label[text()='Paste YouTube Video URL']/..//input");
    
    private By Height_Txb = By.xpath("//label[text()='Height']/..//input");
    
    private By Width_Txb = By.xpath("//label[text()='Width']/..//input");
    
    private By OK_Btn = By.xpath("//span[text()='OK']");
    
    private By Video_Frm(String videoSrc, String height, String width) {
    	return By.xpath("//iframe[contains(@src, '" + videoSrc + "')][@height='" + height + "'][@width='" + width + "']");
    }
    
    private By WYSIWYGSynopsis_Frm = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    
    private By Synopsis_Frm = By.xpath("//iframe[@id='edit-body-und-0-value_ifr']");
    
    private By EmbededMPXVideo_Tag(String index) {
    	return By.xpath("(//mpx[@class='mpx-video'])[" + index + "]");
    }
    
    private By EmbededYouTubeVideo_Tag(String index) {
    	return By.xpath("(//youtube[@class='google-youtube'])[" + index + "]");
    }
    
    private By EmbeddedImage_Img(String index) {
    	return By.xpath("(//img[@class='media-element file-full'])[" + index + "]");
    }
    
    
    //PAGE OBJECT METHODS
    public void SwitchToSynopsisFrm() throws Exception {
    	
    	webDriver.switchTo().frame(waitFor.OneElementOrAnotherPresent(Synopsis_Frm, WYSIWYGSynopsis_Frm));
    
    }
    
    public void EnterMPXVideoURL(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'MPX Video URL' text box.");
    	interact.Type(waitFor.ElementVisible(MPXVideoURL_Txb), url);
    	
    }
    
    public void EnterYouTubeVideoURL(String url) throws Exception {
    	
    	Reporter.log("Enter '" + url + "' in the 'Paste YouTube Video URL' text box.");
    	interact.Type(waitFor.ElementVisible(YouTubeVideoURL_Txb), url);
    	
    }

    public void EnterHeight(String height) throws Exception {
    	
    	Reporter.log("Enter '" + height + "' in the 'Height' text box.");
    	interact.Type(waitFor.ElementVisible(Height_Txb), height);
    	
    }
    
    public void EnterWidth(String width) throws Exception {
    	
    	Reporter.log("Enter '" + width + "' in the 'Width' text box.");
    	interact.Type(waitFor.ElementVisible(Width_Txb), width);
    	
    }
    
    public void ClickOkBtn() throws Exception {
    	
    	Reporter.log("Click the 'OK' button.");
    	interact.Click(waitFor.ElementVisible(OK_Btn));
    	waitFor.ElementNotVisible(Width_Txb);
    	
    }
    
    public void VerifyVideoPresent(String videoSrc, String height, String width) throws Exception {
    	
    	Reporter.log("Verify the youtube video with src '" + videoSrc + "', height '" + height + "', and '" + width + "' is visible.");
    	waitFor.ElementVisible(Video_Frm(videoSrc, height, width));
    	
    }
    
    @SuppressWarnings("deprecation")
	public void RightClickEditMPXVideoBtn(String index) throws Exception {
    	
    	this.SwitchToSynopsisFrm();
    	
    	Reporter.log("Right click and select the 'Edit MPX Video' option for video with index '" + index + "'.");
    	Thread.sleep(1000);
    	new Actions(webDriver).contextClick(waitFor.ElementVisible(EmbededMPXVideo_Tag(index)))
    		.pause(1000).sendKeys(Keys.ARROW_DOWN).pause(1000).sendKeys(Keys.ARROW_DOWN).pause(1000)
    		.sendKeys(Keys.ARROW_DOWN).pause(1000).sendKeys(Keys.ARROW_DOWN)
    		.sendKeys(Keys.RETURN).build().perform();
    	
    	webDriver.switchTo().defaultContent();
    	
    }
    
    @SuppressWarnings("deprecation")
	public void RightClickEditYouTubeVideoBtn(String index) throws Exception {
    	
    	this.SwitchToSynopsisFrm();
    	
    	Reporter.log("Right click and select the 'Edit YouTube Video' option for video with index '" + index + "'.");
    	Thread.sleep(1000);
    	new Actions(webDriver).contextClick(waitFor.ElementVisible(EmbededYouTubeVideo_Tag(index)))
    		.pause(1000).sendKeys(Keys.ARROW_DOWN).pause(1000).sendKeys(Keys.ARROW_DOWN).pause(1000).sendKeys(Keys.ARROW_DOWN)
    		.pause(1000).sendKeys(Keys.ARROW_DOWN).pause(1000).sendKeys(Keys.RETURN).build().perform();
    	
    	webDriver.switchTo().defaultContent();
    	
    }
    
    public void VerifyEmbeddedImagePresent(String index, String src, String width, String height) throws Exception {
    	
    	Reporter.log("Verify image is present with src '" + src + "', width = '" + width + "', and height = '" + height + "'.");
    	waitFor.ElementContainsAttribute(EmbeddedImage_Img(index), "src", src);
    	waitFor.ElementContainsAttribute(EmbeddedImage_Img(index), "width", width);
    	waitFor.ElementContainsAttribute(EmbeddedImage_Img(index), "height", height);
    	waitFor.ImageVisible(waitFor.ElementVisible(EmbeddedImage_Img(index)));
    	
    }
    
    public void VerifyEmbeddedImageAdjustedPresent(String index, String src, String pWidth, String pHeight) throws Exception {
    	
    	Reporter.log("Verify image is present with src '" + src + "', adjusted width = '" + pWidth + "' pixels, and adjusted height = '" + pHeight + "' pixels.");
    	waitFor.ElementContainsAttribute(EmbeddedImage_Img(index), "src", src);
    	waitFor.ElementContainsAttribute(EmbeddedImage_Img(index), "style", "width: " + pWidth + "px; height: " + pHeight + "px;");
    	waitFor.ImageVisible(waitFor.ElementVisible(EmbeddedImage_Img(index)));
    	
    }
    
    public void DoubleClickImagePropertiesBtn(String index) throws Exception {
    	
    	this.SwitchToSynopsisFrm();
    	
    	Reporter.log("Double click and select the 'Image Properties' option.");
    	new Actions(webDriver).doubleClick(waitFor.ElementVisible(EmbeddedImage_Img(index))).build().perform();
    	
    	webDriver.switchTo().defaultContent();
    	
    }
    
}

