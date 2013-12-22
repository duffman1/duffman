package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Person;


import java.util.Arrays;
import java.util.List;

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
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


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
    @Test(groups = {"full", "smoke" })
    public void CreatePerson() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ContentTier2AddContentTier3PersonLnk();
            
            //Step 3
            Overlay overlay = new Overlay(webDriver);
            overlay.SwitchToCreatePersonFrm();
            PersonsInformation personsInformation = new PersonsInformation(webDriver);
            Random random = new Random();
            String personFirstName = random.GetCharacterString(15);
            personsInformation.EnterFirstName(personFirstName);
            String biography = personsInformation.EnterBiography();
            
            //Step 4
            overlay.switchToDefaultContent();
            overlay.SwitchToCreatePersonFrm();
            personsInformation.ClickCoverPhotoSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToCreatePersonFrm();
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            
            //Step 5
            overlay.switchToDefaultContent();
            contentParent.VerifyMessageStatus("Person " + personFirstName + " has been created.");
            
            
    }
}
