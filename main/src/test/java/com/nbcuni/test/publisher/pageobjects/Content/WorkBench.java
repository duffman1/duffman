package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Work Bench Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/
public class WorkBench {

	private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    
	//PAGE OBJECT CONSTRUCTORS
    public WorkBench(Driver webDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
    }
    
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    private By WorkBenchInfo_Ctr = By.xpath("//div[@class='workbench-info-block']");
    
    private By WorkBench_Tab(String tabName) {
    	return By.xpath("//a[text()='" + tabName + "']");
    }
    
    private By CloneContent_Lnk(String contentType) {
    	return By.xpath("//a[text() = 'Clone this " + contentType + "']");
    }
    
    private By MPXPlayer_Frm = By.cssSelector("iframe[id='pdk-player']");
    
    private By WorkBench_Img(String imageIndex) {
    	return By.xpath("(//div[contains(@class, 'image')]//img)[" + imageIndex + "]");
    }
    
    private By WorkBenchImg_Lnk(String linkIndex) {
    	return By.xpath("(//div[contains(@class, 'image')]//a[contains(@type, 'image')])[" + linkIndex + "]");
    }
    
    private By WorkBenchFileImage_Ids(String imageIndex) {
    	return By.xpath("(//div[contains(@class, 'image')])[" + imageIndex + "]");
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickWorkBenchTab(String tabName) throws Exception{
    
    	Reporter.log("Click the '" + tabName + "' link in the work bench.");
    	interact.Click(waitFor.ElementVisible(WorkBench_Tab(tabName)));

    }
    
    public void VerifyWorkBenchTabPresent(String tabName) throws Exception {  	
    	 	
    	Reporter.log("Verify the '" + tabName + "' tab is present in the work bench.");
    	waitFor.ElementVisible(WorkBench_Tab(tabName));
    	 	
    }

    public void VerifyWorkBenchBlockTextPresent(List<String> txtItems) throws Exception {

        for (String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present in the work bench info block.");
        	waitFor.ElementContainsText(WorkBenchInfo_Ctr, text);
        }
        
    }
    
    public void ClickCloneContentLnk(String contentType) throws Exception {
        
    	Reporter.log("Click the 'Clone this " + contentType + "' link.");
    	interact.Click(waitFor.ElementVisible(CloneContent_Lnk(contentType)));
    	
    }
    
    public void VerifyMPXPlayerPresent() throws Exception {
        
    	Reporter.log("Verify the mpx video player frame is present.");
    	waitFor.ElementVisible(MPXPlayer_Frm);
        
    }
    
    public void VerifyMPXPlayerSourceNotPresent(String source) throws Exception {
        
    	Reporter.log("Verify the mpx video player frame source does NOT contain '" + source + "'.");
        Assert.assertFalse(waitFor.ElementVisible(MPXPlayer_Frm).getAttribute("src").contains(source));
        
    }
    
    public void VerifyFileImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Assert that img source of the Media Item contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementVisible(WorkBench_Img(imageIndex));
    	Assert.assertTrue(ele.getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void VerifyFileImageLinkPresent(String imageHref, String linkIndex) throws Exception {
    	
    	Reporter.log("Verify that the image link is present and contains file url '" + imageHref + "'.");
    	Assert.assertTrue(waitFor.ElementVisible(WorkBenchImg_Lnk(linkIndex)).getAttribute("href").contains(imageHref));
    	
    }
    
    public void VerifyFileImageSize(String imageIndex, String height, String width) throws Exception {
    	
    	Reporter.log("Assert that the workbench image width = '" + width + "' and the image height = '" + height + "'.");
    	WebElement ele = waitFor.ElementVisible(WorkBench_Img(imageIndex));
    	Assert.assertEquals(ele.getAttribute("width"), width);
    	Assert.assertEquals(ele.getAttribute("height"), height);
    	  
    }
    
    public String GetFileImageId(String imageIndex) throws Exception {
    	
    	Reporter.log("Get the file id of image number " + imageIndex + ".");
    	return waitFor.ElementVisible(WorkBenchFileImage_Ids(imageIndex)).getAttribute("id").replace("file-", "");
    
    }
    
    public String GetContentNodeNumber() throws Exception {
        
    	Reporter.log("Note the node number for the content item.");
    	String[] splitOn = waitFor.ElementVisible(WorkBench_Tab("Edit Draft")).getAttribute("href").split("/edit");
    	String nodeID = splitOn[0].replace(config.getConfigValueString("AppURL") + 
    			"/node/", "");
    	return nodeID;

    }

}
