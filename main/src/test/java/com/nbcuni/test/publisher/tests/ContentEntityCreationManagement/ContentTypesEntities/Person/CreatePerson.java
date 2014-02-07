package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Person;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;

public class CreatePerson extends ParentTest{

    /*************************************************************************************
     * TEST CASE 3106 Adding new custom content type
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on Content --> Add content --> person<br>
     * Step 3 - Populate the valid value in following mandatory fields: 'Character: First Name' or 'Character: Last Name' 'Biography'<br>
	 * Step 4 - Click on 'Select' button under Cover Media, click on 'Browse' button and choose the image from the local machine. Click on 'Next' button twice then click on 'Save' button.<br>
     * Step 5 - Click on 'Save' button.<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void CreatePerson_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	PageFactory.initElements(webDriver, userLogin);
            userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Person");
            
            //Step 3
            overlay.SwitchToFrame("Create Person");
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            personsInformation.EnterBiography();
            
            //Step 4
            overlay.switchToDefaultContent();
            overlay.SwitchToFrame("Create Person");
            personsInformation.ClickCoverPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            PageFactory.initElements(webDriver, selectFile);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToFrame("Create Person");
            ContentParent contentParent = new ContentParent(webDriver, applib);
            PageFactory.initElements(webDriver, contentParent);
            contentParent.ClickSaveBtn();
            
            //Step 5
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            
    }
}
