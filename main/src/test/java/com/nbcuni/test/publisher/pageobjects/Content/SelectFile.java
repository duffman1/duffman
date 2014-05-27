package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Select File Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 16, 2013
 *********************************************/

public class SelectFile {

    private Driver webDriver;
    private AppLib applib;
    private WebDriverWait wait;
    private Random random;
    
    //PAGE OBJECT CONSTRUCTOR
    public SelectFile(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
        random = new Random();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//iframe[@id='mediaBrowser']")
    private WebElement SelectFile_Frm;
    
    @FindBy(how = How.XPATH, using = "//a[@title='View Library']")
    private WebElement ViewLibrary_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[@title='Publisher7 MPX Video']")
    private WebElement Pub7MPXVideo_Btn;
    
    @FindBy(how = How.ID, using = "edit-filename")
    private WebElement FileName_Txb;
    
    @FindBy(how = How.ID, using = "edit-title")
    private WebElement Title_Txb;
    
    @FindBy(how = How.XPATH, using = "//div[@class='media-thumbnail']/img")
    private WebElement Default_Img;
    
    private List<WebElement> Submit_Btn() {
    	return webDriver.findElements(By.xpath("//a[text()='Submit']"));
    }
    
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'button button-yes fake-submit')]")
    private WebElement ViewLibrarySubmit_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-upload-upload']")
    private WebElement BrowseToFile_Upl;
    
    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'edit-upload-upload-button')]")
    private WebElement Upload_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-next']")
    private WebElement Next_Btn;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-scheme-public']")
    private WebElement PublicLocalFiles_Rdb;
    
    @FindBy(how = How.XPATH, using = "//div[@id='file_image_form_group_image_top_wrapper']//img")
    private WebElement File_Img;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-submit']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[@id='edit-upload_browse']")
    private WebElement AddFiles_Lnk;
    
    @FindBy(how = How.XPATH, using = "//div[@class='throbber']")
    private WebElement Spinner_Img;
    
    @FindBy(how = How.ID, using = "edit-indicator")
    private WebElement FocalPoint_Ind;
    
    @FindBy(how = How.ID, using = "edit-focal-point")
    private WebElement FocalPoint_Txb;
    
    private WebElement File_Lnk(String fileName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + fileName + "']"));
    }
    
    private WebElement MediaThumbnail_Img(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//div[@class='media-thumbnail']//img)[" + imageIndex + "]"));
    }
    
    private WebElement MPXMediaThumbnail_Img(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//img[@class='pub-mpx-video-thumbnail'])[" + imageIndex + "]"));
    }
    
    @FindBy(how = How.ID, using = "//img[@class='pub-mpx-video-thumbnail']")
    private WebElement DefaultMPXMediaThumbnail_Img;
    
    private WebElement SearchFileResult_Ttl(String title) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + title + "')]"));
    }
    
    private List<WebElement> Apply_Btns() {
    	return webDriver.findElements(By.cssSelector("input[value='Apply']"));
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
    
    public void ClickPub7MPXVideoBtn() throws Exception {
    	
    	Reporter.log("Click the 'PUBLISHER 7 MPX VIDEO' button.");
    	Pub7MPXVideo_Btn.click();
    	
    }
    
    public void EnterFileName(String fileName) throws Exception {
    	
    	Reporter.log("Enter '" + fileName + "' in the 'File name' text box.");
    	wait.until(ExpectedConditions.visibilityOf(FileName_Txb)).sendKeys(fileName);
    	
    }
    
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	wait.until(ExpectedConditions.visibilityOf(Title_Txb)).sendKeys(title);
    	
    }
    
    public void ClickApplyBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply' button.");
    	for (WebElement btn : Apply_Btns()) {
    		if (btn.isDisplayed()) {
    			btn.click();
    		}
    	}
    	
    }
    
    public void WaitForFileSearchComplete() throws Exception {
    	
    	Reporter.log("Wait for the file search 'spinner' image to be present.");
    	Spinner_Img.isDisplayed();
    	
    	Reporter.log("Wait for the file search 'spinner' image to not be present, indicating image search is complete.");
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	boolean searchComplete;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("File search not complete after timeout");
            }
            try {
            	Spinner_Img.isDisplayed();
            	searchComplete = false;
            }
            catch (Exception e) {
            	searchComplete = true;
            }
                
            if (searchComplete == true){ break;}
            Thread.sleep(500);
        }
        webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void ClickDefaultImg() throws Exception {
    	
    	Reporter.log("Click on the default img.");
    	Default_Img.click();
    	
    }
    
    public void ClickSubmitBtn() throws Exception {
    	
    	Reporter.log("Click on the 'Submit' button.");
    	List<WebElement> submitBtns = Submit_Btn();
    	for (WebElement btn : submitBtns) {
    		if (btn.isDisplayed()) {
    			btn.click();
    		}
    	}
    	
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for file upload.");
    	//webDriver.setFileDetector(new LocalFileDetector());
    	BrowseToFile_Upl.sendKeys(pathToFile);
    	
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	Upload_Btn.click();
    	
    	try {
    		webDriver.switchTo().alert().accept();
    		Upload_Btn.click();
    	}
    	catch (Exception e) { }
    	
    }
    
    public void DoucleClickFocalPointIndicator() throws Exception {
    	
    	Reporter.log("Double click the focal point indicator arrow.");
    	Actions actions = new Actions(webDriver);
    	actions.doubleClick(FocalPoint_Ind);
    	actions.perform();
    	
    }
    
    public void VerifyFocalPointCoordinates(String coordinatesTxt) throws Exception {
    	
    	Reporter.log("Verify value of 'Focal Point' text box is '" + coordinatesTxt + "'.");
    	wait.until(ExpectedConditions.visibilityOf(FocalPoint_Txb));
    	Assert.assertEquals(FocalPoint_Txb.getAttribute("value"), coordinatesTxt);
    	
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
    	
    	Reporter.log("Verify the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(File_Img.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Verify the img is loaded and visible.");
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
    
    public void VerifyMediaThumbnailImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Verify the media thumbnail image '" + imageSrc + "' is present.");
    	for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not present after timeout");
            }
            
            if (MediaThumbnail_Img(imageIndex).getAttribute("src").contains(imageSrc)) {
            	break;
            }
            Thread.sleep(500);
        }
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			MediaThumbnail_Img(imageIndex));
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void ClickMediaThumbnailImage(String imageIndex) throws Exception {
    	
    	Reporter.log("Click media thumbnail image number " + imageIndex );
    	MediaThumbnail_Img(imageIndex).click();
                
    }
    
    public void ClickMPXMediaThumbnailImage(String imageIndex) throws Exception {
    	
    	Reporter.log("Click mpx media thumbnail image number " + imageIndex );
    	MPXMediaThumbnail_Img(imageIndex).click();
                
    }
    
    public void ClickDefaultMPXMediaThumbnailImage() throws Exception {
    	
    	Reporter.log("Click a default mpx media thumbnail image with the 'nbc small' logo.");
    	DefaultMPXMediaThumbnail_Img.click();
                
    }
    
    public void ClickSearchResultTtl(String title) throws Exception {
    	
    	Reporter.log("Click search file result title '" + title + "'.");
    	SearchFileResult_Ttl(title).click();
                
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
    public void SelectDefaultCoverImg() throws Exception {
    	
    	this.SwitchToSelectFileFrm();
    	Integer randomInt = random.GetInteger(0, 3);
        String defaultImgFile = "HanSolo1.jpg";
        switch (randomInt) {
        case 0:
        	defaultImgFile = "HanSolo1.jpg";
        	break;
        case 1:
        	defaultImgFile = "HanSolo2.jpg";
        	break;
        case 2:
        	defaultImgFile = "HanSolo3.jpg";
        	break;
        case 3:
        	defaultImgFile = "HanSolo4.jpg";
        	break;
        }
    	this.EnterFilePath(applib.getPathToMedia() + defaultImgFile);
    	this.ClickUploadBtn();
    	this.WaitForFileUploaded(defaultImgFile);
    	this.ClickNextBtn();
    	this.ClickPublicLocalFilesRdb();
    	this.ClickNextBtn();
    	this.VerifyFileImagePresent("HanSolo");
    	this.ClickSaveBtn();
    	Overlay overlay = new Overlay(webDriver, applib);
    	overlay.switchToDefaultContent();
    }
    
}

