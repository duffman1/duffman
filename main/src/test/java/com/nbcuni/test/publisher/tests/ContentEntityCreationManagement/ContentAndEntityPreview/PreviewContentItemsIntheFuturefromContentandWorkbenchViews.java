package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentAndEntityPreview;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.SitePreview.SitePreview;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PreviewContentItemsIntheFuturefromContentandWorkbenchViews extends GlobalBaseTest {

	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1
     * Step 2 - Create  a Published post
     * Step 3 - Click on Preview Site link on menu option
     * Step 4 - Populate "Select A Condition"
     * Step 5 - Populate Date and Time.
     * Step 6 - Click on Enable Preview
     * Step 7 - Verify That DisablePreview and UpdatePreview buttons appeared  
     * Step 8 - Edit Draft and update Title, Short Description, Synopsis 
     * Step 9 - Schedule the post on one day ahead
     * Step 10 - Verify that SitePreview information retained.
     * Step 11 - Click on SitePreview menu and Verify that SitePreview got hidden.
     * Step 12 - Click on SitePreview menu and Verify that SitePreview got Visible
     * Step 13 - Schedule the post on two days ahead. Refresh the URL
     * Step 14 - Verify that SitePreview information retained 
     * Step 15 - Update Site Preview info. 
     * Step 16 - Verify Short description and body text     
     * Step 17 - Disable the SitePreview
     * Step 18 - Click on SitePreview menu option  
     * Step 19 - Verify the "Select A Condition" dropdown has "- Select -" selected 
     * Step 20 - Verify that EnablePreview Button displayed
     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
	public void PreviewContentItemsIntheFuturefromContentandWorkbenchViews_Test() throws Exception{
		
		Reporter.log("STEP 1");
    	UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        Reporter.log("SETUP");
        navigation.Modules();
        Modules modules = new Modules(webDriver);
        modules.DisableModule("Pub SPS (Site Preview System)");
        
        Reporter.log("STEP 2");    
        navigation.AddContent("Post");
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String postTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(postTitle);    	
    	basicInformation.EnterSynopsis();
    	String shortDescriptionText = random.GetCharacterString(25);
    	basicInformation.EnterShortDescription(shortDescriptionText);
    	basicInformation.ClickCoverSelectBtn();
    	SelectFile selectFile = new SelectFile(webDriver);
    	selectFile.SelectDefaultCoverImg();
    	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	ContentParent contentParent = new ContentParent(webDriver);
    	contentParent.ClickSaveBtn();
    	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
    	Thread.sleep(2000);
    	String postURL = webDriver.getCurrentUrl();
    	
        Reporter.log("STEP 3");
    	SitePreview sitePreview = new SitePreview(webDriver);
        sitePreview.ClickInteractivePreviewBtn();    	
        
    	Reporter.log("STEP 4");			
        sitePreview.SelectACondition();
		
        Reporter.log("STEP 5");
        Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	Date date = cal.getTime();
    	SimpleDateFormat pub7PreviewDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat pub7StandardDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	pub7PreviewDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	pub7StandardDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String sPreviewDate = pub7PreviewDateFormat.format(date);
        String sStandardDate = pub7StandardDateFormat.format(date);
        sitePreview.EnterDate(sPreviewDate);
        sitePreview.EnterTime("16:40");
        
        Reporter.log("STEP 6");        
        sitePreview.ClickEnablePreviewLnk(); 
        
        Reporter.log("STEP 7");
        sitePreview.ClickInteractivePreviewBtn();
        sitePreview.VerifyDisablePreviewLnkVisible();
        sitePreview.VerifyUpdatePreviewLnkVisible();
        
        //Step 8
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Edit Draft");
        String updatedPostTitle = "Updated" + postTitle;
        String updatedShortDescription = "Updated" + shortDescriptionText;
        basicInformation.EnterTitle(updatedPostTitle);
        basicInformation.EnterShortDescription(updatedShortDescription);
        String updatedBodyTxt = basicInformation.EnterSynopsis(); 
        contentParent.ClickSaveBtn();
        
        //Step 9
    	workBench.ClickWorkBenchTab("Schedule");
    	ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
    	scheduleQueue.ClickAddScheduledRevisionLnk();
    	scheduleQueue.SelectRevision(updatedPostTitle);
    	scheduleQueue.SelectOperation("Moderate to Published");
    	scheduleQueue.EnterDate(sStandardDate);
    	scheduleQueue.EnterTime("12:40 PM");
    	scheduleQueue.ClickScheduleBtn();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
    	
    	//Step 10
    	appLib.openSitePage(postURL);
    	sitePreview.ClickInteractivePreviewBtn();
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sPreviewDate);
    	sitePreview.VerifyTimeValue("16:40");
    	appLib.refreshPage();
    	contentParent.VerifyPageContentPresent(Arrays.asList(updatedShortDescription, updatedBodyTxt));
    	Thread.sleep(2000);
    	
    	//Step 11
    	sitePreview.ClickInteractivePreviewBtn();
    	sitePreview.VerifyEnablePreviewLnkNotPresent();
    	
    	//Step 12
    	sitePreview.VerifyDisablePreviewLnkVisible();
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sPreviewDate);
    	sitePreview.VerifyTimeValue("16:40");
    	
    	//Step 13
    	workBench.ClickWorkBenchTab("Schedule");
    	scheduleQueue.ClickAddScheduledRevisionLnk();    	
    	scheduleQueue.SelectRevision(updatedPostTitle);
    	scheduleQueue.SelectOperation("Moderate to Published");    	
    	cal.add(Calendar.DATE, 2);
    	Date dateafter2 = cal.getTime();    	
        String ssDate = pub7StandardDateFormat.format(dateafter2);
    	scheduleQueue.EnterDate(ssDate);
    	scheduleQueue.EnterTime("1:40 AM");
    	scheduleQueue.ClickScheduleBtn();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved"); 
    	
    	//Step 14
    	sitePreview.ClickInteractivePreviewBtn();
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sPreviewDate);
    	sitePreview.VerifyTimeValue("16:40");
    	
    	//Step 15
    	sitePreview.EnterDate(pub7PreviewDateFormat.format(dateafter2));
        sitePreview.EnterTime("16:40");
        sitePreview.ClickUpdatePreviewLnk();
        
        //Step 16
        appLib.openSitePage(postURL);
        contentParent.VerifyPageContentPresent(Arrays.asList(updatedShortDescription, updatedBodyTxt));
        
    	//Step 17
        sitePreview.ClickInteractivePreviewBtn();
    	sitePreview.ClickDisablePreviewLnk();
    	
    	//Step 18
    	sitePreview.ClickInteractivePreviewBtn();
    	
    	//Step 19
    	sitePreview.VerifySelectAConditionValue("- Select -");
    	
    	//Step 20
    	sitePreview.VerifyEnablePreviewLnkVisible();
      
    		
	}
}
