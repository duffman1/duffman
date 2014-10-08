package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Person;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
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
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Person");
            
            //Step 3
            overlay.SwitchToFrame("Create Person");
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            
            //Step 4
            overlay.switchToDefaultContent(true);
            overlay.SwitchToFrame("Create Person");
            personsInformation.ClickCoverPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            PageFactory.initElements(webDriver, selectFile);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Person");
            ContentParent contentParent = new ContentParent(webDriver);
            PageFactory.initElements(webDriver, contentParent);
            contentParent.ClickSaveBtn();
            
            //Step 5
            overlay.switchToDefaultContent(true);
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            
    }
}
