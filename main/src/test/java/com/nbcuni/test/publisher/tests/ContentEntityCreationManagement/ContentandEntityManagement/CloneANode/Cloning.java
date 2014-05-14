package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.CloneANode;

import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.NodeCloneModule;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class Cloning extends ParentTest {

	/*************************************************************************************
	 * TEST CASE - TC1054
     * Steps - https://rally1.rallydev.com/#/14663927728ud/detail/testcase/17441598048
     *************************************************************************************/
	@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
	public void Cloning_TC1054() throws Exception {

		//Step 1
		UserLogin userLogin = applib.openApplication();
		userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
       
		//Step 2
		Modules module = new Modules(webDriver, applib);
		module.VerifyModuleEnabled("Node clone");
    
		//Step 3
		taxonomy.NavigateSite("Configuration>>Content authoring>>Node clone module");
		overlay.SwitchToActiveFrame();
    
		//Step 4
		NodeCloneModule nodeCloneModule = new NodeCloneModule(webDriver);
		nodeCloneModule.EnableNodeCloneModuleSetting();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		overlay.ClickCloseOverlayLnk();
    
		//Step 5
		CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver, applib);
		String postTitle = createDefaultContent.Post("Published");
    
		//Step 6
		WorkBench workBench = new WorkBench(webDriver, applib);
		workBench.ClickCloneContentLnk("post");
    
		//Step 7
		overlay.SwitchToActiveFrame();
		contentParent.ClickSaveBtn();
		contentParent.VerifyMessageStatus("Post Clone of " + postTitle + " has been created.");
    
		//Step 8
		workBench.ClickWorkBenchTab("Revisions");
		overlay.SwitchToActiveFrame();
		Revisions revision = new Revisions(webDriver, applib);
		revision.SelectChangeState("Published");
		revision.EnterLogMessageForStateChange("Change state - Draft to Published");
		revision.ClickUpdateStateBtn();
		overlay.switchToDefaultContent();
		contentParent.VerifyMessageStatus("Clone of " + postTitle + " transitioned to the published state.");
    
		//Step 9
		workBench.ClickContextualConfigureLnk();
		workBench.VerifyConfigureBlockLnkPresent();
   
	}
}