package com.nbcuni.test.publisher.tests.Setup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;
import com.nbcuni.test.publisher.pageobjects.Queues.ScheduleQueue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class A1_TestSetup extends ParentTest {
	
    /*************************************************************************************
     * Test executes some common setup logic prior to full suite execution
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "smoke", "mpx"})
    public void TestSetup_Test() throws Exception{
         
        	//login
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        	
        	//enable overlay module if necessary
        	applib.openSitePage("/admin/modules");
            Modules modules = new Modules(webDriver, applib);
            if (modules.IsModuleEnabled("Overlay") == true) {
            	applib.openSitePage("/#overlay=admin/modules");
            }
            else {
            	modules.EnterFilterName("Overlay");
                modules.EnableModule("Overlay");
            }
            overlay.SwitchToActiveFrame();
            
        	//disable sticky edit actions module
        	modules.EnterFilterName("Sticky Edit Actions");
        	modules.DisableModule("Sticky Edit Actions");
            
        	//enable devel module
        	modules.EnterFilterName("Devel");
            modules.EnableModule("Devel");
            
            //disable devel module
        	modules.EnterFilterName("Acquia Purge");
            modules.DisableModule("Acquia Purge");
            
            //disable the deprecated 'ImageField Focus" module.
            modules.EnterFilterName("ImageField Focus");
            modules.DisableModule("ImageField Focus");
            
            //disable database logging module
            modules.EnterFilterName("Database logging");
            modules.DisableModule("Database logging");
            
            //enable pub post module
            modules.EnterFilterName("Pub Post");
            modules.EnableModule("Pub Post");
            
            //enable logo manage module
            modules.EnterFilterName("Logo Manager");
            modules.EnableModule("Logo Manager");
            
            //uninstall mps module to clean out any previously created mps blocks
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try {
            	modules.EnterFilterName("MPS");
            	modules.DisableModule("MPS");
            	overlay.ClickOverlayTab("Uninstall");
            	overlay.SwitchToActiveFrame();
            	modules.UninstallModule("MPS");
            	overlay.SwitchToActiveFrame();
            	overlay.ClickCloseOverlayLnk();
            }
            catch (Exception | AssertionError e) {
            	overlay.SwitchToActiveFrame();
            	overlay.ClickCloseOverlayLnk();
            }
            webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
            
            //schedule a post content item revision to be consumed by the SchedulingContentPublishUnpublished test later in the suite
            taxonomy.NavigateSite("Content>>Add content>>Post");
        	overlay.SwitchToActiveFrame();
        	contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
        	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        	publishingOptions.ClickPublishingOptionsLnk();
        	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	basicInformation.ClickBasicInformationTab();
        	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	Date currentDate = new Date();
        	String postTitle = "futurePost" + pub7DateFormat.format(currentDate) + random.GetCharacterString(15);
        	basicInformation.EnterTitle(postTitle);
        	basicInformation.EnterSynopsis();
        	overlay.SwitchToActiveFrame();
        	basicInformation.ClickCoverSelectBtn();
        	SelectFile selectFile = new SelectFile(webDriver, applib);
        	selectFile.SelectDefaultCoverImg();
        	overlay.SwitchToActiveFrame();
        	publishingOptions.ClickPublishingOptionsLnk();
        	publishingOptions.SelectModerationState("Draft");
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent(true);
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        	WorkBench workBench = new WorkBench(webDriver, applib);
            workBench.ClickWorkBenchTab("Schedule");
            overlay.SwitchToActiveFrame();
            ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
            scheduleQueue.ClickAddScheduledRevisionLnk();
            overlay.SwitchToActiveFrame();
            scheduleQueue.SelectRevision(postTitle);
            scheduleQueue.VerifyOperationOptions(Arrays.asList("Revert", "Delete", "Moderate to Draft", 
            		"Moderate to Published", "Moderate to Unpublished"));
            scheduleQueue.SelectOperation("Moderate to Published");
            Calendar cal1MinuteFuture = Calendar.getInstance();
            cal1MinuteFuture.add(Calendar.SECOND, 300);
        	Date date5MinuteFuture = cal1MinuteFuture.getTime();
        	pub7DateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7TimeFormat = new SimpleDateFormat("hh:mm a");
        	pub7TimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	String pub7Date5MinuteFuture = pub7DateFormat.format(date5MinuteFuture);
    	    String pub7Time5MinuteFuture = pub7TimeFormat.format(date5MinuteFuture);
            scheduleQueue.EnterDate(pub7Date5MinuteFuture);
            scheduleQueue.EnterTime(pub7Time5MinuteFuture);
            scheduleQueue.ClickScheduleBtn();
            overlay.SwitchToActiveFrame();
            contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
        	overlay.ClickCloseOverlayLnk();
        	
        	//schedule a new logo to be used later in the suite
        	taxonomy.NavigateSite("Content>>Logos");
        	overlay.SwitchToActiveFrame();
        	Logos logos = new Logos(webDriver, applib);
    	    logos.DeleteAllLogos();
        	overlay.ClickCloseOverlayLnk();
        	try {
        		taxonomy.NavigateSite("Content>>Logos>>Add Logo");
        	}
        	catch (Exception | AssertionError e) {
        		taxonomy.NavigateSite("Content>>Add Logo");
        	}
            overlay.SwitchToActiveFrame();
            AddLogo addLogo = new AddLogo(webDriver);
            String logoTitle = random.GetCharacterString(15);
            addLogo.EnterTitle(logoTitle);
            addLogo.EnterFilePath(applib.getPathToMedia() + "nbclogosmall.jpg");
    	    addLogo.ClickUploadBtn();
    	    addLogo.WaitForFileUploaded("nbclogosmall.jpg");
    	    addLogo.VerifyFileImagePresent("nbclogosmall");
            Calendar cal5MinuteFuture = Calendar.getInstance();
            cal5MinuteFuture.add(Calendar.MINUTE, 5);
        	date5MinuteFuture = cal5MinuteFuture.getTime();
        	Calendar cal60MinuteFuture = Calendar.getInstance();
            cal60MinuteFuture.add(Calendar.MINUTE, 60);
        	Date date60MinuteFuture = cal60MinuteFuture.getTime();
        	SimpleDateFormat pub7LogoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	pub7LogoDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	SimpleDateFormat pub7LogoTimeFormat = new SimpleDateFormat("hh:mm:ss a");
        	pub7LogoTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	addLogo.EnterStartDate(pub7LogoDateFormat.format(date5MinuteFuture));
    	    addLogo.EnterStartTime(pub7LogoTimeFormat.format(date5MinuteFuture));
    	    addLogo.EnterEndDate(pub7LogoDateFormat.format(date60MinuteFuture));
    	    addLogo.EnterEndTime(pub7LogoTimeFormat.format(date60MinuteFuture));
    	    addLogo.ClickSaveBtn();
    	    overlay.SwitchToActiveFrame();
    	    SimpleDateFormat pub7CreatedLogoDateTimeFormat = new SimpleDateFormat("EEE, MM/dd/yyyy - kk:mm");
    	    pub7CreatedLogoDateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	contentParent.VerifyPageContentPresent(Arrays.asList(logoTitle, 
    	    		pub7CreatedLogoDateTimeFormat.format(date5MinuteFuture).replace("24:", "00:"), pub7CreatedLogoDateTimeFormat.format(date60MinuteFuture)));
    	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
    	    overlay.ClickCloseOverlayLnk();
    	    
            //delete any old mpx account file types (DE3921)
            webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            try {
            	List<String> eachURL = new ArrayList<String>();
            	String allURLs = null;
            	for (WebElement el : webDriver.findElements(By.xpath("//a[contains(text(), 'MPX Video for Account')][contains(text(), 'DB TV')]"))) {
            		allURLs = allURLs + el.getAttribute("href");
            		eachURL.add(el.getAttribute("href"));
            	}
            	allURLs = allURLs.replaceAll(applib.getApplicationURL() + "/admin/structure/file-types/manage/", "");
            	String[] index = allURLs.split("mpx_video_");
            	
            	ArrayList<Integer> allIndexInts = new ArrayList<Integer>();
            	allIndexInts.removeAll(Collections.singleton("empty"));
            	for (String s : index) {
            		try {
            			allIndexInts.add(Integer.parseInt(s));
            		}
            		catch (NumberFormatException e) {}
            	}
            	Integer maxScore = Collections.max(allIndexInts);
            	for (String url : eachURL) {
    			
            		if (!url.contains("mpx_video_" + maxScore.toString())) {
            			webDriver.navigate().to(url);
            			webDriver.findElement(By.id("edit-delete")).click();
            			webDriver.findElement(By.id("edit-submit")).click();
            		}
            	}
            }
            catch (Exception e) {}
            webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
            
            //delete any created custom content types
            List<String> allowedContentTypes = new ArrayList<String>();
            allowedContentTypes.add("character-profile");
            allowedContentTypes.add("media-gallery");
            allowedContentTypes.add("movie");
            allowedContentTypes.add("person");
            allowedContentTypes.add("post");
            allowedContentTypes.add("tv-episode");
            allowedContentTypes.add("tv-season");
            allowedContentTypes.add("tv-show");
            List<WebElement> allContentTypes = webDriver.findElements(By.xpath("//a[text()='Content']/../ul//a[text()='Add content']/..//ul/li/a"));
            List<String> allContentTypeURLsToDelete = new ArrayList<String>();
            for (WebElement contentType : allContentTypes) {
            	allContentTypeURLsToDelete.add(contentType.getAttribute("href").replace(config.getConfigValue("AppURL") + "/node/add/", ""));
            }
            for (String contentType : allContentTypeURLsToDelete) {
            	if (!allowedContentTypes.contains(contentType)) {
            		
            		applib.openSitePage("/admin/structure/types/manage/" + contentType + "/delete");
            		webDriver.findElement(By.id("edit-submit")).click();
            	}
            }
            
            //run cron to clear out mpx content that's been deleted
            Cron cron = new Cron(webDriver, applib);
            cron.RunCron(false);
            
            //flush all caches
            taxonomy.NavigateSite("Home>>Flush all caches");
            
    }
}
