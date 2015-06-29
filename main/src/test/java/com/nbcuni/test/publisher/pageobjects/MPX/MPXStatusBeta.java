package com.nbcuni.test.publisher.pageobjects.MPX;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com MPX Statues Beta Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: May 9, 2014
 *********************************************/

public class MPXStatusBeta {

	private WebDriver webWebWebDriver;
	private WebDriverWait wait;
	
    //PAGE OBJECT CONSTRUCTOR
    public MPXStatusBeta(WebDriver webWebWebDriver) {
    	this.webWebWebDriver = webWebWebDriver;
        PageFactory.initElements(webWebWebDriver, this);
        wait = new WebDriverWait(webWebWebDriver, 10);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a[text()='Video Updates to be Processed During the Next Cron Run']")
    private WebElement ViewVideosToBeProcessed_Lnk;
    
    private WebElement VideoToBeProcessed_Ttl(String title) {
    	return webWebWebDriver.findElement(By.xpath("(//tbody)[3]//tr/td[4][contains(text(), '" + title + "')]"));
    }
    
    @FindBy(how = How.XPATH, using = "//h3[contains(text(), 'Total Videos Ingested')]")
    private WebElement TotalVideosIngested_Txt;
    
    @FindBy(how = How.XPATH, using = "//h3[contains(text(), 'Total Players Ingested')]")
    private WebElement TotalPlayersIngested_Txt;
    
    @FindBy(how = How.XPATH, using = "//h3[contains(text(), 'Videos Updated Last Cron')]")
    private WebElement VideosUpdatedLastCron_Txt;
    
    @FindBy(how = How.XPATH, using = "//h3[contains(text(), 'Total Videos Queued for Ingestion')]")
    private WebElement TotalVideosQueuedIngestion_Txt;
    
    @FindBy(how = How.XPATH, using = "//h3[contains(text(), 'Total Video Updates Waiting to be Processed')]")
    private WebElement TotalVideosToBeProcessed_Txt;
    
    
    //PAGE OBJECT METHODS
    public void ClickViewVideosToBeProcessedLnk() throws Exception {
    	
    	Reporter.log("Click the 'VIDEO UPDATES TO BE PROCESSED DURING THE NEXT CRON RUN' link.");
    	ViewVideosToBeProcessed_Lnk.click();
    }
    
    public void VerifyVideoToBeProcessed(String title) throws Exception {
    	
    	Reporter.log("Verify video titled '" + title + "' is present in the video to be processed table.");
    	wait.until(ExpectedConditions.visibilityOf(VideoToBeProcessed_Ttl(title)));
    }
    
    public Integer GetTotalVideosIngestedCount() throws Exception {
    	
    	String[] ingestionCount = TotalVideosIngested_Txt.getText().split(": ");
    	Reporter.log("Note the 'Total Videos Ingested' count which is '" + ingestionCount[1] + "'.");
    	return Integer.parseInt(ingestionCount[1]); 
    }
    
    public Integer GetTotalPlayersIngestedCount() throws Exception {
    	
    	String[] ingestionCount = TotalPlayersIngested_Txt.getText().split(": ");
    	Reporter.log("Note the 'Total Players Ingested' count which is '" + ingestionCount[1] + "'.");
    	return Integer.parseInt(ingestionCount[1]); 
    }
    
    public Integer GetVideosUpdatedLastCronCount() throws Exception {
    	
    	String[] ingestionCount = VideosUpdatedLastCron_Txt.getText().split(": ");
    	Reporter.log("Note the 'Videos Updated Last Cron' count which is '" + ingestionCount[1] + "'.");
    	return Integer.parseInt(ingestionCount[1]); 
    }
    
    public Integer GetTotalVideosQueuedIngestionCount() throws Exception {
    	
    	String[] ingestionCount = TotalVideosQueuedIngestion_Txt.getText().split(": ");
    	Reporter.log("Note the 'Total Videos Queued for Ingestion' count which is '" + ingestionCount[1] + "'.");
    	return Integer.parseInt(ingestionCount[1]); 
    }
    
    public Integer GetTotalVideosToBeProcessedCount() throws Exception {
    	
    	String[] ingestionCount = TotalVideosToBeProcessed_Txt.getText().split(": ");
    	Reporter.log("Note the 'Total Video Updates Waiting to be Processed' count which is '" + ingestionCount[1] + "'.");
    	return Integer.parseInt(ingestionCount[1]); 
    }
    
    public void VerifyTotalVideosIngestedCount(Integer videoCount) throws Exception {
    	
    	String[] ingestionCount = TotalVideosIngested_Txt.getText().split(": ");
    	Reporter.log("Verify the 'Total Videos Ingested' count is equal to '" + videoCount + "'.");
    	Assert.assertEquals(videoCount, ingestionCount[1]);
    	 
    }
    
    public void VerifyTotalPlayersIngestedCount(Integer playerCount) throws Exception {
    	
    	String[] ingestionCount = TotalPlayersIngested_Txt.getText().split(": ");
    	Reporter.log("Verify the 'Total Players Ingested' count is equal to '" + playerCount + "'.");
    	Assert.assertEquals(playerCount, ingestionCount[1]);
    	 
    }
    
    public void VerifyVideosUpdatedLastCronCount(Integer videoCount) throws Exception {
    	
    	String[] ingestionCount = VideosUpdatedLastCron_Txt.getText().split(": ");
    	Reporter.log("Verify the 'Videos Updated Last Cron' count is equal to '" + videoCount + "'.");
    	Assert.assertEquals(videoCount, ingestionCount[1]);
    	 
    }
    
}

