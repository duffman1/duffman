package com.nbcuni.test.publisher.contentbuildscripts;

import java.util.Arrays;
import java.util.List;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.pageobjects.Modules;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DefaultModulesEnabled extends ParentTest{
	
    @Test()
    public void DefaultModulesEnabled_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 2
    	taxonomy.NavigateSite("Modules");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 3
    	List<String> allCoreModules = Arrays.asList("Block", "Contextual links", "Help", "Image", "List", "Menu", "Number", "Options", "Path", "Taxonomy", "Syslog", 
    			"Shortcut", "Overlay", "Field UI", "File");
    	Modules modules = new Modules(webDriver, applib);
    	for (String module : allCoreModules) {
    		modules.EnterFilterName(module);
    		Reporter.log("Verify core module '" + module + "' is enabled.");
    		if (modules.IsModuleEnabled(module) == false) {
    			Assert.fail("Core module '" + module + "' is not enabled.");
    		}
    	}
    	
    	//Step 4
    	List<String> allContribModules = Arrays.asList("Acquia Cloud Sticky Sessions", "Administration menu Adminimal Theme", "Node clone", "Collections", "Collection Field", 
    			"Content locking (edit lock)", "Content locking (edit lock) timeouts", "Context", "Date Popup", "Diff", "Display suite", "Entity cache", 
    				"Environment indicator", "Exportable Scheduler", "Features", "File entity Revisions", "Global Redirect", "Interactive Information Bar", 
    					"Media", "Media Multiselect", "Metatag", "Metatag: Open Graph", "Metatag: Twitter Cards", "Metatag: Twitter Cards (See It)", 
    						"Module filter", "Multiple forms", "Pathauto", "Plupload integration module", "Queues Workbench", "Revision Scheduler SPS Integration", 
    							"Soft length limit", "Strongarm", "Site Preview System", "Token", "Views", "Views Bulk Operations", "Wysiwyg", "Simple EXIF");
    	for (String module : allContribModules) {
    		modules.EnterFilterName(module);
    		Reporter.log("Verify contrib module '" + module + "' is enabled.");
    		if (modules.IsModuleEnabled(module) == false) {
    			Assert.fail("Contrib module '" + module + "' is not enabled.");
    		}
    	}
    	
    	//Step 5
    	List<String> allCustomModules = Arrays.asList("Pub Analytics", "Publisher Certify Utility", "Pub Editorial", "Pub Hide Submit", "Pub Media Galleries", "Pub Movie", 
    			"Pub MPX", "Pub Promo Queue", "Publisher QA Releases Utility", "Publisher Releases Utility", "Pub Relationships", "Pub Movie Relationships", "Pub TV Relationships Enhanced", 
    				"Pub Report Client", "Publisher Sec", "Pub Social", "Pub SPS (Site Preview System)", "Publisher Sync Database Utility", "Publisher Sync Files Utility", 
    					"Pub Television", "Pub Users", "Pub Workbench");
    	for (String module : allCustomModules) {
    		modules.EnterFilterName(module);
    		Reporter.log("Verify custom module '" + module + "' is enabled.");
    		if (modules.IsModuleEnabled(module) == false) {
    			Assert.fail("Custom module '" + module + "' is not enabled.");
    		}
    	}
    	
    }
}
