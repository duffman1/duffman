package com.nbcuni.test.publisher.tests.Queues.SchedulingQueues;

import com.ibm.icu.util.Calendar;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.queues.Queues;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    @Test(groups = {"full", "smoke" })
    public void ScheduleRevisionsOfQueues_Test() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 1a
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Pub Post");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 2
        taxonomy.NavigateSite("Content>>Queues>>Add Promo Queue");
        
        //Step 3
        overlay.SwitchToFrame("Add promo queue");
        Queues queues = new Queues(webDriver);
        String queueTitle = random.GetCharacterString(15);
        queues.EnterTitle(queueTitle);
        queues.EnterQueueItem(postTitle, "1");
        queues.ClickSaveQueueBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Queues Listing");
        queues.VerifyQueuesInList(Arrays.asList(queueTitle)); 
        
        //Step 4
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(queueTitle);
        
        //Step 5, 6, and 7 (truncated)
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        
        //Step 8
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(queueTitle);
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
        overlay.SwitchToActiveFrame();
        ContentParent contentParent = new ContentParent(webDriver, applib);
        PageFactory.initElements(webDriver, contentParent);
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
        overlay.switchToDefaultContent();
        taxonomy.NavigateSite("Content>>Queues");
        overlay.SwitchToFrame("Queues Listing");
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(queueTitle);
        scheduleQueue.VerifyScheduledQueue("Moderate to Publish");
        scheduleQueue.VerifyScheduledQueue(sDate + " - 05:00 PM");
        
        
    }
}
