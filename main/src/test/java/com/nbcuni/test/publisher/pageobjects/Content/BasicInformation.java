package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Basic Information Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class BasicInformation {

    private static CustomWebDriver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public BasicInformation(CustomWebDriver webDriver) {
        BasicInformation.webDriver = webDriver;
        
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='Basic Information']")
    private static WebElement BasicInformation_Tab;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-title')]")
    private static WebElement Title_Txb;
    
    @FindBy(how = How.XPATH, using = "//iframe[@id='edit-body-und-0-value_ifr']")
    private static WebElement Synopsis_Frm;
    
    @FindBy(how = How.XPATH, using = "//body[@id='tinymce']")
    private static WebElement Synopsis_Txa;
    
    @FindBy(how = How.XPATH, using = "//a[contains(@id, 'cover')][text()='Select']")
    private static WebElement CoverSelect_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-media-items-und-add-more']")
    private static WebElement MediaItemsSelect_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='media-thumbnail']/img")
    private static WebElement Cover_Img;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-episode-number-und-0-value']")
    private static WebElement EpisodeNumber_Txb;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-field-season-id-und-0-value']")
    private static WebElement SeasonNumber_Txb;
    
    
    //PAGE OBJECT METHODS
    public void ClickBasicInformationTab() throws Exception {
    	
    	Reporter.log("Scroll to and click the 'Basic Information' tab.");
    	webDriver.executeScript("window.scrollBy(0,-500);"); 
    	BasicInformation_Tab.click();
    }
    
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	Title_Txb.sendKeys(title);
    }
    
    public String EnterSynopsis() throws Exception{

        Reporter.log("Switch to the Synopsis frame.");
    	webDriver.switchTo().frame(Synopsis_Frm);

        Reporter.log("Enter a randomized text string into the 'Body' text area.");
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        
        Synopsis_Txa.clear();
        Synopsis_Txa.sendKeys(body);
        webDriver.switchTo().defaultContent();
        
        return body;
    }
    
    public void ClickCoverSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cover' select button.");
    	CoverSelect_Btn.click();
    }
    
    public void ClickMediaItemsSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Media Items Select' button.");
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
    
}

