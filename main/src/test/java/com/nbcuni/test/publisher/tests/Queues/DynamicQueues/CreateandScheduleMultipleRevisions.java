package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.SitePreview.SitePreview;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;

public class CreateandScheduleMultipleRevisions extends ParentTest {
	
	/*************************************************************************************
	 * publisher.nbcuni.com Create Schedule Multiple Revisions Dynamic Queue. Copyright
     * TEST CASE - TC4969
     * @author Vineela Juturu
     * @version 1.0 Date: October 13, 2014
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/22576323009
     *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "broken"})
	    public void CreateScheduleMultipleRevisions_TC4969() throws Exception{
		 
		 Reporter.log("STEP 1");
         UserLogin userLogin = applib.openApplication();
         userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
	 
         Reporter.log("SETUP");
         CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
         String charProfBiography = random.GetCharacterString(15);
         String characterProfileTitle = createDefaultContent.CharacterProfile("Published", null, null, charProfBiography);
         String postBody = random.GetCharacterString(15);
         String postTitle = createDefaultContent.Post("Published", postBody);
         
         Reporter.log("STEP 2");
         Modules modules = new Modules(webDriver);
         taxonomy.NavigateSite("Modules");
         overlay.SwitchToActiveFrame();
         for (String module : Arrays.asList("Dynamic Queue", "Dynamic Queue Workbench")) {
         	modules.EnterFilterName(module);
         	modules.EnableModule(module);
         }
         overlay.ClickCloseOverlayLnk();
         
         Reporter.log("STEP 3");
         taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
         overlay.SwitchToActiveFrame();
         
         Reporter.log("STEP 4");
         AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
         String dynamicQueueTypeName = random.GetCharacterString(15); 
         addDynamicQueueType.EnterName(dynamicQueueTypeName);
         addDynamicQueueType.SelectCacheLifetime("1 min");
         addDynamicQueueType.SelectEntityType();
         addDynamicQueueType.ClickSaveBtn();
         overlay.SwitchToActiveFrame();
         contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
         overlay.ClickCloseOverlayLnk();
         
         Reporter.log("STEP 5");
         taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
         overlay.SwitchToActiveFrame();  
         
         Reporter.log("STEP 6");
         String dynamicQueueTitle1 = random.GetCharacterString(15) +"_REVISION1";
         AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
         addDynamicQueue.EnterTitle(dynamicQueueTitle1);       
         addDynamicQueue.CheckTargetBundle_Cbx("Character Profile");
         addDynamicQueue.ClickSortByNewestRdb();
         addDynamicQueue.ClickSaveDynamicQueueBtn();
         overlay.switchToDefaultContent(true);
         
         Reporter.log("STEP 7- MOVED TO SETUP");
         
         
         Reporter.log("STEP 8");
         taxonomy.NavigateSite("Content>>Dynamic Queues");
         overlay.SwitchToActiveFrame();
         DynamicQueues dynamicQueues = new DynamicQueues(webDriver);
         String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle1);
         overlay.ClickCloseOverlayLnk();
         String parentWindow = webDriver.getWindowHandle();
         applib.openNewWindow();
         applib.switchToNewWindow(parentWindow);
         applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
         contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle,charProfBiography));
         
         Reporter.log("STEP 9");
         applib.switchToParentWindow(parentWindow);
         WorkBench workbench = new WorkBench(webDriver);
         workbench.VerifyWorkBenchTabPresent("Edit");
         workbench.ClickWorkBenchTab("Edit");
         
         overlay.SwitchToActiveFrame();
         String dynamicQueueTitle2 = random.GetCharacterString(15) +"_REVISION2"; 
         addDynamicQueue.EnterTitle(dynamicQueueTitle2); 
         addDynamicQueue.CheckTargetBundle_Cbx("Post");
         addDynamicQueue.CheckCreatenewrevision_Cbx();
         addDynamicQueue.ClickSaveDynamicQueueBtn();
         overlay.switchToDefaultContent(true);
         
         Reporter.log("STEP 10- MOVED TO SETUP");
         
         Reporter.log("STEP 11");
         applib.switchToNewWindow(parentWindow);
         applib.refreshPage();
         Thread.sleep(1000);
         contentParent.VerifyPageContentPresent(Arrays.asList(postTitle,postBody));
         
         Reporter.log("STEP 12");
         applib.switchToParentWindow(parentWindow);
         workbench.VerifyWorkBenchTabPresent("Revisions");
         workbench.ClickWorkBenchTab("Revisions");
         overlay.SwitchToActiveFrame();
         
         Reporter.log("STEP 13");
         QueuesRevisionList dqrevisionslist = new QueuesRevisionList(webDriver);
         int revisionscount = 2;
         dqrevisionslist.VerifyRevisionCount(revisionscount);
         
         Reporter.log("STEP 14");
         dqrevisionslist.clickScheduleTab();
         overlay.SwitchToActiveFrame();
         
         Reporter.log("STEP 15");
         ScheduleQueue scheduledynamicqueue = new ScheduleQueue(webDriver);
         scheduledynamicqueue.VerifyAddScheduleVersionLnkPresent();
         scheduledynamicqueue.ClickAddScheduledRevisionLnk();
         overlay.SwitchToActiveFrame();
         scheduledynamicqueue.SelectRevision(dynamicQueueTitle1);
         scheduledynamicqueue.SelectOperation("Moderate to Publish");
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 String date = sdf.format(new Date());
     	 SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm a");
     	 sdf_time.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 Calendar cal = Calendar.getInstance();
     	 cal.setTime(new Date());
     	 cal.add(Calendar.HOUR, 1);
     	 String time = sdf_time.format(cal.getTime());
         scheduledynamicqueue.EnterDate(date);
         scheduledynamicqueue.EnterTime(time);
         scheduledynamicqueue.ClickScheduleBtn();
         contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
         
         Reporter.log("STEP 16");
         scheduledynamicqueue.VerifyAddScheduleVersionLnkPresent();
         scheduledynamicqueue.ClickAddScheduledRevisionLnk();
         overlay.SwitchToActiveFrame();
         scheduledynamicqueue.SelectRevision(dynamicQueueTitle2);
         scheduledynamicqueue.SelectOperation("Moderate to Publish");
         cal.add(Calendar.DATE, 1);
     	 String date1 = sdf.format(cal.getTime());
     	 String time1 = sdf_time.format(cal.getTime());
         scheduledynamicqueue.EnterDate(date1);
         scheduledynamicqueue.EnterTime(time1);
         scheduledynamicqueue.ClickScheduleBtn();
         contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
         overlay.ClickCloseOverlayLnk();
         
         Reporter.log("STEP 17");
         SitePreview sitepreview = new SitePreview(webDriver);
         sitepreview.ClickPreviewSiteLnk();
         sitepreview.SelectACondition();
         sitepreview.VerifyDateTxbTobeVisible();
         SimpleDateFormat sdf_prevdate = new SimpleDateFormat("yyyy-MM-dd");
         sdf_prevdate.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 String prevdate = sdf_prevdate.format(new Date());
         sitepreview.EnterDate(prevdate);
         SimpleDateFormat sdf_prevtime = new SimpleDateFormat("HH:mm");
         sdf_prevtime.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 String prevtime = sdf_prevtime.format(cal.getTime());
         sitepreview.EnterTime(prevtime);
         sitepreview.ClickEnablePreviewBtn();
         sitepreview.VerifyDisablePreviewBtnVisible();
         contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle,charProfBiography));
         
         Reporter.log("STEP 18");
         prevdate = sdf_prevdate.format(cal.getTime());
         sitepreview.EnterDate(prevdate);
         sitepreview.EnterTime(prevtime);
		 sitepreview.ClickUpdatePreviewBtn();
		 Thread.sleep(1000);
		 contentParent.VerifyPageContentPresent(Arrays.asList(postTitle,postBody));
		 
		 Reporter.log("STEP 19"); // Defect - DE10096 exists for Disable Module dynamic queue should clear all reference data.
         
	 }

}