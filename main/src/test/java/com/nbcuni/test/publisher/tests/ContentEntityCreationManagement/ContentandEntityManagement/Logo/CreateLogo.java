package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.Logo;

import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
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
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	//Step 2 (previously scheduled in setup test)
        	Logos logos = new Logos(webWebWebDriver, applib);
        	logos.VerifyHomePageLogoImgPresent("nbclogosmall");
        	
        	//Step 3
        	navigation.Content("Logos");
        	logos.ClickAddLogoLnk();
        	
            //Step 4
            AddLogo addLogo = new AddLogo(webWebWebDriver);
            String logoTitle = random.GetCharacterString(15);
            addLogo.EnterTitle(logoTitle);
            addLogo.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "nbclogosmall.jpg");
    	    addLogo.ClickUploadBtn();
    	    addLogo.WaitForFileUploaded("nbclogosmall.jpg");
    	    addLogo.VerifyFileImagePresent("nbclogosmall");
            Calendar cal2DaysFuture = Calendar.getInstance();
            cal2DaysFuture.add(Calendar.HOUR, 48);
        	Date date2DaysFuture = cal2DaysFuture.getTime();
        	Calendar cal3DaysFuture = Calendar.getInstance();
            cal3DaysFuture.add(Calendar.HOUR, 72);
        	Date date3DaysFuture = cal3DaysFuture.getTime();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm:ss a");
        	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	addLogo.EnterStartDate(pub7DateFormat.format(date2DaysFuture));
    	    addLogo.EnterStartTime(pub7TimeFormat.format(date2DaysFuture));
    	    addLogo.EnterEndDate(pub7DateFormat.format(date3DaysFuture));
    	    addLogo.EnterEndTime(pub7TimeFormat.format(date3DaysFuture));
    	    addLogo.ClickSaveBtn();
    	    
    	    //Step 5
    	    SimpleDateFormat pub7DateTimeFormat = new SimpleDateFormat("EEE, MM/dd/yyyy - kk:mm");
        	pub7DateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	contentParent.VerifyPageContentPresent(Arrays.asList(logoTitle, 
    	    		pub7DateTimeFormat.format(date2DaysFuture).replace("24:", "00:"), pub7DateTimeFormat.format(date3DaysFuture).replace("24:", "00:")));
    	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
    	    
    	    //Step 6
    	    navigation.Content("Logos");
    	    logos.DeleteAllLogos();
    	    contentParent.VerifyMessageStatus("Logo has been deleted successfully.");
    	    contentParent.VerifyPageContentNotPresent(Arrays.asList(logoTitle, 
    	    		pub7DateTimeFormat.format(date2DaysFuture).replace("24:", "00:"), pub7DateTimeFormat.format(date2DaysFuture).replace("24:", "00:")));
    	    
    }
}
