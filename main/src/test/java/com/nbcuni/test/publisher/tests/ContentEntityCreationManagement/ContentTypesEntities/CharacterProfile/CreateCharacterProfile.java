package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CharacterProfile;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CoverPhoto;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

public class CreateCharacterProfile extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC1041
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441338760
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreateCharacterProfile_TC1041() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = appLib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        	
            //Step 2
        	navigation.AddContent("Character Profile");
            
            //Step 3
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            String characterName = random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            
            //Step 4
            CoverPhoto coverPhoto = new CoverPhoto(webDriver);
            coverPhoto.ClickSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            coverPhoto.VerifyFileImagePresent("HanSolo");
            
            //Step 5
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
    }
}
