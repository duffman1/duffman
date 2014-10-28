package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Person;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;

public class CreatePerson extends ParentTest{

    /*************************************************************************************
     * TEST CASE - TC1044
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441340093
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void CreatePerson_TC1044() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Person");
            
            //Step 3
            overlay.SwitchToActiveFrame();
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            
            //Step 4
            overlay.SwitchToActiveFrame();
            personsInformation.ClickCoverPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToActiveFrame();
            contentParent.ClickSaveBtn();
            
            //Step 5
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            
    }
}
