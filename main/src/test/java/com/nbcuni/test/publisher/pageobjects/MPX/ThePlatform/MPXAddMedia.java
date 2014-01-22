package com.nbcuni.test.publisher.pageobjects.MPX.ThePlatform;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.sikuli.script.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com MPX Add Media Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 17, 2014
 *********************************************/

public class MPXAddMedia {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    private final Util ul;
    Screen s = new Screen();
    MPXAssets mpxAssets = new MPXAssets(this.webDriver, this.applib);
    
    public MPXAddMedia(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
        ul = new Util(webDriver);
        
    }
    
    private String getImagePath() {
    	
    	String PathToImages = applib.getPathToSikuliImages();
    	return PathToImages;
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Common/Upload_Btn.png");
    	s.click(path + "Common/Upload_Btn.png");
    }
    
    public void ClickChooseFilesBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "FileUpload/ChooseFiles_Btn.png");
    	s.click(path + "FileUpload/ChooseFiles_Btn.png");
    }
    
    public void ClickMoviesUploadBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "FileUpload/MoviesUpload_Btn.png");
    	Thread.sleep(500); //slight pause required here
    	try {
    		s.click(path + "FileUpload/MoviesUpload_Btn.png");
    	}
    	catch (Exception e) {
    		s.click(path + "FileUpload/HighlightedMoviesUpload_Btn.png");
    	}
    }
    
    public void ClickTestMovieBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "FileUpload/TestMovie_Btn.png");
    	s.click(path + "FileUpload/TestMovie_Btn.png");
    }
    
    public void ClickOpenBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "FileUpload/Open_Btn.png");
    	s.click(path + "FileUpload/Open_Btn.png");
    }
    
    public void ClickUploadFromDialogBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Common/UploadFromDialog_Btn.png");
    	s.click(path + "Common/UploadFromDialog_Btn.png");
    	
    	mpxAssets.WaitForImgPresent(path + "Media/DefaultMediaPresent.png");
    }
    
    public void GiveFocusToMediaItem() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Media/AvailableDate_Txb.png");
    	s.click(path + "Media/AvailableDate_Txb.png");
    	
    }
    
    public void EnterTitle(String mediaTitle) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/Title_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/Title_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(mediaTitle);
    }
    
    public void EnterAuthor(String author) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Media/Author_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/Author_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(author);
    }
    
    public void EnterCategories(String categories) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/Categories_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/Categories_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(categories);
        
    }
    
    public void EnterAvailableDate(String date) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/AvailableDate_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/AvailableDate_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(date);
        
    }

    public void EnterExpirationDate(String date) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/ExpirationDate_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/ExpirationDate_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(date);
        
    }
    
    public void EnterAirDate(String date) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/AirDate_Txb.png");
    	Pattern pImage = new Pattern(path + "Media/AirDate_Txb.png").targetOffset(0, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(date);
        
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.WaitForImgPresent(path + "Common/Save_Btn.png");
    	s.click(path + "Common/Save_Btn.png");
    	
    	mpxAssets.WaitForImgNotPresent(path + "Common/Spinner.png");
    	//Thread.sleep(2000);
    }
    
    public void SelectContentRatingUSMPAA_PG13() throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/ContentRatingUSMPAA_Ddl.png");
    	mpxAssets.Scroll("Down", 10); //extra scroll needed for interaction with ddl
    	Pattern pImage = new Pattern(path + "Media/ContentRatingUSMPAA_Ddl.png").targetOffset(-5, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	
    	mpxAssets.WaitForImgPresent(path + "Media/PG13_Opt.png");
    	s.click(path + "Media/PG13_Opt.png");
    	
        
    }
    
    public void EnterContentRatingUSMPAASubRating(String subRating) throws Exception {
    	
    	String path = this.getImagePath();
    	mpxAssets.ScrollDownForImgPresent(path + "Media/ContentRatingUSMPAA_Ddl.png");
    	Pattern pImage = new Pattern(path + "Media/ContentRatingUSMPAA_Ddl.png").targetOffset(20, 15);
    	Region r = s.exists(pImage, 1);
    	s.click(r, 1);
    	mpxAssets.ClearInput();
    	s.type(subRating);
    	
        
    }
    
    
    
  
}

