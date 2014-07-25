package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.Logo;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;
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
            
        	//Step 2 (previously scheduled in setup test)
        	Logos logos = new Logos(webDriver, applib);
        	logos.VerifyHomePageLogoImgPresent("nbclogosmall");
        	
        	//Step 3
        	try {
        		taxonomy.NavigateSite("Content>>Logos>>Add Logo");
        	}
        	catch (Exception | AssertionError e) {
        		taxonomy.NavigateSite("Content>>Add Logo");
        	}
            overlay.SwitchToActiveFrame();
            
            //Step 4
            AddLogo addLogo = new AddLogo(webDriver);
            String logoTitle = random.GetCharacterString(15);
            addLogo.EnterTitle(logoTitle);
            addLogo.EnterFilePath(applib.getPathToMedia() + "nbclogosmall.jpg");
    	    addLogo.ClickUploadBtn();
    	    addLogo.WaitForFileUploaded("nbclogosmall.jpg");
    	    addLogo.VerifyFileImagePresent("nbclogosmall");
            Calendar cal5MinutesFuture = Calendar.getInstance();
            cal5MinutesFuture.add(Calendar.MINUTE, 5);
        	Date date5MinutesFuture = cal5MinutesFuture.getTime();
        	Calendar cal10MinutesFuture = Calendar.getInstance();
            cal10MinutesFuture.add(Calendar.MINUTE, 10);
        	Date date10MinutesFuture = cal10MinutesFuture.getTime();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm:ss a");
        	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	addLogo.EnterStartDate(pub7DateFormat.format(date5MinutesFuture));
    	    addLogo.EnterStartTime(pub7TimeFormat.format(date5MinutesFuture));
    	    addLogo.EnterEndDate(pub7DateFormat.format(date10MinutesFuture));
    	    addLogo.EnterEndTime(pub7TimeFormat.format(date10MinutesFuture));
    	    addLogo.ClickSaveBtn();
    	    
    	    //Step 5
    	    overlay.SwitchToActiveFrame();
    	    SimpleDateFormat pub7DateTimeFormat = new SimpleDateFormat("EEE, MM/dd/yyyy - kk:mm");
        	pub7DateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	contentParent.VerifyPageContentPresent(Arrays.asList(logoTitle, 
    	    		pub7DateTimeFormat.format(date5MinutesFuture).replace("24:", "00:"), pub7DateTimeFormat.format(date10MinutesFuture).replace("24:", "00:")));
    	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
    	    
    	    //Step 6
    	    overlay.ClickCloseOverlayLnk();
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
    	    		pub7DateTimeFormat.format(date5MinutesFuture).replace("24:", "00:"), pub7DateTimeFormat.format(date10MinutesFuture).replace("24:", "00:")));
    	    
    }
}
