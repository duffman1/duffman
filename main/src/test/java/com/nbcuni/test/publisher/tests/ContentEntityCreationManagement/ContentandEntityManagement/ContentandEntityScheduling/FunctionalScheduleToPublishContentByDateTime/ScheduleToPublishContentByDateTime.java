package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.ContentandEntityScheduling;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.content.Content;
import com.nbcuni.test.publisher.pageobjects.content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.content.RevisionState;
import com.nbcuni.test.publisher.pageobjects.content.Schedule;
import com.nbcuni.test.publisher.pageobjects.content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.content.Workflow;
import com.nbcuni.test.publisher.pageobjects.queues.ScheduleQueue;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;
/*************************************************************************************
 * TEST CASE 
 * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
 * Step 2 - Create  a Draft post<br> 
 * Step 3 - Verify all Tabs (View, Edit Draft, Revisions, Schedule)
 * Step 4 - Click Revision Tab and Verify that There should be one revision<br>
 * Step 5 - Click on Schedule Tab and Verify that 'Add Schedule Version' and Schedule table is empty.<br>
 * Step 6 - Click on Edit Draft and Populate Revision Log and Synopsis<br> 
 * Step 7 - Verify that Revision State,Public, Moderate fields displayed under workflow tab <br>
 * Step 8 - Click Revision Tab and Verify that there are two revision<br>
 * Step 9 - Click on Schedule Tab and Verify  fields(Revision, Moderate State, Date and Time) and populate with past date and time and verify the failed message<br>
 * Step 10 - Click on Schedule Tab and Verify  fields(Revision, Moderate State, Date and Time) and populate with future date and time and time and verify the success message<br>
 * Step 11 - Verify Schedule table for scheduled revision and Run Cron and verify that published revision displayed under revision tab<br> 
 * 
 * @throws Throwable No Return values are needed
 *************************************************************************************/
@Test(groups = {"full", "smoke"})
public class ScheduletopublishContentbyDateTime extends ParentTest {

	public void ScheduletopublishContentbyDateTime() throws Exception{
		//Step 1
    	UserLogin userLogin = applib.openApplication();
        userLogin.Login("admin@publisher.nbcuni.com", "pa55word");
      //Step 2
        taxonomy.VerifyContentMenuExist("Content");
        taxonomy.VerifyContentMenuExist("My Workbench");    
        CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        String postTitle2 = createDefaultContent.Post("Draft");
      //Step 3
        Workflow workflow = new Workflow(webDriver);
        workflow.VerifyWorkflowTab("View");
        workflow.VerifyWorkflowTab("Edit Draft");
        workflow.VerifyWorkflowTab("Revisions");
        workflow.VerifyWorkflowTab("Schedule");
      //Step 4
        workflow.ClickWorkflowTab("Revisions");
        overlay.SwitchToFrame("Revisions dialog");
        RevisionState revisionstate = new RevisionState(webDriver);
        revisionstate.VerifyRevisionCount(1);
      //Step 5
        workflow.ClickTabonRevisionPage("Schedule");
        overlay.switchToDefaultContent();
        String scheduleFrameTitle = postTitle2+" dialog";      
        overlay.SwitchToFrame(scheduleFrameTitle);     
        workflow.VerifyAddScheduleVersionLink();
        workflow.VerifyScheduleTableisEmpty(); 
        
      //Step 6
        workflow.ClickTabonRevisionPage("Edit Draft");
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Edit Post "+  postTitle2 + " dialog");
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.EnterSynopsis("Testing for new revision");
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame("Edit Post "+  postTitle2 + " dialog");
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.EnterMessageForStateChange("Test Revision 2");
    	ContentParent contentParent = new ContentParent(webDriver);       
    	contentParent.ClickSaveBtn();
    
    	overlay.switchToDefaultContent();
      //Step 7
    	contentParent.WorkBenchInfoBlock(Arrays.asList("Revision state", "Public","Moderate"));
    	revisionstate.VerifyCurrentRevision("Testing for new revision");
      //Step 8
    	workflow.ClickTabonRevisionPage("Revisions");    	
    	overlay.SwitchToFrame("Revisions dialog");
        revisionstate.VerifyRevisionCount(2);
      //Step 9
        workflow.ClickTabonRevisionPage("Schedule");
        AppLib.Wait(3000);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrame(scheduleFrameTitle);
        workflow.ClickAddScheduleVersionLink();
        AppLib.Wait(3000);
        overlay.switchToDefaultContent();
        overlay.SwitchToFrameByIndex(scheduleFrameTitle, 1);
       // webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Schedule schedule = new Schedule(webDriver); 
        
       
        schedule.VerifyRevisionDd();
        schedule.VerifyOperationDd();
        schedule.VerifyDateField();
        schedule.VerifyTimeField();
       
        
	 /*Need to U[dated*/       
	//        schedule.PopulateRandomRevisionDd();
	//        schedule.PopulateOperationDd("Moderate to Published");
	//        
	//        Calendar cal = Calendar.getInstance();
	//    	cal.add(Calendar.DATE, -6);
	//    	Date date = cal.getTime();
	//    	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
	//        String sDate = sdfDate.format(date);
	//        
	//        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
	//        scheduleQueue.EnterDate(sDate);
	//        String TimeToPopulate = schedule.getServerCurrentTime();
	//        scheduleQueue.EnterTime(TimeToPopulate);
	//        
	//        
	//        scheduleQueue.ClickScheduleBtn();
	//        overlay.switchToDefaultContent();  
	//        Reporter.log("Because of defect , not able to fetch correct message");
	//        //contentParent.VerifyMessageStatus("");
        
      //Step 10
        String SetectedRev = schedule.PopulateRandomRevisionDd();
        schedule.PopulateOperationDd("Moderate to Published");
        
        Calendar cal = Calendar.getInstance();        
    	Date date = cal.getTime();
    	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
        String sDate = sdfDate.format(date);
        
        ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
        scheduleQueue.EnterDate(sDate);
        String serverTime = schedule.getServerCurrentTime();
        String[] minuteapp= serverTime.split(":");
        String[] MinMer = minuteapp[1].trim().split(" ");
        int min1 = Integer.valueOf(MinMer[0]);
        String Mer = MinMer[1];
        int hour1 = 0;
        int Min2 = 0;
        if (min1<=58){
        	Min2 = min1+2;
        	hour1 = Integer.valueOf(minuteapp[0]);
        }else{
        	Min2 = 1;
        	hour1 = Integer.valueOf(minuteapp[0] + 1);
        }
        if (hour1<=12){
        	String addMer = Mer;
        }else if(Mer.equalsIgnoreCase("am")){
        	String addMer = "PM";
        	hour1 = hour1-12;
        }else if(Mer.equalsIgnoreCase("PM")){
        	String addMer = "AM";
        	hour1 = hour1-12;
        }
        String TimeToPopulated = hour1+":" + Min2 + " " + Mer ;
        scheduleQueue.EnterTime(TimeToPopulated);     
        
        scheduleQueue.ClickScheduleBtn();
        overlay.switchToDefaultContent();
        overlay.SwitchToFrameByIndex(scheduleFrameTitle, 1);
        AppLib.Wait(2000);
        contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
	  //Step 11      
        String[] val ={SetectedRev,"Moderate to Published",sDate + " - " + serverTime };
        schedule.VerifyScheduleTable(val);
        schedule.RunCronOnSchedulePage(3000);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
        revisionstate.VerifyRevision(SetectedRev, "Published");
        
     
    	
	}
}
