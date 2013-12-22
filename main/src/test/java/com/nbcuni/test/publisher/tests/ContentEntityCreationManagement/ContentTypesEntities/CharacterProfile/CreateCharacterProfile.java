package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.CharacterProfile;


import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CreateCharacterProfile extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on Content >> add new content >> Character Profile<br>
     * Step 3 - Populate the valid value in the following mandatory fields: Character, First Name OR Character, Last Name<br>
     * Step 4 - Click on "Select" button under Cover Media. Click on "Next" button twice, then click on "Save" button<br>
     * Step 5 - Click on the "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreateCharacterProfile() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ContentTier2AddContentTier3CharacterProfileLnk();
            
            //Step 3
            CharactersInformation charactersInformation = new CharactersInformation(webDriver);
            Overlay overlay = new Overlay(webDriver);
            overlay.SwitchToCreateCharacterProfileFrm();
            Random random = new Random();
            String characterName = random.GetCharacterString(15);
            charactersInformation.EnterCharacterFirstName(characterName);
            
            //Step 4
            charactersInformation.ClickAddPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToCreateCharacterProfileFrm();
            charactersInformation.VerifyDefaultImagePresent("HanSolo");
            
            //Step 5
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Character Profile " + characterName + " has been created.");
            
       
    }
}
