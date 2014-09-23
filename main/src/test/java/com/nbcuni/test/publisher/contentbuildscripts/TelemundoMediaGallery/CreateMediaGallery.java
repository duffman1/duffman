package com.nbcuni.test.publisher.contentbuildscripts.TelemundoMediaGallery;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.AddFile;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.MediaItems;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

public class CreateMediaGallery extends ParentTest{
	
    @Test(retryAnalyzer = RerunOnFailure.class)
    public void CreateMediaGallery_Test() throws Exception{
        
        	//login
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        	
        	for (int i = 0; i<=15; i++) {
        		
        		try {
        			//add a media gallery with multiple images
                    taxonomy.NavigateSite("Content>>Add content>>Media Gallery");
                    overlay.SwitchToActiveFrame();
                    BasicInformation basicInformation = new BasicInformation(webDriver);
                    String title = random.GetCharacterString(15);
                    basicInformation.EnterTitle(title);
                    basicInformation.EnterSynopsis();
                    overlay.SwitchToActiveFrame();
                    basicInformation.EnterShortDescription("short description");
                    AddFile addFile = new AddFile(webDriver, applib);
                    MediaItems mediaItems = new MediaItems(webDriver);
                    SelectFile selectFile = new SelectFile(webDriver, applib);
                    for(int Count=0;Count<2;Count++) {
                    	if (Count == 0) {
                    		basicInformation.ClickMediaItemsSelectBtn();
                    	}
                    	else {
                    		mediaItems.WaitForImgLoadComplete();
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
                        overlay.SwitchToActiveFrame();
                    }
                    
                    //verify the images are actually loaded
                    try {
                    	mediaItems.VerifyFileImagePresent("IPTCDefault", "1");
                        mediaItems.VerifyFileImagePresent("nbclogosmall", "2");
                    }
                    catch (Exception | AssertionError e) {
                    	Date date = new Date();
                    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMddyyhhmmssa");
                    	Reporter.log("MEDIA GALLERY IMAGE NOT FULLY LOADED - " + dateTimeFormat.format(date));
                    	break;
                    }
                    
                    //add a cover image
                    basicInformation.ClickCoverSelectBtn();
                    selectFile.SelectDefaultCoverImg();
                    overlay.SwitchToActiveFrame();
                    basicInformation.VerifyCoverImagePresent("HanSolo");
                    
                    //verify the media gallery saves
                    contentParent.ClickSaveBtn();
                    overlay.switchToDefaultContent(true);
                    contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
        		}
        		catch (Exception | AssertionError e) {
        			overlay.switchToDefaultContent(false);
        			taxonomy.NavigateSite("Home");
        		}
        		
        	}
    }
}
