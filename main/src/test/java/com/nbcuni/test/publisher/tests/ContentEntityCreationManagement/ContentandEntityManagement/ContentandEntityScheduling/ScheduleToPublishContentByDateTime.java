package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.Queues.ScheduleQueue;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ScheduleToPublishContentByDateTime extends ParentTest {

	/*************************************************************************************
	* TEST CASE
	* Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
	* Step 2 - Create a Draft post<br>
	* Step 3 - Verify all Tabs (View, Edit Draft, Revisions, Schedule)
	* Step 4 - Click Revision Tab and Verify that There should be one revision<br>
	* Step 5 - Click on Schedule Tab and Verify that 'Add Schedule Version' and Schedule table is empty.<br>
	* Step 6 - Click on Edit Draft and Populate Revision Log and Synopsis<br>
	* Step 7 - Verify that Revision State,Public, Moderate fields displayed under workflow tab <br>
	* Step 8 - Click Revision Tab and Verify that there are two revision<br>
	* Step 9 - Click on Schedule Tab and Verify fields(Revision, Moderate State, Date and Time) and populate with past date and time and verify the failed message<br>
	* Step 10 - Click on Schedule Tab and Verify fields(Revision, Moderate State, Date and Time) and populate with future date and time and time and verify the success message<br>
	* Step 11 - Verify Schedule table for scheduled revision and Run Cron and verify that published revision displayed under revision tab<br>
	* @throws Throwable No Return values are needed
	*************************************************************************************/
    @Test(groups = {"full", "smoke"})
        public void ScheduleToPublishContentByDateTime_Test() throws Exception{

        //Step 1
        UserLogin userLogin = applib.openApplication();
        PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());

        //Step 1a
        Modules modules = new Modules(webDriver, applib);
        PageFactory.initElements(webDriver, modules);
        modules.VerifyModuleEnabled("Pub Post");
        
        //Step 2
        taxonomy.VerifyContentMenuExist("Content");
        taxonomy.VerifyContentMenuExist("My Workbench");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");

        //Step 3
        WorkBench workBench = new WorkBench(webDriver, applib);
        PageFactory.initElements(webDriver, workBench);
        workBench.VerifyWorkBenchTabPresent("View");
        workBench.VerifyWorkBenchTabPresent("Edit Draft");
        workBench.VerifyWorkBenchTabPresent("Revisions");
        workBench.VerifyWorkBenchTabPresent("Schedule");

        //Step 4
        Revisions revisions = new Revisions(webDriver, applib);
        PageFactory.initElements(webDriver, revisions);
        revisions.ClickRevisionTab();
        overlay.SwitchToFrame("Revisions dialog");
        RevisionState revisionstate = new RevisionState(webDriver);
        revisionstate.VerifyRevisionCount(1);

        //Step 5
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyAddScheduleVersionLink();
        scheduleQueue.VerifyScheduleTableisEmpty();
        
        //Step 6
        workBench.ClickWorkBenchTab("Edit Draft");
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Edit Post "+ postTitle + " dialog");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterSynopsis("Testing for new revision");
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.EnterMessageForStateChange("Test Revision 2");
        ContentParent contentParent = new ContentParent(webDriver, applib);
        PageFactory.initElements(webDriver, contentParent);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();

        //Step 7
        workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Revision state", "Public","Moderate"));
        
        //Step 8
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToFrame("Revisions dialog");
        revisionstate.VerifyRevisionCount(2);

        //Step 9
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();

        //Step 10
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.SelectOperation("Moderate to Published");
        com.ibm.icu.util.Calendar cal = com.ibm.icu.util.Calendar.getInstance();
        cal.add(com.ibm.icu.util.Calendar.DATE, 10);
        Date date = cal.getTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        scheduleQueue.EnterDate(sDate);
        scheduleQueue.EnterTime("05:00 PM");
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");

         //Step 11 //TODO - this step needs additional work and validation as time allows
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(postTitle);
        scheduleQueue.VerifyScheduledQueue("Moderate to Publish");
        scheduleQueue.VerifyScheduledQueue(sDate + " - 05:00 PM");
        scheduleQueue.ClickRunNowLnk();
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(postTitle + " dialog");
        scheduleQueue.VerifyScheduledQueue("Moderate to Published");
        scheduleQueue.VerifyScheduledQueue("Completed");
        revisions.ClickRevisionTab();
        overlay.SwitchToActiveFrame();
        //revisionstate.VerifyRevisionState("Revision was set from Draft to Published on " + sDate);
        }
}