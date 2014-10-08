package com.nbcuni.test.publisher.contentbuildscripts.TelemundoMediaGallery;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.MediaItems;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

public class CreateMediaGallery extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class)
    public void CreateMediaGallery_Test() throws Exception{
        
        	//login
        	//UserLogin userLogin = applib.openApplication();
        	webDriver.navigate().to("http://acc.telemundo.com/user");
        	Thread.sleep(2000);
        	webDriver.findElement(By.id("edit-name")).sendKeys("admin@telemundo.com");
        	webDriver.findElement(By.id("edit-pass")).sendKeys("Pa55word");
        	webDriver.findElement(By.id("edit-submit")).click();
        	
        	for (int i = 0; i<=15; i++) {
        		
        		//add a media gallery with multiple images
                    webDriver.navigate().to("http://acc.telemundo.com/node/add/media-gallery");
                    BasicInformation basicInformation = new BasicInformation(webDriver);
                    String title = random.GetCharacterString(15);
                    contentParent.Scroll("1000");
                    Thread.sleep(1000);
                    basicInformation.EnterTitle(title);
                    basicInformation.EnterSynopsis();
                    overlay.switchToDefaultContent(false);
                    basicInformation.EnterShortDescription("short description");
                    AddFile addFile = new AddFile(webDriver, applib);
                    MediaItems mediaItems = new MediaItems(webDriver);
                    SelectFile selectFile = new SelectFile(webDriver, applib);
                    for(int Count=0;Count<2;Count++) {
                    	if (Count == 0) {
                    		contentParent.Scroll("500");
                    		basicInformation.ClickMediaItemsSelectBtn();
                    	}
                    	else {
                    		mediaItems.WaitForImgLoadComplete();
                    		contentParent.Scroll("500");
                    		mediaItems.ClickAddBtn();
                    	}
                        selectFile.SwitchToSelectFileFrm();
                        addFile.ClickAddFilesLnk();
                        if (webDriver.getCapabilities().getPlatform().toString() == "MAC") {
                        	addFile.ClickPicturesUploadBtn();
                        	if (Count == 0) {
                        		addFile.ClickTestPictureIPTCBtn();
                        	}
                        	else {
                        		addFile.ClickTestPictureDefaultBtn();
                        	}
                        	addFile.ClickOpenBtn();
                        }
                        else {
                        	addFile.EnterPathToFile_Win(applib.getPathToMedia());
                        	addFile.ClickGoBtn_Win();
                        	if (Count == 0) {
                        		addFile.EnterFileName_Win("IPTCDefault.jpg");
                        	}
                        	else {
                        		addFile.EnterFileName_Win("nbclogosmall.jpg");
                        	}
                        	addFile.ClickOpenBtn();
                        }
                        addFile.ClickStartUploadLnk();
                        addFile.WaitForSuccessfulUpload();
                        addFile.ClickNextBtn();
                        overlay.switchToDefaultContent(false);
                    }
                    
                    //verify the images are actually loaded
                    try {
                    	Thread.sleep(3000);
                    	mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
                        mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
                    }
                    catch (Exception | AssertionError e) {
                    	e.printStackTrace();
                    	contentParent.Scroll("500");
                    	Date date = new Date();
                    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
                    	Assert.fail("MEDIA GALLERY IMAGE NOT FULLY LOADED - " + dateTimeFormat.format(date));
                    }
                    
                    //add a cover image
                    basicInformation.ClickCoverSelectBtn();
                    selectFile.SelectDefaultCoverImg();
                    overlay.switchToDefaultContent(false);
                    basicInformation.VerifyCoverImagePresent("HanSolo");
                    
                    //verify the media gallery saves
                    contentParent.ClickSaveBtn();
                    overlay.switchToDefaultContent(true);
                    contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
        	}
    }
}
