package com.nbcuni.test.publisher.pageobjects.Facebook;


import com.nbcuni.test.publisher.pageobjects.Content.ContentParent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Node Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 26, 2013
 *********************************************/

public class NodeTypes {

    ContentParent contentParent;
    
    //PAGE OBJECT CONSTRUCTOR
    public NodeTypes(WebDriver webWebWebDriver) {
        PageFactory.initElements(webWebWebDriver, this);
        contentParent = new ContentParent(webWebWebDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-facebook-post-types-post")
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

