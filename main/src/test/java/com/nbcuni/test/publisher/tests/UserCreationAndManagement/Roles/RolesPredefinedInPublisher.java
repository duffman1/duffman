package com.nbcuni.test.publisher.tests.UserCreationAndManagement.Roles;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.UserLogin;
import com.nbcuni.test.publisher.common.ParentTest;
import com.nbcuni.test.publisher.content.People;
import com.nbcuni.test.publisher.pageobjects.taxonomy.Taxonomy;

public class RolesPredefinedInPublisher extends ParentTest {
	 /*************************************************************************************
     * TEST CASE
     * Step 1 - Login to publisher using drupal 1 credentials <br>
     * Step 2 - Click on People >> Permissions <br>
     * Step 3 - Verify that role columns are present for only, and all of, the roles
	 *			a.  anonymous user
	 *			b.  authenticated user
	 *			c.  administrator 
	 *			d.  editor
	 *			e.  senior editor
	 *			in that order from left to right.<br>
     * Step 4 - Click on Roles<br>
     * Step 5 - Verifies that Name entries are present for only, and all of, the roles
	 *			a  anonymous user
	 *			b  authenticated user
	 *			c  administrator 
	 *			d  editor
	 *			e  senior editor				
	 *			in that order from top to bottom.<br> 
     * @throws Throwable No Return values are needed
     *************************************************************************************/
    @Test(groups = {"full", "smoke" })
    public void RolesPredefinedInPublisher7() throws Exception {
         
        	//Step 1
        	UserLogin userLogin = applib.openApplication();
            userLogin.Login("admin@publisher.nbcuni.com", "pa55word");            
            //Step 2
            Taxonomy taxonomy = new Taxonomy(webDriver);
           // taxonomy.clickDrupalBar("People-Permissions");
            //Step 3            
            Overlay overlay = new Overlay(webDriver);
            //overlay.moveToCurrentFrame();
            People people = new People(webDriver);
            boolean permission = people.VerifyRolesCoumnTypes();
            if(permission){Reporter.log("PASSED : "+"anonymous user, authenticated user,administrator" +
    		", editor and senior editor role's Columns are present on Permissions. ");
		    }else{Reporter.log("FAILED : "+"anonymous user, authenticated user,administrator" +
			", editor and senior editor role's Columns are NOT present on Permissions. ");
		    }            
            //Step 4
           // people.ClickRolelnk();
          //  taxonomy.clickDrupalBar("People-Permissions-Roles");
           // overlay.moveToCurrentFrame();
            //Step 5
            boolean roles = people.VerifyRoles();
            if(roles){Reporter.log("PASSED : "+"anonymous user, authenticated user,administrator" +
            		", editor and senior editor roles are present on Roles page under Permissions. ");
            }else{Reporter.log("FAILED : "+"anonymous user, authenticated user,administrator" +
    		", editor and senior editor roles are NOT present on Roles page under Permissions. ");
            } 
            overlay.switchToDefaultContent(); 
       
    }
}
