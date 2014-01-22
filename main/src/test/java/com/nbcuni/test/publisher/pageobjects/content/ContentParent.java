package com.nbcuni.test.publisher.pageobjects.content;


import com.nbcuni.test.lib.Util;
import com.nbcuni.test.publisher.common.AppLib;
import com.nbcuni.test.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;


/*********************************************
 * publisher.nbcuni.com Content Parent Library. Copyright
 * 
 * @author Brandon Clark
 * @version 1.0 Date: December 13, 2013
 *********************************************/

public class ContentParent {

    private static CustomWebDriver webDriver;
    private static AppLib al;
    private final Util ul;
    
    private static String Save_Btn = "//input[@id='edit-submit']";
    private static String Message_Ctr = "//div[@class='messages status']";
    private static String Warning_Ctr = "//div[@class='messages warning']";
    private static String EditDraft_Tab = "//a[text()='Edit Draft']";
    private static String Revisions_Tab = "//a[text()='Revisions']";
    private static String pageTitle = ".//*[@id='page-title']";
    private static String View_Tab = "//a[text()='View']";
    private static String workBenchInfoBlock = ".//*[@class='workbench-info-block']";
    private static String AddAnotherItem_Btn=".//input[@name='field_movie_credit_add_more']";

    public ContentParent(final CustomWebDriver custWebDr) {
        webDriver = custWebDr;
        ul = new Util(webDriver);
        
    }
   
    public void ClickSaveBtn() throws Exception {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(Save_Btn)));
    	webDriver.click(Save_Btn);
    }
    
    public void VerifyMessageStatus(String messageStatus){
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.textToBePresentInElement(By.xpath(Message_Ctr)
    					, messageStatus));
    }
    
    public void VerifyMessageWarning(String warningTxt){
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.textToBePresentInElement(By.xpath(Warning_Ctr)
    					, warningTxt));
    }
    
    public void ClickEditDraftTab() {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(EditDraft_Tab))).click();
    }
    
    public void ClickRevisionsTab() {
    	
    	new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
    			By.xpath(Revisions_Tab))).click();
    }

    public void ClickViewTab() {

        new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(
                By.xpath(View_Tab))).click();;

    }

    public void VerifyRequiredFields(List<String> allFields) {
    	
    	for (String field : allFields) {
    		webDriver.findElement(By.xpath("//*[contains(text(), '" + field + "')]/span[text()='*']"));
    	}
    }
    public void VerifyPostTitle(String postName){
    	
    	new WebDriverWait(webDriver, 10).until(
    			ExpectedConditions.textToBePresentInElement(By.xpath(pageTitle)
    					, postName));
    }

    public void WorkBenchInfoBlock(List<String> fields){

        for (String field:fields){
            new WebDriverWait(webDriver, 10).until(
                    ExpectedConditions.textToBePresentInElement(By.xpath(workBenchInfoBlock)
                            , field));
        }
    }

    public void VerifyPageContentPresent(List<String> txtItems) throws Exception {

        String bodyTxt = webDriver.findElement(By.xpath("//body")).getText();

        for (String s : txtItems) {

            Assert.assertTrue(bodyTxt.contains(s));

        }
    }

    public void VerifyPageContentNotPresent(List<String> txtItems) throws Exception {

        String bodyTxt = webDriver.findElement(By.xpath("//body")).getText();

        for (String s : txtItems) {

            Assert.assertFalse(bodyTxt.contains(s));

        }
    }
}

