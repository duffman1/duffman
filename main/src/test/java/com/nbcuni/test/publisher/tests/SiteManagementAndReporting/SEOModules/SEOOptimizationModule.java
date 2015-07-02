package com.nbcuni.test.publisher.tests.SiteManagementAndReporting.SEOModules;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Configuration.XMLSiteMap;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Cron.Cron;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypeStructure;
import com.nbcuni.test.publisher.pageobjects.Structure.ContentTypes;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SEOOptimizationModule extends GlobalBaseTest {

	/*************************************************************************************
	 * TEST CASE - TC1219
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17564564056
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void SEOOptimizationModule_TC1219() throws Exception {

		//Step 1
		UserLogin userLogin = appLib.openApplication();
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
       
		//Step 2
		navigation.Modules();
		Modules module = new Modules(webDriver);
		module.EnableModule("Pub SEO");
		
		//Step 3
		navigation.Structure("Content types");
		ContentTypes contentTypes = new ContentTypes(webDriver);
		contentTypes.ClickEditLnk("Post");
		
		//Step 4
		ContentTypeStructure contentTypeStructure = new ContentTypeStructure(webDriver);
		contentTypeStructure.ClickXMLSiteMapLink();
		contentTypeStructure.SelectInclusionOption("Included");
		contentTypeStructure.ClickSaveContentType();
		ContentParent contentParent = new ContentParent(webDriver);
		contentParent.VerifyMessageStatus("The content type Post has been updated.");
		
		//Step 5
		CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
		String DraftPostTitle = createDefaultContent.Post("Draft");
		String PublishedPostTitle = createDefaultContent.Post("Published");
		
		//Step 6
		Cron cron = new Cron(webDriver);
		cron.RunCron();
		
		//Step 7
		navigation.Configuration("XML sitemap");
		navigation.ClickPrimaryTabNavLnk("Rebuild links");
		XMLSiteMap xmlSiteMap = new XMLSiteMap(webDriver);
		xmlSiteMap.ClickSaveAndRestoreCbx();
		xmlSiteMap.ClickRebuildSitemapBtn();
		contentParent.WaitForProgressBarNotPresent();
		contentParent.VerifyMessageStatus("The sitemap links were rebuilt.");
			
		//Step 8
		navigation.Configuration("XML sitemap");
		xmlSiteMap.ClickXMLSiteMapLnk();
		contentParent.VerifyPageContentPresent(Arrays.asList(PublishedPostTitle));
		contentParent.VerifyPageContentNotPresent(Arrays.asList(DraftPostTitle));
		
		//Step 9 and 10 - N/A NOTE - for automation purposes we are only validating SEO for post content types in the interest of performance
		
	}
}
