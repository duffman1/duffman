package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.SoftLengthLimit;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.SoftLength;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFieldInput;

public class AddCharacterCountOptionTextFields extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Step 1 - Log in as admin. ,User should be successfully logged in. 
     * Step 2 - Start editing a content type (e.g., Character Profile)  via  Structure > Content type > Character Profile to add the limits to a title, textfield, or textarea based field.  Note: You must change the Text Processing option to plain text for soft length limit to work properly on an existing content type field where the option was previously set to Filtered Text). For content types that use node auto title to generate the title field, the soft length limit fields for the title can be found on the Submission form settings tab.,Length limits are added to "Title" field successfully
     * Step 3 - Click on Manage fields tab at the top of the page (you can also add a limit and minimum to titles if the content type has a Title field).,
     * Step 4 - Select one of the text fields (for example, Character Last Name) and edit it via the edit link.,
     * Step 5 - Optionally enter a maximum preferred length in the Soft Length Limit field  (for example, 120).,
     * Step 6 - Optionally enter a Soft Length Minimum value (for example, 20).,
     * Step 7 - Click Save settings or continue adding limits to other fields by following Steps 4 through 6.  Note: This only works for text fields, textarea, and title fields.,Configuration saved successfully. 
     * Step 8 - Next, verify the acceptance criteria below by creating a new Character Profile: Content > Add content -> Character profile. ,Create Character profile page opens.
     * Step 9 - Enter text in the field that you set the limits on. ,A counter starts, indicating characters currently entered and maximum limit. 1) If character count equals minimum character count then a Green symbol appears on left of the counter. 2)  If character count goes above maximum character count then a Red symbol appears on left of the counter  NOTE: Currently only red color symbol is displayed.  
     * Step 10 - Save content item.
     * Step 11 - Turn off soft length limit.
     * Step 12 - Verify soft length notifications not present in content item.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void AddCharacterCountOptionTextFields_Test() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        	
            //Step 2 through 4
        	taxonomy.NavigateSite("Structure>>Content types>>Character Profile>>Manage fields>>Character: First Name");
        	overlay.SwitchToActiveFrame();
        	
        	//Step 5
        	ManageFieldInput manageFieldInput = new ManageFieldInput(webDriver);
        	manageFieldInput.EnterSoftLengthLimit("150");
        	
        	//Step 6
        	manageFieldInput.EnterSoftLengthMinimum("10");
        	
        	//Step 7
        	manageFieldInput.ClickSaveSettingsBtn();
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyMessageStatus("Saved Character: First Name configuration.");
        	
        	//Step 8
        	overlay.ClickCloseOverlayLnk();
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            overlay.SwitchToActiveFrame();
            
            //Step 9
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            String characterNameUnder = random.GetCharacterString(4);
            String characterNameMeets = random.GetCharacterString(15);
            String characterNameExceeds = random.GetCharacterString(160);
            charactersInformation.EnterCharacterFirstName(characterNameUnder);
            SoftLength softLength = new SoftLength(webDriver, applib);
            softLength.VerifySoftLengthUnderLimit("4/150");
            charactersInformation.EnterCharacterFirstName(characterNameMeets);
            softLength.VerifySoftLengthMeetsLimit("15/150");
            charactersInformation.EnterCharacterFirstName(characterNameExceeds);
            softLength.VerifySoftLengthExceedsLimit("160/150");
            
            //Step 10
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Character Profile " + characterNameExceeds + " has been created.");
            
            //Step 11
            taxonomy.NavigateSite("Structure>>Content types>>Character Profile>>Manage fields>>Character: First Name");
        	overlay.SwitchToActiveFrame();
        	manageFieldInput.EnterSoftLengthLimit("");
        	manageFieldInput.EnterSoftLengthMinimum("");
        	manageFieldInput.ClickSaveSettingsBtn();
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyMessageStatus("Saved Character: First Name configuration.");
        	overlay.ClickCloseOverlayLnk();
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            overlay.SwitchToActiveFrame();
            
            //Step 12
            charactersInformation.EnterCharacterFirstName(characterNameUnder);
            softLength.VerifySoftLengthUnderLimitNotPresent("4/150");
            charactersInformation.EnterCharacterFirstName(characterNameMeets);
            softLength.VerifySoftLengthMeetsLimitNotPresent("15/150");
            charactersInformation.EnterCharacterFirstName(characterNameExceeds);
            softLength.VerifySoftLengthExceedsLimitNotPresent("160/150");
            
       
    }
}
