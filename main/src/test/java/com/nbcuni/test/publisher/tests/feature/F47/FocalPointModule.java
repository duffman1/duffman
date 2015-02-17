package com.nbcuni.test.publisher.tests.feature.F47;

import java.util.Arrays;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.AddFocalPointCropEffect;
import com.nbcuni.test.publisher.pageobjects.Configuration.FocalPoint;
import com.nbcuni.test.publisher.pageobjects.Configuration.ImageStyles;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.FileTypes.FileTypes;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFileDisplay;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay;

import org.testng.annotations.Test;

public class FocalPointModule extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1052
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441593285
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "F47"})
    public void FocalPointModule_TC1052() throws Exception {
         
        //Step 1
    	Boolean publicFileOptionPresent = true;
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        //Step 2
        Modules modules = new Modules(webDriver);
        modules.VerifyModuleEnabled("Focal Point");
        	
        //Step 3
        navigation.Configuration("Focal Point");
        FocalPoint focalPoint = new FocalPoint(webDriver);
        focalPoint.ClickStandardImageFieldsCbx();
        focalPoint.ClickMediaModuleImageFieldsCbx();
        focalPoint.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        navigation.Configuration("Image styles");	
        ImageStyles imageStyles = new ImageStyles(webDriver);
        if (imageStyles.FocalImageStylePresent("AutomationFocalStyle") == true) {
        	
        	imageStyles.ClickDeleteStyleLnk("AutomationFocalStyle");
        	Delete delete = new Delete(webDriver);
        	delete.ClickDeleteBtn();
        	contentParent.VerifyMessageStatus("Style AutomationFocalStyle was deleted.");
        	
        }
        		
        //Step 4
        navigation.Configuration("Image styles");
        imageStyles.ClickAddStyleLnk();
        imageStyles.EnterStyleName("AutomationFocalStyle");
        imageStyles.ClickCreateNewStyleBtn();
            	
        //Step 5
        imageStyles.SelectEffect("Focal Point Crop");
        imageStyles.ClickAddBtn();
            	
        //Step 6
        AddFocalPointCropEffect addFocalPointCropEffect = new AddFocalPointCropEffect(webDriver);
        addFocalPointCropEffect.EnterWidth("200");
        addFocalPointCropEffect.EnterHeight("200");
        addFocalPointCropEffect.ClickAddEffectBtn();
        contentParent.VerifyMessageStatus("The image effect was successfully applied.");
            	
        //Step 7
        imageStyles.ClickUpdateStyleBtn();
        contentParent.VerifyMessageStatus("Changes to the style have been saved.");
        contentParent.VerifyPageContentPresent(Arrays.asList("AutomationFocalStyle", "200px"));
            	
        //Step 8
        navigation.Structure("Content types");
        ContentTypes contentTypes = new ContentTypes(webDriver);
        contentTypes.ClickManageDisplayLnk("Movie");
        ManageDisplay manageDisplay = new ManageDisplay(webDriver);
        manageDisplay.SelectCoverMediaFormat("Rendered file");
        manageDisplay.VerifyDefaultViewModeSelected();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
            	
        //Step 9
        navigation.Structure("File types");
        FileTypes fileTypes = new FileTypes(webDriver);
        fileTypes.ClickManageFileDisplayLnk("Image");
        ManageFileDisplay manageFileDisplay = new ManageFileDisplay(webDriver);
        manageFileDisplay.CheckImageCbx();
        manageFileDisplay.SelectImageStyle("AutomationFocalStyle");
        manageFileDisplay.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        	
        //Step 10
        navigation.AddContent("Movie");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String movieTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver);
        selectFile.SwitchToSelectFileFrm();
        selectFile.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "HanSolo.jpg");
        selectFile.ClickUploadBtn();
        selectFile.WaitForFileUploaded("HanSolo.jpg");
        selectFile.ClickNextBtn();
        publicFileOptionPresent = selectFile.ClickPublicLocalFilesRdb();
        if (publicFileOptionPresent == true) {
    		selectFile.ClickNextBtn();
    	}
        selectFile.VerifyFileImagePresent("HanSolo");
                
        //Step 11
        selectFile.DoucleClickFocalPointIndicator();
        selectFile.VerifyFocalPointCoordinates("50,50");
        selectFile.ClickSaveBtn();
        webDriver.switchTo().defaultContent();
        selectFile.WaitForSelectFileFrameClose();
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            	
        //Step 12
        WorkBench workBench = new WorkBench(webDriver);
        workBench.VerifyFileImageLinkPresent("HanSolo", "1");
        //workBench.VerifyFileImagePresent("HanSolo", "1");
        //workBench.VerifyFileImageSize("1", "200", "200");
            
        //Cleanup
        navigation.Modules();
        modules.DisableModule("Focal Point");
        	
    }
}
