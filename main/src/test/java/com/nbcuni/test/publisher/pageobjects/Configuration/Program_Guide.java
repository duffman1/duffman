package com.nbcuni.test.publisher.pageobjects.Configuration;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;


/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Khan, Faizan
 * @version 1.0 Date: Jan 28, 2014
 *********************************************/

public class Program_Guide {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    private final String DataURL= "http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json";
    
    
  //PAGE OBJECT CONSTRUCTOR    
    public Program_Guide(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    
    
  //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.XPATH, using =".//*[@id='edit-program-guide-data-url']")
    private static WebElement DataURL_Txt;  
    
    @FindBy(how = How.XPATH, using =".//input[@id='edit-submit'][@value='Save configuration']")
    private static WebElement SaveConfiguration_btn; 
    
    @FindBy(how = How.XPATH, using =".//div[@id='block-program-guide-example-program-guide']/h2[contains(text(),'Program Guide')]")
     private static WebElement ProgramGuideTextOnHomePage_Lbl;
    
    @FindBy(how = How.XPATH, using =".//table[@class='sticky-enabled tableheader-processed sticky-table']/..//td[contains(text(),'There is no data')]")
    private static WebElement NoDataTextInTheFirstRow_txt;
    
    @FindBy(how = How.XPATH, using ="//table[@class='system-status-report']/..//td[contains(text(),'Program Guide Data URL')]/..//td[@class='status-value']")
     private static WebElement ProgramGuideRunCronStatus_status;
    
    @FindBy(how = How.XPATH, using =".//table[@class='sticky-enabled tableheader-processed sticky-table']/..//th")
     private static WebElement ProgramGuidetbl_cols;
    //PAGE OBJECT METHODS
    public void PopulateDataURL() throws Exception { 
    	Reporter.log("Populating Data URL textbox..");
    	DataURL_Txt.sendKeys(DataURL);     	
    }
    
    public String getDataURL(){
    	return DataURL;
    }
    
    public void ClickSaveConfigBtn() throws Exception { 
    	Reporter.log("Populating Data URL textbox.");
    	SaveConfiguration_btn.click();    
    }
    
    
    public void VerifyProgramGuideText() throws Exception { 
    	Assert.assertTrue(ProgramGuideTextOnHomePage_Lbl.getText().contains("Program Guide"));
    }
    
    public void VerifyDateShowInfoColumn() throws Exception {  	
	 	
    	List<WebElement> allColumns = webDriver.findElements(By.xpath(".//table[@class='sticky-enabled tableheader-processed sticky-table']/..//th"));
 		Assert.assertTrue(allColumns.get(3).getText().contains("Date"));
    	Assert.assertTrue(allColumns.get(4).getText().contains("Show"));
    	Assert.assertTrue(allColumns.get(5).getText().contains("Information"));
    }
	 	
    
    
    public void VerifyNoDataRow() throws Exception { 
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.
            visibilityOf(NoDataTextInTheFirstRow_txt));
	   	
    } 
    public void ProgramGuideRunCronStatus() throws Exception { 
    	
    	Assert.assertTrue(ProgramGuideRunCronStatus_status.getText().contains(getDataURL()));
	   	
    }     
    
}