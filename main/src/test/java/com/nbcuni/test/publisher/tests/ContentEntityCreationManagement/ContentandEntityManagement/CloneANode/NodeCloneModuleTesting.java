package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.CloneANode;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Configuration.NodeCloneModule;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.pageobjects.Content.CreateDefaultContent;
import com.nbcuni.test.publisher.pageobjects.Content.Revisions;
import com.nbcuni.test.publisher.pageobjects.Content.WorkBench;

public class NodeCloneModuleTesting extends ParentTest {

/*************************************************************************************
* TEST CASE
* Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br>
* Step 2 - In the Modules page, ensure that the Node clone module is turned on.<br>
* Step 3 - Navigate to Configuration > Content authoring > Node clone module.<br>
* Step 4 - All Node Clone module options are set and in effect as expected.<br>
* Step 5 - Create and save and then examine its View tab for the presence of a Clone content link.<br>
* Step 6 - Click Clone content.<br>
* Step 7 - Click Save.<br>
* Step 8 - Publish from its Workflow tab the cloned content just created, and then navigate to the user homepage.<br>
* Step 9 - Mouse over the teaser for the content just published, and confirm that its context-sensitive link icon (gear and down arrow, at the upper right corner of the teaser) are displayed.<br>
* @throws Throwable No Return values are needed
*************************************************************************************/
@Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
public void NodeCloneModuleTesting_Test() throws Exception{

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
    ContentParent contentParent = new ContentParent(webDriver, applib);
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
    workBench.VerifyConfigureBlockLnkVisible();
   
	}
}