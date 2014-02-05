package com.nbcuni.test.publisher.pageobjects.Configuration;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

public class ProgramGuide {

    private static CustomWebDriver webDriver;
    private final String DataURL= "http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json";
    
    //PAGE OBJECT CONSTRUCTOR    
    public ProgramGuide(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.ID, using ="edit-program-guide-data-url")
    private static WebElement DataURL_Txb;  
    
    @FindBy(how = How.XPATH, using ="//input[@id='edit-submit'][@value='Save configuration']")
    private static WebElement SaveConfiguration_Btn; 
    
    @FindBy(how = How.XPATH, using ="//div[@id='block-program-guide-example-program-guide']/h2[contains(text(),'Program Guide')]")
     private static WebElement ProgramGuideTextOnHomePage_Lbl;
    
    @FindBy(how = How.XPATH, using ="//table[@class='sticky-enabled tableheader-processed sticky-table']/..//td[contains(text(),'There is no data')]")
    private static WebElement NoDataTextInTheFirstRow_Txt;
    
    @FindBy(how = How.XPATH, using ="//table[@class='system-status-report']/..//td[contains(text(),'Program Guide Data URL')]/..//td[@class='status-value']")
    private static WebElement ProgramGuideRunCronStatus_Sts;
    
    private static List<WebElement> ProgramGuideTbl_Cls() {
    	return webDriver.findElements(By.xpath("//table[@class='sticky-enabled tableheader-processed sticky-table']/..//th"));
    }
    
    private static List<WebElement> ProgramGuideTbl_Rws() {
    	return webDriver.findElements(By.xpath("//h2[text()='Program Guide']/..//table/tbody/tr"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterDataURL() throws Exception { 
    	
    	Reporter.log("Enter '" + DataURL + "' in the 'Data URL' text box.");
    	DataURL_Txb.clear();
    	DataURL_Txb.sendKeys(DataURL); 
    	
    }
    
    public void ClickSaveConfigBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save Configuration' button.");
    	SaveConfiguration_Btn.click();
    	
    }
    
    public void VerifyProgramGuideText() throws Exception { 
    	
    	Reporter.log("Verify home page program guide contains text 'Program Guide'.");
    	Assert.assertTrue(ProgramGuideTextOnHomePage_Lbl.getText().contains("Program Guide"));
    	
    }
    
    public void VerifyDateShowInfoColumn() throws Exception {  	
	 	
    	Reporter.log("Verify column headers present of 'Date', 'Show', and 'Information'.");
    	List<WebElement> allColumns = ProgramGuideTbl_Cls();
 		Assert.assertTrue(allColumns.get(3).getText().contains("Date"));
    	Assert.assertTrue(allColumns.get(4).getText().contains("Show"));
    	Assert.assertTrue(allColumns.get(5).getText().contains("Information"));
    }
	 	
    public void ProgramGuideRunCronStatus() throws Exception { 
    	
    	Reporter.log("Verify Cron Status contains data url '" + DataURL + "'.");
    	Assert.assertTrue(ProgramGuideRunCronStatus_Sts.getText().contains(DataURL));
	   	
    }     
    
    public void VerifyProgramGuideContainsShows() throws Exception {
    	
    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    	if (calendar.get(Calendar.HOUR_OF_DAY) < 18 && calendar.get(Calendar.HOUR_OF_DAY) > 14) {
    		Reporter.log("Verify that there is at least one show in the program guide table.");
    		List<WebElement> allRows = ProgramGuideTbl_Rws();
    		boolean showPresent = false;
    		for (WebElement row : allRows) {
    			if (!row.getText().contains("Network Programming")) {
    				showPresent = true;
    				break;
    			}
    		}
    		if (showPresent == false) {
    			Assert.fail("No shows present in the Program Guide.");
    		}
    	}
    	else {
    		Reporter.log("After 8pm, show availability limited.");
    	}
    }
    
}