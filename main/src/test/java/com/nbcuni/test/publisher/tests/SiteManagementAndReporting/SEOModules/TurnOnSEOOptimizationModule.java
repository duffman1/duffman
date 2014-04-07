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

public class TurnOnSEOOptimizationModule extends ParentTest {

	/*************************************************************************************
	 * TEST CASE
	 * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
	 * Step 2 - Navigate to the Modules page, and ensure that the modules Pub SEO and Pub Post are enabled.<br>
	 * Step 3 - Navigate to Structure > Content Types > Post.<br>
	 * Step 4 - In the XML sitemap vertical tab, set the value of Inclusion to Included, and then click click Save content type.<br>
	 * Step 5 - Create and save two new Post content nodes, one unpublished and the other published.<br>
	 * Step 6 - If you had to enable Pub SEO at Step 2, run cron. If not, mark this step as Passed, and go to the next step.<br>
	 * Step 7 - Navigate to Configuration > Search and Metadata > XML sitemap > Rebuild links.<br>
	 * Step 8 - Navigate to Configuration > Search and Metadata > XML sitemap, click the link for the new sitemap, and view the map to ensure that published content is included and unpublished content is excluded<br>
	 * Step 9 - For every additional standard Content Type and one newly created custom Content Type, ensure that the value of Inclusion on the vertical tab XML sitemap is set to Included, and save that setting for each Content Type reconfigured.<br>
	 * Step 10 - Create a new published node for each remain standard Content Type, and a new published node for a new custom Content Type, rebuild the sitemap, and view it to ensure that all newly published content is included in the map.<br>
	 * @throws Throwable No Return values are needed
	 *************************************************************************************/
	 @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	 public void TurnOnSEOOptimizationModule_Test() throws Exception {

		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
       
		//Step 2
		Modules module = new Modules(webDriver, applib);
		module.VerifyModuleEnabled("Pub SEO");
    
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
		XMLSiteMap xmlSiteMap = new XMLSiteMap(webDriver, applib);
		xmlSiteMap.ClickSaveAndRestoreCbx();
		xmlSiteMap.ClickRebuildSitemapBtn();
		overlay.switchToDefaultContent();
		overlay.SwitchToFrame("Rebuilding Sitemap");
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
