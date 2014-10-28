package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CharacterProfile;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

public class CreateCharacterProfile extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1041
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441338760
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreateCharacterProfile_TC1041() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Character Profile");
            
            //Step 3
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            overlay.SwitchToActiveFrame();
            String characterName = random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            
            //Step 4
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Character Profile");
            coverPhoto.VerifyFileImagePresent("HanSolo");
            
            //Step 5
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
    }
}
