package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.IntegrateFocalPointModule;

import java.util.Arrays;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.AddFocalPointCropEffect;
import com.nbcuni.test.publisher.pageobjects.Configuration.FocalPoint;
import com.nbcuni.test.publisher.pageobjects.Configuration.ImageStyles;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.FileTypes.ManageFileDisplay;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageDisplay;
import org.testng.annotations.Test;

public class FocalPointModule extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1052
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441593285
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FocalPointModule_TC1052() throws Exception {
         
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        //Step 2
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Focal Point");
        	
        //Step 3
        taxonomy.NavigateSite("Configuration>>Media>>Focal Point");
        overlay.SwitchToActiveFrame();
        FocalPoint focalPoint = new FocalPoint(webDriver);
        focalPoint.ClickStandardImageFieldsCbx();
        focalPoint.ClickMediaModuleImageFieldsCbx();
        focalPoint.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("The configuration options have been saved.");
        overlay.ClickCloseOverlayLnk();
        	
        taxonomy.NavigateSite("Configuration>>Media>>Image styles");
        overlay.SwitchToActiveFrame();
        ImageStyles imageStyles = new ImageStyles(webDriver, applib);
        if (imageStyles.FocalImageStylePresent("AutomationFocalStyle") == true) {
        	
        	imageStyles.ClickDeleteStyleLnk("AutomationFocalStyle");
        	overlay.SwitchToActiveFrame();
        	Delete delete = new Delete(webDriver);
        	delete.ClickDeleteBtn();
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyMessageStatus("Style AutomationFocalStyle was deleted.");
        	
        }
        		
        //Step 4
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Configuration>>Media>>Image styles>>Add style");
        overlay.SwitchToActiveFrame();
        imageStyles.EnterStyleName("AutomationFocalStyle");
        imageStyles.ClickCreateNewStyleBtn();
            	
        //Step 5
        imageStyles.SelectEffect("Focal Point Crop");
        imageStyles.ClickAddBtn();
            	
        //Step 6
        overlay.SwitchToActiveFrame();
        AddFocalPointCropEffect addFocalPointCropEffect = new AddFocalPointCropEffect(webDriver);
        addFocalPointCropEffect.EnterWidth("200");
        addFocalPointCropEffect.EnterHeight("200");
        addFocalPointCropEffect.ClickAddEffectBtn();
        contentParent.VerifyMessageStatus("The image effect was successfully applied.");
            	
        //Step 7
        imageStyles.ClickUpdateStyleBtn();
        contentParent.VerifyMessageStatus("Changes to the style have been saved.");
        contentParent.VerifyPageContentPresent(Arrays.asList("AutomationFocalStyle", "200px"));
        overlay.ClickCloseOverlayLnk();
            	
        //Step 8
        taxonomy.NavigateSite("Structure>>Content types>>Movie>>Manage display");
        overlay.SwitchToActiveFrame();
        ManageDisplay manageDisplay = new ManageDisplay(webDriver);
        manageDisplay.SelectCoverMediaFormat("Rendered file");
        manageDisplay.VerifyDefaultViewModeSelected();
        manageDisplay.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        overlay.ClickCloseOverlayLnk();
            	
        //Step 9
        taxonomy.NavigateSite("Structure>>File types>>Image>>Manage file display");
        overlay.SwitchToActiveFrame();
        ManageFileDisplay manageFileDisplay = new ManageFileDisplay(webDriver, applib);
        manageFileDisplay.CheckImageCbx();
        manageFileDisplay.SelectImageStyle("AutomationFocalStyle");
        manageFileDisplay.ClickSaveConfigurationBtn();
        contentParent.VerifyMessageStatus("Your settings have been saved.");
        	
        //Step 10
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Content>>Add content>>Movie");
        overlay.SwitchToActiveFrame();
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String movieTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(movieTitle);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        selectFile.SwitchToSelectFileFrm();
        selectFile.EnterFilePath(applib.getPathToMedia() + "HanSolo.jpg");
        selectFile.ClickUploadBtn();
        selectFile.WaitForFileUploaded("HanSolo.jpg");
        selectFile.ClickNextBtn();
        selectFile.ClickPublicLocalFilesRdb();
        selectFile.ClickNextBtn();
        selectFile.VerifyFileImagePresent("HanSolo");
                
        //Step 11
        selectFile.DoucleClickFocalPointIndicator();
        selectFile.VerifyFocalPointCoordinates("50,50");
        selectFile.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Movie " + movieTitle + " has been created.");
            	
        //Step 12
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.VerifyFileImageLinkPresent("HanSolo", "1");
        //workBench.VerifyFileImagePresent("HanSolo", "1");
        //workBench.VerifyFileImageSize("1", "200", "200");
            
        //Cleanup
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        modules.EnterFilterName("Focal Point");
        modules.DisableModule("Focal Point");
        	
    }
}
