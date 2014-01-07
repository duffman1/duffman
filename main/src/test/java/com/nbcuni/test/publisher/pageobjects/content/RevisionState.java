package com.nbcuni.test.publisher.pageobjects.content;


import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 18, 2013
 *********************************************/

public class RevisionState {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String RevisionState_Ctr = "//div[@class='workbench-info-block'][contains(text(), 'Revision state')]";
    private static String RevisionsState_Tbl = ".//*[@id='revision-form-table']/tbody/tr";
    private static String Body_txt = "//div[@class='field field-name-body field-type-text-with-summary field-label-hidden']";
    private static String Node_num = ".//*[@id='revision-form-table']/tbody/..//td[@class='views-field views-field-vid']";
    public RevisionState(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void VerifyRevisionState(String state) throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(RevisionState_Ctr))));
    	Assert.assertTrue(webDriver.findElement(By.xpath(RevisionState_Ctr)).getText().contains(state));
    }
 
    public void VerifyRevisionCount(Integer RevisionCount) throws Exception {
    	
    	Integer revisionsNode = webDriver.findElements(By.xpath(Node_num)).size();  
    	Integer revisionsRows = (webDriver.findElements(By.xpath(RevisionsState_Tbl)).size())-1;
    	Assert.assertTrue(revisionsNode.intValue() == (RevisionCount));
    	Assert.assertTrue(revisionsRows.intValue() == (RevisionCount));
    	
    }
    public void VerifyCurrentRevision(String txt){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Body_txt))));
    	Assert.assertTrue(webDriver.findElement(By.xpath(Body_txt)).getText().contains(txt));
    	    	
    }
    
    public void VerifyRevision(String STR, String Mstatus){
    	String[] Node1 = STR.split(":");
    	String[] NodeOne = Node1[0].trim().split(" ");
    	int NodeToVerify = Integer.valueOf(NodeOne[1]);
    	List<WebElement> allRows = webDriver.findElements(By.xpath(RevisionsState_Tbl));
    	for(WebElement rowN:allRows){
    		int NodeNum = Integer.valueOf(rowN.findElement(By.className("views-field views-field-vid")).getText());
    		if(NodeNum==NodeToVerify){
    			String MState = rowN.findElement(By.className("views-field views-field-state")).getText();
    			Assert.assertTrue(MState.equalsIgnoreCase(Mstatus));
    		}
    	}   	
    	    	
    }
}

