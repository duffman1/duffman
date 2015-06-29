package com.nbcuni.test.publisher.tests.Advertising.MPS;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Configuration.MPSConfiguration;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewView;
import com.nbcuni.test.publisher.pageobjects.Structure.Views;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MPSURLPathSegment extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC6529
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/27886655326
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MPSURLPathSegment_TC6529() throws Exception {
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("SETUP");
        	navigation.Configuration("MPS Configuration");
        	MPSConfiguration mpsConfiguration = new MPSConfiguration(webWebWebDriver);
            mpsConfiguration.EnterMPSHost("stage-mps.nbcuni.com");
            mpsConfiguration.ClickIntegrationMethod("Document Write");
            mpsConfiguration.EnterSiteInstanceOverride("pub7-development");
            mpsConfiguration.CheckSendQueryStringsCbx();
            mpsConfiguration.CleanAllMPSOptions();
            mpsConfiguration.ClickSaveConfigurationBtn();
            contentParent.VerifyMessageStatus("The configuration options have been saved.");
            
        	Reporter.log("STEP 2");
        	navigation.Modules();
        	Modules modules = new Modules(webWebWebDriver);
        	modules.EnableModule("MPS");
        	modules.EnableModule("Views UI");
        	
        	Reporter.log("STEP 3 - N/A");
        	
        	Reporter.log("STEP 4");
        	navigation.Structure("Views");
        	Views views = new Views(webWebWebDriver);
        	views.ClickAddNewViewLnk();
        	AddNewView addNewView = new AddNewView(webWebWebDriver);
        	String viewName = random.GetCharacterString(15);
        	addNewView.EnterViewName(viewName);
        	addNewView.SelectOfType("Movie");
        	contentParent.WaitForThrobberNotPresent();
        	String randomPath = random.GetCharacterString(10);
        	addNewView.EnterPath(randomPath + "/foo/bar");
        	addNewView.ClickSaveAndExitBtn();
        	contentParent.VerifyPageContentPresent(Arrays.asList(viewName));
        	
        	Reporter.log("STEP 5");
        	contentParent.VerifySourceInPage(Arrays.asList("\"cat\":\"" + randomPath + "|foo|bar\""));
            
        	//remaining steps redundant. Not necessary for automation.
        	
        	Reporter.log("CLEANUP");
        	navigation.Structure("Views");
        	views.ClickEditExtendMenuBtn(viewName);
        	views.ClickDeleteMenuBtn(viewName);
        	Delete delete = new Delete(webWebWebDriver);
        	delete.ClickDeleteBtn();
        	contentParent.VerifyMessageStatus("The view has been deleted.");
            
    }
}
