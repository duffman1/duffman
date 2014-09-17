package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EntityEmbeds;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.TextFormat;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.EmbedYoutubeVideo;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SnippetInlineContent extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC5129
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/22802361829
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SnippetInlineContent_TC5129() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver, applib);
        	modules.VerifyModuleEnabled("Publisher WYSIWYG");
        	
        	Reporter.log("STEP 3");
        	taxonomy.NavigateSite("Configuration>>Content authoring>>Text formats>>Publisher");
        	overlay.SwitchToActiveFrame();
        	TextFormat textFormat = new TextFormat(webDriver);
        	textFormat.ClickEnabledFilters(Arrays.asList("Limit allowed HTML tags", "Publisher youtube formatter", 
        			"Convert line breaks into HTML", "Correct faulty and chopped off HTML"));
        	textFormat.EnterAllowedHTMLTags("<a> <em> <strong> <youtube> <div> <p>");
        	textFormat.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The text format Publisher has been updated.");
        	overlay.ClickCloseOverlayLnk();
        	
        	Reporter.log("STEP 4");
        	taxonomy.NavigateSite("Content>>Add content>>Post");
        	overlay.SwitchToActiveFrame();
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	String postTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(postTitle);
        	basicInformation.EnterSynopsis("<strong>Embedding a youtube video</strong>");
        	overlay.SwitchToActiveFrame();
        	basicInformation.ClickYoutubeBtn();
        	EmbedYoutubeVideo embedYoutubeVideo = new EmbedYoutubeVideo(webDriver);
        	embedYoutubeVideo.EnterYoutubeURL("https://www.youtube.com/watch?v=__dZ_v7rQh4");
        	embedYoutubeVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	overlay.switchToDefaultContent(true);
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        	
        	
        	
        	
        	
    	    
    }
}
