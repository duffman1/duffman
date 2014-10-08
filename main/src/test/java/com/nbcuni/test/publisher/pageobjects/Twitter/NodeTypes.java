package com.nbcuni.test.publisher.pageobjects.Twitter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com Node Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 19, 2013
 *********************************************/

public class NodeTypes {

    ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public NodeTypes(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        contentParent = new ContentParent(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-twitter-post-types-post")
    private WebElement Post_Cbx;
    
    @FindBy(how = How.XPATH, using = "//input[@value='Save configuration']")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnablePostNode() throws Exception {
    	
    	if (Post_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Post' checkbox.");
    		Post_Cbx.click();
    		
    		Reporter.log("Click the 'Save configuration' button.");
    		SaveConfiguration_Btn.click();
    		
    		contentParent.VerifyMessageStatus("The configuration options have been saved.");
    	}
    }
    
}

