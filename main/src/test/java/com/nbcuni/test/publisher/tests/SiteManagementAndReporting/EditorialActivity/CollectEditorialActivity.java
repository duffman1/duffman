package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.EditorialActivity;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.EntityTracker;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Reports.EntityTrackerReports;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class CollectEditorialActivity extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC5071
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/22667340545
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CollectEditorialActivity_TC5071() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("Entity Tracker");
        	
        	Reporter.log("STEP 3");
        	taxonomy.NavigateSite("Configuration>>Content authoring>>Entity tracker");
        	overlay.SwitchToActiveFrame();
        	EntityTracker entityTracker = new EntityTracker(webDriver);
        	for (String role : Arrays.asList("administrator", "editor", "senior editor")) {
        		entityTracker.CheckRoleCbx(role);
        	}
        	entityTracker.ClickSaveConfigurationBtn();
        	contentParent.VerifyPageContentPresent(Arrays.asList("The configuration options have been saved."));
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 4");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        	String postTitle = createDefaultContent.Post("Draft");
        	
        	Reporter.log("STEP 5");
        	applib.openSitePage("/#overlay=admin/reports/entity_tracker");
        	overlay.SwitchToActiveFrame();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Date currentDate = new Date();
        	EntityTrackerReports entityTrackerReports = new EntityTrackerReports(webDriver);
        	entityTrackerReports.EnterFromDate(pub7DateFormat.format(currentDate));
        	entityTrackerReports.EnterToDate(pub7DateFormat.format(currentDate));
        	entityTrackerReports.ClickApplyBtn();
        	overlay.switchToDefaultContent(true);
        	
        	Reporter.log("STEP 6 - N/A");
        	
        	Reporter.log("STEP 7");
        	ErrorChecking errorChecking = new ErrorChecking(webDriver);
        	errorChecking.VerifyNoMessageErrorsPresent();
        	contentParent.VerifyPageContentPresent(Arrays.asList(postTitle, "Created", "Post"));
        	
        	Reporter.log("STEP 8");
        	entityTrackerReports.ClickContentLnk(postTitle);
        	contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        	
        	//TODO - some additional steps as time allows
            
    }
}
