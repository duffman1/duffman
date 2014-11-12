package com.nbcuni.test.publisher.tests.Queues.CreateUpdateRevisionsToQueues;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.Queues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.QueuesRevisionList;

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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "certify"})
    public void BasicUIForCreatingAndRevisingQueues_Test() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
        navigation.Content("Queues");
        Queues queues = new Queues(webDriver);
        queues.ClickAddPromoQueueLnk();
        
        //Step 3
        queues.ClickSaveQueueBtn();
        ErrorChecking errorChecking = new ErrorChecking(webDriver);
        errorChecking.VerifyAllRequiredFields(Arrays.asList("Title"));
        
        //Step 4
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle));
        
        //Step 5
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickDeleteQueueMenuBtn(queueTitle);
        
        //Step 6
        Delete delete = new Delete(webDriver);
        delete.ClickCancelLnk();
        
        //Step 7
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        
        //Step 8
        String modQueueTitle = random.GetCharacterString(15);
        queues.EnterTitle(modQueueTitle);
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        queues.VerifyQueuesInList(Arrays.asList(modQueueTitle));
        
        //Step 9
        queues.ClickEditQueueExtendMenuBtn(modQueueTitle);
        queues.ClickEditQueueMenuBtn(modQueueTitle);
        
        //Step 10
        String modQueueTitle2 = random.GetCharacterString(15);
        queues.EnterTitle(modQueueTitle2);
        publishingOptions.ClickCreateNewRevisionCbx();
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        
        //Step 11
        queues.VerifyQueuesInList(Arrays.asList(modQueueTitle2));
        queues.ClickEditQueueExtendMenuBtn(modQueueTitle2);
        queues.ClickEditQueueMenuBtn(modQueueTitle2);
        QueuesRevisionList queuesRevisionList = new QueuesRevisionList(webDriver);
        queuesRevisionList.ClickRevisionsLnk();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    	String date = sdf.format(new Date());
    	
        queuesRevisionList.VerifyStateFlowHistoryEvent("--> Draft on " + date);
        
        //Step 12-14 N/A
        
    }
}
