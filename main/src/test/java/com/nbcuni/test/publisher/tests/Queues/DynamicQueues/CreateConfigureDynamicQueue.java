package com.nbcuni.test.publisher.tests.Queues.DynamicQueues;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.AddDynamicQueue;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.AddDynamicQueueType;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;

public class CreateConfigureDynamicQueue extends ParentTest{
	
	/*************************************************************************************
     * TEST CASE - TC4197
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/20794225692
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke" })
    public void CreateConfigureDynamicQueue_TC4197() throws Exception{
    	
        Reporter.log("STEP 1");
        UserLogin userLogin = applib.openApplication();
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        Reporter.log("STEP 2");
        Modules modules = new Modules(webDriver, applib);
        modules.VerifyModuleEnabled("Dynamic Queue");
        
        Reporter.log("STEP 3");
        taxonomy.NavigateSite("Structure>>Dynamic Queue types>>Add dynamic queue type");
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 4");
        String dynamicQueueTypeName = random.GetCharacterString(15);
        AddDynamicQueueType addDynamicQueueType = new AddDynamicQueueType(webDriver);
        addDynamicQueueType.EnterName(dynamicQueueTypeName);
        addDynamicQueueType.SelectEntityType("Node");
        addDynamicQueueType.ClickSaveBtn();
        overlay.SwitchToActiveFrame();
        contentParent.VerifyPageContentPresent(Arrays.asList(dynamicQueueTypeName));
        overlay.ClickCloseOverlayLnk();
        
        Reporter.log("STEP 5");
        taxonomy.NavigateSite("Content>>Dynamic Queues>>Add " + dynamicQueueTypeName);
        overlay.SwitchToActiveFrame();
        
        Reporter.log("STEP 6");
        String dynamicQueueTitle = random.GetCharacterString(15);
        AddDynamicQueue addDynamicQueue = new AddDynamicQueue(webDriver);
        
        
        /*
        Assert.fail("restart here.");
        
        
        
        //Step 1a
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");
        
        //Step 2
        taxonomy.NavigateSite("Content>>Queues>>Add Promo Queue");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Queues queues = new Queues(webDriver, applib);
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
        
        //Step 5, 6, and 7 (truncated)
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.VerifyCreateNewRevisionCbxChecked();
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        
        //Step 8
        overlay.SwitchToActiveFrame();
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
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved.");
        overlay.ClickCloseOverlayLnk();
        taxonomy.NavigateSite("Content>>Queues");
        overlay.SwitchToActiveFrame();
        queues.ClickEditQueueExtendMenuBtn(queueTitle);
        queues.ClickEditQueueMenuBtn(queueTitle);
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.VerifyScheduledQueue(Arrays.asList(queueTitle, "Moderate to Publish", sDate + " - 05:00 PM"));
        */
    }
}
