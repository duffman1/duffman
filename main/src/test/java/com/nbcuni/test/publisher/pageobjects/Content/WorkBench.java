package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Mohd Faizan Khan
 * @version 1.0 Date: Jan 02, 2014
 *********************************************/
public class WorkBench {

	private static CustomWebDriver webDriver;
    private static WebDriverWait wait;
    
	//PAGE OBJECT CONSTRUCTORS
    public WorkBench(CustomWebDriver webDriver, AppLib applib) {
        WorkBench.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    private static String MouseOver_Js = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" + "arguments[0].dispatchEvent(evObj);";
	
    @FindBy(how = How.CLASS_NAME, using = "workbench-info-block")
    private static WebElement WorkBenchInfo_Ctr;
    
    private static WebElement WorkBench_Tab(String tabName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + tabName + "']"));
    }
    
    @FindBy(how = How.CSS, using = "div[id='block-workbench-block'] a.contextual-links-trigger")
    private static WebElement ContextualLinksGearDown_Lnk;
    
    @FindBy(how = How.XPATH, using ="//li[@class='block-configure first last']/a[contains(text(),'Configure block')]")
    private static WebElement ConfigureBlock_Lnk;
    
    private static WebElement CloneContent_Lnk(String contentType) {
    	return webDriver.findElement(By.xpath("//a[text() = 'Clone this " + contentType + "']"));
    }
    
    @FindBy(how = How.CSS, using = "iframe[id='pdk-player']")
    private static WebElement MPXPlayer_Frm;
    
    
    //PAGE OBJECT METHODS
    public void ClickWorkBenchTab(String tabName) throws Exception{
    
    	Reporter.log("Click the '" + tabName + "' link in the work bench.");
    	WorkBench_Tab(tabName).click();

    }
    
    public void VerifyWorkBenchTabPresent(String tabName) throws Exception {  	
    	 	
    	Reporter.log("Verify the '" + tabName + "' is present in the work bench.");
    	WorkBench_Tab(tabName).isDisplayed(); 	
    }

    public void VerifyWorkBenchBlockTextPresent(List<String> txtItems) throws Exception {

        for (String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present in the work bench info block.");
        	Assert.assertTrue(WorkBenchInfo_Ctr.getText().contains(text));
        }
    }
    
    public void ClickContextualConfigureLnk() throws Exception {
        
        Reporter.log("Mouse over the work bench info container.");
        webDriver.executeScript(MouseOver_Js, WorkBenchInfo_Ctr);
        
        Reporter.log("Verify the contextual link configure gear and down arrow link are visible.");
        wait.until(ExpectedConditions.visibilityOf(ContextualLinksGearDown_Lnk)).click();
    }
          
    public void VerifyConfigureBlockLnkPresent() throws Exception {
        
    	Reporter.log("Verify 'Configure Block' link is present.");
        ConfigureBlock_Lnk.getLocation();
    }
    
    public void ClickCloneContentLnk(String contentType) throws Exception {
        
    	Reporter.log("Click the 'Clone this " + contentType + "' link.");
    	CloneContent_Lnk(contentType).click();
    }
    
    public void VerifyMPXPlayerPresent() throws Exception {
        
    	Reporter.log("Verify the mpx video player frame is present.");
        MPXPlayer_Frm.isDisplayed();
    }

}
