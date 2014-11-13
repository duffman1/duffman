package com.nbcuni.test.publisher.pageobjects.Logo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Add Logo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 20, 2014
 *********************************************/

public class AddLogo {

	private Driver webDriver;
	private Config config;
	private Interact interact;
	private WaitFor waitFor;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddLogo(Driver webDriver) {
    	this.webDriver = webDriver;
        config = new Config();
        Integer timeout = config.getConfigValueInt("WaitForWaitTime");
        interact = new Interact(webDriver, timeout);
        waitFor = new WaitFor(webDriver, timeout);
        webDriver.setFileDetector(new LocalFileDetector());
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Title_Txb = By.id("edit-title");
    
    private By StartDate_Txb = By.id("edit-start-datepicker-popup-0");
    
    private By StartTime_Txb = By.id("edit-start-timeEntry-popup-1");
    
    private By EndDate_Txb = By.id("edit-end-datepicker-popup-0");
    
    private By EndTime_Txb = By.id("edit-end-timeEntry-popup-1");
    
    private By BrowseToFile_Upl = By.id("edit-field-logo-und-0-upload");
    
    private By Upload_Btn = By.id("edit-field-logo-und-0-upload-button");
    
    private By Save_Btn = By.id("edit-submit");
    
    private By File_Lnk(String fileName) {
    	return By.xpath("//a[text()='" + fileName + "']");
    }
    
    private By File_Img = By.cssSelector("div[class='image-preview'] img");
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	interact.Type(waitFor.ElementVisible(Title_Txb), title);
    	
    }
    
    public void EnterStartDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Start Date' text box.");
    	interact.Type(waitFor.ElementVisible(StartDate_Txb), date);
    	
    }
    
    public void EnterStartTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'Start Time' text box.");
    	webDriver.executeScript("arguments[0].setAttribute('value', '" 
    			+ time.replace("PM", "pm").replace("AM", "am") + "');", waitFor.ElementVisible(StartTime_Txb));
    	
    }
    
    public void EnterEndDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'End Date' text box.");
    	interact.Type(waitFor.ElementVisible(EndDate_Txb), date);
    	
    }
    
    public void EnterEndTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'End Time' text box.");
    	webDriver.executeScript("arguments[0].setAttribute('value', '" 
    			+ time.replace("PM", "pm").replace("AM", "am") + "');", waitFor.ElementVisible(EndTime_Txb));
    	
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for file upload.");
    	waitFor.ElementPresent(BrowseToFile_Upl).sendKeys(pathToFile);
    	
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	interact.Click(waitFor.ElementVisible(Upload_Btn));
    	
    }
    
    public void WaitForFileUploaded(String fileName) throws Exception {
    	
    	Reporter.log("Wait for the file link to be visible after upload.");
    	waitFor.ElementVisible(File_Lnk(fileName));
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
    }
    
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify the file image '" + imageSrc + "' is present.");
    	WebElement ele = waitFor.ElementVisible(File_Img);
    	Assert.assertTrue(ele.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
}

