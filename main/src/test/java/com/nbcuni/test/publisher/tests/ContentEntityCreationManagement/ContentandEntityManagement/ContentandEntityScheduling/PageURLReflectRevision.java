package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PageURLReflectRevision extends GlobalBaseTest {

	/*************************************************************************************
	* TEST CASE - TC6585
    * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/28252175590
	*************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "broken"})
        public void PageURLReflectRevision_TC6585() throws Exception{

        Reporter.log("STEP 1");
        UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));

        Reporter.log("STEP 2");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Published");
        String postURL = webDriver.getCurrentUrl();
        
        Reporter.log("STEP 3");
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Edit Draft");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        String postTitleRevision1 = "Revision1" + random.GetCharacterString(10);
        basicInformation.EnterTitle(postTitleRevision1);
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        publishingOptions.SelectModerationState("Draft");
        publishingOptions.SelectOperation("Moderate to Published");
        publishingOptions.SelectOperation("Moderate to Published");
        Calendar cal15MinuteFuture = Calendar.getInstance();
        cal15MinuteFuture.add(Calendar.MINUTE, 15);
    	Date date15MinuteFuture = cal15MinuteFuture.getTime();
    	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm a");
    	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	String pub7Date15MinuteFuture = pub7DateFormat.format(date15MinuteFuture);
	    String pub7Time15MinuteFuture = pub7TimeFormat.format(date15MinuteFuture);
        publishingOptions.EnterDate(pub7Date15MinuteFuture);
        publishingOptions.EnterTime(pub7Time15MinuteFuture);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitleRevision1 + " has been updated.");
        contentParent.VerifyMessageStatus(postTitleRevision1 + " has been scheduled to Moderate to Published on " + pub7Date15MinuteFuture + " - ");
        contentParent.VerifyPageURL(postURL);
        
        Reporter.log("STEP 4");
        workBench.ClickWorkBenchTab("Edit Draft");
        String postTitleRevision2 = "Revision2" + random.GetCharacterString(10);
        basicInformation.EnterTitle(postTitleRevision2);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        publishingOptions.SelectModerationState("Draft");
        publishingOptions.SelectOperation("Moderate to Published");
        cal15MinuteFuture.add(Calendar.HOUR, 24);
    	Date date1DayFuture = cal15MinuteFuture.getTime();
    	String pub7Date1DayFuture = pub7DateFormat.format(date1DayFuture);
	    String pub7Time1DayFuture = pub7TimeFormat.format(date1DayFuture);
        publishingOptions.EnterDate(pub7Date1DayFuture);
        publishingOptions.EnterTime(pub7Time1DayFuture);
        contentParent.ClickSaveBtn();
        contentParent.VerifyMessageStatus("Post " + postTitleRevision2 + " has been updated.");
        contentParent.VerifyMessageStatus(postTitleRevision2 + " has been scheduled to Moderate to Published on " + pub7Date1DayFuture + " - ");
        contentParent.VerifyPageURL(postURL);
        
        Reporter.log("STEP 5");
        workBench.ClickWorkBenchTab("Schedule");
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickAddScheduledRevisionLnk();
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.SelectOperation("Moderate to Unpublished");
        cal15MinuteFuture.add(Calendar.HOUR, 48);
    	Date date2DaysFuture = cal15MinuteFuture.getTime();
    	String pub7Date2DaysFuture = pub7DateFormat.format(date2DaysFuture);
	    String pub7Time2DaysFuture = pub7TimeFormat.format(date2DaysFuture);
        scheduleQueue.EnterDate(pub7Date2DaysFuture);
        scheduleQueue.EnterTime(pub7Time2DaysFuture);
        scheduleQueue.ClickScheduleBtn();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
        
        Reporter.log("STEP 6");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle, "Moderate to Unpublished", pub7Date2DaysFuture + " - "));
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitleRevision2, "Moderate to Published", pub7Date1DayFuture + " - "));
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitleRevision1, "Moderate to Published", pub7Date15MinuteFuture + " - "));
        
        Reporter.log("STEP 7");
        navigation.ClickPrimaryTabNavLnk("View");
        contentParent.VerifyPageURL(postURL);
        
        Reporter.log("STEP 8");
        Logout logout = new Logout(webDriver);
        logout.ClickLogoutBtn();
        appLib.openSitePage(postURL);
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitle));
        
        Reporter.log("STEP 9");
        appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        appLib.openSitePage(postURL);
        workBench.ClickWorkBenchTab("Schedule");
        scheduleQueue.ClickRunNowLnk(postTitleRevision1, "Moderate to Published");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitleRevision1, "Moderate to Published", pub7Date15MinuteFuture + " - ", "Completed"));
        
        Reporter.log("STEP 10");
        appLib.openSitePage(postURL);
        contentParent.VerifyPageURL(config.getConfigValueString("AppURL") + "/content/" + postTitleRevision1.toLowerCase());
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitleRevision1));
        
        Reporter.log("STEP 11");
        logout.ClickLogoutBtn();
        appLib.openSitePage(postURL);
        contentParent.VerifyPageURL(config.getConfigValueString("AppURL") + "/content/" + postTitleRevision1.toLowerCase());
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitleRevision1));
        
        Reporter.log("STEP 12");
        appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        appLib.openSitePage(postURL);
        workBench.ClickWorkBenchTab("Schedule");
        scheduleQueue.ClickRunNowLnk(postTitleRevision2, "Moderate to Published");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitleRevision2, "Moderate to Published", pub7Date15MinuteFuture + " - ", "Completed"));
        appLib.openSitePage(postURL);
        contentParent.VerifyPageURL(config.getConfigValueString("AppURL") + "/content/" + postTitleRevision2.toLowerCase());
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitleRevision2));
        
        Reporter.log("STEP 13");
        logout.ClickLogoutBtn();
        appLib.openSitePage(postURL);
        contentParent.VerifyPageURL(config.getConfigValueString("AppURL") + "/content/" + postTitleRevision2.toLowerCase());
        contentParent.VerifyPageContentPresent(Arrays.asList(postTitleRevision2));
        
        Reporter.log("STEP 14");
        appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        appLib.openSitePage(postURL);
        workBench.ClickWorkBenchTab("Schedule");
        scheduleQueue.ClickRunNowLnk(postTitle, "Moderate to Unpublished");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(postTitle, "Moderate to Unpublished", pub7Date15MinuteFuture + " - ", "Completed"));
        
        Reporter.log("STEP 15");
        logout.ClickLogoutBtn();
        appLib.openSitePage(postURL);
        contentParent.VerifyPageURL(postURL);
        contentParent.VerifyPageContentPresent(Arrays.asList("Access denied"));
        
    }
}
