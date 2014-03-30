package com.nbcuni.test.publisher.pageobjects.Structure;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.nbcuni.test.publisher.common.Driver.Driver;

/*********************************************
* publisher.nbcuni.com Content Type Structure Library. Copyright
*
* @author Mohd Faizan Khan
* @version 1.0 Date: Feb, 25, 2014
*********************************************/

public class ContentTypeStructure {

    //PAGE OBJECT CONSTRUCTOR
    public ContentTypeStructure(Driver webDriver) {
    	PageFactory.initElements(webDriver, this);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.XPATH, using = "//a/strong[text()='XML sitemap']")
    private WebElement XMLsitemap_Lnk;
    
    @FindBy(how = How.ID, using = "edit-xmlsitemap-status")
    private WebElement Inclustion_Ddl;
    
    @FindBy(how = How.ID, using = "edit-submit")
    private WebElement SaveContentType_Btn;

    
    //PAGE OBJECT METHODS
    public void ClickXMLSiteMapLink()throws Exception{
    
    	Reporter.log("Click the 'XML sitemap' link.");
    	XMLsitemap_Lnk.click();
    }
    
    public void SelectInclusionOption(String option)throws Exception{
    	
    	Reporter.log("Select '" + option + "' from the 'Inclusion' drop down list.");
    	new Select(Inclustion_Ddl).selectByVisibleText(option);
    }
    
    public void ClickSaveContentType()throws Exception{
     
    	Reporter.log("Click the 'Save content type' link.");
    	SaveContentType_Btn.click();
    }
      
    
}
