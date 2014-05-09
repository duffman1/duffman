package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.Images.RepresentativeImage;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;
import org.testng.annotations.Test;

public class RepresentativeImage extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC1065
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17441709403
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void RepresentativeImage_TC1065() throws Exception{
         
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