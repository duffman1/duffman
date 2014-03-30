package com.nbcuni.test.publisher.pageobjects.Content;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Random;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com PersonsInformation Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class PersonsInformation {

    private Driver webDriver;
    
    //PAGE OBJECT CONSTCUTOR
    public PersonsInformation(Driver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-field-person-first-name-und-0-value")
    private WebElement FirstName_Txb;
    
    @FindBy(how = How.ID, using = "edit-body-und-0-value_ifr")
    private WebElement Biography_Frm;
    
    @FindBy(how = How.ID, using = "tinymce")
    private WebElement Biography_Txa;
    
    @FindBy(how = How.ID, using = "edit-field-person-cover-photo-und-0-select")
    private WebElement CoverPhotoSelect_Btn;
    
    
    //PAGE OBJECT METHODS
    public void EnterFirstName(String firstName) throws Exception {
    	
    	Reporter.log("Enter '" + firstName + "' in the 'First name' text box.");
    	FirstName_Txb.sendKeys(firstName);
    }
    
    public String EnterBiography() throws Exception{

        webDriver.switchTo().frame(Biography_Frm);

        Reporter.log("Enter a random block of text in the 'Biography' text area.");
        Random random = new Random();
        String body = random.GetCharacterString(20) + " " +
                        random.GetCharacterString(20) + " " +
                            random.GetCharacterString(20) + " " +
                                random.GetCharacterString(20);
        Biography_Txa.click();
        Biography_Txa.sendKeys(body);
        
        return body;
    }
    
    public void ClickCoverPhotoSelectBtn() throws Exception {
    	
    	Reporter.log("Click the 'Cover Photo' Select button.");
    	CoverPhotoSelect_Btn.click();
    }
    
    
    
}

