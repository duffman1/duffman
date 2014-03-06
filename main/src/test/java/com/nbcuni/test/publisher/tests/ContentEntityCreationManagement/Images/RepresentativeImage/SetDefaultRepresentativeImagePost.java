package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.RepresentativeImage;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;

public class SetDefaultRepresentativeImagePost extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the QA env as Drupal User 1 (usually admin | pa55word in Publisher test sites).,Login succeeds.
     * Step 2 - Click Modules, Search for Pub Post and enable it (if it is not already enabled), and then click Save Configuration.,Post is enabled.
     * Step 3 - Go to Content > Add Content > Post.,The Post page loads.
     * Step 4 - Provide all the required values (Title and Post), and then click the Cover Media Select button.,The Select a file overlay opens.
     * Step 5 - Upload an image or select an existing image from View Library, and then click Submit.,An editing session is opened for the newly uploaded image.
     * Step 6 - In the image editing overlay, click Save.,The Create Post page is displayed with the newly added image showing as a thumbnail in the Cover Media section.
     * Step 7 - Click Save.,a  The new Post is successfully created. b  The View tab for the new Post is displayed.
     * Step 8 - Go to Admin > Content and look for the Post created in Step 6.,The Post created should be listed with a thumbnail of the cover image selected.  
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void SetDefaultRepresentativeImagePost_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
            
        	//Step 2 - N/A as post will be enabled on prior tests
        	
        	//Step 3 through 7
        	CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
        	String postTitle = createDefaultContent.Post("Published");
        	
        	//Step 8
        	taxonomy.NavigateSite("Content");
        	overlay.SwitchToActiveFrame();
        	SearchFor searchFor = new SearchFor(webDriver, applib);
        	searchFor.EnterTitle(postTitle);
        	searchFor.ClickApplyBtn();
        	overlay.switchToDefaultContent();
        	searchFor.VerifySearchThumbnailImgPresent(postTitle, "HanSolo");
        	
    }
}
