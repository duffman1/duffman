package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.*;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/*************************************************************************************
* TEST CASE
* Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
* Step 2 - Create a Draft post<br>
* Step 3 - Click on Schedule Tab populate with future date and time and verify the success message
* Step 4 - Verify 'Run Now' & 'Cancel' Element(Button) exists 
* Step 5 - Click on 'Run Now' button<br>
* Step 6 - Verify 'Complete' text appears<br>
*
* @throws Throwable No Return values are needed
*************************************************************************************/

public class EnhanceOperationsDropdownonScheduleTab extends ParentTest {

    @Test(groups = {"full", "smoke"})
        public void ScheduletopublishContentbyDateTime() throws Exception{

      //Step 1
        UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");

      //Step 2
        taxonomy.VerifyContentMenuExist("Content");
        taxonomy.VerifyContentMenuExist("My Workbench");
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle = createDefaultContent.Post("Draft");

        
      //Step 3
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);           
        scheduleQueue.ClickScheduleTab();
        overlay.SwitchToActiveFrame();
        scheduleQueue.ClickAddScheduledRevisionLnk();
        overlay.SwitchToActiveFrame();     
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.SelectOperation("Moderate to Published");
        com.ibm.icu.util.Calendar cal = com.ibm.icu.util.Calendar.getInstance();
        cal.add(com.ibm.icu.util.Calendar.DATE, 10);
        Date date = cal.getTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        scheduleQueue.EnterDate(sDate);
        scheduleQueue.EnterTime("05:00 PM");
        scheduleQueue.ClickScheduleBtn();
        overlay.SwitchToActiveFrame();        
        ContentParent contentParent = new ContentParent(webDriver);
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
     //Step 4
        overlay.SwitchToActiveFrame(); 
        scheduleQueue.VerifyRunNowBtn();     
        scheduleQueue.VerifyCancelBtn();
     //Step 5
        scheduleQueue.ClickRunNowLnk();
     //Step 6
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(postTitle + " dialog");
        scheduleQueue.VerifyCompleteCronRunStatus();
       }
}