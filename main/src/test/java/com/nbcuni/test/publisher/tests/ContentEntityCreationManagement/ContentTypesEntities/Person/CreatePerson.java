package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Person;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

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
        	navigation.AddContent("Person");
            
            //Step 3
            PersonsInformation personsInformation = new PersonsInformation(webWebWebDriver);
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            
            //Step 4
            personsInformation.ClickCoverPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webWebWebDriver);
            selectFile.SelectDefaultCoverImg();
            contentParent.ClickSaveBtn();
            
            //Step 5
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            
    }
}
