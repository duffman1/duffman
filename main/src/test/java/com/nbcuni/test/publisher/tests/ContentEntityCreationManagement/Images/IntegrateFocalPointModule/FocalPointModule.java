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
     * TEST CASE 
     * Step 1 - Login to the site as admin with valid credentials.,Observe that user should be successfully logged in.
     * Step 2 - Click on Module and enable Focal Point and click on Save Configuration button. ,Observe that Focal Point module should be successfully enabled. 
     * Step 3 - Navigate to Configuration-> Media-> Focal Point then enable 'Standard image fields' and 'Media module image fields' checkboxes, and choose "Thumbnail" value from 'Preview Image Style' drop down and click on 'Save configuration' button.,Observe that 'Standard image fields' and 'Media module image fields' checkboxes should be enabled and correct value should be displayed in 'Preview Image Style' drop down.  
     * Step 4 - Navigate to Configuration-> Media-> Image styles-> Add style then type valid name in 'Style name' text box and click on 'Create new style' button. ,Observe that image style should be successfully saved and successful message should be displayed. 
     * Step 5 - Choose 'Focal Point Crop' from the drop down under 'Effect' column then click on 'Add' button.  Additional Scenarios: Test with 'Focal Point scale and crop' effect. Out of scope scenario: Don't test with 'Rotate' effect .,Observe that 'Add Focal Point Crop effect' overlay is displayed. 
     * Step 6 - Type 200 in both 'Width' and 'Height' text box then click on 'Add effect' button. ,Observe that values should be saved and successful message should be displayed. 
     * Step 7 - Click on 'Update style' button. ,Observe that image style should be successfully saved and successful message should be displayed. 
     * Step 8 - Go to Structure -> Content type (movie) -> Manage Display -> Cover Media -> Manage Display-> In 'Cover Media' field column -> In 'FORMAT' column-> Set 'Rendered file'-> Click on the contextual link on the right of cover media row -> Choose the view mode as 'Default' -> Save. ,Setting are saved sucessfully 
     * Step 9 - Go to Structure -> File type-> Image-> Manage File Display-> Default tab-> Under Image Style field Set the image style created in configuration test set -> Save Configuration. ,Setting are saved successfully 
     * Step 10 - Navigate to Content-> Add Content-> Content type (Movie)-> Set the mandatory fields-> Click on Select on Cover Media field-> upload a image. ,Image should be uploaded successfully and Select a file overlay appears with the image and crosshair. 
     * Step 11 - Drag a cross hair to the desired focal point on the image and double click > Verify that Focal point fields with coordinates appears > Save,Content type (Movie) gets Saved 
     * Step 12 - Verify that the on the view tab of movie content type node created, 200*200 square image w.r.t. the focal point appears. ,Image appears as expected 
     * Cleanup - Disable the focal point module
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void FocalPointModule_Test() throws Exception {
         
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
        workBench.VerifyFileImagePresent("HanSolo", "1");
        workBench.VerifyFileImageSize("1", "200", "200");
            
        //Cleanup
        taxonomy.NavigateSite("Modules");
        overlay.SwitchToActiveFrame();
        modules.EnterFilterName("Focal Point");
        modules.DisableModule("Focal Point");
        	
    }
}
