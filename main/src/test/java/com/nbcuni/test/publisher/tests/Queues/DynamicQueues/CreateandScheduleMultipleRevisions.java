package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.SearchFor;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.SitePreview.SitePreview;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.AddDynamicQueueType;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueueTypes;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues.DynamicQueues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
         CreateDefaultContent createDefaultContent = new CreateDefaultContent(webWebWebDriver);
         String charProfBiography = random.GetCharacterString(15);
         String characterProfileTitle = createDefaultContent.CharacterProfile("Published", null, null, charProfBiography);
         String characterProfileURL = webWebWebDriver.getCurrentUrl();
         String postBody = random.GetCharacterString(15);
         String postTitle = createDefaultContent.Post("Published", postBody);
         String postURL = webWebWebDriver.getCurrentUrl();
         
         Reporter.log("STEP 2");
         navigation.Modules();
         Modules modules = new Modules(webWebWebDriver);
         for (String module : Arrays.asList("Dynamic Queue", "Dynamic Queue Workbench")) {
         	modules.EnableModule(module);
         }
         
         Reporter.log("STEP 3");
         navigation.Structure("Dynamic Queue types");
         DynamicQueueTypes dynamicQueueTypes = new DynamicQueueTypes(webWebWebDriver);
         dynamicQueueTypes.ClickAddDynamicQueueTypeLnk();
         
         Reporter.log("STEP 4");
         AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webWebWebDriver);
         String dynamicQueueTypeName = random.GetCharacterString(15); 
         addDynamicQueueType.EnterName(dynamicQueueTypeName);
         addDynamicQueueType.SelectCacheLifetime("1 min");
         addDynamicQueueType.SelectEntityType();
         addDynamicQueueType.ClickSaveBtn();
         contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
         
         Reporter.log("STEP 5");
         navigation.Content("Dynamic Queues");
         DynamicQueues dynamicQueues = new DynamicQueues(webWebWebDriver);
         dynamicQueues.ClickAddDynamicQueueLnk(dynamicQueueTypeName);
         
         Reporter.log("STEP 6");
         String dynamicQueueTitle1 = random.GetCharacterString(15) +"_REVISION1";
         AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webWebWebDriver);
         addDynamicQueue.EnterTitle(dynamicQueueTitle1);       
         addDynamicQueue.CheckTargetBundle_Cbx("Character Profile");
         addDynamicQueue.ClickSortByNewestRdb();
         addDynamicQueue.ClickSaveDynamicQueueBtn();
         
         Reporter.log("STEP 7- MOVED TO SETUP");
         
         
         Reporter.log("STEP 8");
         navigation.Content("Dynamic Queues");
         String dynamicQueueNodeID = dynamicQueues.GetDynamicQueueNodeNumber(dynamicQueueTitle1);
         String parentWindow = webWebWebDriver.getWindowHandle();
         applib.openNewWindow();
         applib.switchToNewWindow(parentWindow);
         applib.openSitePage("/dynamic-queue/" + dynamicQueueNodeID);
         contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle,charProfBiography));
         
         Reporter.log("STEP 9");
         applib.switchToParentWindow(parentWindow);
         WorkBench workbench = new WorkBench(webWebWebDriver);
         SearchFor searchFor = new SearchFor(webWebWebDriver);
         searchFor.ClickSearchTitleLnk(dynamicQueueTitle1);
         workbench.ClickWorkBenchTab("Edit");
         String dynamicQueueTitle2 = random.GetCharacterString(15) +"_REVISION2"; 
         addDynamicQueue.EnterTitle(dynamicQueueTitle2); 
         addDynamicQueue.CheckTargetBundle_Cbx("Post");
         addDynamicQueue.CheckCreatenewrevision_Cbx();
         addDynamicQueue.ClickSaveDynamicQueueBtn();
         
         Reporter.log("STEP 10- MOVED TO SETUP");
         
         Reporter.log("STEP 11");
         applib.switchToNewWindow(parentWindow);
         applib.refreshPage();
         contentParent.VerifyPageContentPresent(Arrays.asList(postTitle,postBody));
         
         Reporter.log("STEP 12");
         applib.switchToParentWindow(parentWindow);
         workbench.VerifyWorkBenchTabPresent("Revisions");
         workbench.ClickWorkBenchTab("Revisions");
         
         Reporter.log("STEP 13");
         QueuesRevisionList dqrevisionslist = new QueuesRevisionList(webWebWebDriver);
         //int revisionscount = 2;
         //dqrevisionslist.VerifyRevisionCount(revisionscount); //defect of revisions not valid on dq module uninstall
         
         Reporter.log("STEP 14");
         dqrevisionslist.clickScheduleTab();
         
         Reporter.log("STEP 15");
         ScheduleQueue scheduledynamicqueue = new ScheduleQueue(webWebWebDriver);
         scheduledynamicqueue.VerifyAddScheduleVersionLnkPresent();
         scheduledynamicqueue.ClickAddScheduledRevisionLnk();
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
         scheduledynamicqueue.SelectRevision(dynamicQueueTitle2);
         scheduledynamicqueue.SelectOperation("Moderate to Publish");
         cal.add(Calendar.DATE, 1);
     	 String date1 = sdf.format(cal.getTime());
     	 String time1 = sdf_time.format(cal.getTime());
         scheduledynamicqueue.EnterDate(date1);
         scheduledynamicqueue.EnterTime(time1);
         scheduledynamicqueue.ClickScheduleBtn();
         contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
         
         Reporter.log("STEP 17");
         applib.openSitePage(characterProfileURL);
         SitePreview sitepreview = new SitePreview(webWebWebDriver);
         sitepreview.ClickInteractivePreviewBtn();
         sitepreview.SelectACondition();
         Thread.sleep(5000);
         SimpleDateFormat sdf_prevdate = new SimpleDateFormat("yyyy-MM-dd");
         sdf_prevdate.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 String prevdate = sdf_prevdate.format(new Date());
         sitepreview.EnterDate(prevdate);
         SimpleDateFormat sdf_prevtime = new SimpleDateFormat("HH:mm");
         sdf_prevtime.setTimeZone(TimeZone.getTimeZone("UTC"));
     	 String prevtime = sdf_prevtime.format(cal.getTime());
         sitepreview.EnterTime(prevtime);
         sitepreview.ClickEnablePreviewLnk();
         contentParent.VerifyPageContentPresent(Arrays.asList(characterProfileTitle,charProfBiography));
         
         Reporter.log("STEP 18");
         applib.openSitePage(postURL);
         sitepreview.ClickInteractivePreviewBtn();
         prevdate = sdf_prevdate.format(cal.getTime());
         sitepreview.EnterDate(prevdate);
         sitepreview.EnterTime(prevtime);
		 sitepreview.ClickUpdatePreviewLnk();
		 contentParent.VerifyPageContentPresent(Arrays.asList(postTitle,postBody));
		 
		 Reporter.log("STEP 19"); // Defect - DE10096 exists for Disable Module dynamic queue should clear all reference data.
         
	 }

}
