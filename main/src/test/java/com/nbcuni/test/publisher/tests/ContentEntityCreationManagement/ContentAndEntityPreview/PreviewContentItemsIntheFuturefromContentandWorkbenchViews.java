package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentAndEntityPreview;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.SitePreview.SitePreview;

public class PreviewContentItemsIntheFuturefromContentandWorkbenchViews extends ParentTest {

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
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	public void PreviewContentItemsIntheFuturefromContentandWorkbenchViews_Test() throws Exception{
		
		//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2        
    	taxonomy.NavigateSite("Content>>Add content>>Post");
    	overlay.SwitchToActiveFrame();
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String postTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(postTitle);    	
    	basicInformation.EnterSynopsis();
    	overlay.SwitchToActiveFrame();
    	String shortDescriptionText = random.GetCharacterString(25);
    	basicInformation.EnterShortDescription(shortDescriptionText);
    	basicInformation.ClickCoverSelectBtn();
    	SelectFile selectFile = new SelectFile(webDriver, applib);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToActiveFrame();
    	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	ContentParent contentParent = new ContentParent(webDriver, applib);
    	contentParent.ClickSaveBtn();
    	overlay.switchToDefaultContent();
    	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
    	
        //Step 3
    	SitePreview sitePreview = new SitePreview(webDriver);
        sitePreview.ClickPreviewSiteLnk();    	
        
    	//Step 4			
        sitePreview.SelectACondition();
		
        //Step 5
        Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	Date date = cal.getTime();
    	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        sitePreview.EnterDate(sDate);
        sitePreview.EnterTime("12:40 pm");
        
        //Step 6        
        sitePreview.ClickEnablePreviewBtn(); 
        
        //Step 7  
        sitePreview.VerifyDisablePreviewBtnVisible();
        sitePreview.VerifyUpdatePreviewBtnVisible();
        
        //Step 8
        WorkBench workBench = new WorkBench(webDriver, applib);
        workBench.ClickWorkBenchTab("Edit Draft");
        overlay.SwitchToActiveFrame();
        String updatedPostTitle = "Updated" + postTitle;
        String updatedShortDescription = "Updated" + shortDescriptionText;
        basicInformation.EnterTitle(updatedPostTitle);
        basicInformation.EnterShortDescription(updatedShortDescription);
        String updatedBodyTxt = basicInformation.EnterSynopsis(); 
        overlay.SwitchToActiveFrame();
        contentParent.ClickSaveBtn();
        
        //Step 9
    	overlay.switchToDefaultContent();
    	workBench.ClickWorkBenchTab("Schedule");
    	overlay.SwitchToActiveFrame();
    	ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
    	scheduleQueue.ClickAddScheduledRevisionLnk();
    	overlay.SwitchToActiveFrame();
    	scheduleQueue.SelectRevision(updatedPostTitle);
    	scheduleQueue.SelectOperation("Moderate to Published");
    	scheduleQueue.EnterDate(sDate);
    	scheduleQueue.EnterTime("12:40 PM");
    	scheduleQueue.ClickScheduleBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 10
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sDate);
    	sitePreview.VerifyTimeValue("12:40 pm");
    	webDriver.navigate().refresh();  
    	contentParent.VerifyPageContentPresent(Arrays.asList(updatedShortDescription, updatedBodyTxt));
    	
    	//Step 11
    	sitePreview.ClickPreviewSiteLnk();
    	sitePreview.VerifyDisablePreviewBtnNotVisible();
    	
    	//Step 12
    	sitePreview.ClickPreviewSiteLnk();
    	sitePreview.VerifyDisablePreviewBtnVisible();
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sDate);
    	sitePreview.VerifyTimeValue("12:40 pm");
    	
    	//Step 13
    	workBench.ClickWorkBenchTab("Schedule");
    	overlay.SwitchToActiveFrame();    	
    	scheduleQueue.ClickAddScheduledRevisionLnk();    	
    	overlay.SwitchToActiveFrame();
    	scheduleQueue.SelectRevision(updatedPostTitle);
    	scheduleQueue.SelectOperation("Moderate to Published");    	
    	cal.add(Calendar.DATE, 2);
    	Date dateafter2 = cal.getTime();    	
        String ssDate = sdfDate.format(dateafter2);
    	scheduleQueue.EnterDate(ssDate);
    	scheduleQueue.EnterTime("1:40 AM");
    	scheduleQueue.ClickScheduleBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved"); 
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 14
    	sitePreview.VerifySelectAConditionValue("Site as of ...");
    	sitePreview.VerifyDateValue(sDate);
    	sitePreview.VerifyTimeValue("12:40 pm");
    	
    	//Step 15
    	sitePreview.EnterDate(ssDate);
        sitePreview.EnterTime("12:40 pm");
        sitePreview.ClickUpdatePreviewBtn();
        
        //Step 16
        overlay.switchToDefaultContent();
        contentParent.VerifyPageContentPresent(Arrays.asList(updatedShortDescription, updatedBodyTxt));
        
    	//Step 17
    	sitePreview.ClickDisablePreviewBtn();
    	
    	//Step 18
    	sitePreview.ClickPreviewSiteLnk();
    	
    	//Step 19
    	sitePreview.VerifySelectAConditionValue("- Select -");
    	
    	//Step 20
    	sitePreview.VerifyEnablePreviewBtnVisible();
      
    		
	}
}
