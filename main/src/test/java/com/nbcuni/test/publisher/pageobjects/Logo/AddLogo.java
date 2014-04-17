package com.nbcuni.test.publisher.pageobjects.Logo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Add Logo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 20, 2014
 *********************************************/

public class AddLogo {

	private Driver webDriver;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddLogo(Driver webDriver) {
    	this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-title")
    private WebElement Title_Txb;
    
    @FindBy(how = How.ID, using = "edit-start-datepicker-popup-0")
    private WebElement StartDate_Txb;
    
    @FindBy(how = How.ID, using = "edit-start-timeEntry-popup-1")
    private WebElement StartTime_Txb;
    
    @FindBy(how = How.ID, using = "edit-end-datepicker-popup-0")
    private WebElement EndDate_Txb;
    
    @FindBy(how = How.ID, using = "edit-end-timeEntry-popup-1")
    private WebElement EndTime_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-logo-und-0-upload")
    private WebElement BrowseToFile_Upl;
    
    @FindBy(how = How.ID, using = "edit-field-logo-und-0-upload-button")
    private WebElement Upload_Btn;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement Save_Btn;
    
    private WebElement File_Lnk(String fileName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + fileName + "']"));
    }
    
    @FindBy(how = How.CSS, using = "div[class='image-preview'] img")
    private WebElement File_Img;
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	Title_Txb.sendKeys(title);
    }
    
    public void EnterStartDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Start Date' text box.");
    	StartDate_Txb.sendKeys(date);
    }
    
    public void EnterStartTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'Start Time' text box.");
    	webDriver.executeScript("arguments[0].setAttribute('value', '" + time.replace("PM", "pm").replace("AM", "am") + "');", StartTime_Txb);
    }
    
    public void EnterEndDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'End Date' text box.");
    	EndDate_Txb.sendKeys(date);
    }
    
    public void EnterEndTime(String time) throws Exception {
    	
    	Reporter.log("Enter '" + time + "' in the 'End Time' text box.");
    	webDriver.executeScript("arguments[0].setAttribute('value', '" + time.replace("PM", "pm").replace("AM", "am") + "');", EndTime_Txb);
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for file upload.");
    	//webDriver.setFileDetector(new LocalFileDetector());
    	BrowseToFile_Upl.sendKeys(pathToFile);
    	
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	Upload_Btn.click();
    	
    }
    
    public void WaitForFileUploaded(String fileName) throws Exception {
    	
    	Reporter.log("Wait for the file link to be visible after upload.");
    	File_Lnk(fileName).isDisplayed();
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(File_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            				File_Img);
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
}

