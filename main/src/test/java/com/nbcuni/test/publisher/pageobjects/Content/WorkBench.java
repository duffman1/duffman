package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTORS
    public WorkBench(CustomWebDriver webDriver, AppLib applib) {
        WorkBench.webDriver = webDriver;
        WorkBench.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CLASS_NAME, using = "workbench-info-block")
    private static WebElement WorkBenchInfo_Ctr;
    
    private static WebElement WorkBench_Tab(String tabName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + tabName + "']"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickWorkBenchTab(String tabName) throws Exception{
    
    	Reporter.log("Click the '" + tabName + "' in the work bench.");
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

}
