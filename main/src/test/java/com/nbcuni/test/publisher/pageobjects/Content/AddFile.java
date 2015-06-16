package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.sikuli.basics.Settings;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform.MPXAssets;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Add File Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class AddFile {

    private Driver webDriver;
    private Config config;
    private Integer timeout;
    private Screen sikuli;
    private MPXAssets mpxAssets;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public AddFile(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        sikuli = new Screen();
        Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        mpxAssets = new MPXAssets();
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By AddFiles_Lnk = By.xpath("//a[@id='edit-upload_browse']");
    
    private By StartUpload_Lnk = By.xpath("//a[@class='plupload_button plupload_start']");
    
    private By UploadStatus1of1Files_Txt = By.xpath("//span[@class='plupload_upload_status'][text()='Uploaded 1/1 files']");
    
    private By Next_Btn = By.xpath("//input[@id='edit-next']");
    
    
    //PAGE OBJECT METHODS
    private String getImagePath() {
    	
    	return config.getConfigValueFilePath("PathToSikuliImages");
    	
    }

    public void ClickAddFilesLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add files' link.");
    	interact.Click(waitFor.ElementVisible(AddFiles_Lnk));
    	
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
    
    public void ClickMoviesUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Movies' button in the OS Upload dialog.");
    	try {
    		sikuli.click(getImagePath() + "FileUpload/AllMyFiles_Btn.png");
    	}
    	catch (Exception e) {
    		sikuli.click(getImagePath() + "FileUpload/HighlightedAllMyFiles_Btn.png");
    	}
    	
    	mpxAssets.WaitForImgPresent(getImagePath() + "FileUpload/MoviesUpload_Btn.png");
    	try {
    		sikuli.click(getImagePath() + "FileUpload/MoviesUpload_Btn.png");
    	}
    	catch (Exception e) {
    		sikuli.click(getImagePath() + "FileUpload/HighlightedMoviesUpload_Btn.png");
    	}
    }
    
    public void ClickNBCLogoLnk() throws Exception {
    	
    	Reporter.log("Click the nbc logo image link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/NBCLogo_Lnk.png");
    	sikuli.click(path + "AddFile/NBCLogo_Lnk.png");
    }

    public void ClickTestPictureExifDataBtn() throws Exception {
    	
    	Reporter.log("Click the test picture link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/TestPictureExifData_Btn.png");
    	sikuli.click(path + "AddFile/TestPictureExifData_Btn.png");
    }
    
    public void ClickTestPictureIPTCBtn() throws Exception {
    	
    	Reporter.log("Click the test picture link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/TestPictureIPTC_Btn.png");
    	sikuli.click(path + "AddFile/TestPictureIPTC_Btn.png");
    }
    
    public void ClickTestPictureDefaultBtn() throws Exception {
    	
    	Reporter.log("Click the test picture link.");
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "AddFile/TestPictureDefault_Btn.png");
    	sikuli.click(path + "AddFile/TestPictureDefault_Btn.png");
    }
    
    public void ClickTestMovieBtn() throws Exception {
    	
    	Reporter.log("Click the Test Movie in th OS Upload dialog.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "FileUpload/TestMovie_Btn.png");
    	sikuli.click(getImagePath() + "FileUpload/TestMovie_Btn.png");
    }

    public void ClickOpenBtn() throws Exception {
    	
    	Reporter.log("Click the 'Open' button.");
    	String path = this.getImagePath();
    	if (System.getProperty("os.name").contains("Mac")) {
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
    	interact.Click(waitFor.ElementVisible(StartUpload_Lnk));
    	
    }
    
    public void WaitForSuccessfulUpload() throws Exception {
    	
    	Reporter.log("Wait for file upload to reach 100%");
    	new WaitFor(webDriver, 60).ElementVisible(UploadStatus1of1Files_Txt);
    	Thread.sleep(2000); //slight pause to avoid problematic js alert
    	
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	Reporter.log("Click the 'Next' button.");
    	interact.Click(waitFor.ElementVisible(Next_Btn));
    	
    }
    
    public void AddDefaultVideo() throws Exception {
    	
    	if (System.getProperty("os.name").contains("Mac")) {
    		Thread.sleep(1000);
    		this.ClickMoviesUploadBtn();
    		this.ClickTestMovieBtn();
    	}
    	else {
    		if (sikuli.exists(this.getImagePath() + "FileUpload/DefaultVideo_Tmb.png", .9) == null) {
    			this.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
            	this.ClickGoBtn_Win();
    		}
    		this.EnterFileName_Win("DefAutMed.m4v");
    	}
    	this.ClickOpenBtn();
    }
    
    public void AddDefaultPicture() throws Exception {
    	
    	if (System.getProperty("os.name").contains("Mac")) {
    		this.ClickPicturesUploadBtn();
    		this.ClickTestPictureDefaultBtn();
    	}
    	else {
    		this.EnterPathToFile_Win(config.getConfigValueFilePath("PathToMediaContent"));
        	this.ClickGoBtn_Win();
        	this.EnterFileName_Win("nbclogosmall.jpg");
    	}
    	this.ClickOpenBtn();
    	
    }
   
  
}

