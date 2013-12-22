package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.MediaGallery;


import java.awt.Robot;
import java.util.Arrays;

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
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CreateMediaGallery extends ParentTest{
	
	
    /*************************************************************************************
     * TEST CASE Create Media Gallery
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on "Content" >> "Add Content" >> "Media Gallery"<br>
     * Step 3 - Populate the valid value in the following mandatory fields: "Title"<br>
     * Step 4 - Click on "Select" button under "Cover Media", click on "Browse" button and choose the image from the local machine. Click on "Next" button twice then click on "Save" button.<br>
     * Step 5 [UNABLE TO AUTOMATE THIS STEP] - Click on "Select media" button under "Media" items. Drag and drop 3 or more images from the local machine and click on "Next" button<br>
     * Step 6 - Click on "Save" button<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreateMediaGallery() throws Exception{
        
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
            taxonomy.ClickTier1ContentTier2AddContentTier3MediaGalleryLnk();
            
            //Step 3
            BasicInformation basicInformation = new BasicInformation(webDriver);
            Overlay overlay = new Overlay(webDriver);
            overlay.SwitchToCreateMediaGalleryFrm();
            Random random = new Random();
            String title = random.GetCharacterString(15);
            basicInformation.EnterTitle(title);
            
            //Step 4
            basicInformation.ClickCoverSelectBtn();
            SelectFile selectFile = new SelectFile(webDriver, applib);
            selectFile.SelectDefaultCoverImg();
            overlay.SwitchToCreateMediaGalleryFrm();
            basicInformation.VerifyCoverImagePresent("HanSolo");
            
            //Step 5
            /* TODO - find a way to get multiple file uploads working
            mediaGallery.ClickMediaItemsSelectMediaBtn();
            selectFile.SwitchToSelectFileFrm();
            
            selectFile.AddMultipleFiles(Arrays.asList("/Users/brandonclark/Desktop/HanSolo.jpg", 
            		"/Users/brandonclark/Desktop/HanSolo.jpg", 
            			"/Users/brandonclark/Desktop/HanSolo.jpg"));
            
            */
            
            //Step 6
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.ClickSaveBtn();
            contentParent.VerifyMessageStatus("Media Gallery " + title + " has been created.");
            
        
    }
}
