package com.nbcuni.test.publisher.pageobjects.content;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.AppLib;
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
    private Util ul;
    
    private static String SelectFile_Frm = "//iframe[@id='mediaBrowser']";
    private static String ViewLibrary_Btn = "//a[@title='View Library']";
    private static String Default_Img = "//div[@class='media-thumbnail']/img";
    private static String Submit_Btn = "//a[text()='Submit']";
    private static String BrowseToFile_Upl = "//input[@id='edit-upload-upload']";
    private static String Upload_Btn = "//input[contains(@id, 'edit-upload-upload-button')]";
    private static String Next_Btn = "//input[@id='edit-next']";
    private static String PublicLocalFiles_Rdb = "//input[@id='edit-scheme-public']";
    private static String File_Img = "//div[@id='file_image_form_group_image_top_wrapper']//img";
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String AddFiles_Lnk = "//a[@id='edit-upload_browse']";
    
    
    public SelectFile(final CustomWebDriver custWebDr, AppLib applib) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        this.applib = applib;
    }
    
    public void SwitchToSelectFileFrm() throws Exception {
    	
    	WebElement frm = webDriver.findElement(By.xpath(SelectFile_Frm));
    	webDriver.switchTo().frame(frm);
    	
    }
    
    public void ClickViewLibraryBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(ViewLibrary_Btn)));
    	webDriver.click(ViewLibrary_Btn);
    	
    }
    
    public void ClickDefaultImg() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Default_Img)));
    	webDriver.click(Default_Img);
    	
    }
    
    public void ClickSubmitBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Submit_Btn)));
    	webDriver.click(Submit_Btn);
    	
    }
    
    public void EnterFilePath(String pathToFile) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(BrowseToFile_Upl)));
    	webDriver.setFileDetector(new LocalFileDetector());
    	webDriver.findElement(By.xpath(BrowseToFile_Upl)).sendKeys(pathToFile);
    	
    }
    
    public void ClickUploadBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Upload_Btn)));
    	webDriver.click(Upload_Btn);
    	
    }
    
    public void WaitForFileUploaded(String fileName) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + fileName + "']")));
    	
    }
    
    public void ClickNextBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Next_Btn)));
    	webDriver.click(Next_Btn);
    }
    
    public void ClickPublicLocalFilesRdb() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(PublicLocalFiles_Rdb)));
    	webDriver.click(PublicLocalFiles_Rdb);
    }
    
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.elementToBeClickable(By.xpath(File_Img)));
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(File_Img)).getAttribute("src").contains(imageSrc));
    	
    	webDriver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " +
    			"!= \"undefined\" && arguments[0].naturalWidth > 0", File_Img);
    }
    
    public void ClickSaveBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Save_Btn)));
    	webDriver.click(Save_Btn);
    }
    
    public void AddMultipleFiles(List<String> filePaths) throws Exception {
    	
    	for (String path : filePaths){
    		
    		new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(AddFiles_Lnk)));
        	
    		webDriver.findElement(By.xpath(AddFiles_Lnk)).sendKeys(path);
    	}
    	
    	
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
    	Overlay overlay = new Overlay(webDriver);
    	overlay.switchToDefaultContent();
    }
    
   
    
  
}

