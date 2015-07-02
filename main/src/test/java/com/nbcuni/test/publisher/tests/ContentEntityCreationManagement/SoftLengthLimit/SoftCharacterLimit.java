package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.SoftLengthLimit;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.SoftLength;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.ManageFieldInput;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class SoftCharacterLimit extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC1053
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441597655
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SoftCharacterLimit_TC1053() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = appLib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
            //Step 2 through 4
        	navigation.Structure("Content types");
        	ContentTypes contentTypes = new ContentTypes(webDriver);
        	contentTypes.ClickManageFieldLnk("Character Profile");
        	contentTypes.ClickEditLnk("Character: First Name");
        	
        	//Step 5
        	ManageFieldInput manageFieldInput = new ManageFieldInput(webDriver);
        	manageFieldInput.EnterSoftLengthLimit("150");
        	
        	//Step 6
        	manageFieldInput.EnterSoftLengthMinimum("10");
        	
        	//Step 7
        	manageFieldInput.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Character: First Name configuration.");
        	
        	//Step 8
        	navigation.AddContent("Character Profile");
            
            //Step 9
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            String characterNameUnder = random.GetCharacterString(4);
            String characterNameMeets = random.GetCharacterString(15);
            String characterNameExceeds = random.GetCharacterString(160);
            charactersInformation.EnterCharacterFirstName(characterNameUnder);
            SoftLength softLength = new SoftLength(webDriver);
            softLength.VerifySoftLengthUnderLimit("4/150");
            charactersInformation.EnterCharacterFirstName(characterNameMeets);
            softLength.VerifySoftLengthMeetsLimit("15/150");
            charactersInformation.EnterCharacterFirstName(characterNameExceeds);
            softLength.VerifySoftLengthExceedsLimit("160/150");
            
            //Step 10
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Character Profile " + characterNameExceeds + " has been created.");
            
            //Step 11
            navigation.Structure("Content types");
        	contentTypes.ClickManageFieldLnk("Character Profile");
        	contentTypes.ClickEditLnk("Character: First Name");
            manageFieldInput.EnterSoftLengthLimit("");
        	manageFieldInput.EnterSoftLengthMinimum("");
        	manageFieldInput.ClickSaveSettingsBtn();
        	contentParent.VerifyMessageStatus("Saved Character: First Name configuration.");
        	navigation.AddContent("Character Profile");
        	
            //Step 12
            charactersInformation.EnterCharacterFirstName(characterNameUnder);
            softLength.VerifySoftLengthUnderLimitNotPresent("4/150");
            charactersInformation.EnterCharacterFirstName(characterNameMeets);
            softLength.VerifySoftLengthMeetsLimitNotPresent("15/150");
            charactersInformation.EnterCharacterFirstName(characterNameExceeds);
            softLength.VerifySoftLengthExceedsLimitNotPresent("160/150");
            
       
    }
}
