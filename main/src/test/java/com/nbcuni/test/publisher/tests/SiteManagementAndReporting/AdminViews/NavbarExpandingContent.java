package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.AdminViews;

import java.util.Arrays;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;

public class NavbarExpandingContent extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC5935
     * Steps - https://rally1.rallydev.com/#/14663927728/detail/testcase/25519987209
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void NavbarExpandingContent_TC5935() throws Exception {
    	
    	Reporter.log("STEP 1");
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	Reporter.log("STEP 2");
    	navigation.ShowMenu();
    	navigation.ClickMenuVerticalBtn();
    	
    	Reporter.log("STEP 3");
    	navigation.ClickPrimaryVerticalExpandLnk("Content");
    	
    	Reporter.log("STEP 4");
    	navigation.ClickSecondaryVerticalExpandLnk("Add content");
    	for (String contentType : Arrays.asList("Character Profile", "Media Gallery", "Person", "Post")) {
    		navigation.VerifyLevel3VerticalLnkVisible(contentType);
    	}
    	navigation.ClickSecondaryVerticalExpandLnk("Add content");
    	
    	Reporter.log("STEP 5");
    	navigation.ClickPrimaryVerticalExpandLnk("My Workbench");
    	navigation.ClickSecondaryVerticalExpandLnk("Create content");
    	for (String contentType : Arrays.asList("Character Profile", "Media Gallery", "Person", "Post")) {
    		navigation.VerifyLevel3VerticalLnkVisible(contentType);
    	}
    	navigation.ClickSecondaryVerticalExpandLnk("Scheduled");
    	for (String contentType : Arrays.asList("Content", "mpxMedia")) {
    		navigation.VerifyLevel3VerticalLnkVisible(contentType);
    	}
    	navigation.ClickSecondaryVerticalExpandLnk("Scheduled");
    	
    	Reporter.log("STEP 6");
    	navigation.ClickPrimaryVerticalExpandLnk("People");
    	navigation.ClickSecondaryVerticalExpandLnk("Permissions");
    	for (String contentType : Arrays.asList("Roles", "Apply Permission Set")) {
    		navigation.VerifyLevel3VerticalLnkVisible(contentType);
    	}
    	navigation.ClickSecondaryVerticalExpandLnk("Permissions");
    	
    	Reporter.log("STEP 7");
    	navigation.ClickPrimaryVerticalExpandLnk("Content");
    	navigation.ClickSecondaryVerticalExpandLnk("Add content");
    	navigation.ClickLevel3VerticalLnk("Post");
    	contentParent.VerifyPageContentPresent(Arrays.asList("Create Post", "Title"));
    	//TODO add some additional menu link checks but almost all of them are checked
    	//in other tests so most of this is redundant
    	
    }
}
