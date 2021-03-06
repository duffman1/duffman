package com.nbcuni.test.publisher.tests.ContentEntityCreationManagement.ContentandEntityManagement.InstallZeeboxModule;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.*;

import org.testng.annotations.Test;

import java.util.Arrays;

public class InstallZeeboxModule extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into the QA instance as an admin(user1).   NOTE: The code for this test is initally deployed to http://qa1dev.publisher.nbcuni.com/ ,Login succeeds 
     * Step 2 - Go to Modules. Enter 'Zeebox Example' in the Filter list  ,Zeebox example is listed in the result set 
     * Step 3 - Check zeebox Example module and click Save Configuration.  NOTE: If it is already turned on, skip this step ,The configuration is saved and returns confirmation message(s) in the below format:  - Zeebox <name of the example> example block has been created. - Zeebox <name of the example> example block has been created. - Zeebox <name of the example> example block has been created. - Zeebox <name of the example>  example block has been created. - Zeebox <name of the example>  example block has been created. - Visit the zeebox-example page to view the zeebox blocks available. When you disable the zeebox Example module make sure to uninstall it and it will delete all the example blocks. - The configuration options have been saved. 
	 * Step 4 - Add /zeebox-example to the end of the test site URL and hit enter  Ex: In this case, go to http://qa1dev.publisher.nbcuni.com/zeebox-example ,Page loads listing zeebox Example blocks 
     * Step 5 - Count the number of example blocks listed.    ,There are 5 example blocks listed with the block names like 'Zeebox <any name> example block' 
     * Step 6 - Go back to Modules. Search for Zeebox Example, uncheck and click Save Configuration.  NOTE: This step is to let other QA personnel test Zeebox installation from scratch. ,The configuration is saved successfully. 
     * Step 7 - Go to Modules > Uninstall, check 'Zeebox Example' and click Uninstall. Confirm the action when prompted.  NOTE: This step is to let other QA personnel test Zeebox installation from scratch. ,Zeebox Example module is uninstalled and the below listed confirmation message is seen  - Zeebox <name of the example> example block has been deleted. - Zeebox <name of the example> example block has been deleted. - Zeebox <name of the example> example block has been deleted. - Zeebox <name of the example>  example block has been deleted. - Zeebox <name of the example> example block has been deleted. - The selected modules have been uninstalled. 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full" })
    public void InstallZeeboxModule_Test() throws Exception{
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
        	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
            
            //Step 2 and 3
            Modules modules = new Modules(webDriver);
            modules.VerifyModuleEnabled("zeebox Example");
            
            //Step 4
            Thread.sleep(2000);
            applib.openSitePage("/zeebox-example");
            
            //Step 5
            ContentParent contentParent = new ContentParent(webDriver);
            contentParent.VerifyPageContentPresent(Arrays.asList("Zeebox Follow Button example block",
            		"Zeebox Play-along example block",
            			"Zeebox Hot TV Rooms example block",
            				"Zeebox TV Room Teaser example block",
            					"Zeebox TV Room example block",
            						""));
            
            //Step 6
            navigation.Modules();
            modules.DisableModule("zeebox Example");
            
            //Step 7
            navigation.Modules("Uninstall");
            modules.UninstallModule("zeebox Example");
            contentParent.VerifyPageContentPresent(Arrays.asList("Zeebox Follow Button example block has been deleted.",
            		"Zeebox Hot TV Rooms example block has been deleted.",
            			"Zeebox Play-along example block has been deleted.",
            				"Zeebox TV Room Teaser example block has been deleted.",
            					"Zeebox TV Room example block has been deleted.",
            						"The selected modules have been uninstalled."));
            applib.openSitePage("/zeebox-example");
            contentParent.VerifyPageContentPresent(Arrays.asList("Page not found"));
            
    }
}
