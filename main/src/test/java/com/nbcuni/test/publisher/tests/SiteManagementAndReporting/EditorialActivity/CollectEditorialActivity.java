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
        	navigation.Modules();
        	Modules modules = new Modules(webDriver);
        	if (modules.IsModuleEnabled("Entity Tracker")) {
        		modules.EnterFilterName("Entity Tracker");
        		modules.DisableModule("Entity Tracker");
        		navigation.ClickPrimaryTabNavLnk("Uninstall");
        		modules.UninstallModule("Entity Tracker");
        		
        		navigation.Modules();
        		modules.EnterFilterName("Entity Tracker");
        		modules.EnableModule("Entity Tracker");
        	}
        	else {
        		modules.EnterFilterName("Entity Tracker");
        		modules.EnableModule("Entity Tracker");
        	}
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Entity tracker");
        	EntityTracker entityTracker = new EntityTracker(webDriver);
        	for (String role : Arrays.asList("administrator", "editor", "senior editor")) {
        		entityTracker.CheckRoleCbx(role);
        	}
        	entityTracker.ClickSaveConfigurationBtn();
        	contentParent.VerifyPageContentPresent(Arrays.asList("The configuration options have been saved."));
        	
        	Reporter.log("STEP 4");
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        	createDefaultContent.Post("Draft");
        	
        	Reporter.log("STEP 5");
        	navigation.Reports("Entity tracker report");
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Date currentDate = new Date();
        	EntityTrackerReports entityTrackerReports = new EntityTrackerReports(webDriver);
        	entityTrackerReports.EnterFromDate(pub7DateFormat.format(currentDate));
        	entityTrackerReports.EnterToDate(pub7DateFormat.format(currentDate));
        	entityTrackerReports.ClickApplyBtn();
        	
        	Reporter.log("STEP 6 - N/A");
        	
        	Reporter.log("STEP 7");
        	ErrorChecking errorChecking = new ErrorChecking(webDriver);
        	errorChecking.VerifyNoMessageErrorsPresent();
        	entityTrackerReports.ClickParentArrayElementLnk();
        	entityTrackerReports.ClickChildArrayElementLnk();
        	Integer childNodeID = entityTrackerReports.GetChildNodeId();
        	entityTrackerReports.ClickChildArrayInfoElementLnk();
        	String childTitle = entityTrackerReports.GetChildTitle();
        	
        	Reporter.log("STEP 8");
        	applib.openSitePage("/node/" + childNodeID.toString());
        	contentParent.VerifyPageContentPresent(Arrays.asList(childTitle));
        	
        	//TODO - some additional steps as time allows
            
    }
}
