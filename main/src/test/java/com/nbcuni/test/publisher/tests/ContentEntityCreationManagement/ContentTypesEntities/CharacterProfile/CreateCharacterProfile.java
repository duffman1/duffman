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

import com.nbcuni.test.publisher.CharacterProfile;
import com.nbcuni.test.publisher.ContentTypes;
import com.nbcuni.test.publisher.AppLib;
import com.nbcuni.test.publisher.SelectFile;
import com.nbcuni.test.publisher.Logout;
import com.nbcuni.test.publisher.Overlay;
import com.nbcuni.test.publisher.Taxonomy;
import com.nbcuni.test.publisher.UserLogin;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CreateCharacterProfile {
	
	private CustomWebDriver webDriver;
    private AppLib applib;

    /**
     * Instantiate the TestNG Before Class Method.
     * 
     * @param sEnv - environment
     * @throws Exception - error
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("Environment")
    public void startSelenium(@Optional("PROD") String sEnv) {
        try {
            webDriver = WebDriverClientExecution.getInstance().getDriver();
            applib = new AppLib(webDriver);
            applib.setEnvironmentInfo(sEnv);
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /**
     * Instantiate the TestNG After Class Method.
     * 
     * @throws Exception - error
     */
    @AfterMethod(alwaysRun = true)
    public void stopSelenium() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            applib.fail(e.toString());
        }

    }

    /*************************************************************************************
     * TEST CASE 3106 Adding new custom content type
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on Content >> add new content >> Character Profile<br>
     * Step 3 - Populate the valid value in the following mandatory fields: Character, First Name OR Character, Last Name<br>
     * Step 4 - Click on "Select" button under Cover Media. Click on "Next" button twice, then click on "Save" button<br>
     * Step 5 - Click on the "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.MouseOverTier1ContentLnk();
            taxonomy.MouseOverTier1ContentTier2AddContentLnk();
            taxonomy.ClickTier1ContentTier2AddContentTier3CharacterProfileLnk();
            
            //Step 3
            CharacterProfile characterProfile = new CharacterProfile(webDriver);
            characterProfile.SwitchToCreateCharacterProfileFrm();
            Random random = new Random();
            String characterName = random.GetCharacterString(15);
            characterProfile.EnterCharacterFirstName(characterName);
            
            //Step 4
            characterProfile.ClickAddPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver);
            selectFile.SwitchToSelectFileFrm();
            //TODO - this needs to be modified to find a file from a local file repository.
            //ultimately it will need to leverage the remote file upload feature of webdriver for sauce execution
            selectFile.EnterFilePath("/Users/brandonclark/Desktop/HanSolo.jpg");
            selectFile.ClickUploadBtn();
            selectFile.WaitForFileUploaded("HanSolo.jpg");
            selectFile.ClickNextBtn();
            selectFile.ClickPublicLocalFilesRdb();
            selectFile.ClickNextBtn();
            selectFile.VerifyFileImagePresent("HanSolo");
            selectFile.ClickSaveBtn();
            applib.switchToDefaultContent();
            characterProfile.SwitchToCreateCharacterProfileFrm();
            characterProfile.VerifyDefaultImagePresent("HanSolo");
            
            //Step 5
            characterProfile.ClickSaveBtn();
            applib.switchToDefaultContent();
            characterProfile.VerifyCharacterProfileSaved(characterName);
            
       
    }
}
