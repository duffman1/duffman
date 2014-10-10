package com.nbcuni.test.publisher.pageobjects.Logo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Driver.Driver;
import com.nbcuni.test.publisher.pageobjects.Overlay;
import com.nbcuni.test.publisher.pageobjects.Content.Delete;

/*********************************************
 * publisher.nbcuni.com Logos Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: February 20, 2014
 *********************************************/

public class Logos {

	private Driver webDriver;
	private WebDriverWait wait;
	private Config config;
	private Overlay overlay;
	private Delete delete;
	
    //PAGE OBJECT CONSTRUCTOR
    public Logos(Driver webDriver, AppLib applib) {
    	this.webDriver = webDriver;
    	PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
        config = new Config();
        overlay = new Overlay(webDriver);
        delete = new Delete(webDriver);
    }
    
    //PAGE OBJECT IDENTIFIERS
    @FindBy(how = How.CSS, using = "img[alt='Home']")
    private WebElement HomeLogo_Img;
    
    private List<WebElement> AllLogo_Ttls() {
    	return webDriver.findElements(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(@class, 'title')]"));
    }
    
    private WebElement Logo_Img(String logoTitle) {
    	return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//img"));
    }
    
    private WebElement EditExtendMenu_Btn(String logoTitle) {
		return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='operations']"));
	}
	
	private WebElement EditMenuDelete_Btn(String logoTitle) {
		return webDriver.findElement(By.xpath("//div[@class='view-content']//tbody/tr/td[contains(text(), '" + logoTitle + "')]/..//a[text()='Delete']"));
	}
    
    //PAGE OBJECT METHODS
	public void ClickEditExtendMenuBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Edit' extend menu button for logo titled '" + logoTitle + "'.");
    	EditExtendMenu_Btn(logoTitle).click();
    	
    }
    
    public void ClickEditMenuDeleteBtn(String logoTitle) throws Exception {
    	
    	Reporter.log("Click the 'Delete' button for logo titled '" + logoTitle + "'.");
    	wait.until(ExpectedConditions.visibilityOf(
    			EditMenuDelete_Btn(logoTitle))).click();
    }
    
    public void VerifyHomePageLogoImgPresent(final String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	Thread.sleep(500);
    	wait.until(new ExpectedCondition<Boolean>(){
   		 	@Override
   		 	public Boolean apply(WebDriver webDriver) {
   		 		return HomeLogo_Img.getAttribute("src").contains(imageSrc);
   		 	}
    	}); 
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++) {
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            				HomeLogo_Img);
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }

    public void VerifyLogoImgPresent(String logoTitle, String imageSrc) throws Exception {
    	
    	Reporter.log("Assert the file image '" + imageSrc + "' is present.");
    	Assert.assertTrue(Logo_Img(logoTitle).getAttribute("src").contains(imageSrc));
    	
    	Reporter.log("Assert the the img is loaded and visible.");
    	boolean imgLoaded;
        for (int second = 0; ; second++) {
            if (second >= 30) {
                Assert.fail("Image '" + imageSrc + "' is not fully loaded after timeout");
            }
            
            imgLoaded = (Boolean) ((JavascriptExecutor)webDriver).executeScript(
            			"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
            				Logo_Img(logoTitle));
                
            if (imgLoaded == true){ break;}
            Thread.sleep(500);
        }
    }
    
    public void DeleteAllLogos() throws Exception {
    	
    	webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	
    	if (AllLogo_Ttls().size() > 0) {
    		
    		List<String> allLogoTtls = new ArrayList<String>();
    		for (WebElement logo : AllLogo_Ttls()) {
    			 allLogoTtls.add(logo.getText().trim());
    		}
    		
    		//delete each logo
    		for (String title : allLogoTtls) {
    			this.ClickEditExtendMenuBtn(title);
    			this.ClickEditMenuDeleteBtn(title);
    			overlay.SwitchToActiveFrame();
    			delete.ClickDeleteBtn();
    		}
    	}
    	
    	webDriver.manage().timeouts().implicitlyWait(config.getConfigValueInt("ImplicitWaitTime"), TimeUnit.SECONDS);
    }
    
    
}

