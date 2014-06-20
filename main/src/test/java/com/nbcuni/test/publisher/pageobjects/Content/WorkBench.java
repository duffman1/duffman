package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.List;

import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

/*********************************************
 * publisher.nbcuni.com Work Bench Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 2, 2014
 *********************************************/
public class WorkBench {

	private Driver webDriver;
    private AppLib applib;
    
	//PAGE OBJECT CONSTRUCTORS
    public WorkBench(Driver webDriver, AppLib applib) {
        this.webDriver = webDriver;
        this.applib = applib;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS AND SCRIPTS
    @FindBy(how = How.CLASS_NAME, using = "workbench-info-block")
    private WebElement WorkBenchInfo_Ctr;
    
    private WebElement WorkBench_Tab(String tabName) {
    	return webDriver.findElement(By.xpath("//a[text()='" + tabName + "']"));
    }
    
    @FindBy(how = How.XPATH, using ="//li[@class='block-configure first last']/a[contains(text(),'Configure block')]")
    private WebElement ConfigureBlock_Lnk;
    
    private WebElement CloneContent_Lnk(String contentType) {
    	return webDriver.findElement(By.xpath("//a[text() = 'Clone this " + contentType + "']"));
    }
    
    @FindBy(how = How.CSS, using = "iframe[id='pdk-player']")
    private WebElement MPXPlayer_Frm;
    
    private WebElement WorkBench_Img(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//div[contains(@class, 'file-image')]//img)[" + imageIndex + "]"));
    }
    
    private WebElement WorkBenchImg_Lnk(String linkIndex) {
    	return webDriver.findElement(By.xpath("(//div[contains(@class, 'file-image')]//a[contains(@type, 'image')])[" + linkIndex + "]"));
    }
    
    private WebElement WorkBenchFileImage_Ids(String imageIndex) {
    	return webDriver.findElement(By.xpath("(//div[contains(@class, 'file-image')])[" + imageIndex + "]"));
    }
    
    
    //PAGE OBJECT METHODS
    public void ClickWorkBenchTab(String tabName) throws Exception{
    
    	Reporter.log("Click the '" + tabName + "' link in the work bench.");
    	WorkBench_Tab(tabName).click();

    }
    
    public void VerifyWorkBenchTabPresent(String tabName) throws Exception {  	
    	 	
    	Reporter.log("Verify the '" + tabName + "' is present in the work bench.");
    	WorkBench_Tab(tabName).isDisplayed(); 	
    }

    public void VerifyWorkBenchBlockTextPresent(List<String> txtItems) throws Exception {

        for (String text : txtItems) {
        	Reporter.log("Verify the text '" + text + "' is present in the work bench info block.");
        	Assert.assertTrue(WorkBenchInfo_Ctr.getText().contains(text), "Text '" + text + "' was not present in the work bench info block.");
        }
    }
    
    public void ClickCloneContentLnk(String contentType) throws Exception {
        
    	Reporter.log("Click the 'Clone this " + contentType + "' link.");
    	CloneContent_Lnk(contentType).click();
    }
    
    public void VerifyMPXPlayerPresent() throws Exception {
        
    	Reporter.log("Verify the mpx video player frame is present.");
        MPXPlayer_Frm.isDisplayed();
    }
    
    public void VerifyMPXPlayerSourceNotPresent(String source) throws Exception {
        
    	Reporter.log("Verify the mpx video player frame source does NOT contain '" + source + "'.");
        Assert.assertFalse(MPXPlayer_Frm.getAttribute("src").contains(source));
    }
    
    public void VerifyFileImagePresent(String imageSrc, String imageIndex) throws Exception {
    	
    	Reporter.log("Assert that img source of the Media Item contains '" + imageSrc + "'.");
    	Assert.assertTrue(WorkBench_Img(imageIndex).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++){
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            			WorkBench_Img(imageIndex));
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void VerifyFileImageLinkPresent(String imageHref, String linkIndex) throws Exception {
    	
    	Reporter.log("Verify that the image link is present and contains file url '" + imageHref + "'.");
    	Assert.assertTrue(WorkBenchImg_Lnk(linkIndex).getAttribute("href").contains(imageHref));
    	
    }
    
    public void VerifyFileImageSize(String imageIndex, String height, String width) throws Exception {
    	
    	Reporter.log("Assert that the workbench image width = '" + width + "' and the image height = '" + height + "'.");
    	Assert.assertEquals(WorkBench_Img(imageIndex).getAttribute("width"), width);
    	Assert.assertEquals(WorkBench_Img(imageIndex).getAttribute("height"), height);
    	  
    }
    
    public String GetFileImageId(String imageIndex) {
    	
    	Reporter.log("Get the file id of image number " + imageIndex + ".");
    	return WorkBenchFileImage_Ids(imageIndex).getAttribute("id").replace("file-", "");
    }
    
    public String GetContentNodeNumber() throws Exception {
        
    	Reporter.log("Note the node number for the content item.");
    	return WorkBench_Tab("Edit Draft").getAttribute("href").replace(applib.getApplicationURL() + 
    			"/node/", "").replace("/edit", "");

    }

}
