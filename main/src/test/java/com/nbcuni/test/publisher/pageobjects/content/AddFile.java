package com.nbcuni.test.publisher.pageobjects.content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Add File Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class AddFile {

    private static CustomWebDriver webDriver;
    private AppLib applib;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(this.webDriver, this.applib);
    
    //PAGE OBJECT CONSTRUCTOR
    public AddFile(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[@id='edit-upload_browse']")
    private static WebElement AddFiles_Lnk;
    
    @FindBy(how = How.XPATH, using = "//a[@class='plupload_button plupload_start']")
    private static WebElement StartUpload_Lnk;
    
    @FindBy(how = How.XPATH, using = "//span[@class='plupload_upload_status'][text()='Uploaded 1/1 files']")
    private static WebElement UploadStatus1of1Files_Txt;
    
    @FindBy(how = How.XPATH, using = "//input[@id='edit-next']")
    private static WebElement Next_Btn;
    
    
    //PAGE OBJECT METHODS
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }

    public void ClickAddFilesLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add files' link.");
    	AddFiles_Lnk.click();
    }
    
    public void EnterPathToFile_Win(String pathToFile) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/WinPathToFile.png");
    	
    	Reporter.log("Click the windows arrow to expand file path for file upload.");
    	Pattern pImage = new Pattern(path + "AddFile/WinPathToFile.png").targetOffset(-3, 0);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	
    	Reporter.log("Enter the path to the file for upload.");
    	Pattern pImage2 = new Pattern(path + "AddFile/WinPathToFile.png").targetOffset(-50, 0);
    	Region r2 = s.exists(pImage2, 1);
    	s.click(r2, 1);
    	s.type("a", KeyModifier.CTRL);
    	s.type(Key.BACKSPACE);
    	s.type(pathToFile);
    }
    
    public void ClickGoBtn_Win() throws Exception {
    	
    	Reporter.log("Click the windows 'Go' arrow.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/WinGo_Btn.png");
    	s.click(path + "AddFile/WinGo_Btn.png");
    }
    
    public void EnterFileName_Win(String fileName) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/WinFileName_Txb.png");
    	
    	Reporter.log("Enter the file name.");
    	Pattern pImage = new Pattern(path + "AddFile/WinFileName_Txb.png").targetOffset(0, 25);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	s.type("a", KeyModifier.CTRL);
    	s.type(Key.BACKSPACE);
    	s.type(fileName);
    }

    public void ClickPicturesUploadBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/Cancel_Btn.png");
    	
    	Reporter.log("Click the 'All Files' icon.");
    	try {
    		s.click(path + "FileUpload/AllMyFiles_Btn.png");
    	}
    	catch (Exception e) {
    		s.click(path + "FileUpload/HighlightedAllMyFiles_Btn.png");
    	}
    	
    	Reporter.log("Click the local 'Pictures' icon.");
    	Thread.sleep(500); //slight pause required here
    	try {
    		s.click(path + "AddFile/PicturesUpload_Btn.png");
    	}
    	catch (Exception e) {
    		s.click(path + "AddFile/HighlightedPicturesUpload_Btn.png");
    	}
    }
    
    public void ClickTestPictureBtn() throws Exception {
    	
    	Reporter.log("Click the test picture link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/TestPicture_Btn.png");
    	s.click(path + "AddFile/TestPicture_Btn.png");
    }
    
    public void ClickOpenBtn() throws Exception {
    	
    	Reporter.log("Click the 'Open' button.");
    	String path = this.getImagePath();
    	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
    		mpxAssets.WaitForImgPresent(path + "FileUpload/Open_Btn.png");
    		s.click(path + "FileUpload/Open_Btn.png");
    	}
    	else {
    		mpxAssets.WaitForImgPresent(path + "AddFile/WinOpen_Btn.png");
    		s.click(path + "AddFile/WinOpen_Btn.png");
    	}
    	
    }
    
    public void ClickStartUploadLnk() throws Exception {
    	
    	Reporter.log("Click the 'Start upload' link.");
    	StartUpload_Lnk.click();
    	
    }
    
    public void WaitForSuccessfulUpload() throws Exception {
    	
    	Reporter.log("Wait for file upload to reach 100%");
    	new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOf(UploadStatus1of1Files_Txt));
    	Thread.sleep(1000); //slight pause to avoid problematic js alert
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	Reporter.log("Click the 'Next' button.");
    	Next_Btn.click();
    	
    }
    
   
  
}

