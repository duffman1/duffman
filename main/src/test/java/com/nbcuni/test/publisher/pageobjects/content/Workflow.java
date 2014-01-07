package com.nbcuni.test.publisher.pageobjects.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
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
    
   private static String Schedule_Tbl = ".//table[@class='sticky-enabled tableheader-processed sticky-table']/..//td[@class='empty message']";
   private static String AddSchedule_Lnk = ".//*[@id='content']/ul/li/a[contains(text(),'Add scheduled revision')]";
    public Workflow(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    public void ClickWorkflowTab(String TabName) throws Exception{
    	WebElement workflowTab = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(".//div[@id='content']/..//a[text()='"+TabName+"']")));
    	workflowTab.click();
    }
    public void ClickTabonRevisionPage(String TabName) throws Exception{
    	WebElement tabRP = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(".//a[text()='"+TabName+"']")));
    	tabRP.click();
    }
    public void VerifyWorkflowTab(String TabName) throws Exception {  	
    	 	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(".//div[@id='content']/..//a[text()='"+TabName+"']"))));
    	   	
    }
    public void VerifyScheduleTableisEmpty() throws Exception {  	
	 	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(Schedule_Tbl)))).getText().contains("No scheduled revisions available");
    	   	
    }
   public void VerifyAddScheduleVersionLink() throws Exception {
	   new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(AddSchedule_Lnk))));
   }
   public void ClickAddScheduleVersionLink() throws Exception {
	   new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(AddSchedule_Lnk)))).click();
   }
   public void VerifySchedulePageFieds() throws Exception {
	   new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(AddSchedule_Lnk)))).click();
   }
}
