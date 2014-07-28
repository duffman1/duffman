package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SEOModules;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.XMLSiteMap;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypeStructure;

public class SEOOptimizationModule extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC1219
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17564564056
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SEOOptimizationModule_TC1219() throws Exception {

		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
       
		//Step 2
		Modules module = new Modules(webDriver, applib);
		taxonomy.NavigateSite("Modules");
		overlay.SwitchToActiveFrame();
		module.EnterFilterName("Pub SEO");
		module.EnableModule("Pub SEO");
		overlay.ClickCloseOverlayLnk();
    
		//Step 3
		taxonomy.NavigateSite("Structure>>Content types>>Post");
		overlay.SwitchToActiveFrame();
		
		//Step 4
		ContentTypeStructure contentTypeStructure = new ContentTypeStructure(webDriver);
		contentTypeStructure.ClickXMLSiteMapLink();
		contentTypeStructure.SelectInclusionOption("Included");
		contentTypeStructure.ClickSaveContentType();
		ContentParent contentParent = new ContentParent(webDriver, applib);
		contentParent.VerifyMessageStatus("The content type Post has been updated.");
		overlay.ClickCloseOverlayLnk();
		
		//Step 5
		CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
		String DraftPostTitle = createDefaultContent.Post("Draft");
		String PublishedPostTitle = createDefaultContent.Post("Published");
		System.out.println(PublishedPostTitle);
		
		//Step 6
		Cron cron = new Cron(webDriver, applib);
		cron.RunCron(true);
		
		//Step 7
		taxonomy.NavigateSite("Configuration>>Search and metadata>>XML sitemap>>Rebuild links");
		overlay.SwitchToActiveFrame();
		XMLSiteMap xmlSiteMap = new XMLSiteMap(webDriver);
		xmlSiteMap.ClickSaveAndRestoreCbx();
		xmlSiteMap.ClickRebuildSitemapBtn();
		Thread.sleep(2000); //TODO - dynamic wait
		overlay.switchToDefaultContent();
		overlay.WaitForFrameNotPresent("Rebuilding Sitemap");
		overlay.SwitchToActiveFrame();
		contentParent.VerifyMessageStatus("The sitemap links were rebuilt.");
		overlay.ClickCloseOverlayLnk();
			
		//Step 8
		taxonomy.NavigateSite("Configuration>>Search and metadata>>XML sitemap");
		overlay.SwitchToActiveFrame();
		xmlSiteMap.ClickXMLSiteMapLnk();
		contentParent.VerifyPageContentPresent(Arrays.asList(PublishedPostTitle));
		contentParent.VerifyPageContentNotPresent(Arrays.asList(DraftPostTitle));
		
		//Step 9 and 10 - N/A NOTE - for automation purposes we are only validating SEO for post content types in the interest of performance
		
	}
}
