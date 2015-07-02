package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EnhanceOperationsDropdownonScheduleTab extends GlobalBaseTest {
	/*************************************************************************************
	* TEST CASE
	* Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
	* Step 2 - Create a Draft post<br>
	* Step 3 - Click on Schedule Tab populate with future date and time and verify the success message
	* Step 4 - Verify 'Run Now' & 'Cancel' Element(Button) exists 
	* Step 5 - Click on 'Run Now' button<br>
	* Step 6 - Verify 'Complete' text appears<br>
	* @throws Throwable No Return values are needed
	*************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
        public void EnhanceOperationsDropdownonScheduleTab_Test() throws Exception{

    	//Step 1
        UserLogin userLogin = appLib.openApplication();
        userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));

        //Step 2
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
        String postTitle = createDefaultContent.Post("Draft");

        //Step 3
        WorkBench workBench = new WorkBench(webDriver);
        workBench.ClickWorkBenchTab("Schedule");
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.ClickAddScheduledRevisionLnk();
        scheduleQueue.SelectRevision(postTitle);
        scheduleQueue.SelectOperation("Moderate to Published");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        Date date = cal.getTime();
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        scheduleQueue.EnterDate(sDate);
        scheduleQueue.EnterTime("05:00 PM");
        scheduleQueue.ClickScheduleBtn();
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
     
        //Step 4
        scheduleQueue.VerifyRunNowLnkPresent(postTitle, "Moderate to Published");     
        scheduleQueue.VerifyCancelLnkPresent(postTitle, "Moderate to Published");
     
        //Step 5
        scheduleQueue.ClickRunNowLnk(postTitle, "Moderate to Published");
     
        //Step 6
        scheduleQueue.VerifyRunStatusComplete();
        
       }
}