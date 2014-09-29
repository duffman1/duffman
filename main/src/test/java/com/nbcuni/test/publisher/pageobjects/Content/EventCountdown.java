package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com EventCountdown Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: September 3, 2014
 *********************************************/

public class EventCountdown {

    private Driver webDriver;
    private WebDriverWait wait;
    
    //PAGE OBJECT CONSTRUCTOR
    public EventCountdown(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-title")
    private WebElement Title_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-event-timer-und-0-value-datepicker-popup-0")
    private WebElement TimerDate_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-event-timer-und-0-value-timeEntry-popup-1")
    private WebElement TimerTime_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-active-event-und-0-url")
    private WebElement ActiveCallToAction_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-active-event-image-und-0-select")
    private WebElement ActiveImageSelect_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-active-background-color-und-0-value")
    private WebElement ActiveBackgroundColor_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-expiry-background-color-und-0-value")
    private WebElement ExpiryCallToAction_Txb;
    
    @FindBy(how = How.ID, using = "edit-field-expiry-event-image-und-0-select")
    private WebElement ExpiryImageSelect_Btn;
    
    @FindBy(how = How.ID, using = "edit-field-expiry-background-color-und-0-value")
    private WebElement ExpiryBackgroundColor_Txb;
    
    private WebElement CountdownTimer_Ctr(String eventTitle) {
    	return webDriver.findElement(By.xpath("//h3[text()='" + eventTitle + "']/..//div[contains(@id, 'field-event-timer')]"));
    }
    
    private WebElement ActiveColor_Ctr(String eventTitle, String hexColor) {
    	return webDriver.findElement(By.xpath("//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-active']/div[@style='background-color:" + hexColor + ";']"));
    }
    
    private WebElement ActiveAction_Lnk(String eventTitle, String href) {
    	return webDriver.findElement(By.xpath("//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-active']//a[@href='" + href + "']"));
    }
    
    private WebElement Active_Img(String eventTitle, String imageIndex) {
    	return webDriver.findElement(By.xpath("(//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-active']//img)[" + imageIndex + "]"));
    }
    
    private WebElement ExpiryColor_Ctr(String eventTitle, String hexColor) {
    	return webDriver.findElement(By.xpath("//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-expiry']/div[@style='background-color:" + hexColor + ";']"));
    }
    
    private WebElement ExpiryAction_Lnk(String eventTitle, String href) {
    	return webDriver.findElement(By.xpath("//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-expiry']//a[@href='" + href + "']"));
    }
    
    private WebElement Expiry_Img(String eventTitle, String imageIndex) {
    	return webDriver.findElement(By.xpath("(//h3[text()='" + eventTitle + "']/..//div[@class='event-countdown-expiry']//img)[" + imageIndex + "]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void EnterTitle(String title) throws Exception {
    	
    	Reporter.log("Enter '" + title + "' in the 'Title' text box.");
    	Title_Txb.clear();
    	Title_Txb.sendKeys(title);
    }
    
    public void EnterTimerDateTime(String date, String time) throws Exception {
    	
    	Reporter.log("Enter '" + date + " in the 'Date' text box.");
    	TimerDate_Txb.clear();
    	TimerDate_Txb.sendKeys(date);
    	
    	Reporter.log("Enter '" + time + " in the 'Time' text box.");
    	TimerTime_Txb.clear();
    	TimerTime_Txb.sendKeys(time);
    }
    
    public void EnterActiveCallToAction(String action) throws Exception {
    	
    	Reporter.log("Enter '" + action + "' in the 'Active Call to Action' text box.");
    	ActiveCallToAction_Txb.clear();
    	ActiveCallToAction_Txb.sendKeys(action);
    }
    
    public void ClickActiveImageSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Active Image Select' button.");
    	ActiveImageSelect_Btn.click();
    }

    public void EnterActiveBackgroundColor(String color) throws Exception {
    	
    	Reporter.log("Enter '" + color + "' in the 'Active Background Color' text box.");
    	ActiveBackgroundColor_Txb.clear();
    	ActiveBackgroundColor_Txb.sendKeys(color);
    }
    
    public void EnterExpiryCallToAction(String action) throws Exception {
    	
    	Reporter.log("Enter '" + action + "' in the 'Expiry Call to Action' text box.");
    	ExpiryCallToAction_Txb.clear();
    	ExpiryCallToAction_Txb.sendKeys(action);
    }
    
    public void ClickExpiryImageSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Expiry Image Select' button.");
    	ExpiryImageSelect_Btn.click();
    }

    public void EnterExpiryBackgroundColor(String color) throws Exception {
    	
    	Reporter.log("Enter '" + color + "' in the 'Expiry Background Color' text box.");
    	ExpiryBackgroundColor_Txb.clear();
    	ExpiryBackgroundColor_Txb.sendKeys(color);
    }
    
    public void VerifyCountDownTimerRunning(String eventTitle) throws Exception {
    	
    	Reporter.log("Verify the countdown timer is visible.");
    	wait.until(ExpectedConditions.visibilityOf(CountdownTimer_Ctr(eventTitle)));
    	
    	Reporter.log("Verify the countdown timer is counting down.");
    	String timerTxt = CountdownTimer_Ctr(eventTitle).getText();
    	System.out.println(timerTxt);
    	Thread.sleep(2000);
    	Assert.assertFalse(CountdownTimer_Ctr(eventTitle).getText().equals(timerTxt));
    	
    }
    
    public void WaitForCountDownExpiration(final String eventTitle) throws Exception {
    	
    	new WebDriverWait(webDriver, 120).until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver webDriver) {
    			return CountdownTimer_Ctr(eventTitle).getText().equals("0\nDays\n0\nHours\n0\nMinutes\n0\nSeconds");
   		 	}
    	});
    }
    
    public void VerifyActiveImagePresent(String eventTitle, String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Assert that active img source of the event contains '" + imageSrc + "'.");
    	Assert.assertTrue(Active_Img(eventTitle, imageIndex).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			Active_Img(eventTitle, imageIndex));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }

    public void VerifyActiveBackgroundColor(String eventTitle, String hexColor) throws Exception {
    	
    	Reporter.log("Verify the active background color of '" + hexColor + "' is visible for event with title '" + eventTitle + "'.");
    	Assert.assertTrue(ActiveColor_Ctr(eventTitle, hexColor).isDisplayed());
    }
    
    public void VerifyActiveActionLnk(String eventTitle, String href) throws Exception {
    	
    	Reporter.log("Verify the active link href of '" + href + "' is visible for event with title '" + eventTitle + "'.");
    	Assert.assertTrue(ActiveAction_Lnk(eventTitle, href).isDisplayed());
    }
    
    public void VerifyCountDownTimerNotRunning(String eventTitle) throws Exception {
    	
    	Reporter.log("Verify the countdown timer is NOT counting down.");
    	String timerTxt = CountdownTimer_Ctr(eventTitle).getText();
    	Thread.sleep(2000);
    	Assert.assertEquals(CountdownTimer_Ctr(eventTitle).getText(), timerTxt);
    }
    
    public void VerifyExpiryImagePresent(String eventTitle, String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Assert that expiry img source of the event contains '" + imageSrc + "'.");
    	Assert.assertTrue(Expiry_Img(eventTitle, imageIndex).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			Expiry_Img(eventTitle, imageIndex));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }

    public void VerifyExpiryBackgroundColor(String eventTitle, String hexColor) throws Exception {
    	
    	Reporter.log("Verify the expiry background color of '" + hexColor + "' is visible for event with title '" + eventTitle + "'.");
    	Assert.assertTrue(ExpiryColor_Ctr(eventTitle, hexColor).isDisplayed());
    }
    
    public void VerifyExpiryActionLnk(String eventTitle, String href) throws Exception {
    	
    	Reporter.log("Verify the expiry link href of '" + href + "' is visible for event with title '" + eventTitle + "'.");
    	Assert.assertTrue(ExpiryAction_Lnk(eventTitle, href).isDisplayed());
    }
  
}

