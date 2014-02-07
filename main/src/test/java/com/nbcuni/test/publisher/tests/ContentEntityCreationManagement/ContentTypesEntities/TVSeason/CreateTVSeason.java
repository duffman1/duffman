package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentTypesEntities.TVSeason;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.content.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class CreateTVSeason extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Login to Pub7<br>
     * Step 2 - Navigate to create Tv Season page<br> 
     * Step 3 - Verify that below fields are the mandatory ones and have star symbol marked on them:-  Basic Information tab:- 1) Title 2) Season 3) Synopsis  Publishhing Options tab:- 1) Moderation state<br>
     * Step 4 - Enter value in Title, Season and Synopsis<br>
     * Step 5 - Upload image in Cover media<br> 
     * Step 6 - Click on publishing options tab and select draft in Moderation state<br> 
     * Step 7 - Click on save<br>
     * Step 8 - Repeat above steps with Moderation state value as Review and Published<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void CreateTVSeason_Test() throws Exception{
    	
        //Step 1
        UserLogin userLogin = applib.openApplication();
        PageFactory.initElements(webDriver, userLogin);
        userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        List<String> allStates = Arrays.asList("Draft", "Review", "Published");
        for (String state : allStates) {
        	
        //Step 2
        taxonomy.NavigateSite("Content>>Add content>>TV Season");
        overlay.SwitchToFrame("Create TV Season");
        
        //Step 3
        ContentParent contentParent = new ContentParent(webDriver, applib);
        PageFactory.initElements(webDriver, contentParent);
        contentParent.VerifyRequiredFields(Arrays.asList("Title", "Season", "Synopsis"));
        PublishingOptions publishingOptions = new PublishingOptions(webDriver);
        publishingOptions.ClickPublishingOptionsLnk();
        contentParent.VerifyRequiredFields(Arrays.asList("Moderation State"));
        
        //Step 4
        BasicInformation basicInformation = new BasicInformation(webDriver);
        basicInformation.ClickBasicInformationTab();
        String tvSeasonTitle = random.GetCharacterString(15);
        basicInformation.EnterTitle(tvSeasonTitle);
        basicInformation.EnterSeasonNumber("1");
        basicInformation.EnterSynopsis();
        overlay.SwitchToFrame("Create TV Season");
        
        //Step 5
        basicInformation.ClickCoverSelectBtn();
        SelectFile selectFile = new SelectFile(webDriver, applib);
        PageFactory.initElements(webDriver, selectFile);
    	selectFile.SelectDefaultCoverImg();
    	overlay.SwitchToFrame("Create TV Season");
    	
    	//Step 6
    	publishingOptions.ClickPublishingOptionsLnk();
    	publishingOptions.SelectModerationState(state);
    	
    	//Step 7
    	contentParent.ClickSaveBtn();
    	contentParent.VerifyMessageStatus("TV Season " + tvSeasonTitle + " has been created.");
    	WorkBench workBench = new WorkBench(webDriver, applib);
    	PageFactory.initElements(webDriver, workBench);
    	workBench.VerifyWorkBenchBlockTextPresent(Arrays.asList(state));
    	
        }
        
    }
}
