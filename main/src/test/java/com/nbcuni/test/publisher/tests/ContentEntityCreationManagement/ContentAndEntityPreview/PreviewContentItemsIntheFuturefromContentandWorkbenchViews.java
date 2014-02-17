package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentAndEntityPreview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.ProgramGuide;
import com.nbcuni.test.publisher.pageobjects.PreviewSites.PreviewSites;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.content.Workflow;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;

public class PreviewContentItemsIntheFuturefromContentandWorkbenchViews extends ParentTest {

	 /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 2 - Create  a Published post<br> 
     * Step 3 - Click on Preview Site link on menu option<br>
     * Step 4 - Populate "Select A Condition"<br>
     * Step 5 - Populate Date and Time.<br>
     * Step 6 - Click on Enable Preview<br> 
     * Step 7 - Verify That DisablePreview and UpdatePreview buttons appeared <br> 
     * Step 8 - Edit Draft and update Title, Short Description, Synopsis <br>
     * Step 9 - Schedule the post on one day ahead<br>
     * Step 10 - Verify that SitePreview information retained.<br>
     * Step 11 - Click on SitePreview menu and Verify that SitePreview got hidden.
     * Step 12 - Click on SitePreview menu and Verify that SitePreview got Visible
     * 
     * Step 13 - Schedule the post on two days ahead<br>
     * Refresh the URL<br> 
     * Step 14 - Verify that SitePreview information retained<br> 
     * Step 15 - Update Site Preview info. <br> 
     * Step 16 - Verify Short description and body text <br>    
    
     * Step 17 - Disable the SitePreview<br> 
     * Step 18 - Click on SitePreview menu option <br> 
     * Step 19 - Verify the "Select A Condition" dropdown has "- Select -" selected <br>
     * Step 20 - Verify that EnablePreview Button displayed<br>
     
     * @throws Throwable No Return values are needed
     *************************************************************************************/
	@Test(groups = {"full"})
	public void PreviewContentItemsIntheFuturefromContentandWorkbenchViews()throws Exception{
		
		//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2        
    	taxonomy.NavigateSite("Content>>Add content>>Post");
    	overlay.SwitchToFrame("Create Post");
    	ContentParent contentParent = new ContentParent(webDriver);
    	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	BasicInformation basicInformation = new BasicInformation(webDriver);
    	String postTitle = random.GetCharacterString(15);
    	basicInformation.EnterTitle(postTitle);    	
    	String BodyText = random.GetCharacterString(25);
    	basicInformation.EnterSynopsis(BodyText);
    	String shortDescriptionText = random.GetCharacterString(25);
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	basicInformation.EnterShortDescription(shortDescriptionText);
    	overlay.switchToDefaultContent();
    	overlay.SwitchToFrame("Create Post");
    	basicInformation.ClickCoverSelectBtn();
    	SelectFile selectFile = new SelectFile(webDriver, applib);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToFrame("Create Post");
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState("Published");
    	contentParent.ClickSaveBtn();
    	overlay.switchToDefaultContent();
    	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
    	
    	PreviewSites previewSites = new PreviewSites(webDriver);
        PageFactory.initElements(webDriver, previewSites);
        //Step 3
        previewSites.ClickPrviewSiteLink();    	
        overlay.switchToDefaultContent();
    				ContentParent contentparent = new ContentParent(webDriver);
    	//Step 4			
        previewSites.SelectACondition();
		Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	Date date = cal.getTime();
    	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        //Step 5
        previewSites.EnterDate(sDate);
        //Step 6        
        previewSites.EnterTime("12:40 pm");
        //Step 7
        previewSites.ClickEnablePrview();        
        previewSites.VerifyDisablePreviewButton(true);
        overlay.switchToDefaultContent();
        previewSites.VerifyUpdatePreviewButton(true);
        //Step 8
        overlay.switchToDefaultContent();
        Workflow workflow = new Workflow(webDriver);
        workflow.ClickWorkflowTab("Edit Draft");
        String UpadatedpostTitle = "Updated"+postTitle;
        String UpadatedBodyText = "Updated"+BodyText;
        String UpadatedshortDescriptionText = "Updated"+shortDescriptionText;
        overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
        basicInformation.EnterTitle(UpadatedpostTitle);
        basicInformation.EnterShortDescription(UpadatedshortDescriptionText);
        basicInformation.EnterSynopsis(UpadatedBodyText); 
        overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
        contentParent.ClickSaveBtn();
        
        //Step 9
    	overlay.switchToDefaultContent();
    	workflow.ClickWorkflowTab("Schedule");
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	ScheduleQueue schedule = new ScheduleQueue(webDriver);
    	schedule.ClickAddScheduledRevisionLnk();
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	schedule.SelectRevision(UpadatedpostTitle);
    	schedule.SelectOperation("Moderate to Published");
    	schedule.EnterDate(sDate);
    	schedule.EnterTime("12:40 PM");
    	schedule.ClickScheduleBtn();
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 10
    	overlay.switchToDefaultContent();
    	previewSites.VerifySelectACondition("Site as of ...");
    	previewSites.VerifyDate(sDate);
    	previewSites.VerifyTime("12:40 pm");
    	webDriver.navigate().refresh();    	
    	contentparent.VerifyPostShortDescription(UpadatedshortDescriptionText);
    	contentparent.VerifyPostBodyDescription(UpadatedBodyText);
    	
    	//Step 11
    	previewSites.ClickPrviewSiteLink(); 
    	overlay.switchToDefaultContent();
    	previewSites.VerifyPreviewSiteAreaHidden();
    	
    	//Step 12
    	previewSites.ClickPrviewSiteLink();
    	overlay.switchToDefaultContent();
    	previewSites.VerifyPreviewSiteAreaVisible();    	
    	previewSites.VerifySelectACondition("Site as of ...");
    	previewSites.VerifyDate(sDate);
    	previewSites.VerifyTime("12:40 pm");
    	//Step 13
    	workflow.ClickWorkflowTab("Schedule");    	
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();    	
    	schedule.ClickAddScheduledRevisionLnk();    	
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	schedule.SelectRevision(UpadatedpostTitle);
    	schedule.SelectOperation("Moderate to Published");    	
    	cal.add(Calendar.DATE, 2);
    	Date dateafter2 = cal.getTime();    	
        String ssDate = sdfDate.format(dateafter2);
    	schedule.EnterDate(ssDate);
    	schedule.EnterTime("1:40 AM");
    	schedule.ClickScheduleBtn();
    	overlay.switchToDefaultContent();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("The scheduled revision operation has been saved"); 
    	overlay.ClickCloseOverlayLnk();
    	overlay.switchToDefaultContent();
    	//Step 14
    	previewSites.VerifySelectACondition("Site as of ...");
    	previewSites.VerifyDate(sDate);
    	previewSites.VerifyTime("12:40 pm");
    	overlay.switchToDefaultContent();
    	
    	//Step 15
    	previewSites.EnterDate(ssDate);
        previewSites.EnterTime("12:40 pm");
        previewSites.ClickUpdatePrview();
        //Step 16
        overlay.switchToDefaultContent();    	
        contentparent.VerifyPostShortDescription(UpadatedshortDescriptionText);
    	contentparent.VerifyPostBodyDescription(UpadatedBodyText);
    	//Step 17
    	previewSites.ClickDisablePreviewButton();
    	//Step 18
    	previewSites.ClickPrviewSiteLink();
    	//Step 19
    	previewSites.VerifySelectACondition("- Select -");
    	//Step 20
    	previewSites.VerifyEnablePrviewButton(true);
      
    	
    		
	}
}
