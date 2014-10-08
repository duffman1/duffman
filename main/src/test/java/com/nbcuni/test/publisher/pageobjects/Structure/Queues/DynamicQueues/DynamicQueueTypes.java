package com.nbcuni.test.publisher.pageobjects.Structure.Queues.DynamicQueues;

import java.util.ArrayList;
import java.util.List;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Dynamic Queue Types Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: July 31, 2014
 *********************************************/

public class DynamicQueueTypes {

    private Driver webDriver;
    private Overlay overlay;
    private Delete delete;
    
    //PAGE OBJECT CONSTRUCTOR
    public DynamicQueueTypes(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        delete = new Delete(webDriver);
        overlay = new Overlay(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Add dynamic queue type']")
    private WebElement AddDynamicQueueType_Lnk;
    
    private List<WebElement> AllDynamicQueue_Lnks() {
    	return webDriver.findElements(By.xpath("//table[2]//td[1]//a"));
    }
    
    private WebElement Edit_Lnk(String queueType) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='edit']"));
    }
    
    private WebElement Delete_Lnk(String queueType) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='delete']"));
    }
    
    private WebElement ManageDisplay_Lnk(String queueType) {
    	return webDriver.findElement(By.xpath("//td[contains(text(), '" + queueType + "')]/..//a[text()='manage display']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickAddDynamicQueueTypeLnk() throws Exception {
    	
    	Reporter.log("Click the 'Add dynamic queue type' link.");
    	AddDynamicQueueType_Lnk.click();
    }
    
    public void ClickEditLnk(String dynamicQueueType) throws Exception {
    	
    	Reporter.log("Click the 'edit' link for Dynamic Queue Type '" + dynamicQueueType + ".");
    	Edit_Lnk(dynamicQueueType).click();
    }
    
    public void ClickManageDisplayLnk(String dynamicQueueType) throws Exception {
    	
    	Reporter.log("Click the 'manage display' link for Dynamic Queue Type '" + dynamicQueueType + ".");
    	ManageDisplay_Lnk(dynamicQueueType).click();
    }
    
    public void DeleteAllDynamicQueues() throws Exception {
    	
    	List<String> allDynamicQueueTitles = new ArrayList<String>();
    	for (WebElement lnk : AllDynamicQueue_Lnks()) {
    		allDynamicQueueTitles.add(lnk.getText());
    	}
    	
    	for (String queueTitle : allDynamicQueueTitles) {
    		Delete_Lnk(queueTitle).click();
    		overlay.SwitchToActiveFrame();
    		delete.ClickConfirmBtn();
    		overlay.SwitchToActiveFrame();
    	}
    }
    
}

