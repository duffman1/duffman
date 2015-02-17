package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.EntityEmbeds;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.BasicInformation;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;

public class MarkdownTextAreas extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC4802
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/21992626044
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void MarkdownTextAreas_TC4802() throws Exception {
         
        	Reporter.log("STEP 1");
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
        	Reporter.log("STEP 2");
        	navigation.AddContent("Post");
        	
        	Reporter.log("STEP 3 AND 4");
        	BasicInformation basicInformation = new BasicInformation(webDriver);
        	basicInformation.SelectTextFormat("Markdown");
        	
        	Reporter.log("STEP 5");
        	contentParent.VerifyPageContentPresent(Arrays.asList("Two or more spaces at a line's end = Line break", "Double returns = Paragraph"));
        	
        	Reporter.log("STEP 6");
        	String postTitle = random.GetCharacterString(15);
        	basicInformation.EnterTitle(postTitle);
        	String bodyTxt = "This is a paragraph example. Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n\n# Header 1\n## Header 2\n### Header 3\n#### Header 4\n##### Header 5\n###### Header 6\n\n> This is a blockquote example. Lorem ipsum dolor sit amet\n\n1. This\n2. Is\n3. An\n4. Ordered\n5. List";
        	basicInformation.EnterBody(bodyTxt);
        	contentParent.ClickSaveBtn();
        	contentParent.VerifyMessageStatus("Post " + postTitle + " has been created.");
        	contentParent.VerifySourceInPage(Arrays.asList("<p>This is a paragraph example. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>",
        			"<h1>Header 1</h1>", "<h6>Header 6</h6>",
        			"<blockquote>", "<p>This is a blockquote example. Lorem ipsum dolor sit amet</p>", "</blockquote>",
        			"<li>This</li>"));
    }
}
