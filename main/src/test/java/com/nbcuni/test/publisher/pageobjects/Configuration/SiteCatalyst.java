package com.nbcuni.test.publisher.pageobjects.Configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
 * publisher.nbcuni.com SiteCatalyst Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 17, 2014
 *********************************************/

public class SiteCatalyst {

    WebDriverWait wait;
    Driver webDriver;
    
    //PAGE OBJECT CONSTRUCTOR    
    public SiteCatalyst(Driver webDriver, AppLib applib) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
        this.webDriver = webDriver;
    }
    
    //PAGE OBJECT IDENTIFIERS    
    @FindBy(how = How.XPATH, using ="(//fieldset[@id='edit-variables']//a)[1]")
    private WebElement CustomVariables_Lnk;
    
    @FindBy(how = How.XPATH, using ="//fieldset[@id='edit-variable-overrides']//a")
    private WebElement Overrides_Lnk;
    
    @FindBy(how = How.ID, using ="edit-sitecatalyst-variable-overrides-media-gallery")
    private WebElement MediaGallery_Cbx;
    
    @FindBy(how = How.ID, using ="edit-sitecatalyst-variable-overrides-post")
    private WebElement Post_Cbx;
    
    @FindBy(how = How.ID, using ="edit-submit")
    private WebElement SaveConfiguration_Btn;
    
    
    //PAGE OBJECT METHODS
    public void ClickCustomVariablesLnk() throws Exception { 
    	
    	Reporter.log("Click the 'Custom Variables' link to expand the menu.");
        webDriver.executeScript("arguments[0].click()", CustomVariables_Lnk);
    }
    
    public void ClickOverridesLnk() throws Exception { 
    	
    	Reporter.log("Click the 'Overrides' link to expand the menu."); 
    	wait.until(ExpectedConditions.visibilityOf(Overrides_Lnk)).click();
    }
    
    public void ClickMediaGalleryCbx() throws Exception { 
    	
    	wait.until(ExpectedConditions.visibilityOf(MediaGallery_Cbx));
    	if (MediaGallery_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Media Gallery' check box.");
    		MediaGallery_Cbx.click();
    	}
    }
    
    public void ClickPostCbx() throws Exception { 
    	
    	wait.until(ExpectedConditions.visibilityOf(Post_Cbx));
    	if (Post_Cbx.isSelected() == false) {
    		Reporter.log("Check the 'Post' check box.");
    		Post_Cbx.click();
    	}
    }
    
    public void ClickSaveConfigurationBtn() throws Exception { 
    	
    	Reporter.log("Click the 'Save configuration' button.");
    	SaveConfiguration_Btn.click();
    }
    
}