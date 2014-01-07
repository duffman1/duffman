package com.nbcuni.test.publisher.pageobjects.content;

import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/*********************************************
 * publisher.nbcuni.com Revision State Library. Copyright
 * 
 * @author Mohd Faizan Khan
 * @version 1.0 Date: Jan 4, 2014
 *********************************************/
public class Schedule {

	private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
   private static String Revision_Dd = ".//*[@id='edit-revision-id']";
   private static String Operation_Dd = ".//*[@id='edit-operation']";
   private static String DateFld = ".//*[@id='edit-datetime-datepicker-popup-0']";
   private static String TimeFld = ".//*[@id='edit-datetime-timeEntry-popup-1']";
   private static String Current_date = ".//*[@id='edit-datetime']/div[1]/div[@class='description']";
   private static String Current_time = ".//*[@id='edit-datetime']/div[2]/div[@class='description']";
   private static String Revision_txt = ".//*[@id='block-system-main']/div/table[2]/tbody/tr[1]/td[1]";
   private static String Action_txt = ".//*[@id='block-system-main']/div/table[2]/tbody/tr[1]/td[2]";
   private static String ModerateState_txt = ".//*[@id='block-system-main']/div/table[2]/tbody/tr[1]/td[3]";
   private static String RunNow_Btn = ".//*[@id='ctools-button-1']/..//a[text()='Run now']";
   
   public Schedule(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
    public void VerifyRevisionDd(){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Revision_Dd)));
    	    	
    }
    public void VerifyOperationDd(){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Operation_Dd)));    			  	
    	    	
    }
    public void VerifyDateField(){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(DateFld)));
    	
    }
    public void VerifyTimeField(){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(TimeFld)));   
    }
    public void PopulateRevisionDd(String revision_opt){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Revision_Dd)))); 
    	webDriver.selectFromDropdown(Revision_Dd, revision_opt);
    	    	
    }
    public String PopulateRandomRevisionDd(){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Revision_Dd)))); 
    	List<WebElement> allOptions = webDriver.getSelectValuesObject(Revision_Dd);
    	//Random rand = new Random();
    	//int  RNum=rand.nextInt(allOptions.size());    	
    	String OptionTXT = allOptions.get(1).getText().trim();
    	webDriver.selectFromDropdown(Revision_Dd, OptionTXT);
    	return OptionTXT;
    }
    public void PopulateOperationDd(String Operation_opt){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(Operation_Dd)))); 
    	webDriver.selectFromDropdown(Operation_Dd, Operation_opt);
    	
    }
    public void PopulateDateField(String cal){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(DateFld))));
    	webDriver.type(webDriver.findElement(By.xpath(DateFld)), cal);         	
    }
    public void PopulateTimeField(String c){
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(
    			webDriver.findElement(By.xpath(TimeFld))));
    	webDriver.type(webDriver.findElement(By.xpath(TimeFld)), TimeFld);    	
    }
    public String getServerCurrentDate(){    	
		String CSDate = webDriver.findElement(By.xpath(Current_date)).getText().trim();    	
		String[] dates = AppLib.getDate("([0-9]{1,2}/([0-9]{2})/[0-9]{4}",CSDate);		  
		return dates[0];
    }
    public String getServerCurrentTime(){    	
		String CSTime = webDriver.findElement(By.xpath(Current_time)).getText().trim();    	
		String[] Times = AppLib.getDate("\\d+:\\d+\\s*(A|P)M?",CSTime);		  
		return Times[0];
    }
    public void VerifyScheduleTable(String[] ScheduleData) throws Exception {
    	   	 
    	//Integer revisionsRows = (webDriver.findElements(By.xpath(RevisionsState_Tbl)).size());
    	
    	Assert.assertTrue(webDriver.findElement(By.xpath(Revision_txt)).getText().trim().equalsIgnoreCase(ScheduleData[0]));
    	Assert.assertTrue(webDriver.findElement(By.xpath(Action_txt)).getText().trim().equalsIgnoreCase(ScheduleData[1]));
    	try{
    	Assert.assertTrue(webDriver.findElement(By.xpath(ModerateState_txt)).getText().trim().equalsIgnoreCase(ScheduleData[2]));    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunNow_Btn)));
    	}catch(Exception e){e.printStackTrace();}
    	
    }
    public void RunCronOnSchedulePage(int timeToWait) throws Exception {
    	WebElement runBtn = new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(RunNow_Btn)));
    	runBtn.click();
    	AppLib.Wait(timeToWait);
    }    
    
}
