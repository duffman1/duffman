package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Basic Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class BasicInformation {

    private Driver webDriver;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR
    public BasicInformation(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Basic Information']")
    private WebElement BasicInformation_Tab;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-title')]")
    private WebElement Title_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-summary-und-0-value")
    private WebElement ShortDescription_Txa;
    
    @FindBy(how = How.XPATH, using = "//a[@title='Embed YouTube Video']")
    private WebElement YouTube_Btn;
    
    private WebElement Synopsis_Frm() {
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	WebElement frm = null;
    	try {
    		frm = webDriver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
    	}
    	catch (NoSuchElementException e) {
    		frm = webDriver.findElement(By.xpath("//iframe[@id='edit-body-und-0-value_ifr']"));
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return frm;
    }
    
    private WebElement Synopsis_Txa() {
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	WebElement txa = null;
    	try {
    		txa = webDriver.findElement(By.xpath("//body[contains(@class, 'editable')]"));
    	}
    	catch (NoSuchElementException e) {
    		txa = webDriver.findElement(By.xpath("//body[@id='tinymce']"));
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return txa;
    }
    
    @FindBy(how = How.XPATH, using = "//a[contains(@id, 'cover')][text()='Select']")
    private WebElement CoverSelect_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-media-items-und-add-more']")
    private WebElement MediaItemsSelect_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='media-thumbnail']/img")
    private WebElement Cover_Img;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-episode-number-und-0-value']")
    private WebElement EpisodeNumber_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-season-id-und-0-value']")
    private WebElement SeasonNumber_Txb;
    
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Text format')]/../select")
    private WebElement TextFormat_Ddl;
    
    private WebElement CustomSelect_Ddl(String label) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + label + "')]/../select"));
    }
    
    private WebElement CustomButton_Btn(String label) {
    	return webDriver.findElement(By.xpath("//label[contains(text(), '" + label + "')]/../..//a[text()='Select']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickBasicInformationTab() throws Exception {
    	
    	Reporter.log("Scroll to and click the 'Basic Information' tab.");
    	webDriver.executeScript("window.scrollBy(0,-500);"); 
    	BasicInformation_Tab.click();
    }
    
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	Title_Txb.clear();
    	Title_Txb.sendKeys(title);
    }
    
    public void EnterShortDescription(String shortDescription) throws Exception {
    	
    	Reporter.log("Enter '" + shortDescription + "' in the 'Short Description' text area.");
    	ShortDescription_Txa.clear();
    	ShortDescription_Txa.sendKeys(shortDescription);
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
        Body_Txa.clear();
        Body_Txa.sendKeys(body);
        webDriver.switchTo().defaultContent();
        
        return body;
    }
    
    public void EnterSynopsis(String synopsis) throws Exception {

        Reporter.log("Switch to the Synopsis frame.");
    	webDriver.switchTo().frame(Synopsis_Frm());

        Reporter.log("Enter '" + synopsis + "' into the 'Body' text area.");
        WebElement Body_Txa = Synopsis_Txa();
        Body_Txa.clear();
        Body_Txa.sendKeys(synopsis);
        webDriver.switchTo().defaultContent();
        
    }
    
    public void ClickCoverSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cover' select button.");
    	webDriver.executeScript("window.scrollBy(0,-500);");
    	CoverSelect_Btn.click();
    }
    
    public void ClickMediaItemsSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Media Items Select' button.");
    	Thread.sleep(250);
    	MediaItemsSelect_Btn.click();
    }
    
    public void VerifyCoverImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify the Cover image src contains '" + imageSrc + "'.");
    	Assert.assertTrue(Cover_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Wait for the image to fully load.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 60) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			Cover_Img);
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
        
    }
    
    public void EnterEpisodeNumber(String episodeNum) throws Exception {
    	
    	Reporter.log("Enter '" + episodeNum + "' in the 'Episode Number' text box.");
    	EpisodeNumber_Txb.sendKeys(episodeNum);
    }
    
    public void EnterSeasonNumber(String seasonNum) throws Exception {
    	
    	Reporter.log("Enter '" + seasonNum + "' in the 'Season Number' text box.");
    	SeasonNumber_Txb.sendKeys(seasonNum);
    }
    
    public void SelectCustomField(String label, String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the '" + label + "' drop down list.");
    	new Select(CustomSelect_Ddl(label)).selectByVisibleText(option);
    }
    
    public void SelectTextFormat(String option) throws Exception {
    	
    	Reporter.log("Select the '" + option + "' from the 'Text format' drop down list.");
    	new Select(TextFormat_Ddl).selectByVisibleText(option);
    }
    
    public void ClickCustomBtn(String label) throws Exception {
    	
    	Reporter.log("Click the 'Select' button for '" + label + "'.");
    	CustomButton_Btn(label).click();
    }
    
    public void ClickYoutubeBtn() throws Exception {
    	
    	Reporter.log("Click the embed youtube button.");
    	YouTube_Btn.click();
    }
    
}

