package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.Logo;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CreateLogo extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1066
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441709609
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreateLogo_TC1066() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Setup
        	Modules modules = new Modules(webDriver, applib);
            modules.VerifyModuleEnabled("Logo Manager");
        	taxonomy.NavigateSite("Content>>Logos");
        	overlay.SwitchToActiveFrame();
        	Logos logos = new Logos(webDriver, applib);
    	    logos.DeleteAllLogos();
        	overlay.ClickCloseOverlayLnk();
        	
            //Step 2
        	try {
        		taxonomy.NavigateSite("Content>>Logos>>Add Logo");
        	}
        	catch (NoSuchElementException e) {
        		taxonomy.NavigateSite("Content>>Add Logo");
        	}
            overlay.SwitchToActiveFrame();
            
            //Step 3
            AddLogo addLogo = new AddLogo(webDriver);
            String logoTitle = random.GetCharacterString(15);
            addLogo.EnterTitle(logoTitle);
            addLogo.EnterFilePath(applib.getPathToMedia() + "nbclogosmall.jpg");
    	    addLogo.ClickUploadBtn();
    	    addLogo.WaitForFileUploaded("nbclogosmall.jpg");
    	    addLogo.VerifyFileImagePresent("nbclogosmall");
            Calendar cal15SecondsFuture = Calendar.getInstance();
            cal15SecondsFuture.add(Calendar.SECOND, 15);
        	Date date15SecondsFuture = cal15SecondsFuture.getTime();
        	Calendar cal60SecondsFuture = Calendar.getInstance();
            cal60SecondsFuture.add(Calendar.SECOND, 60);
        	Date date60SecondsFuture = cal60SecondsFuture.getTime();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm:ss a");
        	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	String pub7Date15SecondsFuture = pub7DateFormat.format(date15SecondsFuture);
    	    String pub7Time30SecondsFuture = pub7TimeFormat.format(date15SecondsFuture);
    	    String pub7Time60SecondsFuture = pub7TimeFormat.format(date60SecondsFuture);
    	    addLogo.EnterStartDate(pub7Date15SecondsFuture);
    	    addLogo.EnterStartTime(pub7Time30SecondsFuture);
    	    addLogo.EnterEndDate(pub7Date15SecondsFuture);
    	    addLogo.EnterEndTime(pub7Time60SecondsFuture);
    	    addLogo.ClickSaveBtn();
    	    
    	    //Step 4
    	    overlay.SwitchToActiveFrame();
    	    SimpleDateFormat pub7DateTimeFormat = new SimpleDateFormat("EEE, MM/dd/yyyy - kk:mm");
        	pub7DateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	String pub7DateTime15SecondsFuture = pub7DateTimeFormat.format(date15SecondsFuture);
        	String pub7DateTime60SecondsFuture = pub7DateTimeFormat.format(date60SecondsFuture);
    	    contentParent.VerifyPageContentPresent(Arrays.asList(logoTitle, 
    	    		pub7DateTime15SecondsFuture.replace("24:", "00:"), pub7DateTime60SecondsFuture.replace("24:", "00:")));
    	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
    	    
    	    //Step 5
    	    overlay.ClickCloseOverlayLnk();
    	    Thread.sleep(15000);
    	    Cron cron = new Cron(webDriver, applib);
    	    cron.RunCron(true);
    	    taxonomy.NavigateSite("Home");
    	    
    	    //Step 6
    	    logos.VerifyHomePageLogoImgPresent("nbclogosmall");
    	    
    	    //Step 7
    	    Thread.sleep(45000); 
    	    cron.RunCron(true);
    	    taxonomy.NavigateSite("Home");
    	    logos.VerifyHomePageLogoImgPresent("logo");
    	    
    	    //Step 8
    	    taxonomy.NavigateSite("Content>>Logos");
    	    overlay.SwitchToActiveFrame();
    	    logos.ClickEditExtendMenuBtn(logoTitle);
    	    logos.ClickEditMenuDeleteBtn(logoTitle);
    	    overlay.SwitchToActiveFrame();
    	    Delete delete = new Delete(webDriver);
    	    delete.ClickDeleteBtn();
    	    overlay.SwitchToActiveFrame();
    	    contentParent.VerifyMessageStatus("Logo has been deleted successfully.");
    	    contentParent.VerifyPageContentNotPresent(Arrays.asList(logoTitle, 
    	    		pub7DateTime15SecondsFuture.replace("24:", "00:"), pub7DateTime60SecondsFuture.replace("24:", "00:")));
    	    
    }
}
