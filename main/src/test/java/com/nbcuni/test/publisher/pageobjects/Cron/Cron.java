package com.nbcuni.test.publisher.pageobjects.Cron;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Cron Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/

public class Cron {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String RunCronToCompleteImport_Lnk = "//li[contains(text(), 'to complete the import')]/a[text()='Run cron']";
    private static String RunCron_Btn = "//input[@id='edit-run']";
    private static String RunCronFromHome_Lnk = ".//*[@id='admin-menu-icon']/..//a[contains(text(),'Run cron')]";
    
    
    public Cron(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    public void ClickRunCronToCompleteImportLnk() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(RunCronToCompleteImport_Lnk)))).click();
    }
    
    public void ClickRunCronBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(RunCron_Btn)))).click();
    }
    public void ClickRunCronHomeLink() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
    			visibilityOf(webDriver.findElement(By.xpath(RunCronFromHome_Lnk)))).click();
    }
   
    
    
}

