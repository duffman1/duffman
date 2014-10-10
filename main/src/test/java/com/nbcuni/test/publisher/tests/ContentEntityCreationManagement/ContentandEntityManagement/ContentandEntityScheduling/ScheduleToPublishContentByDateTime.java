package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
        public void ScheduleToPublishContentByDateTime_Test() throws Exception{

        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));

        //Step 2
        taxonomy.VerifyContentMenuExist("Content");
        taxonomy.VerifyContentMenuExist("My Workbench");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Draft");

        //Step 3
        WorkBench workBench = new WorkBench(webDriver);
        workBench.VerifyWorkBenchTabPresent("View");
        workBench.VerifyWorkBenchTabPresent("Edit Draft");
        workBench.VerifyWorkBenchTabPresent("Revisions");
        workBench.VerifyWorkBenchTabPresent("Schedule");

        //Step 4
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        RevisionState revisionstate = new RevisionState(webDriver);
        revisionstate.VerifyRevisionCount(1);

        //Step 5
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyAddScheduleVersionLnkPresent();
        scheduleQueue.VerifyScheduleTableisEmpty();
        
        //Step 6
        workBench.ClickWorkBenchTab("Edit Draft");
        overlay.SwitchToActiveFrame();
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.EnterMessageForStateChange("Test Revision 2");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);

        //Step 7
        workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Revision state", "Public","Moderate"));
        
        //Step 8
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        revisionstate.VerifyRevisionCount(2);

        //Step 9
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();

        //Step 10
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.SelectOperation("Moderate to Published");
        Calendar cal10DaysFuture = Calendar.getInstance();
        cal10DaysFuture.add(Calendar.DATE, 10);
        Date date10DaysFuture = cal10DaysFuture.getTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        sdfDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        String sdfDate10DaysFuture = sdfDate.format(date10DaysFuture);
        scheduleQueue.EnterDate(sdfDate10DaysFuture);
        scheduleQueue.EnterTime("05:00 PM");
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");

        //Step 11
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle, "Moderate to Publish", sdfDate10DaysFuture + " - 05:00 PM"));
        scheduleQueue.ClickRunNowLnk(postTitle, "Moderate to Published");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList("Moderate to Published", "Completed"));
        Revisions revisions = new Revisions(webDriver);
        revisions.ClickRevisionTab();
        overlay.SwitchToActiveFrame();
        Calendar calToday = Calendar.getInstance();
        Date dateToday = calToday.getTime();
        String sdfDateToday = sdfDate.format(dateToday);
        contentParent.VerifyPageContentPresent(Arrays.asList("Revision was set from Draft to Published on " + sdfDateToday));
        
    }
}