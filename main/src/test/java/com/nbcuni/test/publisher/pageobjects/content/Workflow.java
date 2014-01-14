package com.nbcuni.test.publisher.pageobjects.content;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*********************************************
 * publisher.nbcuni.com Taxonomy Library. Copyright
 * 
 * @author Mohd Faizan Khan
 * @version 1.0 Date: Jan 02, 2014
 *********************************************/
public class Workflow {

	private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    

    public Workflow(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    public void ClickWorkflowTab(String TabName) throws Exception{
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(".//div[@id='content']/..//a[text()='"+TabName+"']"))).click();

    }
    public void ClickTabonRevisionPage(String TabName) throws Exception{
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//a[text()='"+TabName+"']"))).click();
    }
    public void VerifyWorkflowTab(String TabName) throws Exception {  	
    	 	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
                visibilityOf(webDriver.findElement(By.xpath(".//div[@id='content']/..//a[text()='" + TabName + "']"))));
    	   	
    }


}
