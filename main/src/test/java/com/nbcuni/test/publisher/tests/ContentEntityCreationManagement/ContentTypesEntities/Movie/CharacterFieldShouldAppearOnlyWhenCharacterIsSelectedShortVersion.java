package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.Movie;


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
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class CharacterFieldShouldAppearOnlyWhenCharacterIsSelectedShortVersion extends ParentTest{
	

    /*************************************************************************************
     * TEST CASE 3106 Adding new custom content type
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Go to "Content" >> "Add Content" >> "Movie"<br>
     * Step 3 - Click on the "Cast/Crew" tab, and then in turn select every "Role" option except "Character". These include:
     * Contributor
     * Directory
     * Executive Producer
     * Host
     * Judge
     * Producer
     * Self
     * Song Writer
     * Writer
     * Step 4 - In the "Role" list, click "Character"<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full" })
    public void CharacterFieldShouldAppearOnlyWhenCharacterIsSelectedShortVersion() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
            
            //Step 2
            taxonomy.NavigateSite("Content>>Add content>>Movie");
            
            //Step 3
            CastCrew castCrew = new CastCrew(webDriver);
            overlay.SwitchToFrame("Movie");
            castCrew.ClickCastCrewLnk();
            List<String> allRoles = Arrays.asList("Contributor", "Directory", "Executive Producer", "Host",
            		"Judge", "Producer", "Self", "Song Writer", "Writer");
            for (String role : allRoles) {
            	
            	castCrew.SelectRole(role); Thread.sleep(1000);
            	castCrew.VerifyCharacterTxbNotDisplayed();
            	
            }
            
            //Step 4
            castCrew.SelectRole("Character");
            castCrew.VerifyCharacterTxbDisplayed();
            
            
    }
}
