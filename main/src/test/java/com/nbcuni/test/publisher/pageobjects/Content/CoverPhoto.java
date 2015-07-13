package com.nbcuni.test.publisher.pageobjects.Content;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;

/*********************************************
 * publisher.nbcuni.com Cover Photo Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 16, 2014
 *********************************************/

public class CoverPhoto {

	private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    private Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR
    public CoverPhoto(final Driver webDriver) {
    	config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webDriver, timeout);
        interact = new Interact(webDriver, timeout);
        this.webDriver = webDriver;
    }
    
    //PAGE OBJECT IDENTIFIERS
    private By CoverPhoto_Img = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//img");
    
    private By Edit_Btn = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//a[text()='Edit']");
    
    private By Select_Btn = By.xpath("//div[contains(@id, 'field-character-cover-photo')]//a[text()='Browse']");


    //PAGE OBJECT METHODS
    public void VerifyFileImagePresent(String imageSrc) throws Exception {
    	
    	Reporter.log("Assert that img source of the Cover Photo contains '" + imageSrc + "'.");
    	WebElement ele = waitFor.ElementContainsAttribute(CoverPhoto_Img, "src", imageSrc);
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	waitFor.ImageVisible(ele);
    	
    }
    
    public void ClickEditBtn() throws Exception {
    	
    	Reporter.log("Click the Cover Photo 'Edit' button.");
    	interact.Click(waitFor.ElementVisible(Edit_Btn));
    	
    }
    
    public void ClickSelectBtn() throws Exception {

        Reporter.log("Click the Cover Photo 'Select' button.");
//    	interact.Click(waitFor.ElementPresent(By.cssSelector("#edit-field-character-cover-photo-und-0-browse-button")));
//        waitFor.ElementVisible(Select_Btn).click();
//        WebDriverWait wait = new WebDriverWait(webDriver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-field-character-cover-photo-und-0-browse-button")));
//        interact.waitPageToLoad();
//        webDriver.executeScript("var btn = document.querySelector('#edit-field-character-cover-photo-und-0-browse-button');btn.click()");
        interact.waitPageToLoad();
        webDriver.executeScript("arguments[0].click()", waitFor.ElementVisible(Select_Btn));
//        interact.Click(waitFor.ElementPresent(Select_Btn));

//                webDriver.executeScript("arguments[0].click();", webDriver.findElement(By.id("edit-field-character-cover-photo-und-0-browse-button")));
//
//        if (webDriver.findElements(By.xpath("//iframe[@id='mediaBrowser']")).size()==0){
//            webDriver.executeScript("arguments[0].click();", webDriver.findElement(By.id("edit-field-character-cover-photo-und-0-browse-button")));
//        }
//
    }
    
    
}

