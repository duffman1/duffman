package com.nbcuni.test.publisher.pageobjects.Content;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com PersonsInformation Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.1 Date: September 16, 2014
 *********************************************/

public class PersonsInformation {

    private Driver webDriver;
    private Config config;
    
    //PAGE OBJECT CONSTCUTOR
    public PersonsInformation(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        config = new Config();
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-field-person-first-name-und-0-value")
    private WebElement FirstName_Txb;
    
    private WebElement Biography_Frm() {
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	WebElement frm = null;
    	try {
    		frm = webDriver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
    	}
    	catch (NoSuchElementException e) {
    		frm = webDriver.findElement(By.xpath("//iframe[@id='edit-body-und-0-value_ifr']"));
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	return frm;
    }
    
    private WebElement Biography_Txa() {
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	WebElement txa = null;
    	try {
    		txa = webDriver.findElement(By.xpath("//body[contains(@class, 'editable')]"));
    	}
    	catch (NoSuchElementException e) {
    		txa = webDriver.findElement(By.xpath("//body[@id='tinymce']"));
    	}
    	webDriver.manage().timeouts().implicitlyWait(config.getImplicitWaitTime(), TimeUnit.SECONDS);
    	return txa;
    }
    
    @FindBy(how = How.ID, using = "edit-field-person-cover-photo-und-0-select")
    private WebElement CoverPhotoSelect_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterFirstName(String firstName) throws Exception {
    	
    	Reporter.log("Enter '" + firstName + "' in the 'First name' text box.");
    	FirstName_Txb.sendKeys(firstName);
    }
    
    public String EnterBiography() throws Exception{

        webDriver.switchTo().frame(Biography_Frm());

        Reporter.log("Enter a random block of text in the 'Biography' text area.");
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        WebElement txa = Biography_Txa();
        txa.click();
        txa.sendKeys(body);
        
        return body;
    }
    
    public void ClickCoverPhotoSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cover Photo' Select button.");
    	CoverPhotoSelect_Btn.click();
    }
    
    
    
}

