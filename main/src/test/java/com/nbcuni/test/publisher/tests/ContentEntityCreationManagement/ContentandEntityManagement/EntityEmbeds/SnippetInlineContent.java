package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EntityEmbeds;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.TextFormat;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import com.nbcuni.test.publisher.pageobjects.Content.EmbedVideo;

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
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	Modules modules = new Modules(webDriver);
        	modules.VerifyModuleEnabled("Publisher WYSIWYG");
        	
        	Reporter.log("STEP 3");
        	navigation.Configuration("Text formats");
        	TextFormat textFormat = new TextFormat(webDriver);
        	textFormat.ClickConfigureLnk("Publisher");
        	textFormat.ClickEnabledFilters(Arrays.asList("Limit allowed HTML tags", "Publisher youtube formatter", 
        			"Convert line breaks into HTML", "Correct faulty and chopped off HTML"));
        	textFormat.EnterAllowedHTMLTags("<a> <em> <strong> <youtube> <div> <p>");
        	textFormat.ClickSaveConfigurationBtn();
        	contentParent.VerifyMessageStatus("The text format Publisher has been updated.");
        	
        	Reporter.log("STEP 4");
        	navigation.AddContent("Post");
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	String postTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(postTitle);
        	basicInformation.EnterSynopsis("<strong>Embedding a youtube video</strong>");
        	basicInformation.ClickEmbedYoutubeBtn();
        	EmbedVideo embedVideo = new EmbedVideo(webDriver);
        	String height = "360";
        	String width = "640";
        	embedVideo.EnterYouTubeVideoURL("http://www.youtube.com/embed/yp9pTFcD2uk");
        	embedVideo.EnterHeight(height);
        	embedVideo.EnterWidth(width);
        	embedVideo.ClickOkBtn();
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        	
        	Reporter.log("STEP 5");
        	embedVideo.VerifyVideoPresent("//youtube.com/embed/yp9pTFcD2uk", height, width);

    }
}
