package com.nbcuni.test.publisher.tests.Queues.AddModerationStatesToRevisionsOfAQueue;


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

import com.nbcuni.test.publisher.AppLib;
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
import com.nbcuni.test.publisher.pageobjects.queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class QueueModerationStates extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test instance as Drupal User 1 (usually admin in Publisher test sites)<br>
     * Step 1a - Add a new unpublished post content item<br>
     * Step 2 - Do Content > Queues > Add Promo Queue<br>
     * Step 3 - In the Add Queue page:  a  Type a Title value b  Add at least one Queue Item (type single vowels to display a list of options) c  Uncheck the Published checkbox d  Click Save queue<br>
     * Step 4 - In the Content > Queues list, click edit for the queue added in the previous step<br>
     * Step 5 - Click the Revisions tab<br>
     * Step 6 - Click Cancel<br> 
     * Step 7 - Click the Revisions tab, and then click the circle-X Close button<br> 
     * Step 8 - Do the following:  a  Redisplay the Revisions tab for the queue created in Step 3 (begin with Via the Content > Queues;  b  Click edit for the queue created in Step 3;  c  Click the Revisions tab;  d  Set the value of Choose the event to fire to Publish;  e  Type a message into the Log message for this state change box; and then click Update State<br> 
     * Step 9 - Click the Revisions tab, and then inspect the entries in the History List column<br>
     * Step 10 - Set Choose the event to fire to Unpublish, type a message in the Log message for this state change box, and then click Update State<br>
     * Step 11 - Click the Revisions tab, and then inspect the State Flow Events Form column and the uppermost entry in the History List column<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void QueueModerationStates() throws Exception{
    	
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
        overlay.SwitchToAddPromoQueueFrm();
        
        //Step 3
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
        
        //Step 5
        QueuesRevisionList queuesRevisionList = new QueuesRevisionList(webDriver);
        queuesRevisionList.ClickRevisionsLnk();
        overlay.switchToDefaultContent();
        overlay.SwitchToQueuesRevisionListFrm();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	String date = sdf.format(new Date());
        queuesRevisionList.VerifyStateFlowHistoryEvent("Revision was set from Draft to Draft on " + date);
        //TODO add verifications for other relevant data on the revision history tab
        
        //Step 6
        queuesRevisionList.ClickCancelLnk();
        overlay.switchToDefaultContent();
        overlay.SwitchToEditQueueFrm(queueTitle);
        
        //Step 7
        overlay.ClickCloseOverlayLnk();
        
        //Step 8
        
        Assert.fail("Test under construction");
        
    }
}
