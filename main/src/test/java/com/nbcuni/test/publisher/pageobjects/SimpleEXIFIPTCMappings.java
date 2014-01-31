package com.nbcuni.test.publisher.pageobjects;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*********************************************
 * publisher.nbcuni.com Simple EXIF/IPTC Mappings Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: January 28, 2014
 *********************************************/

public class SimpleEXIFIPTCMappings {

    private static CustomWebDriver webDriver;
    private static AppLib applib;
    
    //PAGE OBJECT CONSTRUCTOR
    public SimpleEXIFIPTCMappings(final CustomWebDriver custWebDr, final AppLib applib) {
        webDriver = custWebDr;
        this.applib = applib;
    }
   
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.ID, using = "edit-mappings-field-file-image-alt-text")
    private static WebElement AltText_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-file-image-title-text")
    private static WebElement TitleText_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-caption")
    private static WebElement Caption_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-copyright")
    private static WebElement Copyright_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-credit")
    private static WebElement Credit_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-keywords")
    private static WebElement Keywords_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-media-categories")
    private static WebElement MediaCategories_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-media-tags")
    private static WebElement MediaTags_Ddl;
    
    @FindBy(how = How.ID, using = "edit-mappings-field-source")
    private static WebElement Source_Ddl;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private static WebElement Save_Btn;
    
    
    //PAGE OBJECT METHODS
    public void SelectAltText(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Alt Text' drop down list.");
    	new Select(AltText_Ddl).selectByVisibleText(option);
    }
    
    public void SelectTitleText(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Title Text' drop down list.");
    	new Select(TitleText_Ddl).selectByVisibleText(option);
    }
    
    public void SelectCaption(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Caption' drop down list.");
    	new Select(Caption_Ddl).selectByVisibleText(option);
    }
    
    public void SelectCopyright(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Copyright' drop down list.");
    	new Select(Copyright_Ddl).selectByVisibleText(option);
    }
    
    public void SelectCredit(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Credit' drop down list.");
    	new Select(Credit_Ddl).selectByVisibleText(option);
    }
    
    public void SelectKeywords(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Keywords' drop down list.");
    	new Select(Keywords_Ddl).selectByVisibleText(option);
    }
    
    public void SelectMediaCategories(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Media categories' drop down list.");
    	new Select(MediaCategories_Ddl).selectByVisibleText(option);
    }
    
    public void SelectMediaTags(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Media Tags' drop down list.");
    	new Select(MediaTags_Ddl).selectByVisibleText(option);
    }
    
    public void SelectSource(String option) throws Exception {
    	Reporter.log("Select the '" + option + "' from the 'Source' drop down list.");
    	new Select(Source_Ddl).selectByVisibleText(option);
    }
    
    public void ClickSaveBtn() throws Exception {
    	Reporter.log("Click the 'Save' button.");
    	Save_Btn.click();
    }
    
   
}
