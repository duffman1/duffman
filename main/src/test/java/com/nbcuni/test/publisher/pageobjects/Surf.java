package com.nbcuni.test.publisher.pageobjects;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Surf Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 7, 2014
 *********************************************/

public class Surf {

    private Driver webDriver;
    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
    //PAGE OBJECT CONSTRUCTOR
    public Surf(Driver webDriver) {
        this.webDriver = webDriver;
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By SignIn_Btn = By.id("action-signin");
    
    private By SurfContainer_Frm = By.xpath("//iframe[contains(@id, 'easyXDM')]");
    
    private By SurfLogin_Frm = By.id("display-frame");
    
    private By EmailOrUsername_Txb = By.id("input_username");
    
    private By Password_Txb = By.id("input_password");
    
    private By SignInF_Btn = By.id("button_submit");
    
    private By SignOut_Btn = By.id("action-signout");
    
    private By SurfInfo_Ctr = By.id("surf-info");
    
    
    //PAGE OBJECT METHODS
    public void ClickSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign-in' button.");
    	interact.Click(waitFor.ElementVisible(SignIn_Btn));
    	
    }
    
    public void SwitchToSurfFrm() throws Exception {
    	
    	Reporter.log("Switch to the Surf frame.");
    	webDriver.switchTo().frame(waitFor.ElementPresent(SurfContainer_Frm));
    	webDriver.switchTo().frame(waitFor.ElementPresent(SurfLogin_Frm));
    	
    }
    
    public void EnterEmailUsername(String userName) throws Exception {
    	
    	Reporter.log("Enter '" + userName + "' in the 'Email or Username' text box.");
    	interact.Type(waitFor.ElementVisible(EmailOrUsername_Txb), userName);
    	
    }
    
    public void EnterPassword(String password) throws Exception {
    	
    	Reporter.log("Enter '" + password + "' in the 'Password' text box.");
    	interact.Type(waitFor.ElementVisible(Password_Txb), password);
    	
    }
    
    public void ClickSurfFrmSignInBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign In' button.");
    	interact.Click(waitFor.ElementVisible(SignInF_Btn));
    	
    }
    
    public void VerifyUsernamePresent(String userName) throws Exception {
    	
    	Reporter.log("Verify surf username '" + userName + "' is present.");
    	waitFor.ElementContainsText(SurfInfo_Ctr, userName);
    	
    }
    
    public void ClickSignOutBtn() throws Exception {
    	
    	Reporter.log("Click the 'Sign Out' button.");
    	interact.ScrollToTop();
    	interact.Click(waitFor.ElementVisible(SignOut_Btn));
    	
    	Reporter.log("Verify the 'Sign In' button is present.");
    	waitFor.ElementVisible(SignIn_Btn);
    	
    }
    
}

