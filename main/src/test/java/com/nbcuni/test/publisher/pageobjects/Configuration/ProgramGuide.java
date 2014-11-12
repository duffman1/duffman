package com.nbcuni.test.publisher.pageobjects.Configuration;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Modules Library. Copyright
 * 
 * @author Khan, Faizan
 * @version 1.0 Date: Jan 28, 2014
 *********************************************/

public class ProgramGuide {

    private String DataURL= "http://feed.entertainment.tv.theplatform.com/f/dCK2IC/stage_usa_listing?range=1-*&form=json";
    private WaitFor waitFor;
    private Config config;
    
    //PAGE OBJECT CONSTRUCTOR    
    public ProgramGuide(Driver webDriver) {
        PageFactory.initElements(webDriver, this);
        config = new Config();
        waitFor = new WaitFor(webDriver, config.getConfigValueInt("WaitForWaitTime"));
    }
    
    //PAGE OBJECT IDENTIFIERS    
    private By DataURL_Txb = By.id("edit-program-guide-data-url");
    
    private By SaveConfiguration_Btn = By.xpath("//input[@id='edit-submit'][@value='Save configuration']"); 
    
    private By ProgramGuideTextOnHomePage_Lbl = By.xpath("//div[@id='block-program-guide-example-program-guide']/h2[contains(text(),'Program Guide')]");
    
    private By ProgramGuideRunCronStatus_Sts = By.xpath("//table[@class='system-status-report']/..//td[contains(text(),'Program Guide Data URL')]/..//td[@class='status-value']");
    
    private By ProgramGuideTbl_Cls = By.xpath("//table[@class='sticky-enabled tableheader-processed sticky-table']/thead//th");

    private By ProgramGuideTbl_Rws = By.xpath("//table[@class='sticky-enabled tableheader-processed sticky-table']/tbody//tr");

    
    //PAGE OBJECT METHODS
    public void EnterDataURL() throws Exception { 
    	
    	Reporter.log("Enter '" + DataURL + "' in the 'Data URL' text box.");
    	WebElement ele = waitFor.ElementVisible(DataURL_Txb);
    	ele.clear();
    	ele.sendKeys(DataURL); 
    	
    }
    
    public void ClickSaveConfigBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save Configuration' button.");
    	waitFor.ElementVisible(SaveConfiguration_Btn).click();
    	
    }
    
    public void VerifyProgramGuideText() throws Exception { 
    	
    	Reporter.log("Verify home page program guide contains text 'Program Guide'.");
    	waitFor.ElementVisible(ProgramGuideTextOnHomePage_Lbl);

    }
    
    public void VerifyDateShowInfoColumn() throws Exception {  	
	 	
    	Reporter.log("Verify column headers present of 'Date', 'Show', and 'Information'.");
    	List<WebElement> allColumns = waitFor.ElementsVisible(ProgramGuideTbl_Cls);
 		Assert.assertTrue(allColumns.get(0).getText().contains("Date"));
    	Assert.assertTrue(allColumns.get(1).getText().contains("Show"));
    	Assert.assertTrue(allColumns.get(2).getText().contains("Information"));
    	
    }
	 	
    public void ProgramGuideRunCronStatus() throws Exception { 
    	
    	Reporter.log("Verify Cron Status contains data url '" + DataURL + "'.");
    	waitFor.ElementContainsText(ProgramGuideRunCronStatus_Sts, DataURL);
    	
    }     
    
    public void VerifyProgramGuideContainsShows() throws Exception {
    	
    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    	if (calendar.get(Calendar.HOUR_OF_DAY) < 18 && calendar.get(Calendar.HOUR_OF_DAY) > 14) {
    		Reporter.log("Verify that there is at least one show in the program guide table.");
    		List<WebElement> allRows = waitFor.ElementsVisible(ProgramGuideTbl_Rws);
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
