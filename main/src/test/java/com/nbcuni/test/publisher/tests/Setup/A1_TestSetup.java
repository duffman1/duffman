package com.nbcuni.test.publisher.tests.Setup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import com.nbcuni.test.publisher.pageobjects.EmberNav;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.FlushCache;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.PublishingOptions;
import com.nbcuni.test.publisher.pageobjects.Content.SelectFile;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;
import com.nbcuni.test.publisher.pageobjects.Logo.AddLogo;
import com.nbcuni.test.publisher.pageobjects.Logo.Logos;
import com.nbcuni.test.publisher.pageobjects.MPX.Settings;
import com.nbcuni.test.publisher.pageobjects.Structure.Queues.Queues.ScheduleQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class A1_TestSetup {
	
    /*************************************************************************************
     * Test executes some common setup logic prior to full suite execution
     *************************************************************************************/
    public Boolean TestSetup_Test(Driver webDriver, AppLib applib) throws Exception {
    	
    	EmberNav navigation = new EmberNav(webDriver);
    	Config config = new Config();
		ContentParent contentParent = new ContentParent(webDriver);
		WaitFor waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    	Boolean abortTestSuite = false;
    	Boolean allIterationsFailed = false;
    	
    	int iter = 0;
        while (iter <= 3) {
        	iter++;
        	if (iter == 3) {
        		allIterationsFailed = true;
        	}
        	try {
        		
        		//login
            	UserLogin userLogin = applib.openApplication();
            	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            	
            	//disable overlay module if necessary
            	applib.openSitePage("/admin/modules");
                Modules modules = new Modules(webDriver);
                String overlayModule = "Overlay";
                if (modules.IsModuleEnabled(overlayModule) == true) {
                	modules.EnterFilterName(overlayModule);
                    modules.DisableModule(overlayModule);
                }
                webDriver.switchTo().defaultContent();
                
                //enable necessary modules
                for (String module : Arrays.asList("Field UI", "Pub Post", "Logo Manager", "Devel")) {
                	if (!modules.IsModuleEnabled(module)) {
                		modules.EnterFilterName(module);
                        modules.EnableModule(module);
                	}
                }
                
                //disable necessary modules
                for (String module : Arrays.asList("Sticky Edit Actions", "Acquia Purge", 
                		"ImageField Focus", "Database logging", "MPS", 
                		"Dynamic Queue Workbench", "Dynamic Queue", "Event Countdown", 
                		"TVE Auth Example", "Pub SURF Example",
                		"simpleSAMLphp authentication", "Program Guide Example", 
                		"Doubleclick for Publishers")) {
                	if (modules.IsModuleEnabled(module)) {
                		modules.EnterFilterName(module);
                    	modules.DisableModule(module);
                	}
                }
            	
                //uninstall some high data usage modules that overflow lists
                //TODO - this should only be done when needed and not every execution
                for (String module : Arrays.asList("MPS", "Dynamic Queue Workbench", "Dynamic Queue", 
                		"Event Countdown")) {
                	navigation.ClickPrimaryTabNavLnk("Uninstall");
                	if (modules.IsModuleInstalled(module)) {
                    	modules.UninstallModule(module);
                    }
                }
                
                //set timezone utc
            	applib.openSitePage("/admin/config/regional/settings");
            	webDriver.findElement(By.xpath("//select[@id='edit-date-default-timezone']/option[contains(text(), 'UTC')]")).click();
            	contentParent.ClickSaveBtn();
            	
            	//set date/time formats
            	applib.openSitePage("/admin/config/regional/date-time");
            	new Select(webDriver.findElement(By.id("edit-date-format-html5-tools-iso8601"))).selectByValue("c");
            	new Select(webDriver.findElement(By.id("edit-date-format-long"))).selectByValue("l, F j, Y - H:i");
            	new Select(webDriver.findElement(By.id("edit-date-format-medium"))).selectByValue("D, m/d/Y - H:i");
            	new Select(webDriver.findElement(By.id("edit-date-format-short"))).selectByValue("Y-m-d H:i");
            	new Select(webDriver.findElement(By.id("edit-date-format-edit-date"))).selectByValue("m/d/Y - h:i A");
            	contentParent.ClickSaveBtn();
            	
                //configure media gallery multi select
                applib.openSitePage("/admin/structure/types/manage/media-gallery/fields/field_media_items/widget-type");
                new Select(webDriver.findElement(By.id("edit-widget-type"))).selectByVisibleText("Media multiselect");
                contentParent.ClickSaveBtn();
                applib.openSitePage("/admin/structure/types/manage/media-gallery/fields/field_media_items");
                new Select(webDriver.findElement(By.id("edit-field-cardinality"))).selectByVisibleText("Unlimited");
                contentParent.ClickSaveBtn();

                //set file system paths if not already set
                applib.openSitePage("/admin/config/media/file-system");
                WebElement PublicFileSystemPath_Txb = webDriver.findElement(By.id("edit-file-public-path"));
                WebElement PrivateFileSystemPath_Txb = webDriver.findElement(By.id("edit-file-private-path"));
                
                if (PrivateFileSystemPath_Txb.getAttribute("value").equals("")) {
                	String privateFileSystemPath = null;
                	if (config.getConfigValueString("AppURL").contains(".pr")) {
                		privateFileSystemPath = "sites/default/files-private";
                	}
                	else if (config.getConfigValueString("AppURL").contains("acc.")) {
                		privateFileSystemPath = "/mnt/files/nbcupublisher7.acceptance/sites/default/files-private";
                	}
                	else if (config.getConfigValueString("AppURL").contains("acc-test")) {
                		privateFileSystemPath = "/mnt/files/nbcupublisher7.acceptance/sites/default/files-private";
                	}
                	else {
                		privateFileSystemPath = "/mnt/files/nbcupublisher7." + config.getConfigValueString("AppURL").replace("http://", "").replace(".publisher7.com", "") + "/sites/default/files-private";
                		privateFileSystemPath = config.getConfigValueString("AppURL").replace("http://", "").replace(".publisher7.com", "");
                	}
                
                	PublicFileSystemPath_Txb.clear();
                	PublicFileSystemPath_Txb.sendKeys("sites/default/files");
                	PrivateFileSystemPath_Txb.clear();
                	PrivateFileSystemPath_Txb.sendKeys("/mnt/files/nbcupublisher7" + privateFileSystemPath + "/sites/default/files-private");
                	WebElement TemporaryDirectory_Txb = webDriver.findElement(By.id("edit-file-temporary-path"));
                	TemporaryDirectory_Txb.clear();
                	TemporaryDirectory_Txb.sendKeys("/mnt/tmp/nbcupublisher7qa5");
                	webDriver.findElement(By.id("edit-file-default-scheme-public")).click();
                	contentParent.ClickSaveBtn();
                }
                
                //schedule a post content item revision to be consumed by the SchedulingContentPublishUnpublished test later in the suite
                navigation.AddContent("Post");
                contentParent.VerifyRequiredFields(Arrays.asList("Title", "Body"));
            	PublishingOptions publishingOptions = new PublishingOptions(webDriver);
            	publishingOptions.ClickPublishingOptionsLnk();
            	contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
            	BasicInformation basicInformation = new BasicInformation(webDriver);
            	basicInformation.ClickBasicInformationTab();
            	SimpleDateFormat pub7DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            	Date currentDate = new Date();
            	Random random = new Random();
            	String postTitle = "futurePost" + pub7DateFormat.format(currentDate) + random.GetCharacterString(15);
            	basicInformation.EnterTitle(postTitle);
            	basicInformation.EnterSynopsis();
            	basicInformation.ClickCoverSelectBtn();
            	SelectFile selectFile = new SelectFile(webDriver);
            	selectFile.SelectDefaultCoverImg();
            	publishingOptions.ClickPublishingOptionsLnk();
            	publishingOptions.SelectModerationState("Draft");
            	contentParent.ClickSaveBtn();
            	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
            	WorkBench workBench = new WorkBench(webDriver);
                workBench.ClickWorkBenchTab("Schedule");
                ScheduleQueue scheduleQueue = new ScheduleQueue(webDriver);
                scheduleQueue.ClickAddScheduledRevisionLnk();
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
                contentParent.VerifyMessageStatus("The scheduled revision operation has been saved");
            	
            	//schedule a new logo to be used later in the suite
            	applib.openSitePage("/admin/content/logos");
            	Logos logos = new Logos(webDriver, applib);
        	    logos.DeleteAllLogos();
            	applib.openSitePage("/logo/add");
                AddLogo addLogo = new AddLogo(webDriver);
                String logoTitle = random.GetCharacterString(15);
                addLogo.EnterTitle(logoTitle);
                addLogo.EnterFilePath(config.getConfigValueFilePath("PathToMediaContent") + "nbclogosmall.jpg");
        	    addLogo.ClickUploadBtn();
        	    addLogo.WaitForFileUploaded("nbclogosmall.jpg");
        	    addLogo.VerifyFileImagePresent("nbclogosmall");
                Calendar cal5MinuteFuture = Calendar.getInstance();
                cal5MinuteFuture.add(Calendar.MINUTE, 5);
            	date5MinuteFuture = cal5MinuteFuture.getTime();
            	Calendar cal120MinuteFuture = Calendar.getInstance();
                cal120MinuteFuture.add(Calendar.MINUTE, 120);
            	Date date120MinuteFuture = cal120MinuteFuture.getTime();
            	SimpleDateFormat pub7LogoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            	pub7LogoDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            	SimpleDateFormat pub7LogoTimeFormat = new SimpleDateFormat("hh:mm:ss a");
            	pub7LogoTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            	addLogo.EnterStartDate(pub7LogoDateFormat.format(date5MinuteFuture));
        	    addLogo.EnterStartTime(pub7LogoTimeFormat.format(date5MinuteFuture));
        	    addLogo.EnterEndDate(pub7LogoDateFormat.format(date120MinuteFuture));
        	    addLogo.EnterEndTime(pub7LogoTimeFormat.format(date120MinuteFuture));
        	    addLogo.ClickSaveBtn();
        	    SimpleDateFormat pub7CreatedLogoDateTimeFormat = new SimpleDateFormat("EEE, MM/dd/yyyy - kk:mm");
        	    pub7CreatedLogoDateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            	contentParent.VerifyPageContentPresent(Arrays.asList(logoTitle, 
        	    		pub7CreatedLogoDateTimeFormat.format(date5MinuteFuture).replace("24:", "00:"), pub7CreatedLogoDateTimeFormat.format(date120MinuteFuture).replace("24:", "00:")));
        	    logos.VerifyLogoImgPresent(logoTitle, "nbclogosmall");
        	    
        	    //configure mpx if needed
        	    Settings settings = new Settings(webDriver);
            	settings.ConfigureMPXIfNeeded();
            	settings.ConfigureMPXIngestionType();
            	
                //delete any old mpx account file types (DE3921)
            	settings.DeleteAllOldMPXFileTypes();
            	
            	//delete any created custom content types
                List<String> allowedContentTypes = new ArrayList<String>();
                allowedContentTypes.add("character-profile");
                allowedContentTypes.add("media-gallery");
                allowedContentTypes.add("movie");
                allowedContentTypes.add("person");
                allowedContentTypes.add("post");
                allowedContentTypes.add("article");
                allowedContentTypes.add("tv-episode");
                allowedContentTypes.add("tv-season");
                allowedContentTypes.add("tv-show");
                allowedContentTypes.add("event-countdown");
                applib.openSitePage("/node/add");
                String allContentLnksLoc = "//div[@id='content']//ul[@class='admin-list']//a";
                waitFor.ElementVisible(By.xpath(allContentLnksLoc));
                List<WebElement> allContentTypes = webDriver.findElements(By.xpath(allContentLnksLoc));
                List<String> allContentTypeURLsToDelete = new ArrayList<String>();
                for (WebElement contentType : allContentTypes) {
                	allContentTypeURLsToDelete.add(contentType.getAttribute("href").replace(config.getConfigValueString("AppURL") + "/node/add/", ""));
                }
                for (String contentType : allContentTypeURLsToDelete) {
                	if (!allowedContentTypes.contains(contentType)) {
                		
                		applib.openSitePage("/admin/structure/types/manage/" + contentType + "/delete");
                		webDriver.findElement(By.id("edit-submit")).click();
                	}
                }
                
                //flush all caches
                new FlushCache(webDriver).FlushAllCache();
                
                break;
        	}
        	catch (Exception | AssertionError e) {
        		e.printStackTrace();
        		webDriver.manage().deleteAllCookies();
        	}
        }
    	
        if (allIterationsFailed.equals(true)) {
        	if (config.getConfigValueString("AbortSuiteOnSetupFailure").equals("true")) {
        		abortTestSuite = true;
        	}
        }
        webDriver.quit();
        return abortTestSuite;
    }
}
