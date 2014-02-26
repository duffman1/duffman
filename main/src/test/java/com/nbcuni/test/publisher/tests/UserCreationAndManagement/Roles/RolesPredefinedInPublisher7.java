package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Roles;

import org.testng.annotations.Test;

import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.common.RerunOnFailure;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.pageobjects.People.Permissions;
import com.nbcuni.test.publisher.pageobjects.People.Roles;

public class RolesPredefinedInPublisher7 extends ParentTest{
	
    /*************************************************************************************
     * TEST CASE 
     * Step 1 - Log into a new-installation Publisher test instance as Drupal User 1<br> 
     * Step 2 - In the main menu, point to People, and then click Permissions<br> 
     * Step 3 - Verify that role columns are present for only, and all of, the roles  a  anonymous user b  authenticated user c  administrator  d  editor e  senior editor  in that order from left to right<br>
     * Step 4 - Click Roles<br>
     * Step 5 - Verify that Name entries are present for only, and all of, the roles  a  anonymous user b  authenticated user c  administrator  d  editor e  senior editor  in that order from top to bottom<br>
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(retryAnalyzer = RerunOnFailure.class, groups = {"full"})
    public void RolesPredefinedInPublisher7_Test() throws Exception{
    	
    	//Step 1
    	UserLogin userLogin = applib.openApplication();
    	userLogin.Login(applib.getAdmin1Username(), applib.getAdmin1Password());
        
        //Step 2
        taxonomy.NavigateSite("People>>Permissions");
        overlay.SwitchToActiveFrame();
        
        //Step 3
        Permissions permissions = new Permissions(webDriver);
        permissions.VerifyRoleColumns();
        
        //Step 4
        Roles roles = new Roles(webDriver);
        roles.ClickRolesBtn();
        overlay.SwitchToActiveFrame();
       
        //Step 5
        roles.VerifyRoleRows();
       
    }
}
