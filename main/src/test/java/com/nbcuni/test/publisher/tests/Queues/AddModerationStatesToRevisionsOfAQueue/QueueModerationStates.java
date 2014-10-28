package com.nbcuni.test.publisher.tests.Queues.AddModerationStatesToRevisionsOfAQueue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.Queues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.QueuesRevisionList;

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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void QueueModerationStates_Test() throws Exception{
    	
    	//Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 1a
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 2
        taxonomy.NavigateSite("Content>>Queues>>Add Promo Queue");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Queues queues = new Queues(webDriver);
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterQueueItem(postTitle, "1");
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        overlay.SwitchToActiveFrame();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle)); 
        
        //Step 4
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.SwitchToActiveFrame();
        
        //Step 5
        QueuesRevisionList queuesRevisionList = new QueuesRevisionList(webDriver);
        queuesRevisionList.ClickRevisionsLnk();
        overlay.SwitchToActiveFrame();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    	String date = sdf.format(new Date());
        queuesRevisionList.VerifyStateFlowHistoryEvent("Revision was set from Draft to Draft on " + date);
        //TODO add verifications for other relevant data on the revision history tab
        
        //Step 6
        queuesRevisionList.ClickCancelLnk();
        overlay.SwitchToActiveFrame();
        
        //Step 7
        overlay.ClickCloseOverlayLnk();
        
        //Step 8
        taxonomy.NavigateSite("Content>>Queues");
        overlay.SwitchToActiveFrame();
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.SwitchToActiveFrame();
        queuesRevisionList.ClickRevisionsLnk();
        overlay.SwitchToActiveFrame();
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.SelectModerationState("Publish");
        String messageForStateChange = random.GetCharacterString(15) + " " + random.GetCharacterString(10);
        publishingOptions.EnterMessageForStateChange(messageForStateChange);
        queuesRevisionList.ClickUpdateStateBtn();
        contentParent.VerifyMessageStatus(queueTitle + " transitioned to the published state.");
        
        //Step 9
        queuesRevisionList.ClickRevisionsLnk();
        overlay.SwitchToActiveFrame();
        queuesRevisionList.VerifyStateFlowHistoryEvent("Revision was set from Draft to Published on " + date);
        queuesRevisionList.VerifyStateFlowHistoryEvent(messageForStateChange);
        
        //Step 10
        publishingOptions.SelectModerationState("Unpublish");
        String messageForStateChangeUnpub = random.GetCharacterString(15) + " " + random.GetCharacterString(10);
        publishingOptions.EnterMessageForStateChange(messageForStateChangeUnpub);
        queuesRevisionList.ClickUpdateStateBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyMessageStatus(queueTitle + " transitioned to the unpublished state.");
        
        //Step 11
        queuesRevisionList.ClickRevisionsLnk();
        overlay.SwitchToActiveFrame();
        queuesRevisionList.VerifyStateFlowHistoryEvent("Revision was set from Published to Unpublished on " + date);
        queuesRevisionList.VerifyStateFlowHistoryEvent(messageForStateChangeUnpub);
        
    }
}
