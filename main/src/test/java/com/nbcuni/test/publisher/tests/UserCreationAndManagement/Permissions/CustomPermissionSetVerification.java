package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewPermissionSet;
import com.nbcuni.test.publisher.pageobjects.Structure.PermissionSets;

public class CustomPermissionSetVerification extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1371
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17744190392 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CustomPermissionSetVerification_TC1371() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	//Step 2
    	taxonomy.NavigateSite("Structure>>Permission Sets");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 3
    	PermissionSets permissionSets = new PermissionSets(webDriver);
    	permissionSets.ClickAddLnk();
    	overlay.SwitchToActiveFrame();
    	
    	//Step 4
    	AddNewPermissionSet addNewPermissionSet = new AddNewPermissionSet(webDriver, applib);
    	String setName = random.GetCharacterString(15);
    	addNewPermissionSet.EnterPermissionSetName(setName);
    	addNewPermissionSet.ClickSaveBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus(setName + " has been created.");
    	
    	//Step 5
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	overlay.SwitchToActiveFrame();
    	
    	//Step 6
    	addNewPermissionSet.VerifyAllPermissionCbxsNotChecked();
    	
    	//Step 7
    	addNewPermissionSet.ClickExportTab();
    	overlay.SwitchToActiveFrame();
    	
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
    	overlay.SwitchToActiveFrame();
    	addNewPermissionSet.EnablePermissions(Arrays.asList("stick to Acquia web node", 
    			"access administration menu", "flush caches"));
    	addNewPermissionSet.ClickSaveBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus(setName.toLowerCase() + " has been updated.");
    	
    	//Step 10
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	overlay.SwitchToActiveFrame();
    	
    	//Step 11
    	addNewPermissionSet.ClickExportTab();
    	overlay.SwitchToActiveFrame();
    	String exportCodeEditedValue = "$permission_set = new stdClass();" +
    			"\n" + "$permission_set->disabled = FALSE; /* Edit this to true to make a default permission_set disabled initially */" +
    			"\n" + "$permission_set->api_version = 1;" +
    			"\n" + "$permission_set->machinename = '" + setName + "';" +
    			"\n" + "$permission_set->name = '" + setName + "';" +
    			"\n" + "$permission_set->permissions = array(" +
    			"\n" + "  'stick to Acquia web node' => 'stick to Acquia web node'," +
    			"\n" + "  'access administration menu' => 'access administration menu'," +
    			"\n" + "  'flush caches' => 'flush caches'," +
    			"\n" + ");" + "\n";
    	addNewPermissionSet.VerifyExportCodeValue(exportCodeEditedValue);
    	
    	//Step 12 - 15 //TODO - automate as time allows
    	
    	//Cleanup
    	overlay.ClickCloseOverlayLnk();
    	taxonomy.NavigateSite("Structure>>Permission Sets");
    	overlay.SwitchToActiveFrame();
    	permissionSets.ClickPermissionSetExpandEditLnk(setName);
    	permissionSets.ClickPermissionSetDeleteLnk(setName);
    	overlay.SwitchToActiveFrame();
    	Delete delete = new Delete(webDriver);
    	delete.ClickDeleteBtn();
    	overlay.SwitchToActiveFrame();
    	contentParent.VerifyMessageStatus("The item has been deleted.");
    	permissionSets.VerifyPermissionSetNotPresent(setName);
    }
}
