package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
 * publisher.nbcuni.com Select File Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 16, 2013
 *********************************************/

public class SelectFile {

    private static CustomWebDriver webDriver;
    private AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public SelectFile(CustomWebDriver webDriver, AppLib applib) {
        SelectFile.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//iframe[@id='mediaBrowser']")
    private static WebElement SelectFile_Frm;
    
    @FindBy(how = How.XPATH, using = "//a[@title='View Library']")
    private static WebElement ViewLibrary_Btn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='media-thumbnail']/img")
    private static WebElement Default_Img;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Submit']")
    private static WebElement Submit_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-upload-upload']")
    private static WebElement BrowseToFile_Upl;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-upload-upload-button')]")
    private static WebElement Upload_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-next']")
    private static WebElement Next_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-scheme-public']")
    private static WebElement PublicLocalFiles_Rdb;
    
    @FindBy(how = How.XPATH, using = "//div[@id='file_image_form_group_image_top_wrapper']//img")
    private static WebElement File_Img;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private static WebElement Save_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[@id='edit-upload_browse']")
    private static WebElement AddFiles_Lnk;
    
    private WebElement File_Lnk(String fileName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + fileName + "']"));
    }
    
    //PAGE OBJECT IDENTIFIERS
    public void SwitchToSelectFileFrm() throws Exception {
    	
    	Reporter.log("Switch to the select file frame.");
    	webDriver.switchTo().frame(SelectFile_Frm);
    	
    }
    
    public void ClickViewLibraryBtn() throws Exception {
    	
    	Reporter.log("Click the 'View Library' button.");
    	ViewLibrary_Btn.click();
    	
    }
    
    public void ClickDefaultImg() throws Exception {
    	
    	Reporter.log("Click on the default img.");
    	Default_Img.click();
    	
    }
    
    public void ClickSubmitBtn() throws Exception {
    	
    	Reporter.log("Click on the 'Submit' button.");
    	Submit_Btn.click();
    	
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for file upload.");
    	webDriver.setFileDetector(new LocalFileDetector());
    	BrowseToFile_Upl.sendKeys(pathToFile);
    	
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	Upload_Btn.click();
    	
    }
    
    public void WaitForFileUploaded(String fileName) throws Exception {
    	
    	Reporter.log("Wait for the file link to be visible after upload.");
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(File_Lnk(fileName)));
    	
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	Reporter.log("Click the 'Next' button.");
    	Next_Btn.click();
    }
    
    public void ClickPublicLocalFilesRdb() throws Exception {
    	
    	Reporter.log("Click the 'Public Local Files' radio button.");
    	PublicLocalFiles_Rdb.click();
    }
    
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(File_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
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
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void SelectDefaultCoverImg() throws Exception {
    	
    	this.SwitchToSelectFileFrm();
    	this.EnterFilePath(applib.getPathToMedia() + "HanSolo.jpg");
    	this.ClickUploadBtn();
    	this.WaitForFileUploaded("HanSolo.jpg");
    	this.ClickNextBtn();
    	this.ClickPublicLocalFilesRdb();
    	this.ClickNextBtn();
    	this.VerifyFileImagePresent("HanSolo");
    	this.ClickSaveBtn();
    	Overlay overlay = new Overlay(webDriver, applib);
    	overlay.switchToDefaultContent();
    }
    
   
    
  
}

