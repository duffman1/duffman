package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Roles;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;

public class RolesPredefinedInPublisher7 extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE - TC2184
     * Steps - https://rally1.rallydev.com/#/14663927728d/detail/testcase/18356878940
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void RolesPredefinedInPublisher7_TC2184() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        taxonomy.NavigateSite("People>>Permissions");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Permissions permissions = new Permissions(webDriver, applib);
        permissions.VerifyRoleColumns();
        
        //Step 4
        Roles roles = new Roles(webDriver);
        roles.ClickRolesBtn();
        overlay.SwitchToActiveFrame();
       
        //Step 5
        roles.VerifyRoleRows();
       
    }
}
