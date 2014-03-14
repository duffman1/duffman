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
import com.nbcuni.test.webdriver.CustomWebDriver;

/*********************************************
* publisher.nbcuni.com Add a New Side File Library. Copyright
*
* @author Brandon Clark
* @version 1.0 Date: March 12, 2014
*********************************************/

public class AddNewSideFile {

	private static CustomWebDriver webDriver;
	private static AppLib applib;
	private static Overlay overlay;
	
    //PAGE OBJECT CONSTRUCTOR
    public AddNewSideFile(CustomWebDriver webDriver, AppLib applib) {
    	AddNewSideFile.webDriver = webDriver;
    	AddNewSideFile.applib = applib;
    	PageFactory.initElements(webDriver, this);
    	overlay = new Overlay(webDriver, applib);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-name")
    private static WebElement Name_Txb;
    
    @FindBy(how = How.ID, using = "edit-machine-name")
    private static WebElement MachineName_Txb;
    
    @FindBy(how = How.ID, using = "edit-path")
    private static WebElement Path_Txb;
    
    @FindBy(how = How.ID, using = "edit-file-type")
    private static WebElement ResponseType_Ddl;
    
    @FindBy(how = How.ID, using = "edit-content")
    private static WebElement Content_Txa;
    
    @FindBy(how = How.CSS, using = "input[value='Save']")
    private static WebElement Save_Btn;
    
    @FindBy(how = How.ID, using = "edit-delete")
    private static WebElement Revert_Btn;
    
    @FindBy(how = How.XPATH, using = "//a[text()='Export']")
    private static WebElement Export_Lnk;
    
    @FindBy(how = How.ID, using = "edit-code")
    private static WebElement Export_Txa;
    
    @FindBy(how = How.ID, using = "edit-next")
    private static WebElement Update_Btn;
    
    
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
