package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.CloneANode;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.NodeCloneModule;
import com.nbcuni.test.publisher.pageobjects.Content.CoverMedia;
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
		userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
       
		//Step 2
		Modules module = new Modules(webDriver);
		module.VerifyModuleEnabled("Node clone");
    
		//Step 3
		navigation.Configuration("Node clone module");
		
		//Step 4
		NodeCloneModule nodeCloneModule = new NodeCloneModule(webDriver);
		nodeCloneModule.EnableNodeCloneModuleSetting();
		contentParent.VerifyMessageStatus("The configuration options have been saved.");
		
		//Step 5
		CreateDefaultContent createDefaultContent = new CreateDefaultContent(webDriver);
		String postTitle = createDefaultContent.Post("Published");
    
		//Step 6
		WorkBench workBench = new WorkBench(webDriver);
		workBench.ClickCloneContentLnk("post");
		CoverMedia coverMedia = new CoverMedia(webDriver);
		coverMedia.VerifyFileImagePresent("HanSolo");
    
		//Step 7
		contentParent.ClickSaveBtn();
		contentParent.VerifyMessageStatus("Post Clone of " + postTitle + " has been created.");
    
		//Step 8
		workBench.ClickWorkBenchTab("Revisions");
		Revisions revision = new Revisions(webDriver);
		revision.SelectChangeState("Published");
		revision.EnterLogMessageForStateChange("Change state - Draft to Published");
		revision.ClickUpdateStateBtn();
		contentParent.VerifyMessageStatus("Clone of " + postTitle + " transitioned to the published state.");
    
	}
}