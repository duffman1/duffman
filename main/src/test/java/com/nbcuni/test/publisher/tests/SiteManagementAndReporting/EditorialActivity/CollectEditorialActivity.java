package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.EditorialActivity;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.EntityTracker;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Reports.EntityTrackerReports;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CollectEditorialActivity extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC5071
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/22667340545
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CollectEditorialActivity_TC5071() throws Exception {
         
    		webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("Entity Tracker");
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Entity tracker");
        	EntityTracker entityTracker = new EntityTracker(webDriver);
        	for (String role : Arrays.asList("administrator", "editor", "senior editor")) {
        		entityTracker.CheckRoleCbx(role);
        	}
        	entityTracker.ClickSaveConfigurationBtn();
        	contentParent.VerifyPageContentPresent(Arrays.asList("The configuration options have been saved."));
        	
        	Reporter.log("STEP 4 - N/A");
        	//test uses an existing content from several days ago
        	
        	Reporter.log("STEP 5");
        	navigation.Reports("Entity tracker report");
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Calendar cal7DaysPast = Calendar.getInstance();
        	Calendar cal1DaysPast = Calendar.getInstance();
        	cal7DaysPast.add(Calendar.DATE, -7);
        	cal1DaysPast.add(Calendar.DATE, -1);
        	Date date7DaysPast = cal7DaysPast.getTime();
        	Date date1DaysPast = cal1DaysPast.getTime();
        	EntityTrackerReports entityTrackerReports = new EntityTrackerReports(webDriver);
        	entityTrackerReports.EnterFromDate(pub7DateFormat.format(date7DaysPast));
        	entityTrackerReports.EnterToDate(pub7DateFormat.format(date1DaysPast));
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
