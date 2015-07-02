package com.nbcuni.test.publisher.tests.Queues.DeletingQueueRevisions;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.Queues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.QueuesRevisionList;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class DeleteRevisionsQueues extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into QA instance as an admin.   Code was initially deployed to QA3Dev ,Login succeeds 
     * Step 2 - Go to Content > Queues,Queues page loads 
     * Step 3 - Click Add Promo Queue and Create a Queue,Queue is created  
     * Step 4 - Click Edit, add a queue item and click Save  NOTE: keep the create new revision box checked.,A new revision is created. 
     * Step 5 - Repeat Step 4 to create another revision,Another revision is created 
     * Step 6 - Click Edit to open the Queue and navigate to Revisions tab,Revisions tab of the Queue loads with the list of revisions 
     * Step 7 - Expand Operations menu for the first revision listed and Click Delete,An error message " This revision cannot be deleted because it is currently the primary revision for this queue" 
     * Step 8 - Expand Operations for the other revision(s) in the list and Click Delete ,An overlay 'Are you sure you want to delete this revision?' opens with Delete revision and Cancel buttons 
     * Step 9 - Click Delete revision,A success message 'The revision has been successfully deleted' is seen with the latest list of revisions 
     * Step 10 - Repeat Steps 8 and 9 until only one revision is left  ,Only one revision is left 
     * Step 11 - For the only revision left, Expand Operations and Click Delete,An error message "This revision cannot be deleted because it is currently the primary revision for this queue" is seen 
     * Step 12 - Verify there is only 1 revision left in the list
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void DeleteRevisionsQueues_Test() throws Exception{
    	
        //Step 1
        UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 2
        navigation.Content("Queues");
        
        //Step 3
        Queues queues = new Queues(webDriver);
        queues.ClickAddPromoQueueLnk();
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle));
        
        //Step 4
        queues.ClickEditQueueMenuBtn(queueTitle);
        queues.EnterQueueItem("a", "1");
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        
        //Step 5
        queues.ClickEditQueueMenuBtn(queueTitle);
        queues.EnterQueueItem("b", "2");
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        
        //Step 6
        queues.ClickEditQueueMenuBtn(queueTitle);
        QueuesRevisionList queuesRevisionList = new QueuesRevisionList(webDriver);
        queuesRevisionList.ClickRevisionsLnk();
        
        //Step 7
        queuesRevisionList.ClickEditQueueExtendMenuBtn("1");
        queuesRevisionList.ClickDeleteQueueMenuBtn("1");
        contentParent.VerifyMessageError("This revision cannot be deleted because it is currently the primary revision for this queue");
        
        //Step 8
        queuesRevisionList.ClickEditQueueExtendMenuBtn("2");
        queuesRevisionList.ClickDeleteQueueMenuBtn("2");
        
        //Step 9
        Delete delete = new Delete(webDriver);
        delete.ClickDeleteRevisionBtn();
        contentParent.VerifyMessageStatus("The revision has been successfully deleted.");
        
        //Step 10
        queuesRevisionList.ClickEditQueueExtendMenuBtn("2");
        queuesRevisionList.ClickDeleteQueueMenuBtn("2");
        delete.ClickDeleteRevisionBtn();
        contentParent.VerifyMessageStatus("The revision has been successfully deleted.");
        
        //Step 11
        queuesRevisionList.ClickEditQueueExtendMenuBtn("1");
        queuesRevisionList.ClickDeleteQueueMenuBtn("1");
        contentParent.VerifyMessageError("This revision cannot be deleted because it is currently the primary revision for this queue");
        
        //Step 12
        queuesRevisionList.VerifyRevisionCount(1);
        
    }
}
