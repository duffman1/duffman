package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Permissions;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import com.nbcuni.test.publisher.pageobjects.People.ApplyPermissionSet;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.Structure.AddNewPermissionSet;
import com.nbcuni.test.publisher.pageobjects.Structure.PermissionSets;

public class CreatePermissionSetsGUI extends ParentTest {
	
    /*************************************************************************************
     * TEST CASE - TC1381
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/17746733496 
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void CreatePermissionSetsGUI_TC1381() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
    	//Step 2
    	taxonomy.NavigateSite("Structure>>Permission Sets>>Add");
    	overlay.SwitchToActiveFrame();
    	
    	//Step 3
    	AddNewPermissionSet addNewPermissionSet = new AddNewPermissionSet(webDriver, applib);
    	String setName = random.GetCharacterString(15);
    	addNewPermissionSet.EnterPermissionSetName(setName);
    	addNewPermissionSet.EnablePermissions(Arrays.asList("flush caches", "edit any document files"));
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName + " has been created.");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 4
    	taxonomy.NavigateSite("People>>Permissions>>Apply Permission Set");
    	overlay.SwitchToActiveFrame();
    	ApplyPermissionSet applyPermissionSet = new ApplyPermissionSet(webDriver, applib);
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", setName);
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    	//Step 5
    	applyPermissionSet.ClickPermissionsBtn();
    	overlay.SwitchToActiveFrame();
    	Permissions permissions = new Permissions(webDriver, applib);
    	permissions.VerifyPermissionsSelected("senior editor", Arrays.asList("flush caches", "edit any document files"));
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 6
    	taxonomy.NavigateSite("Structure>>Permission Sets");
    	overlay.SwitchToActiveFrame();
    	PermissionSets permissionSets = new PermissionSets(webDriver, applib);
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	overlay.SwitchToActiveFrame();
    	
    	//Step 7
    	addNewPermissionSet.DisablePermissions(Arrays.asList("flush caches", "edit any document files"));
    	addNewPermissionSet.EnablePermissions(Arrays.asList("create post content", "edit own post content"));
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName + " has been updated.");
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 8
    	taxonomy.NavigateSite("People>>Permissions>>Apply Permission Set");
    	overlay.SwitchToActiveFrame();
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", setName);
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    	//Step 9
    	applyPermissionSet.ClickPermissionsBtn();
    	overlay.SwitchToActiveFrame();
    	permissions.VerifyPermissionsSelected("senior editor", Arrays.asList("create post content", "edit own post content"));
    	permissions.VerifyPermissionsNotSelected("senior editor", Arrays.asList("flush caches", "edit any document files"));
    	overlay.ClickCloseOverlayLnk();
    	
    	//Step 10
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
    	
    	//Cleanup
    	overlay.ClickCloseOverlayLnk();
    	taxonomy.NavigateSite("People>>Permissions>>Apply Permission Set");
    	overlay.SwitchToActiveFrame();
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", "Publisher Senior Editor Permissions Set");
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    }
}
