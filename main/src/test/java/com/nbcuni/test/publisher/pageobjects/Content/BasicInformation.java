package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Basic Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class BasicInformation {

    private Driver webDriver;
    private Config config;
    private WaitFor waitFor;
    private Interact interact;
    private Integer timeout;
    
    //PAGE OBJECT CONSTRUCTOR
    public BasicInformation(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    
    //PAGE OBJECT IDENTIFIERS
    private By BasicInformation_Tab = By.xpath("//a/strong[text()='Basic Information']");
    
    private By Title_Txb = By.xpath("//input[contains(@id, 'edit-title')]");
    
    private By ShortDescription_Txa = By.id("edit-field-summary-und-0-value");
    
    private By YouTube_Btn = By.xpath("//a[@title='Embed YouTube Video']");
    
    private By Body_Txa = By.id("edit-body-und-0-value");
    
    private WebElement Synopsis_Frm() throws Exception {
    	return waitFor.OneElementOrAnotherPresent(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"), 
    			By.xpath("//iframe[@id='edit-body-und-0-value_ifr']"));
    }
    
    private WebElement Synopsis_Txa() throws Exception {
    	return waitFor.OneElementOrAnotherPresent(By.xpath("//body[contains(@class, 'editable')]"), 
    			By.xpath("//body[@id='tinymce']"));
    }
    
    private By CoverSelect_Btn = By.xpath("//a[contains(@id, 'cover')][text()='Browse']");
    
    private By MediaItemsSelect_Btn = By.xpath("//a[@id='edit-field-media-items-und-0-browse-button']");
    
    private By Cover_Img = By.xpath("//div[@class='media-thumbnail']/img");
    
    private By EpisodeNumber_Txb = By.xpath("//input[@id='edit-field-episode-number-und-0-value']");
    
    private By SeasonNumber_Txb = By.xpath("//input[@id='edit-field-season-id-und-0-value']");
    
    private By TextFormat_Ddl = By.xpath("//label[contains(text(), 'Text format')]/../select");
    
    private By CustomSelect_Ddl(String label) {
    	return By.xpath("//label[contains(text(), '" + label + "')]/../select");
    }
    
    private By CustomButton_Btn(String label) {
    	return By.id("edit-field-" + label.toLowerCase() + "-und-0-browse-button");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickBasicInformationTab() throws Exception {
    	
    	Reporter.log("Scroll to and click the 'Basic Information' tab.");
    	interact.ScrollToTop();
    	interact.Click(waitFor.ElementVisible(BasicInformation_Tab));
    	
    }
    
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	interact.Type(waitFor.ElementVisible(Title_Txb), title);
    	
    }
    
    public void EnterShortDescription(String shortDescription) throws Exception {
    	
    	Reporter.log("Enter '" + shortDescription + "' in the 'Short Description' text area.");
    	interact.Type(waitFor.ElementVisible(ShortDescription_Txa), shortDescription);
    	
    }

    public String EnterSynopsis() throws Exception {

        Reporter.log("Switch to the Synopsis frame.");
    	webDriver.switchTo().frame(Synopsis_Frm());

        Reporter.log("Enter a randomized text string into the 'Body' text area.");
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        
        WebElement Body_Txa = Synopsis_Txa();
        interact.Type(Body_Txa, body);
        
        webDriver.switchTo().defaultContent();
        
        return body;
        
    }
    
    public void EnterSynopsis(String synopsis) throws Exception {

        Reporter.log("Switch to the Synopsis frame.");
    	webDriver.switchTo().frame(Synopsis_Frm());

        Reporter.log("Enter '" + synopsis + "' into the 'Body' text area.");
        WebElement Body_Txa = Synopsis_Txa();
        interact.Type(Body_Txa, synopsis);
        
        webDriver.switchTo().defaultContent();
        
    }
    
    public void EnterBody(String bodyTxt) throws Exception {

        Reporter.log("Enter '" + bodyTxt + "' into the 'Body' text area.");
        interact.Type(waitFor.ElementVisible(Body_Txa), bodyTxt);
        
    }
    
    public void ClickCoverSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cover' select button.");
    	interact.ScrollToTop();
    	interact.Click(waitFor.ElementVisible(CoverSelect_Btn));
    	
    }
    
    public void ClickMediaItemsSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Media Items Select' button.");
    	interact.Click(waitFor.ElementVisible(MediaItemsSelect_Btn));
    	
    }
    
    public void VerifyCoverImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify the Cover image src contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementContainsAttribute(Cover_Img, "src", imageSrc);
    	
    	Reporter.log("Wait for the image to fully load.");
    	waitFor.ImageVisible(ele);
        
    }
    
    public void EnterEpisodeNumber(String episodeNum) throws Exception {
    	
    	Reporter.log("Enter '" + episodeNum + "' in the 'Episode Number' text box.");
    	interact.Type(waitFor.ElementVisible(EpisodeNumber_Txb), episodeNum);
    	
    }
    
    public void EnterSeasonNumber(String seasonNum) throws Exception {
    	
    	Reporter.log("Enter '" + seasonNum + "' in the 'Season Number' text box.");
    	interact.Type(waitFor.ElementVisible(SeasonNumber_Txb), seasonNum);
    	
    }
    
    public void SelectCustomField(String label, String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the '" + label + "' drop down list.");
    	interact.Select(waitFor.ElementVisible(CustomSelect_Ddl(label)), option);
    	
    }
    
    public void SelectTextFormat(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Text format' drop down list.");
    	interact.Select(waitFor.ElementVisible(TextFormat_Ddl), option);
    	
    }
    
    public void ClickCustomBtn(String label) throws Exception {
    	
    	Reporter.log("Click the 'Select' button for '" + label + "'.");
    	interact.Click(waitFor.ElementVisible(CustomButton_Btn(label)));
    	
    }
    
    public void ClickYoutubeBtn() throws Exception {
    	
    	Reporter.log("Click the embed youtube button.");
    	interact.Click(waitFor.ElementVisible(YouTube_Btn));
    	
    }
    
}

