package com.nbcuni.test.publisher.pageobjects.Content;

import com.nbcuni.test.publisher.common.Config;
import com.nbcuni.test.publisher.common.Util.Interact;
import com.nbcuni.test.publisher.common.Util.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;


/**
 * ******************************************
 * publisher.nbcuni.com Content Pagination Library. Copyright
 *
 * @author Vineela Juturu
 * @version 1.1 Date: October 5, 2014
 *          *******************************************
 */

public class ContentPagination {

    private Config config;
    private Integer timeout;
    private WaitFor waitFor;
    private Interact interact;
    //PAGE OBJECT IDENTIFIERS
    private By Pager_Ctr = By.xpath("//div[@class='item-list']");
    private By AllPageNumber_Lnks = By.xpath("//div[@class='item-list']/ul/li");
    private By NextPage_Lnk = By.xpath("//div[@class='item-list']//a[contains(text(), 'next')]");

    //PAGE OBJECT CONSTRUCTOR
    public ContentPagination(WebDriver webWebWebDriver) {
        config = new Config();
        timeout = config.getConfigValueInt("WaitForWaitTime");
        waitFor = new WaitFor(webWebWebDriver, timeout);
        interact = new Interact(webWebWebDriver, timeout);
    }

    //PAGE OBJECT METHODS
    public void VerifyPageCtrPresent() throws Exception {

        Reporter.log("Verify the pagination links are present.");
        waitFor.ElementVisible(Pager_Ctr);

    }

    public void VerifyCorrectNumberOfPagesDisplayed(Integer numberOfPages) throws Exception {

        Reporter.log("Verify the number of pages equals '" + numberOfPages.toString() + ".");
        Assert.assertTrue(waitFor.ElementsPresent(AllPageNumber_Lnks).size() == numberOfPages);

    }

    public void ClickNextPageLnk() throws Exception {

        Reporter.log("Click the 'next >' Link");
        interact.Click(waitFor.ElementVisible(NextPage_Lnk));

    }

}
