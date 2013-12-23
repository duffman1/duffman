package com.nbcuni.test.publisher.tests.Queues.SchedulingQueues;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.pageobjects.Logout;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.CastCrew;
import com.nbcuni.test.publisher.pageobjects.content.CharactersInformation;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.content.PersonsInformation;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.errorchecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.queues.DeleteQueue;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class ScheduleRevisionsOfQueues extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the QA instance as admin<br>
     * Step 1a - Add an unpublished post<br>
     * Step 2 - Go to Content > Queues > Add Promo Queue<br>
     * Step 3 - Provide a Title and add one or more Queue Items and Click Save<br> 
     * Step 4 - Click Edit for the Queue created in Step 3<br> 
     * Step 5 - Make sure create new revision is checked and click save queue<br>
     * Step 6 - Click edit for the queue saved in step 5<br> 
     * Step 7 - Click Schedule and click Add Scheduled Revision<br> 
     * Step 8 - Make the following selections and click schedule  * Revision - one of the revisions displayed * Operation - Moderate to Publish * Scheduled date and time - valid date and time in future<br> 
     * Step 9 - Click Schedule<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void ScheduleRevisionsOfQueues() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 1a
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ModulesLnk();
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToModulesFrm();
        Modules modules = new Modules(webDriver);
        modules.EnterFilterName("Pub Post");
        modules.EnableModule("Pub Post");
        overlay.ClickCloseOverlayLnk();
        overlay.switchToDefaultContent();
        taxonomy.ClickTier1ContentTier2AddContentTier3PostLnk();
        overlay.SwitchToCreatePostFrm();
        ContentParent contentParent = new ContentParent(webDriver);
        BasicInformation basicInformation = new BasicInformation(webDriver);
        Random random = new Random();
        String postTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(postTitle);
        basicInformation.EnterSynopsis();
        overlay.switchToDefaultContent();
        overlay.SwitchToCreatePostFrm();
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        selectFile.SelectDefaultCoverImg();
        overlay.SwitchToCreatePostFrm();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        publishingOptions.SelectModerationState("Draft");
        contentParent.ClickSaveBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        
        //Step 2
        taxonomy.ClickTier1ContentTier2QueuesTier3AddPromoQueueLnk();
        
        //Step 3
        overlay.SwitchToAddPromoQueueFrm();
        Queues queues = new Queues(webDriver);
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterQueueItem(postTitle, "1");
        queues.ClickSaveQueueBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToQueuesListingFrm();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle)); 
        
        //Step 4
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToEditQueueFrm(queueTitle);
        
        //Step 5, 6, and 7 (truncated for test efficiency)
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickScheduleTab();
        overlay.switchToDefaultContent();
        overlay.SwitchToEditQueueSchedulingFrm(queueTitle);
        scheduleQueue.ClickAddScheduledRevisionLnk();
        
        //Step 8
        overlay.switchToDefaultContent();
        overlay.SwitchToEditQueueSchedulingFrm(queueTitle);
        scheduleQueue.SelectRevision(queueTitle);
        scheduleQueue.SelectOperation("Moderate to Publish");
        Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 10);
    	Date date = cal.getTime();
    	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        scheduleQueue.EnterDate(sDate);
        scheduleQueue.EnterTime("05:00 PM");
        
        //Step 9
        scheduleQueue.ClickScheduleBtn();
        overlay.switchToDefaultContent();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
        taxonomy.ClickTier1ContentTier2QueuesLnk();
        //overlay.SwitchToQueuesListingFrm(); NOT IN AN OVERLAY - STILL FUNCTIONAL THOUGH - EMAILING RICH BURKE
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.switchToDefaultContent();
        //overlay.SwitchToEditQueueFrm(queueTitle);
        scheduleQueue.ClickScheduleTab();
        scheduleQueue.VerifyScheduledQueue(queueTitle);
        scheduleQueue.VerifyScheduledQueue("Moderate to Publish");
        scheduleQueue.VerifyScheduledQueue(sDate + " - 05:00 PM");
        
        
    }
}
