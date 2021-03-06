package com.nbcuni.test.publisher.tests.Queues.SchedulingQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.Queues;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void ScheduleRevisionsOfQueues_Test() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
        //Step 1a
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 2
        navigation.Content("Queues");
        Queues queues = new Queues(webDriver);
        queues.ClickAddPromoQueueLnk();
        
        //Step 3
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterQueueItem(postTitle, "1");
        queues.EnterLogMessageStateChange(random.GetCharacterString(10));
        queues.ClickSaveQueueBtn();
        queues.VerifyQueuesInList(Arrays.asList(queueTitle)); 
        
        //Step 4
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        
        //Step 5, 6, and 7 (truncated)
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        navigation.ClickPrimaryTabNavLnk("Schedule");
        scheduleQueue.ClickAddScheduledRevisionLnk();
        
        //Step 8
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
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
        navigation.Content("Queues");
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        navigation.ClickPrimaryTabNavLnk("Schedule");
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(queueTitle, "Moderate to Publish", sDate + " - 05:00 PM"));
        
    }
}
