package com.nbcuni.test.publisher.pageobjects.Structure;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Add a New Side File Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class AddNewSideFile {

	private Driver webDriver;
	private AppLib applib;
	private Overlay overlay;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddNewSideFile(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	this.applib = applib;
    	PageFactory.initElements(webDriver, this);
    	overlay = new Overlay(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-name")
    private WebElement Name_Txb;
    
    @FindBy(how = How.ID, using = "edit-machine-name")
    private WebElement MachineName_Txb;
    
    @FindBy(how = How.ID, using = "edit-path")
    private WebElement Path_Txb;
    
    @FindBy(how = How.ID, using = "edit-file-type")
    private WebElement ResponseType_Ddl;
    
    @FindBy(how = How.ID, using = "edit-content")
    private WebElement Content_Txa;
    
    @FindBy(how = How.CSS, using = "input[value='Save']")
    private WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-delete")
    private WebElement Revert_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Export']")
    private WebElement Export_Lnk;
    
    @FindBy(how = How.ID, using = "edit-code")
    private WebElement Export_Txa;
    
    @FindBy(how = How.ID, using = "edit-next")
    private WebElement Update_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterName(String name) throws Exception {
    
    	Reporter.log("Enter '" + name + "' in the 'Name' text box.");
    	Name_Txb.clear();
    	Name_Txb.sendKeys(name);
    }
    
    public void EnterMachineName(String name) throws Exception {
        
    	Reporter.log("Enter '" + name + "' in the 'Machine Name' text box.");
    	MachineName_Txb.clear();
    	MachineName_Txb.sendKeys(name);
    }
    
    public void EnterPath(String path) throws Exception {
        
    	Reporter.log("Enter '" + path + "' in the 'Path' text box.");
    	Path_Txb.clear();
    	Path_Txb.sendKeys(path);
    }
    
    public void SelectResponseType(String option) throws Exception {
        
    	Reporter.log("Select the '" + option + "' from the 'Response type' drop down list.");
    	new Select(ResponseType_Ddl).selectByVisibleText(option);
    }
    
    public void EnterContent(String content) throws Exception {
        
    	Reporter.log("Enter '" + content + "' in the 'Content' text box.");
    	Content_Txa.sendKeys(content);
    }
    
    public void ClickSaveBtn() throws Exception {
        
    	Reporter.log("Click the 'Save' button.");
    	webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	try {
    		Save_Btn.click();
    	}
    	catch (NoSuchElementException e) {
    		webDriver.navigate().refresh();
    		overlay.SwitchToActiveFrame();
    		Save_Btn.click();
    	}
    	webDriver.manage().timeouts().implicitlyWait(applib.getImplicitWaitTime(), TimeUnit.SECONDS);
    }
    
    public void ClickRevertBtn() throws Exception {
        
    	Reporter.log("Click the 'Revert' button.");
    	Revert_Btn.click();
    }
    
    public void ClickExportTab() throws Exception {
        
    	Reporter.log("Click the 'Export' tab.");
    	Export_Lnk.click();
    }
    
    public String CopyExportTxt() throws Exception {
        
    	Reporter.log("Copy the text from the 'Export' text area.");
    	return Export_Txa.getAttribute("value");
    }
    
    public void ClickUpdateBtn() throws Exception {
        
    	Reporter.log("Click the 'Update' button.");
    	Update_Btn.click();
    }
    
    
    
}
