package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Reporter;
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
    private static AppLib applib;
    private static Screen sikuli;
    private static MPXAssets mpxAssets;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddFile(CustomWebDriver webDriver, AppLib applib) {
        AddFile.webDriver = webDriver;
        AddFile.applib = applib;
        sikuli = new Screen();
        mpxAssets = new MPXAssets(applib);
        PageFactory.initElements(webDriver, this);
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
    	Region r = sikuli.exists(pImage, 1);
    	sikuli.click(r, 1);
    	
    	Reporter.log("Enter the path to the file for upload.");
    	Pattern pImage2 = new Pattern(path + "AddFile/WinPathToFile.png").targetOffset(-50, 0);
    	Region r2 = sikuli.exists(pImage2, 1);
    	sikuli.click(r2, 1);
    	sikuli.type("a", KeyModifier.CTRL);
    	sikuli.type(Key.BACKSPACE);
    	sikuli.type(pathToFile);
    }
    
    public void ClickGoBtn_Win() throws Exception {
    	
    	Reporter.log("Click the windows 'Go' arrow.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/WinGo_Btn.png");
    	sikuli.click(path + "AddFile/WinGo_Btn.png");
    }
    
    public void EnterFileName_Win(String fileName) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/WinFileName_Txb.png");
    	
    	Reporter.log("Enter the file name.");
    	Pattern pImage = new Pattern(path + "AddFile/WinFileName_Txb.png").targetOffset(25, 0);
    	Region r = sikuli.exists(pImage, 1);
    	sikuli.click(r, 1);
    	sikuli.type("a", KeyModifier.CTRL);
    	sikuli.type(Key.BACKSPACE);
    	sikuli.type(fileName);
    }

    public void ClickPicturesUploadBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/Cancel_Btn.png");
    	
    	Reporter.log("Click the 'All Files' icon.");
    	try {
    		sikuli.click(path + "FileUpload/AllMyFiles_Btn.png");
    	}
    	catch (Exception e) {
    		sikuli.click(path + "FileUpload/HighlightedAllMyFiles_Btn.png");
    	}
    	
    	Reporter.log("Click the local 'Pictures' icon.");
    	Thread.sleep(500); //slight pause required here
    	try {
    		sikuli.click(path + "AddFile/PicturesUpload_Btn.png");
    	}
    	catch (Exception e) {
    		sikuli.click(path + "AddFile/HighlightedPicturesUpload_Btn.png");
    	}
    }
    
    public void ClickNBCLogoLnk() throws Exception {
    	
    	Reporter.log("Click the nbc logo image link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/NBCLogo_Lnk.png");
    	sikuli.click(path + "AddFile/NBCLogo_Lnk.png");
    }

    public void ClickTestPictureBtn() throws Exception {
    	
    	Reporter.log("Click the test picture link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/TestPicture_Btn.png");
    	sikuli.click(path + "AddFile/TestPicture_Btn.png");
    }
    
    public void ClickOpenBtn() throws Exception {
    	
    	Reporter.log("Click the 'Open' button.");
    	String path = this.getImagePath();
    	if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
    		mpxAssets.WaitForImgPresent(path + "FileUpload/Open_Btn.png");
    		sikuli.click(path + "FileUpload/Open_Btn.png");
    	}
    	else {
    		mpxAssets.WaitForImgPresent(path + "AddFile/WinOpen_Btn.png");
    		sikuli.click(path + "AddFile/WinOpen_Btn.png");
    	}
    	
    }
    
    public void ClickStartUploadLnk() throws Exception {
    	
    	Reporter.log("Click the 'Start upload' link.");
    	StartUpload_Lnk.click();
    	
    }
    
    public void WaitForSuccessfulUpload() throws Exception {
    	
    	Reporter.log("Wait for file upload to reach 100%");
    	new WebDriverWait(webDriver, 60).until(ExpectedConditions.visibilityOf(UploadStatus1of1Files_Txt));
    	Thread.sleep(1000); //slight pause to avoid problematic js alert
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	Reporter.log("Click the 'Next' button.");
    	Next_Btn.click();
    	
    }
    
   
  
}

