package com.nbcuni.test.publisher.tests.feature.F17;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.Listeners.RerunOnFailure;
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
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full", "F17"})
    public void CreatePermissionSetsGUI_TC1381() throws Exception {
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(config.getConfigValueString("Admin1Username"), config.getConfigValueString("Admin1Password"));
        
    	//Step 2
    	navigation.Structure("Permission Sets");
    	PermissionSets permissionSets = new PermissionSets(webDriver);
    	permissionSets.ClickAddLnk();
    	
    	//Step 3
    	AddNewPermissionSet addNewPermissionSet = new AddNewPermissionSet(webDriver, applib);
    	String setName = random.GetCharacterString(15);
    	addNewPermissionSet.EnterPermissionSetName(setName);
    	addNewPermissionSet.EnablePermissions(Arrays.asList("view own files", "edit any document files"));
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName + " has been created.");
    	
    	//Step 4
    	navigation.People("Permissions", "Apply Permission Set");
    	ApplyPermissionSet applyPermissionSet = new ApplyPermissionSet(webDriver);
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", setName);
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    	//Step 5
    	navigation.ClickSecondaryTabNavLnk("Permissions");
    	Permissions permissions = new Permissions(webDriver, applib);
    	permissions.VerifyPermissionsSelected("senior editor", Arrays.asList("view own files", "edit any document files"));
    	
    	//Step 6
    	navigation.Structure("Permission Sets");
    	permissionSets.ClickPermissionSetEditLnk(setName);
    	
    	//Step 7
    	addNewPermissionSet.DisablePermissions(Arrays.asList("view own files", "edit any document files"));
    	addNewPermissionSet.EnablePermissions(Arrays.asList("create post content", "edit own post content"));
    	addNewPermissionSet.ClickSaveBtn();
    	contentParent.VerifyMessageStatus(setName + " has been updated.");
    	
    	//Step 8
    	navigation.People("Permissions", "Apply Permission Set");
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", setName);
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    	//Step 9
    	navigation.ClickSecondaryTabNavLnk("Permissions");
    	permissions.VerifyPermissionsSelected("senior editor", Arrays.asList("create post content", "edit own post content"));
    	permissions.VerifyPermissionsNotSelected("senior editor", Arrays.asList("view own files", "edit any document files"));
    	
    	//Step 10
    	navigation.Structure("Permission Sets");
    	permissionSets.ClickPermissionSetExpandEditLnk(setName);
    	permissionSets.ClickPermissionSetDeleteLnk(setName);
    	Delete delete = new Delete(webDriver);
    	delete.ClickDeleteBtn();
    	contentParent.VerifyMessageStatus("The item has been deleted.");
    	permissionSets.VerifyPermissionSetNotPresent(setName);
    	
    	//Cleanup
    	navigation.People("Permissions", "Apply Permission Set");
    	applyPermissionSet.SelectRolePermissionSet("Role: Senior Editor", "Publisher Senior Editor Permissions Set");
    	applyPermissionSet.ClickApplyPermissionSetsBtn();
    	contentParent.VerifyMessageWarning("Applying a permission set may cause you to no longer have access to change user permissions.");
    	contentParent.VerifyPageContentPresent(Arrays.asList("senior editor"));
    	applyPermissionSet.ClickDoItBtn();
    	contentParent.VerifyMessageStatus("The selected permissions set(s) have been applied to the following roles:");
    	contentParent.VerifyMessageStatus("senior editor");
    	
    }
}
