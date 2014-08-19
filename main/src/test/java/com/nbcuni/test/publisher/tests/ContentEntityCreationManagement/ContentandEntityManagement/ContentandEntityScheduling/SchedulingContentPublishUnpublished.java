package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SchedulingContentPublishUnpublished extends ParentTest {

	/*************************************************************************************
	* TEST CASE - TC2159
    * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/18309480903
	*************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify", "mpx"})
        public void SchedulingContentPublishUnpublished_TC2159() throws Exception{

        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());

        Reporter.log("STEP 2 and 3");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        Reporter.log("STEP 4");
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 5");
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.VerifyOperationOptions(Arrays.asList("Revert", "Delete", "Moderate to Draft", 
        		"Moderate to Published", "Moderate to Unpublished"));
        scheduleQueue.SelectOperation("Moderate to Published");
        
        Reporter.log("STEP 6");
        Calendar cal1MinuteFuture = Calendar.getInstance();
        cal1MinuteFuture.add(Calendar.SECOND, 69);
    	Date date1MinuteFuture = cal1MinuteFuture.getTime();
    	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm a");
    	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	String pub7Date1MinuteFuture = pub7DateFormat.format(date1MinuteFuture);
	    String pub7Time1MinuteFuture = pub7TimeFormat.format(date1MinuteFuture);
        scheduleQueue.EnterDate(pub7Date1MinuteFuture);
        scheduleQueue.EnterTime(pub7Time1MinuteFuture);
        
        Reporter.log("STEP 7");
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        
        Reporter.log("STEP 8");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle, "Moderate to Published", pub7Date1MinuteFuture + " - "));
        scheduleQueue.VerifyRunNowLnkPresent(postTitle, "Moderate to Published");
        scheduleQueue.VerifyCancelLnkPresent(postTitle, "Moderate to Published");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 9");
        taxonomy.NavigateSite("Content");
        overlay.SwitchToActiveFrame();
        SearchFor searchFor = new SearchFor(webDriver, applib);
        SimpleDateFormat pub7DateFormatEst = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
    	String previouslyScheduledTitle = "futurePost" + pub7DateFormatEst.format(currentDate);
        searchFor.EnterTitle(previouslyScheduledTitle);
        searchFor.ClickApplyBtn();
        overlay.switchToDefaultContent(true);
        searchFor.ClickSearchTitleLnk(searchFor.GetFirstSearchResult());
        workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList("Revision state: Published", "Public: Yes"));
        
        Reporter.log("STEP 10");
        String postTitle2 = createDefaultContent.Post("Draft");
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();
        scheduleQueue.SelectRevision(postTitle2);
        scheduleQueue.SelectOperation("Moderate to Published");
        Calendar cal5MinutesFuture = Calendar.getInstance();
        cal5MinutesFuture.add(Calendar.MINUTE, 5);
    	Date date5MinutesFuture = cal5MinutesFuture.getTime();
    	String pub7Date5MinutesFuture = pub7DateFormat.format(date5MinutesFuture);
	    String pub7Time5MinutesFuture = pub7TimeFormat.format(date5MinutesFuture);
        scheduleQueue.EnterDate(pub7Date5MinutesFuture);
        scheduleQueue.EnterTime(pub7Time5MinutesFuture);
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle2, "Moderate to Published", pub7Date5MinutesFuture + " - "));
        scheduleQueue.ClickRunNowLnk(postTitle2, "Moderate to Published");
        
        Reporter.log("STEP 11");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle2, "Moderate to Published", pub7Date5MinutesFuture + " - ", "Completed"));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 12");
        String postTitle3 = createDefaultContent.Post("Draft");
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();
        scheduleQueue.SelectRevision(postTitle3);
        scheduleQueue.SelectOperation("Moderate to Published");
        scheduleQueue.EnterDate(pub7Date5MinutesFuture);
        scheduleQueue.EnterTime(pub7Time5MinutesFuture);
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle3, "Moderate to Published", pub7Date5MinutesFuture + " - "));
        scheduleQueue.ClickRunNowExpandLnk(postTitle3, "Moderate to Published");
        scheduleQueue.ClickCancelLnk(postTitle3, "Moderate to Published");
        overlay.SwitchToActiveFrame();
        Delete delete = new Delete(webDriver);
        delete.ClickConfirmBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been cancelled.");
        contentParent.VerifyPageContentNotPresent(Arrays.asList("Moderate to Published"));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 13");
        taxonomy.NavigateSite("My Workbench>>Create content>>Post");
        overlay.SwitchToActiveFrame();
        String postTitle4 = random.GetCharacterString(15);
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterTitle(postTitle4);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Draft");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle4 + " has been created.");
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();
        scheduleQueue.SelectRevision(postTitle4);
        scheduleQueue.SelectOperation("Moderate to Published");
        scheduleQueue.EnterDate(pub7Date5MinutesFuture);
        scheduleQueue.EnterTime(pub7Time5MinutesFuture);
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle4, "Moderate to Published", pub7Date5MinutesFuture + " - "));
        scheduleQueue.ClickRunNowLnk(postTitle4, "Moderate to Published");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle4, "Moderate to Published", pub7Date5MinutesFuture + " - ", "Completed"));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 14");
        String postTitle5 = createDefaultContent.Post("Draft");
        workBench.ClickWorkBenchTab("Edit Draft");
        overlay.SwitchToActiveFrame();
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle5 + " has been updated.");
        
        Reporter.log("STEP 15 and 16");
        workBench.ClickWorkBenchTab("Revisions");
        overlay.SwitchToActiveFrame();
        RevisionState revisionState = new RevisionState(webDriver);
        revisionState.VerifyRevisionCount(2);
        Revisions revisions = new Revisions(webDriver, applib);
        revisions.ClickEditExtendMenuBtn(postTitle5);
        revisions.ClickScheduleMenuBtn(postTitle5);
        overlay.SwitchToActiveFrame();
        scheduleQueue.SelectRevision(postTitle5);
        scheduleQueue.SelectOperation("Moderate to Published");
        scheduleQueue.EnterDate(pub7Date5MinutesFuture);
        scheduleQueue.EnterTime(pub7Time5MinutesFuture);
        scheduleQueue.ClickScheduleBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        
        Reporter.log("STEP 17");
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle5, "Moderate to Published", pub7Date5MinutesFuture + " - "));
        scheduleQueue.VerifyRunNowLnkPresent(postTitle5, "Moderate to Published");
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 18 and 19 - TODO");
        
        Reporter.log("STEP 20");
        taxonomy.NavigateSite("My Workbench>>Create content>>Post");
        overlay.SwitchToActiveFrame();
        String postTitle7 = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle7);
        basicInformation.EnterSynopsis();
        overlay.SwitchToActiveFrame();
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Draft");
        publishingOptions.SelectOperation("Moderate to Published");
        Calendar cal1DayFuture = Calendar.getInstance();
        cal1DayFuture.add(Calendar.HOUR, 24);
    	Date date1DayFuture = cal1DayFuture.getTime();
        String pub7Date1DayFuture = pub7DateFormat.format(date1DayFuture);
	    String pub7Time1DayFuture = pub7TimeFormat.format(date1DayFuture);
        publishingOptions.EnterDate(pub7Date1DayFuture);
        publishingOptions.EnterTime(pub7Time1DayFuture);
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent(true);
        contentParent.VerifyMessageStatus("Post " + postTitle7 + " has been created.");
        contentParent.VerifyMessageStatus(postTitle7 + " has been scheduled to Moderate to Published on " + pub7Date1DayFuture + " - ");
        workBench.ClickWorkBenchTab("Schedule");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle7, "Moderate to Published", pub7Date1DayFuture + " - "));
        scheduleQueue.ClickRunNowLnk(postTitle7, "Moderate to Published");
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle7, "Moderate to Published", "Completed"));
        
    }
}