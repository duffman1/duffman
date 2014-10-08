package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;

import org.sikuli.script.*;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com MPX Add Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXAddMedia {

    private Screen sikuli;
    private MPXAssets mpxAssets;
    private AddFile addFile;
    private Config config;
    
    public MPXAddMedia(AppLib applib) {
    	sikuli = new Screen();
        mpxAssets = new MPXAssets();
        addFile = new AddFile(null);
        config = new Config();
    }
    
    private String getImagePath() {
    	
    	return config.getConfigValueFilePath("PathToSikuliImages");
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Upload_Btn.png");
    	sikuli.doubleClick(getImagePath() + "Common/Upload_Btn.png");
    }
    
    public void ClickChooseFilesBtn() throws Exception {
    	
    	Reporter.log("Click the 'Choose Files' button.");
    	if (System.getProperty("os.name").contains("Mac")) {
    		
    		mpxAssets.WaitForImgPresent(getImagePath() + "FileUpload/ChooseFiles_Btn_Mac.png");
    		sikuli.click(getImagePath() + "FileUpload/ChooseFiles_Btn_Mac.png");
    	}
    	else {
    		
    		mpxAssets.WaitForImgPresent(getImagePath() + "FileUpload/ChooseFiles_Btn.png");
    		sikuli.click(getImagePath() + "FileUpload/ChooseFiles_Btn.png");
    	}
    }
    
    public void ClickOpenBtn() throws Exception {
    	
    	Reporter.log("Click the 'Open' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "FileUpload/Open_Btn.png");
    	sikuli.click(getImagePath() + "FileUpload/Open_Btn.png");
    }
    
    public void ClickUploadFromDialogBtn() throws Exception {
    	
    	Reporter.log("Click the 'Upload' button in the Upload dialog.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/UploadFromDialog_Btn.png");
    	sikuli.click(getImagePath() + "Common/UploadFromDialog_Btn.png");
    	
    	Thread.sleep(1000);
    	mpxAssets.WaitForImgNotPresent(getImagePath() + "Common/Spinner.png");
    	
    }
    
    public void GiveFocusToMediaItem() throws Exception {
    	
    	//Reporter.log("Click the 'Available Date' label on the right hand side to give focus to the media item.");
    	//mpxAssets.WaitForImgPresent(getImagePath() + "Media/AvailableDate_Txb.png");
    	//sikuli.click(getImagePath() + "Media/AvailableDate_Txb.png");
    	
    	Pattern pattern = new Pattern(getImagePath() + "Media/Pub7DefaultFields_Ctr.png").targetOffset(5, 19);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ScrollUpForImgPresent(getImagePath() + "Media/AvailableDate_Txb.png");
    	
    }
    
    public void ClickFilesLnk() throws Exception {
    	
    	Reporter.log("Click the 'Files' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/Files_Lnk.png");
    	sikuli.click(getImagePath() + "Media/Files_Lnk.png");
    	Thread.sleep(3000); //TODO - add dynamic wait
    }
    
    public void ClickAllMediaLnk() throws Exception {
    	
    	Reporter.log("Click the 'All Media' link.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/AllMedia_Lnk.png");
    	sikuli.click(getImagePath() + "Media/AllMedia_Lnk.png");
    	Thread.sleep(3000); //TODO - add dynamic wait
    }
    
    public void EnterTitle(String mediaTitle) throws Exception {
    	
    	Reporter.log("Enter '" + mediaTitle + "' in the 'Title' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/Title_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/Title_Txb.png").targetOffset(5, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(mediaTitle);
    }
    
    public void EnterCanonicalURL(String urlTxt) throws Exception {
    	
    	Reporter.log("Enter '" + urlTxt + "' in the 'Canonical URL' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/CanonicalURL_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/CanonicalURL_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(urlTxt);
    }
    
    public void EnterAuthor(String author) throws Exception {
    	
    	Reporter.log("Enter '" + author + "' in the 'Author' text box.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/Author_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/Author_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(author);
    }
    
    public void EnterCategories(String categories) throws Exception {
    	
    	Reporter.log("Enter '" + categories + "' in the 'Categories' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/Categories_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/Categories_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(categories);
        
    }
    
    public void EnterAvailableDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Available Date' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/AvailableDate_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/AvailableDate_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(date);
        
    }

    public void EnterExpirationDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Expiration Date' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/ExpirationDate_Txb.png");
    	Thread.sleep(1000);
    	Pattern pattern = new Pattern(getImagePath() + "Media/ExpirationDate_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(date);
        
    }
    
    public void EnterDescription(String description) throws Exception {
    	
    	Reporter.log("Enter '" + description + "' in the 'Description' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/Description_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/Description_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(description);
        
    }

    public void EnterAirDate(String date) throws Exception {
    	
    	Reporter.log("Enter '" + date + "' in the 'Air Date' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/AirDate_Txb.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/AirDate_Txb.png").targetOffset(0, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(date);
        
    }
    
    public void ClickSaveBtn(Boolean withPause) throws Exception {
    	
    	Reporter.log("Click the 'Save' button.");
    	mpxAssets.WaitForImgPresent(getImagePath() + "Common/Save_Btn.png");
    	sikuli.click(getImagePath() + "Common/Save_Btn.png");
    	
    	mpxAssets.WaitForImgNotPresent(getImagePath() + "Common/Spinner.png");
    	
    	if (withPause.equals(true)) {
    		Thread.sleep(config.getConfigValueInt("MPXVideoUploadPause")); //TODO - long pause here since file uploads in background requires some time. This is bad I know but there's no visual indicator I can go by...
    	}
    }
    
    public void SelectContentRatingUSMPAA_PG13() throws Exception {
    	
    	Reporter.log("Select 'PG 13' from the 'USMPAA' drop down list.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/ContentRatingUSMPAA_Ddl.png");
    	mpxAssets.Scroll("Down", 10); //extra scroll needed for interaction with ddl
    	Pattern pattern = new Pattern(getImagePath() + "Media/ContentRatingUSMPAA_Ddl.png").targetOffset(-5, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	
    	mpxAssets.WaitForImgPresent(getImagePath() + "Media/PG13_Opt.png");
    	sikuli.click(getImagePath() + "Media/PG13_Opt.png");
    	
    }
    
    public void EnterContentRatingUSMPAASubRating(String subRating) throws Exception {
    	
    	Reporter.log("Enter '" + subRating + "' in the 'USMPAA Sub Rating' text box.");
    	mpxAssets.ScrollDownForImgPresent(getImagePath() + "Media/ContentRatingUSMPAA_Ddl.png");
    	Pattern pattern = new Pattern(getImagePath() + "Media/ContentRatingUSMPAA_Ddl.png").targetOffset(20, 15);
    	Region region = sikuli.exists(pattern, 1);
    	sikuli.click(region, 1);
    	mpxAssets.ClearInput();
    	sikuli.type(subRating);
    	
    }
    
    public void UploadDefaultVideo() throws Exception {
    	
    	this.ClickUploadBtn();
    	this.ClickChooseFilesBtn();
    	addFile.AddDefaultVideo();
        this.ClickUploadFromDialogBtn();
    }
    
}

