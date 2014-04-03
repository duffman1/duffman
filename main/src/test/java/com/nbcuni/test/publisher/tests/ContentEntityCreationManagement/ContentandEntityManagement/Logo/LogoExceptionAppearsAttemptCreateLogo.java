package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.Logo;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.ErrorChecking.ErrorChecking;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LogoExceptionAppearsAttemptCreateLogo extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1,Login with valid Drupal 1 credentials ,Login Succesful 
     * Step 1a, Enable the 'Logo Manager' module if needed.
     * Step 2 - Navigate to Content>>Logos>>Add Logo ,Add Logo page appears 
     * Step 3 - Populate the fields:
     * 		1.Title
     * 		2.Start Date
     * 		3.Enddate
     * 		4.Upload logo
     * 		5.Save, "Manage Logo"  overlay appears with logo view. The Logo should be successfully scheduled at that time 
     * Step 4 - Verify the new logo appears in the 'Logos' list, with corresponding title, start date, and end dates.
	 * Step 5 - Run Cron.
	 * Step 6 - Verify image logo is present on home page.
	 * Step 7 - Wait for logo end date, run cron, assert default logo present.
	 * Step 8 - Navigate to Content>>Logos and delete the newly created logo.
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void LogoExceptionAppearsAttemptCreateLogo_Test() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
            //Step 1a
            Modules modules = new Modules(webDriver, applib);
            modules.VerifyModuleEnabled("Logo Manager");
            
            //Step 2
            taxonomy.NavigateSite("Content>>Logos>>Add Logo");
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
    	    Logos logos = new Logos(webDriver);
    	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
    	    
    	    //Step 5
    	    overlay.ClickCloseOverlayLnk();
    	    Thread.sleep(15000);
    	    taxonomy.NavigateSite("Home>>Run cron");
    	    overlay.SwitchToActiveFrame();
    	    ErrorChecking errorChecking = new ErrorChecking(webDriver, applib);
    	    errorChecking.VerifyNoMessageErrorsPresent();
    	    overlay.ClickCloseOverlayLnk();
    	    taxonomy.NavigateSite("Home");
    	    
    	    //Step 6
    	    logos.VerifyHomePageLogoImgPresent("nbclogosmall");
    	    
    	    //Step 7
    	    Thread.sleep(45000); //TODO - add a better dynamic wait for this item.
    	    taxonomy.NavigateSite("Home>>Run cron");
    	    overlay.SwitchToActiveFrame();
    	    errorChecking.VerifyNoMessageErrorsPresent();
    	    overlay.ClickCloseOverlayLnk();
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
