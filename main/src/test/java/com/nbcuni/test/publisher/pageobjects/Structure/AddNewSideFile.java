package com.nbcuni.test.publisher.pageobjects.Structure;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/*********************************************
* publisher.nbcuni.com Add a New Side File Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class AddNewSideFile {

	private Config config;
	private Integer timeout;
	private WaitFor waitFor;
	private Interact interact;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddNewSideFile(WebDriver webWebWebDriver) {
    	config = new Config();
    	timeout = config.getConfigValueInt("WaitForWaitTime");
    	waitFor = new WaitFor(webWebWebDriver, timeout);
    	interact = new Interact(webWebWebDriver, timeout);
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By Name_Txb = By.id("edit-name");
    
    private By MachineName_Txb = By.id("edit-machine-name");
    
    private By Path_Txb = By.id("edit-path");
    
    private By ResponseType_Ddl = By.id("edit-file-type");
    
    private By Content_Txa = By.id("edit-content");
    
    private By Save_Btn = By.cssSelector("input[value='Save']");
    
    private By Revert_Btn = By.id("edit-delete");
    
    private By Export_Lnk = By.xpath("//a[text()='Export']");
    
    private By Export_Txa = By.id("edit-code");
    
    private By Update_Btn = By.id("edit-next");
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String name) throws Exception {
    
    	Reporter.log("Enter '" + name + "' in the 'Name' text box.");
    	interact.Type(waitFor.ElementVisible(Name_Txb), name);
   
    }
    
    public void EnterMachineName(String name) throws Exception {
        
    	Reporter.log("Enter '" + name + "' in the 'Machine Name' text box.");
    	interact.Type(waitFor.ElementVisible(MachineName_Txb), name);
    	
    }
    
    public void EnterPath(String path) throws Exception {
        
    	Reporter.log("Enter '" + path + "' in the 'Path' text box.");
    	interact.Type(waitFor.ElementVisible(Path_Txb), path);
    	
    }
    
    public void SelectResponseType(String option) throws Exception {
        
    	Reporter.log("Select the '" + option + "' from the 'Response type' drop down list.");
    	interact.Select(waitFor.ElementPresent(ResponseType_Ddl), option);
    	
    }
    
    public void EnterContent(String content) throws Exception {
        
    	Reporter.log("Enter '" + content + "' in the 'Content' text box.");
    	interact.Type(waitFor.ElementVisible(Content_Txa), content);
    	
    }
    
    public void ClickSaveBtn() throws Exception {
        
    	Reporter.log("Click the 'Save' button.");
    	interact.Click(waitFor.ElementVisible(Save_Btn));
    	
    }
    
    public void ClickRevertBtn() throws Exception {
        
    	Reporter.log("Click the 'Revert' button.");
    	interact.Click(waitFor.ElementVisible(Revert_Btn));
    	
    }
    
    public void ClickExportTab() throws Exception {
        
    	Reporter.log("Click the 'Export' tab.");
    	interact.Click(waitFor.ElementVisible(Export_Lnk));
    	
    }
    
    public String CopyExportTxt() throws Exception {
        
    	Reporter.log("Copy the text from the 'Export' text area.");
    	return waitFor.ElementVisible(Export_Txa).getAttribute("value");
    	
    }
    
    public void ClickUpdateBtn() throws Exception {
        
    	Reporter.log("Click the 'Update' button.");
    	interact.Click(waitFor.ElementVisible(Update_Btn));
    	
    }
    
}
