package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Select File Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 16, 2013
 *********************************************/

public class SelectFile {

    private Driver webDriver;
    private WaitFor waitFor;
    private Interact interact;
    private Random random;
    private ErrorChecking errorChecking;
    private Config config;
    private Integer timeout;
    
    //PAGE OBJECT CONSTRUCTOR
    public SelectFile(Driver webDriver) {
        this.webDriver = webDriver;
        random = new Random();
        errorChecking = new ErrorChecking(webDriver);
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        webDriver.setFileDetector(new LocalFileDetector());
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By SelectFile_Frm = By.xpath("//iframe[@id='mediaBrowser']");
    
    private By ViewLibrary_Btn = By.xpath("//a[@title='View Library']");
    
    private By Pub7MPXVideo_Btn = By.xpath("//a[@title='Publisher7 MPX Video']");
    
    private By FileName_Txb = By.id("edit-filename");
    
    private By Title_Txb = By.id("edit-title");
    
    private By Default_Img = By.xpath("//div[@class='media-thumbnail']/img");
    
    private By AllSubmit_Btns = By.xpath("//a[text()='Submit']");
    
    private By BrowseToFile_Upl = By.xpath("//input[@id='edit-upload-upload']");
    
    private By Upload_Btn = By.xpath("//input[contains(@id, 'edit-upload-upload-button')]");
    
    private By Next_Btn = By.xpath("//input[@id='edit-next']");
    
    private By PublicLocalFiles_Rdb = By.xpath("//input[@id='edit-scheme-public']");
    
    private By File_Img = By.xpath("//div[@class='file-entity-left-view file-entity-panel']//img");
    
    private By Save_Btn = By.xpath("//input[@id='edit-submit']");
    
    private By FocalPoint_Ind = By.xpath("//div[contains(@id, 'focal-point-media')]");
    
    private By FocalPoint_Txb = By.id("edit-focal-point");
    
    private By File_Lnk(String fileName) {
    	return By.xpath("//a[text()='" + fileName + "']");
    }
    
    private By MediaThumbnail_Img(String imageIndex) {
    	return By.xpath("(//div[@class='media-thumbnail']//img)[" + imageIndex + "]");
    }
    
    private By MPXMediaThumbnail_Img(String imgSrc, String imageIndex) {
    	return By.xpath("(//img[@class='pub-mpx-video-thumbnail'][contains(@src, '" + imgSrc + "')])[" + imageIndex + "]");
    }
    
    private By DefaultMPXMediaThumbnail_Img = By.id("//img[@class='pub-mpx-video-thumbnail']");
    
    private By SearchFileResult_Ttl(String title) {
    	return By.xpath("//td[contains(text(), '" + title + "')]");
    }
    
    private By AllApply_Btns = By.xpath("//input[@value='Apply']");
    
    private By CustomBrowse_Btn(String label) {
    	return By.xpath("//label[contains(text(), '" + label + "')]/..//input[contains(@id, 'upload')][1]");
    }
    
    private By MediaBrowser_Tst = By.xpath("//div[@id='media-browser-tabset']");
    
    private By CustomUpload_Btn(String label) {
    	return By.xpath("//label[contains(text(), '" + label + "')]/..//input[contains(@id, 'upload')][2]");
    }
    
    
    //PAGE OBJECT IDENTIFIERS
    public void SwitchToSelectFileFrm() throws Exception {
    	
    	webDriver.switchTo().defaultContent();
    	Thread.sleep(1000);
    	
    	webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	for (int I = 0; I <= timeout; I++) {
    		
    		if (I == timeout) {
    			Assert.fail("Select file frame not present after " + timeout + " seconds.");
    		}
    		
    		webDriver.switchTo().frame(waitFor.ElementPresent(SelectFile_Frm));
    		
    		try {
    			webDriver.findElement(MediaBrowser_Tst);
    			break;
    		}
    		catch (NoSuchElementException e) { }
    			
    		Thread.sleep(1000);
    		webDriver.switchTo().defaultContent();
    		
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	
    	errorChecking.VerifyNoMessageErrorsPresent();
    	
    }
    
    public void ClickViewLibraryBtn() throws Exception {
    	
    	Reporter.log("Click the 'View Library' button.");
    	interact.Click(waitFor.ElementVisible(ViewLibrary_Btn));
    	
    }
    
    public void ClickPub7MPXVideoBtn() throws Exception {
    	
    	Reporter.log("Click the 'PUBLISHER 7 MPX VIDEO' button.");
    	interact.Click(waitFor.ElementVisible(Pub7MPXVideo_Btn));
    	
    }
    
    public void EnterFileName(String fileName) throws Exception {
    	
    	Reporter.log("Enter '" + fileName + "' in the 'File name' text box.");
    	waitFor.ElementVisible(FileName_Txb).sendKeys(fileName);
    	
    }
    
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	waitFor.ElementVisible(Title_Txb).sendKeys(title);
    	
    }
    
    public void ClickApplyBtn() throws Exception {
    	
    	Reporter.log("Click the 'Apply' button.");
    	for (WebElement btn : waitFor.ElementsPresent(AllApply_Btns)) {
    		if (btn.isDisplayed()) {
    			interact.Click(btn);
    			break;
    		}
    	}
    	
    }
    
    public void ClickDefaultImg() throws Exception {
    	
    	Reporter.log("Click on the default img.");
    	interact.Click(waitFor.ElementVisible(Default_Img));
    	
    }
    
    public void ClickSubmitBtn() throws Exception {
    	
    	Reporter.log("Click on the 'Submit' button.");
    	for (WebElement btn : waitFor.ElementsPresent(AllSubmit_Btns)) {
    		if (btn.isDisplayed()) {
    			interact.Click(btn);
    			break;
    		}
    	}
    	
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	Reporter.log("Enter the file path for file upload.");
    	WebElement element = waitFor.ElementVisible(BrowseToFile_Upl);
    	Thread.sleep(1000);
    	element.sendKeys(pathToFile);
    	
    }
    
    public void EnterCustomFieldFilePath(String label, String pathToFile) throws Exception {
    	
    	Reporter.log("Enter '" + pathToFile + "' in the '" + label + "' custom file upload field.");
    	waitFor.ElementVisible(CustomBrowse_Btn(label)).sendKeys(pathToFile);
    	
    }

    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	WebElement ele = waitFor.ElementVisible(Upload_Btn);
    	interact.Click(ele);
    	
    	try {
    		Thread.sleep(500);
    		Alert alert1 = webDriver.switchTo().alert();
    		alert1.accept();
    		webDriver.switchTo().defaultContent();
    		this.SwitchToSelectFileFrm();
    		interact.Click(ele);
    	}
    	catch (Exception e) { }
    	
    }
    
    public void ClickCustomFieldUploadBtn(String label) throws Exception {
    	
    	Reporter.log("Click the 'Upload' button for custom field '" + label + "'.");
    	interact.Click(waitFor.ElementVisible(CustomUpload_Btn(label)));
    	
    }
    
    public void DoucleClickFocalPointIndicator() throws Exception {
    	
    	Reporter.log("Double click the focal point indicator arrow.");
    	Actions actions = new Actions(webDriver);
    	actions.doubleClick(waitFor.ElementVisible(FocalPoint_Ind));
    	actions.perform();
    	
    }
    
    public void VerifyFocalPointCrossHairLocation(String pixelX, String pixelY) throws Exception {
    	
    	Reporter.log("Verify the focal point crosshair is in the center of the image.");
    	waitFor.ElementContainsAttribute(this.FocalPoint_Ind, "style", "position: relative; left: " 
    			+ pixelX + "px; top: " + pixelY + "px;");
    	
    }
    
    public void EnterFocalPointCoordinates(String coordinatesTxt) throws Exception {
    	
    	Reporter.log("Enter '" + coordinatesTxt + "' in the 'Focal Point' text box.");
    	WebElement element = waitFor.ElementVisible(FocalPoint_Txb);
    	interact.Click(element);
    	interact.Type(waitFor.ElementVisible(FocalPoint_Txb), coordinatesTxt);
    	
    }
    
    public void WaitForFileUploaded(String fileName) throws Exception {
    	
    	Reporter.log("Wait for the file link to be visible after upload.");
    	waitFor.ElementVisible(File_Lnk(fileName));
    	Thread.sleep(1000);
    	
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	Reporter.log("Click the 'Next' button.");
    	interact.Click(waitFor.ElementVisible(Next_Btn));
    	
    }
    
    public Boolean ClickPublicLocalFilesRdb() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	
    	Boolean publicFileOptionPresent = true;
    	
    	WebElement ele = null;
    	
    	try {
    		ele = webDriver.findElement(PublicLocalFiles_Rdb);
    		publicFileOptionPresent = true;
    	}
    	catch (Exception e) {
    		publicFileOptionPresent = false;
    	}
    	
    	if (publicFileOptionPresent == true) {
    		Reporter.log("Click the 'Public Local Files' radio button.");
    		interact.Click(ele);
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    	return publicFileOptionPresent;
    }
    
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Verify the file image '" + imageSrc + "' is present.");
    	WebElement ele = waitFor.ElementContainsAttribute(File_Img, "src", imageSrc);
    	
    	Reporter.log("Verify the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void VerifyMediaThumbnailImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Verify the media thumbnail image '" + imageSrc + "' is present.");
    	WebElement ele = waitFor.ElementContainsAttribute(MediaThumbnail_Img(imageIndex), "src", imageSrc);
    	
    	Reporter.log("Verify the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void ClickMediaThumbnailImage(String imageIndex) throws Exception {
    	
    	Reporter.log("Click media thumbnail image number " + imageIndex );
    	interact.Click(waitFor.ElementVisible(MediaThumbnail_Img(imageIndex)));
    	       
    }
    
    public void ClickMPXMediaThumbnailImage(String imgSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Click mpx media thumbnail image number " + imageIndex );
    	interact.Click(waitFor.ElementPresent(MPXMediaThumbnail_Img(imgSrc, imageIndex)));
    	Thread.sleep(1000);
    	        
    }
    
    public void ClickDefaultMPXMediaThumbnailImage() throws Exception {
    	
    	Reporter.log("Click a default mpx media thumbnail image with the 'nbc small' logo.");
    	interact.Click(waitFor.ElementVisible(DefaultMPXMediaThumbnail_Img));
    	       
    }
    
    public void ClickSearchResultTtl(String title) throws Exception {
    	
    	Reporter.log("Click search file result title '" + title + "'.");
    	interact.Click(waitFor.ElementVisible(SearchFileResult_Ttl(title)));
    	       
    }
    
    public void WaitForSelectFileFrameClose() throws Exception {
    	
    	webDriver.switchTo().defaultContent();
    	waitFor.ElementNotPresent(SelectFile_Frm);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
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
    	this.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + defaultImgFile);
    	this.ClickUploadBtn();
    	this.WaitForFileUploaded(defaultImgFile);
    	this.ClickNextBtn();
    	Boolean publicFileOptionPresent = this.ClickPublicLocalFilesRdb();
    	if (publicFileOptionPresent == true) {
    		this.ClickNextBtn();
    	}
    	this.VerifyFileImagePresent("HanSolo");
    	this.ClickSaveBtn();
    	this.WaitForSelectFileFrameClose();
    	
    }
    
}

