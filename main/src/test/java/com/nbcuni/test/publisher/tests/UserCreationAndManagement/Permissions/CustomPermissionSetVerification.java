package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import com.nbcuni.test.publisher.common.GlobalBaseTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewPermissionSet;
import com.nbcuni.test.publisher.pageobjects.Structure.PermissionSets;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.testng.annotations.Test;

import java.util.Arrays;

public class CustomPermissionSetVerification extends GlobalBaseTest {
	
    /*************************************************************************************
     * TEST CASE - TC1371
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17744190392 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CustomPermissionSetVerification_TC1371() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = appLib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	//Step 2
    	navigation.Structure("Permission Sets");
    	
    	//Step 3
    	PermissionSets permissionSets = new PermissionSets(webDriver);
    	permissionSets.ClickAddLnk();
    	
    	//Step 4
    	AddNewPermissionSet addNewPermissionSet = new AddNewPermissionSet(webDriver, appLib);
    	String setName = random.GetCharacterString(15);
    	addNewPermissionSet.EnterPermissionSetName(setName);
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName + " has been created.");
    	
    	//Step 5
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	
    	//Step 6
    	addNewPermissionSet.VerifyAllPermissionCbxsNotChecked();
    	
    	//Step 7
    	addNewPermissionSet.ClickExportTab();
    	
    	//Step 8
    	String exportCodeDefaultValue =	"$permission_set = new stdClass();" +
    			"\n" + "$permission_set->disabled = FALSE; /* Edit this to true to make a default permission_set disabled initially */" +
    			"\n" + "$permission_set->api_version = 1;" +
    			"\n" + "$permission_set->machinename = '" + setName + "';" +
    			"\n" + "$permission_set->name = '" + setName + "';" +
    			"\n" + "$permission_set->permissions = array();" + "\n";
    	addNewPermissionSet.VerifyExportCodeValue(exportCodeDefaultValue);
    	
    	//Step 9
    	addNewPermissionSet.ClickEditTab();
    	addNewPermissionSet.EnablePermissions(Arrays.asList("stick to Acquia web node", 
    			"access resource show", "view own files"));
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName.toLowerCase() + " has been updated.");
    	
    	//Step 10
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	
    	//Step 11
    	addNewPermissionSet.ClickExportTab();
    	String exportCodeEditedValue = "$permission_set = new stdClass();" +
    			"\n" + "$permission_set->disabled = FALSE; /* Edit this to true to make a default permission_set disabled initially */" +
    			"\n" + "$permission_set->api_version = 1;" +
    			"\n" + "$permission_set->machinename = '" + setName + "';" +
    			"\n" + "$permission_set->name = '" + setName + "';" +
    			"\n" + "$permission_set->permissions = array(" +
    			"\n" + "  'stick to Acquia web node' => 'stick to Acquia web node'," +
    			"\n" + "  'view own files' => 'view own files'," +
    			"\n" + "  'access resource show' => 'access resource show'," +
    			"\n" + ");" + "\n";
    	addNewPermissionSet.VerifyExportCodeValue(exportCodeEditedValue);
    	
    	//Step 12 - 15 //TODO - automate as time allows
    	
    	//Cleanup
    	navigation.Structure("Permission Sets");
    	permissionSets.ClickPermissionSetExpandEditLnk(setName);
    	permissionSets.ClickPermissionSetDeleteLnk(setName);
    	Delete delete = new Delete(webDriver);
    	delete.ClickDeleteBtn();
    	contentParent.VerifyMessageStatus("The item has been deleted.");
    	permissionSets.VerifyPermissionSetNotPresent(setName);
    }
}
