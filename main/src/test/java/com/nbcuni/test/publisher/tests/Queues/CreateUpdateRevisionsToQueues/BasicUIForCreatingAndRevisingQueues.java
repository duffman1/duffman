package com.nbcuni.test.publisher.tests.Queues.CreateUpdateRevisionsToQueues;


import java.util.Arrays;
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
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
import com.nbcuni.test.webdriver.CustomWebDriver;
import com.nbcuni.test.webdriver.WebDriverClientExecution;


public class BasicUIForCreatingAndRevisingQueues extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the test site as Drupal User 1<br>
     * Step 2 - Do Content > Queues > Add promo queue<br>
     * Step 3 - Click Save queue<br>
     * Step 4 - Type a value in the Title box, and then click Save queue<br>
     * Step 5 - Click the delete link for an entry in the Content list Queues tab<br>
     * Step 6 - Click Cancel<br>
     * Step 7 - Click the edit link for an entry in the Content list Queues tab list<br>
     * Step 8 - Change the value of Title; leave the Published, Create new revision and Set Revision as default boxes checked; and then click Save queue<br>
     * Step 9 - In the Content > Queues list, click the edit link for a queue entry<br>
     * Step 10 - Change the value of Title, uncheck the Set Revision as default checkbox, and then click Save queue<br>
     * Step 11 - Again display the Edit <queuename> overlay, and then uncheck the Create new revision box<br>
     * Step 12 - Change the value of Title, and then click Save queue<br>
     * Step 13 - Display the Edit <queuename> overlay for a queue, uncheck the Create new revision and Published boxes, change the value of Title, and click Save queue<br>
     * Step 14 - Again display the Edit <queuename> overlay<br> 

     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void BasicUIForCreatingAndRevisingQueues() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
        
        //Step 2
        Taxonomy taxonomy = new Taxonomy(webDriver);
        taxonomy.ClickTier1ContentTier2QueuesTier3AddPromoQueueLnk();
        
        //Step 3
        Overlay overlay = new Overlay(webDriver);
        overlay.SwitchToAddPromoQueueFrm();
        Queues queues = new Queues(webDriver);
        queues.ClickSaveQueueBtn();
        ErrorChecking errorChecking = new ErrorChecking(webDriver);
        errorChecking.VerifyAllRequiredFields(Arrays.asList("Title"));
        
        //Step 4
        Random random = new Random();
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.ClickSaveQueueBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToQueuesListingFrm();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle));
        
        //Step 5
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickDeleteQueueMenuBtn(queueTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToDeleteQueueFrm(queueTitle);
        
        //Step 6
        DeleteQueue deleteQueue = new DeleteQueue(webDriver);
        deleteQueue.ClickCancelLnk();
        overlay.switchToDefaultContent();
        overlay.SwitchToQueuesListingFrm();
        
        //Step 7
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToEditQueueFrm(queueTitle);
        
        //Step 8
        String modQueueTitle = random.GetCharacterString(15);
        queues.EnterTitle(modQueueTitle);
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        queues.ClickSaveQueueBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToQueuesListingFrm();
        queues.VerifyQueuesInList(Arrays.asList(modQueueTitle));
        
        //Step 9
        Assert.fail("Test steps indicate a 'set revision as default' checkbox should be present but I can't find it. emailing team for help.");
        
    }
}
